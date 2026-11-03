// SPDX-License-Identifier: GPL-3.0-or-later
// Generated from docs/design/tokens.json by `cargo xtask tokens`. Do not edit.
package app.nectarlink.android.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/** Material color roles of one theme variant. */
@Immutable
data class Palette(
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,
    val secondaryContainer: Color,
    val onSecondaryContainer: Color,
    val surface: Color,
    val surfaceContainerLow: Color,
    val surfaceContainer: Color,
    val surfaceContainerHigh: Color,
    val surfaceContainerHighest: Color,
    val onSurface: Color,
    val onSurfaceVariant: Color,
    val outline: Color,
    val outlineVariant: Color,
)

/** A Bloom color preset: its seed swatch and light and dark palettes. */
@Immutable
data class Seed(val color: Color, val light: Palette, val dark: Palette)

object Tokens {
    const val DEFAULT_SEED = "teal"

    val bloomSeeds: Map<String, Seed> = linkedMapOf(
        "indigo" to Seed(
            color = Color(0xFF5B6AC8),
            light = Palette(
                primary = Color(0xFF3E4FBA),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFDFE1F4),
                onPrimaryContainer = Color(0xFF13193A),
                secondaryContainer = Color(0xFFE0E2EB),
                onSecondaryContainer = Color(0xFF191B27),
                surface = Color(0xFFF9F9FA),
                surfaceContainerLow = Color(0xFFF3F3F5),
                surfaceContainer = Color(0xFFEDEDF0),
                surfaceContainerHigh = Color(0xFFE7E8EB),
                surfaceContainerHighest = Color(0xFFDFDFE4),
                onSurface = Color(0xFF1B1C20),
                onSurfaceVariant = Color(0xFF454650),
                outline = Color(0xFF747688),
                outlineVariant = Color(0xFFC5C6CE),
            ),
            dark = Palette(
                primary = Color(0xFFBFC4EA),
                onPrimary = Color(0xFF1F275C),
                primaryContainer = Color(0xFF313E94),
                onPrimaryContainer = Color(0xFFDFE1F4),
                secondaryContainer = Color(0xFF3C415E),
                onSecondaryContainer = Color(0xFFE0E2EB),
                surface = Color(0xFF151619),
                surfaceContainerLow = Color(0xFF101113),
                surfaceContainer = Color(0xFF1D1D22),
                surfaceContainerHigh = Color(0xFF27282D),
                surfaceContainerHighest = Color(0xFF31333A),
                onSurface = Color(0xFFE5E5E8),
                onSurfaceVariant = Color(0xFFBFC1C9),
                outline = Color(0xFF8D909E),
                outlineVariant = Color(0xFF454650),
            ),
        ),
        "ink" to Seed(
            color = Color(0xFF2B3036),
            light = Palette(
                primary = Color(0xFF515A64),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFE0E3E5),
                onPrimaryContainer = Color(0xFF191B1E),
                secondaryContainer = Color(0xFFE0E3E5),
                onSecondaryContainer = Color(0xFF191B1E),
                surface = Color(0xFFF9FAFA),
                surfaceContainerLow = Color(0xFFF3F4F4),
                surfaceContainer = Color(0xFFEDEEEE),
                surfaceContainerHigh = Color(0xFFE7E8E9),
                surfaceContainerHighest = Color(0xFFDEE0E1),
                onSurface = Color(0xFF1A1B1D),
                onSurfaceVariant = Color(0xFF45474A),
                outline = Color(0xFF73777C),
                outlineVariant = Color(0xFFC4C7C9),
            ),
            dark = Palette(
                primary = Color(0xFFC1C7CD),
                onPrimary = Color(0xFF282C31),
                primaryContainer = Color(0xFF40474F),
                onPrimaryContainer = Color(0xFFE0E3E5),
                secondaryContainer = Color(0xFF3C434A),
                onSecondaryContainer = Color(0xFFE0E3E5),
                surface = Color(0xFF151516),
                surfaceContainerLow = Color(0xFF101111),
                surfaceContainer = Color(0xFF1C1D1F),
                surfaceContainerHigh = Color(0xFF26282A),
                surfaceContainerHighest = Color(0xFF303235),
                onSurface = Color(0xFFE4E6E7),
                onSurfaceVariant = Color(0xFFBFC2C4),
                outline = Color(0xFF8D9296),
                outlineVariant = Color(0xFF45474A),
            ),
        ),
        "olive" to Seed(
            color = Color(0xFF647932),
            light = Palette(
                primary = Color(0xFF4F6027),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFDCE7C3),
                onPrimaryContainer = Color(0xFF181E0C),
                secondaryContainer = Color(0xFFE0E5D6),
                onSecondaryContainer = Color(0xFF191D12),
                surface = Color(0xFFF9FAF9),
                surfaceContainerLow = Color(0xFFF3F4F2),
                surfaceContainer = Color(0xFFEDEEEC),
                surfaceContainerHigh = Color(0xFFE7E8E5),
                surfaceContainerHighest = Color(0xFFDFE0DC),
                onSurface = Color(0xFF1A1C18),
                onSurfaceVariant = Color(0xFF45483E),
                outline = Color(0xFF747969),
                outlineVariant = Color(0xFFC5C7BF),
            ),
            dark = Palette(
                primary = Color(0xFFB9CF88),
                onPrimary = Color(0xFF272F13),
                primaryContainer = Color(0xFF3E4C1F),
                onPrimaryContainer = Color(0xFFDCE7C3),
                secondaryContainer = Color(0xFF3E452C),
                onSecondaryContainer = Color(0xFFE0E5D6),
                surface = Color(0xFF151513),
                surfaceContainerLow = Color(0xFF11110F),
                surfaceContainer = Color(0xFF1C1E1A),
                surfaceContainerHigh = Color(0xFF272923),
                surfaceContainerHighest = Color(0xFF31342D),
                onSurface = Color(0xFFE4E5E2),
                onSurfaceVariant = Color(0xFFC0C2B9),
                outline = Color(0xFF8E9382),
                outlineVariant = Color(0xFF45483E),
            ),
        ),
        "sand" to Seed(
            color = Color(0xFF99682C),
            light = Palette(
                primary = Color(0xFF785123),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFF0E0CD),
                onPrimaryContainer = Color(0xFF25190B),
                secondaryContainer = Color(0xFFE7E2DA),
                onSecondaryContainer = Color(0xFF201B14),
                surface = Color(0xFFFAF9F9),
                surfaceContainerLow = Color(0xFFF4F4F2),
                surfaceContainer = Color(0xFFEFEDEC),
                surfaceContainerHigh = Color(0xFFE9E8E5),
                surfaceContainerHighest = Color(0xFFE2DFDC),
                onSurface = Color(0xFF1D1C19),
                onSurfaceVariant = Color(0xFF4B463F),
                outline = Color(0xFF7E756B),
                outlineVariant = Color(0xFFCAC5C0),
            ),
            dark = Palette(
                primary = Color(0xFFE2C19A),
                onPrimary = Color(0xFF3C2811),
                primaryContainer = Color(0xFF5F411C),
                onPrimaryContainer = Color(0xFFF0E0CD),
                secondaryContainer = Color(0xFF4D4031),
                onSecondaryContainer = Color(0xFFE7E2DA),
                surface = Color(0xFF171614),
                surfaceContainerLow = Color(0xFF12100F),
                surfaceContainer = Color(0xFF1F1D1B),
                surfaceContainerHigh = Color(0xFF2A2724),
                surfaceContainerHighest = Color(0xFF35322E),
                onSurface = Color(0xFFE6E5E2),
                onSurfaceVariant = Color(0xFFC5C0BB),
                outline = Color(0xFF988F86),
                outlineVariant = Color(0xFF4B463F),
            ),
        ),
        "teal" to Seed(
            color = Color(0xFF1F7E82),
            light = Palette(
                primary = Color(0xFF186366),
                onPrimary = Color(0xFFFFFFFF),
                primaryContainer = Color(0xFFB8ECEE),
                onPrimaryContainer = Color(0xFF071E20),
                secondaryContainer = Color(0xFFD7E5E6),
                onSecondaryContainer = Color(0xFF131E1E),
                surface = Color(0xFFF8F9FA),
                surfaceContainerLow = Color(0xFFF1F4F4),
                surfaceContainer = Color(0xFFEBEFEF),
                surfaceContainerHigh = Color(0xFFE4E9E9),
                surfaceContainerHighest = Color(0xFFDAE1E1),
                onSurface = Color(0xFF181D1D),
                onSurfaceVariant = Color(0xFF3D494A),
                outline = Color(0xFF657B7C),
                outlineVariant = Color(0xFFBDC9C9),
            ),
            dark = Palette(
                primary = Color(0xFF6BD6DC),
                onPrimary = Color(0xFF0C3233),
                primaryContainer = Color(0xFF134E52),
                onPrimaryContainer = Color(0xFFB8ECEE),
                secondaryContainer = Color(0xFF2E4648),
                onSecondaryContainer = Color(0xFFD7E5E6),
                surface = Color(0xFF131617),
                surfaceContainerLow = Color(0xFF0E1212),
                surfaceContainer = Color(0xFF191E1F),
                surfaceContainerHigh = Color(0xFF22292A),
                surfaceContainerHighest = Color(0xFF2B3435),
                onSurface = Color(0xFFE1E6E7),
                onSurfaceVariant = Color(0xFFB7C3C4),
                outline = Color(0xFF7E9596),
                outlineVariant = Color(0xFF3D494A),
            ),
        ),
    )

    val graphitePaper = Palette(
        primary = Color(0xFF1A1918),
        onPrimary = Color(0xFFF5F2EB),
        primaryContainer = Color(0xFFE8E3D7),
        onPrimaryContainer = Color(0xFF1A1918),
        secondaryContainer = Color(0xFFE8E3D7),
        onSecondaryContainer = Color(0xFF1A1918),
        surface = Color(0xFFF5F2EB),
        surfaceContainerLow = Color(0xFFF1EDE4),
        surfaceContainer = Color(0xFFF5F2EB),
        surfaceContainerHigh = Color(0xFFEFEBE2),
        surfaceContainerHighest = Color(0xFFE8E3D7),
        onSurface = Color(0xFF1A1918),
        onSurfaceVariant = Color(0xFF5E5A54),
        outline = Color(0xFF4A4743),
        outlineVariant = Color(0xFFD5CFC2),
    )

    val graphiteSlate = Palette(
        primary = Color(0xFFE8EAED),
        onPrimary = Color(0xFF0D0D0E),
        primaryContainer = Color(0xFF1A1A1D),
        onPrimaryContainer = Color(0xFFE8EAED),
        secondaryContainer = Color(0xFF1A1A1D),
        onSecondaryContainer = Color(0xFFE8EAED),
        surface = Color(0xFF0D0D0E),
        surfaceContainerLow = Color(0xFF0B0B0C),
        surfaceContainer = Color(0xFF0D0D0E),
        surfaceContainerHigh = Color(0xFF141416),
        surfaceContainerHighest = Color(0xFF1A1A1D),
        onSurface = Color(0xFFE8EAED),
        onSurfaceVariant = Color(0xFF9A9CA1),
        outline = Color(0xFF7E8084),
        outlineVariant = Color(0xFF26272A),
    )

    val hiveWax = Palette(
        primary = Color(0xFF8A5100),
        onPrimary = Color(0xFFFFFFFF),
        primaryContainer = Color(0xFFFFDDB3),
        onPrimaryContainer = Color(0xFF2C1600),
        secondaryContainer = Color(0xFFF4E3CD),
        onSecondaryContainer = Color(0xFF2A1F14),
        surface = Color(0xFFFFF8F3),
        surfaceContainerLow = Color(0xFFFCF2E9),
        surfaceContainer = Color(0xFFF8ECE1),
        surfaceContainerHigh = Color(0xFFF3E5D7),
        surfaceContainerHighest = Color(0xFFEDDDCD),
        onSurface = Color(0xFF1F1B16),
        onSurfaceVariant = Color(0xFF5C4F43),
        outline = Color(0xFF85766A),
        outlineVariant = Color(0xFFDDCBBA),
    )

    val hiveComb = Palette(
        primary = Color(0xFFFFB95F),
        onPrimary = Color(0xFF2C1600),
        primaryContainer = Color(0xFF5A3A0E),
        onPrimaryContainer = Color(0xFFFFDDB3),
        secondaryContainer = Color(0xFF2F2A24),
        onSecondaryContainer = Color(0xFFF0E0CC),
        surface = Color(0xFF121214),
        surfaceContainerLow = Color(0xFF151517),
        surfaceContainer = Color(0xFF1A1A1D),
        surfaceContainerHigh = Color(0xFF212124),
        surfaceContainerHighest = Color(0xFF29292D),
        onSurface = Color(0xFFEDE6DD),
        onSurfaceVariant = Color(0xFFABA195),
        outline = Color(0xFF8D8379),
        outlineVariant = Color(0xFF34312D),
    )

    val hiveWaxWarning = Color(0xFF7A4A00)
    val hiveWaxError = Color(0xFF9E2B25)
    val hiveCombWarning = Color(0xFFF5D58A)
    val hiveCombError = Color(0xFFF0A39C)

    /** The honeycomb backdrop (themes.hive.comb). */
    const val COMB_CELL_DP = 24f
    const val COMB_LINE_ALPHA_DARK = 0.27f
    const val COMB_LINE_ALPHA_LIGHT = 0.11f
    const val COMB_GLOW_ALPHA_DARK = 0.7f
    const val COMB_GLOW_ALPHA_LIGHT = 0.75f
    const val COMB_BREATH_SECONDS = 6f
    const val COMB_PULSE_SECONDS = 4.5f
    const val COMB_FRAME_MS = 33L

    val warningLight = Color(0xFF7D5200)
    val warningDark = Color(0xFFF2C14E)
    val errorLight = Color(0xFFBA1A1A)
    val errorDark = Color(0xFFFFB4AB)
}
