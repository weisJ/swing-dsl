/*
 * MIT License
 *
 * Copyright (c) 2021 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */
import com.github.weisj.darklaf.settings.ThemeSettings
import com.github.weisj.darklaf.settings.ThemeSettingsUI
import com.github.weisj.swingdsl.core.text.Text
import com.github.weisj.swingdsl.layout.IndentationPolicy
import com.github.weisj.swingdsl.layout.Row
import com.github.weisj.swingdsl.observableUIManagerProperty
import com.github.weisj.swingdsl.properties

fun Row.createDarklafSettings() {
    indent(IndentationPolicy.NO)
    val settings = ThemeSettings.getInstance()
    val themeSettingsUI = ThemeSettingsUI()

    var currentConfig = settings.exportConfiguration()
    themeSettingsUI.loadConfiguration(currentConfig)
    fun String.text(): Text = observableUIManagerProperty(this)

    onGlobalApply {
        currentConfig = themeSettingsUI.settingsConfiguration
        settings.setConfiguration(currentConfig)
        settings.apply()
    }
    onGlobalReset {
        themeSettingsUI.loadConfiguration(currentConfig)
    }
    onGlobalIsModified {
        !currentConfig.isResultingAppearanceEqualTo(themeSettingsUI.settingsConfiguration)
    }

    row(ThemeSettingsUI.THEME_LABEL_KEY.text()) {
        component(themeSettingsUI.themeComboBox)
    }
    row(ThemeSettingsUI.ACCENT_COLOR_LABEL_KEY.text()) {
        cell {
            themeSettingsUI.accentChooser.radioButtons.forEach {
                component(it)
            }
        }
    }
    row(ThemeSettingsUI.SELECTION_COLOR_LABEL_KEY.text()) {
        cell {
            themeSettingsUI.selectionChooser.radioButtons.forEach {
                component(it)
            }
        }
    }
    row(ThemeSettingsUI.FONT_SIZE_LABEL_KEY.text()) {
        component(themeSettingsUI.fontSlider).applyToComponent {
            properties {
                client["JSlider.useTrackAsBaseline"] = true
            }
        }
    }

    titledRow(ThemeSettingsUI.MONITORING_LABEL_KEY.text()) {
        component(themeSettingsUI.systemPreferencesTristateCheckBox)
        row {
            component(themeSettingsUI.themeFollowsSystemCheckBox)
            component(themeSettingsUI.fontSizeFollowsSystemCheckBox)
        }
        row {
            component(themeSettingsUI.accentColorFollowsSystemCheckBox)
            component(themeSettingsUI.selectionColorFollowsSystemCheckBox)
        }
    }
}
