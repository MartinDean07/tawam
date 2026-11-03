// SPDX-License-Identifier: GPL-3.0-or-later
package app.nectarlink.android.ui.pairing

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.nectarlink.android.R
import app.nectarlink.android.core.CoreState
import app.nectarlink.android.core.PairingState
import app.nectarlink.android.ui.theme.LocalAppFonts
import app.nectarlink.android.ui.theme.LocalReducedMotion
import app.nectarlink.core.DiscoveredDevice
import app.nectarlink.core.PairingFailure
import kotlinx.coroutines.delay

/** What the user does on the pairing screen. */
interface PairingActions {
    /** The screen is shown or hidden (discovery needs a multicast lock). */
    fun visible(open: Boolean)
    fun join(link: String)
    fun pairNearby(id: String)
    fun confirm(matches: Boolean)
    fun reset()

    /** A pairing link for another phone or tablet to scan, or null. */
    suspend fun showCode(): String?
}

/**
 * Pairing with a PC: scan the QR code it shows, or pick it from the PCs
 * nearby and compare a 6-digit code. Pairing with another phone or tablet:
 * one shows a code ("Show a code") and the other scans it. `onDone` runs
 * after success (or when the user backs out, if `cancellable`).
 */
@Composable
fun PairingScreen(
    state: CoreState,
    actions: PairingActions,
    cancellable: Boolean,
    onDone: () -> Unit,
) {
    var nearby by rememberSaveable { mutableStateOf(false) }
    var showing by rememberSaveable { mutableStateOf(false) }
    val pairing = state.pairing
    val reducedMotion = LocalReducedMotion.current
    DisposableEffect(Unit) {
        actions.visible(true)
        onDispose { actions.visible(false) }
    }

    LaunchedEffect(pairing) {
        if (pairing is PairingState.Paired) {
            delay(1_400)
            actions.reset()
            onDone()
        }
    }

    Column(
        Modifier.fillMaxSize().safeDrawingPadding().padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Spacer(Modifier.height(16.dp))
        Text(
            stringResource(R.string.tawam_pair_eyebrow),
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            letterSpacing = 1.4.sp,
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = when (pairing) {
                is PairingState.Comparing, is PairingState.Confirmed -> stringResource(R.string.tawam_pair_confirm_title)
                is PairingState.Paired -> stringResource(R.string.tawam_pair_done_title)
                is PairingState.Failed -> stringResource(R.string.pair_failed_title)
                else -> when {
                    showing -> stringResource(R.string.pair_show_title)
                    else -> stringResource(R.string.tawam_pair_title)
                }
            },
            style = MaterialTheme.typography.headlineMedium,
        )
        Spacer(Modifier.height(16.dp))
        if (pairing == PairingState.Idle && !showing) {
            TawamTabs(
                selected = if (nearby) 1 else 0,
                labels = listOf(stringResource(R.string.tawam_tab_scan), stringResource(R.string.tawam_tab_nearby)),
                onSelect = { nearby = it == 1 },
            )
            Spacer(Modifier.height(16.dp))
        }

        AnimatedContent(
            targetState = Triple(pairing, nearby, showing),
            transitionSpec = {
                if (reducedMotion) {
                    EnterTransition.None togetherWith ExitTransition.None
                } else {
                    fadeIn() togetherWith fadeOut()
                }
            },
            modifier = Modifier.weight(1f),
            label = "pairing",
        ) { (current, showNearby, showCode) ->
            when (current) {
                PairingState.Idle -> when {
                    showCode -> ShowCode(actions)
                    showNearby -> NearbyList(state.discovered, actions::pairNearby)
                    else -> Scan(actions::join)
                }
                PairingState.Joining -> Working(stringResource(R.string.pair_joining))
                is PairingState.Connecting ->
                    Working(stringResource(R.string.pair_connecting, state.nameOf(current.peer) ?: ""))
                is PairingState.Comparing -> Compare(state.nameOf(current.peer), current.code, actions::confirm)
                is PairingState.Confirmed -> Column {
                    CodeDigits(current.code)
                    Spacer(Modifier.height(24.dp))
                    Working(stringResource(R.string.pair_waiting, state.nameOf(current.peer) ?: ""))
                }
                is PairingState.Paired -> Outcome(true, stringResource(R.string.tawam_pair_done, current.name))
                is PairingState.Failed -> Column {
                    Outcome(false, failureText(current.failure))
                    Spacer(Modifier.height(16.dp))
                    Button(onClick = actions::reset, shape = MaterialTheme.shapes.medium) {
                        Text(stringResource(R.string.action_try_again))
                    }
                }
            }
        }

        if (pairing == PairingState.Idle) {
            TextButton(onClick = {
                showing = !showing
                nearby = false
            }) {
                Text(stringResource(if (showing) R.string.action_scan_instead else R.string.action_show_code))
            }
        }
        if (cancellable && pairing !is PairingState.Paired) {
            TextButton(onClick = { actions.reset(); onDone() }) { Text(stringResource(R.string.action_cancel)) }
        }
    }
}

