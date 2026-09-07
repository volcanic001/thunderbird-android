package app.k9mail.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.fsck.k9.R
import net.thunderbird.components.ui.bolt.theme.k9mail.K9MailBoltTheme
import net.thunderbird.core.preference.AppFontFamily
import net.thunderbird.core.preference.GeneralSettingsManager
import net.thunderbird.core.ui.theme.api.FeatureThemeProvider

internal class K9FeatureThemeProvider(
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
        K9MailBoltTheme(fontFamily = fontFamily) {
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
        K9MailBoltTheme(darkTheme = darkTheme, fontFamily = fontFamily) {
            content()
        }
    }
}
