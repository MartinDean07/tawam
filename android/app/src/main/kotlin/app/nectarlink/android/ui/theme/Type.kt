// SPDX-License-Identifier: GPL-3.0-or-later
package app.nectarlink.android.ui.theme

import android.content.res.AssetManager
import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight

/**
 * The fonts bundled with the app (`assets/fonts` in the repository, packaged
 * as assets under `fonts/`): IBM Plex Sans for text, IBM Plex Mono for codes.
 */
@Immutable
class AppFonts(assets: AssetManager) {
    /** IBM Plex Sans is a variable font: one file, every weight the app uses. */
    val sans = FontFamily(
        listOf(400, 500, 600, 650, 700).map { weight ->
            Font(
                "fonts/IBMPlexSans.ttf",
                assets,
                FontWeight(weight),
                variationSettings = FontVariation.Settings(FontVariation.weight(weight)),
            )
        },
    )
    val serif = sans
    val mono = FontFamily(
        Font("fonts/IBMPlexMono-Regular.ttf", assets),
        Font("fonts/IBMPlexMono-SemiBold.ttf", assets, FontWeight.Bold),
    )
}

val LocalAppFonts = staticCompositionLocalOf<AppFonts> { error("TawamTheme provides the fonts") }

/**
 * Material's type scale (sizes and line heights suit a phone) in the bundled
 * fonts, with the weights from docs/design/tokens.json: bold headings in
 * Bloom, serif headings in Graphite.
 */
internal fun typography(fonts: AppFonts, graphite: Boolean): Typography {
    val base = Typography()
    fun TextStyle.text(weight: Int) = copy(fontFamily = fonts.sans, fontWeight = FontWeight(weight))
    fun TextStyle.heading() =
        text(600)
    return base.copy(
        displayLarge = base.displayLarge.heading(),
        displayMedium = base.displayMedium.heading(),
        displaySmall = base.displaySmall.heading(),
        headlineLarge = base.headlineLarge.heading(),
        headlineMedium = base.headlineMedium.heading(),
        headlineSmall = base.headlineSmall.heading(),
        titleLarge = base.titleLarge.text(600),
        titleMedium = base.titleMedium.text(600),
        titleSmall = base.titleSmall.text(600),
        bodyLarge = base.bodyLarge.text(400),
        bodyMedium = base.bodyMedium.text(400),
        bodySmall = base.bodySmall.text(400),
        labelLarge = base.labelLarge.text(600),
        labelMedium = base.labelMedium.text(600),
        labelSmall = base.labelSmall.text(500),
    )
}
