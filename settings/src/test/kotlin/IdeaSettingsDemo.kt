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
import com.github.weisj.darklaf.LafManager
import com.github.weisj.darklaf.theme.DarculaTheme
import com.github.weisj.swingdsl.frame
import com.github.weisj.swingdsl.invokeLater
import com.github.weisj.swingdsl.settings.Category
import com.github.weisj.swingdsl.settings.boolean
import com.github.weisj.swingdsl.settings.category
import com.github.weisj.swingdsl.settings.choice
import com.github.weisj.swingdsl.settings.createSettingsPanel
import com.github.weisj.swingdsl.settings.int
import com.github.weisj.swingdsl.settings.string
import com.github.weisj.swingdsl.text.unaryPlus
import com.github.weisj.swingdsl.unaryPlus
import java.awt.Color
import java.awt.Dimension

var welcomeText = "HelloWord"
var brightness = 50
var nightMode = false
var scale = 1f
var screenName = "Monitor 1"

data class Resolution(val w: Int, val h: Int) {
    override fun toString(): String = "${w}x$h"
}

var resolution = Resolution(1024, 768)
val availableResolutions = listOf(
    Resolution(1024, 768),
    Resolution(1280, 1024),
    Resolution(1440, 900),
    Resolution(1920, 1080),
)

enum class Orientation {
    Horizontal,
    Vertical
}

var orientation = Orientation.Horizontal

var fontSize = 12
var fontColor = Color.BLACK
var lineSpacing = 1.5f

fun createCategories(): List<Category> = listOf(
    category("General") {
        group("Greeting") {
            string(name = +"Welcome Text", value = ::welcomeText)
        }
        group("Display") {
            int(name = +"Brightness", value = ::brightness, low = 0, high = 100)
            boolean(name = +"Night mode", value = ::nightMode)
        }
    },
    category("Screen") {
        category("Scaling & Ordering") {
            group(description = +"Screen Options") {
                // Todo scaling (float)
                string(name = +"Monitor", value = ::screenName)
                choice(name = +"Resolution", value = ::resolution, choices = availableResolutions)
                choice(name = +"Orientation", value = ::orientation) // Enum properties infer possible values.
            }
            group {
                int(name = +"Font Size", value = ::fontSize, range = 6..80)
                // Todo font color
                // Todo line Spacing (float)
                // Todo File property (+ w/ default)
                // Todo Folder property (with browse) (+ w/ default)
            }
        }
    },
    category("Favorites") {
        // Todo: What does
    }
)

fun main() {
    invokeLater {
        LafManager.install(DarculaTheme())
        frame {
            content {
                +createSettingsPanel()
            }
            minimumSize = Dimension(600, 300)
            visible = true
        }
    }
}
