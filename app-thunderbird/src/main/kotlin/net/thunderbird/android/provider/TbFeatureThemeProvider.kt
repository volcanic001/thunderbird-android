package net.thunderbird.android.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import net.thunderbird.android.R
import net.thunderbird.components.ui.bolt.theme.thunderbird.ThunderbirdBoltTheme
import net.thunderbird.core.preference.AppFontFamily
import net.thunderbird.core.preference.GeneralSettingsManager
import net.thunderbird.core.ui.theme.api.FeatureThemeProvider

internal class TbFeatureThemeProvider(
    private val generalSettingsManager: GeneralSettingsManager,
) : FeatureThemeProvider {
    @Composable
    override fun WithTheme(content: @Composable () -> Unit) {
        val config by generalSettingsManager.getConfigFlow().collectAsState(
            initial = generalSettingsManager.getConfig(),
        )
        val fontFamily = when (config.display.visualSettings.fontFamily) {
            AppFontFamily.SYSTEM_DEFAULT -> FontFamily.SansSerif
            AppFontFamily.GOOGLE_SANS_ROUNDED_BOLD -> FontFamily(Font(R.font.google_sans_rounded_bold))
        }
        ThunderbirdBoltTheme(fontFamily = fontFamily) {
            content()
        }
    }

    @Composable
    override fun WithTheme(darkTheme: Boolean, content: @Composable () -> Unit) {
        val config by generalSettingsManager.getConfigFlow().collectAsState(
            initial = generalSettingsManager.getConfig(),
        )
        val fontFamily = when (config.display.visualSettings.fontFamily) {
            AppFontFamily.SYSTEM_DEFAULT -> FontFamily.SansSerif
            AppFontFamily.GOOGLE_SANS_ROUNDED_BOLD -> FontFamily(Font(R.font.google_sans_rounded_bold))
        }
        ThunderbirdBoltTheme(darkTheme = darkTheme, fontFamily = fontFamily) {
            content()
        }
    }
}