/** Two flat tabs with an accent underline under the selected one. */
@Composable
private fun TawamTabs(selected: Int, labels: List<String>, onSelect: (Int) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceContainer),
    ) {
        labels.forEachIndexed { i, label ->
            val on = i == selected
            Column(
                Modifier
                    .weight(1f)
                    .clickable { onSelect(i) }
                    .padding(top = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    label,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (on) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Spacer(Modifier.height(10.dp))
                Box(
                    Modifier
                        .fillMaxWidth(0.5f)
                        .height(3.dp)
                        .clip(RoundedCornerShape(topStart = 3.dp, topEnd = 3.dp))
                        .background(if (on) MaterialTheme.colorScheme.primary else androidx.compose.ui.graphics.Color.Transparent),
                )
            }
        }
    }
}

/** Thin accent corner marks around a square area. */
private fun Modifier.cornerMarks(color: androidx.compose.ui.graphics.Color): Modifier = drawWithContent {
    drawContent()
    val l = 22.dp.toPx()
    val w = 3.dp.toPx()
    val (W, H) = size.width to size.height
    listOf(
        Offset(0f, 0f) to Offset(1f, 1f),
        Offset(W, 0f) to Offset(-1f, 1f),
        Offset(0f, H) to Offset(1f, -1f),
        Offset(W, H) to Offset(-1f, -1f),
    ).forEach { (o, d) ->
        drawLine(color, o, Offset(o.x + d.x * l, o.y), strokeWidth = w, cap = StrokeCap.Round)
        drawLine(color, o, Offset(o.x, o.y + d.y * l), strokeWidth = w, cap = StrokeCap.Round)
    }
}

/** This device's pairing code, for another phone or tablet to scan. */
@Composable
private fun ShowCode(actions: PairingActions) {
    var link by androidx.compose.runtime.remember { mutableStateOf<String?>(null) }
    LaunchedEffect(Unit) { link = actions.showCode() }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            stringResource(R.string.pair_show_hint),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(20.dp))
        val current = link
        val label = stringResource(R.string.pair_show_qr_desc)
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .aspectRatio(1f)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(20.dp))
                .background(androidx.compose.ui.graphics.Color.White)
                .padding(16.dp)
                .semantics { contentDescription = label },
            contentAlignment = Alignment.Center,
        ) {
            if (current == null) {
                CircularProgressIndicator()
            } else {
                QrCode(current)
            }
        }
    }
}

/** [text] as a QR code, black on the white it sits on. */
@Composable
private fun QrCode(text: String) {
    val matrix = androidx.compose.runtime.remember(text) {
        runCatching {
            com.google.zxing.qrcode.QRCodeWriter().encode(
                text,
                com.google.zxing.BarcodeFormat.QR_CODE,
                0,
                0,
                mapOf(com.google.zxing.EncodeHintType.MARGIN to 0),
            )
        }.getOrNull()
    } ?: return
    androidx.compose.foundation.Canvas(Modifier.fillMaxSize()) {
        val cell = size.minDimension / matrix.width
        for (y in 0 until matrix.height) {
            for (x in 0 until matrix.width) {
                if (matrix.get(x, y)) {
                    drawRect(
                        androidx.compose.ui.graphics.Color.Black,
                        topLeft = androidx.compose.ui.geometry.Offset(x * cell, y * cell),
                        size = androidx.compose.ui.geometry.Size(cell, cell),
                    )
                }
            }
        }
    }
}

@Composable
private fun Scan(onLink: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        QrScanner(
            onLink = onLink,
            modifier = Modifier
                .weight(1f, fill = false)
                .fillMaxWidth()
                .aspectRatio(1f)
                .cornerMarks(MaterialTheme.colorScheme.primary)
                .padding(6.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.surfaceContainerHigh),
        )
        Spacer(Modifier.height(16.dp))
        listOf(R.string.tawam_step_open, R.string.tawam_step_show, R.string.tawam_step_point).forEachIndexed { i, res ->
            StepRow(i + 1, stringResource(res))
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun StepRow(number: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            Modifier
                .size(22.dp)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(4.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Text("$number", style = MaterialTheme.typography.labelMedium, color = MaterialTheme.colorScheme.primary)
        }
        Spacer(Modifier.width(10.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
private fun NearbyList(devices: List<DiscoveredDevice>, onPair: (String) -> Unit) {
    val reducedMotion = LocalReducedMotion.current
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(
            stringResource(R.string.pair_nearby_hint),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(6.dp))
        if (devices.isEmpty()) {
            Surface(
                shape = MaterialTheme.shapes.medium,
                color = androidx.compose.ui.graphics.Color.Transparent,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    Modifier.fillMaxWidth().padding(vertical = 12.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (reducedMotion) {
                        CircularProgressIndicator(
                            progress = { 0.75f },
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                        )
                    } else {
                        CircularProgressIndicator(Modifier.size(18.dp), strokeWidth = 2.dp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Text(
                        stringResource(R.string.pair_looking),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }
        devices.forEach { device ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .border(1.dp, MaterialTheme.colorScheme.outlineVariant, MaterialTheme.shapes.medium)
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(Modifier.width(3.dp).height(28.dp).clip(RoundedCornerShape(2.dp)).background(MaterialTheme.colorScheme.primary))
                Spacer(Modifier.width(12.dp))
                Text(
                    device.name ?: stringResource(R.string.your_pc),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                )
                Button(onClick = { onPair(device.id) }, shape = MaterialTheme.shapes.medium) {
                    Text(stringResource(R.string.tawam_connect))
                }
            }
        }
    }
}

@Composable
private fun Compare(peerName: String?, code: String, onConfirm: (Boolean) -> Unit) {
    Column {
        Text(
            stringResource(R.string.tawam_compare_hint, peerName ?: stringResource(R.string.your_pc)),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(20.dp))
        CodeDigits(code)
        Spacer(Modifier.height(28.dp))
        Button(onClick = { onConfirm(true) }, shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text(stringResource(R.string.tawam_codes_same))
        }
        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = { onConfirm(false) }, shape = MaterialTheme.shapes.medium, modifier = Modifier.fillMaxWidth().height(52.dp)) {
            Text(stringResource(R.string.tawam_codes_different))
        }
    }
}

/** The 6-digit code as one strip: "123 456". */
@Composable
private fun CodeDigits(code: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer,
        modifier = Modifier.semantics { contentDescription = code.toList().joinToString(" ") },
    ) {
        Text(
            if (code.length == 6) code.substring(0, 3) + " " + code.substring(3) else code,
            fontFamily = LocalAppFonts.current.mono,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 4.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
        )
    }
}

@Composable
private fun Working(text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        if (LocalReducedMotion.current) {
            CircularProgressIndicator(
                progress = { 0.75f },
                modifier = Modifier.size(20.dp),
                strokeWidth = 2.dp,
            )
        } else {
            CircularProgressIndicator(Modifier.size(20.dp), strokeWidth = 2.dp)
        }
        Spacer(Modifier.width(12.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun Outcome(success: Boolean, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = if (success) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.errorContainer,
            modifier = Modifier.size(44.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    if (success) "✓" else "!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (success) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onErrorContainer,
                )
            }
        }
        Spacer(Modifier.width(14.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
private fun failureText(failure: PairingFailure): String = when (failure) {
    PairingFailure.Rejected -> stringResource(R.string.pair_failed_rejected)
    PairingFailure.Declined -> stringResource(R.string.pair_failed_declined)
    PairingFailure.Expired -> stringResource(R.string.pair_failed_expired)
    PairingFailure.Unreachable -> stringResource(R.string.pair_failed_unreachable)
    is PairingFailure.Other -> stringResource(R.string.pair_failed_other)
}
