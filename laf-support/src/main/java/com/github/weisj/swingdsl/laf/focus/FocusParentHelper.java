/*
 * MIT License
 *
 * Copyright (c) 2021 Jannis Weis
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */
package com.github.weisj.swingdsl.laf.focus;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Consumer;

import javax.swing.*;

public final class FocusParentHelper {

    public static final String KEY_FOCUS_PARENT = "focusParent";
    public static final String KEY_FOCUS_ACTION = "focusOnParentChangedAction";
    private static final Map<Component, Component> listeners = new WeakHashMap<>();

    static {
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (!(event instanceof FocusEvent)) {
                return;
            }
            FocusEvent e = (FocusEvent) event;
            if (e.getID() != FocusEvent.FOCUS_GAINED && e.getID() != FocusEvent.FOCUS_LOST) {
                return;
            }
            Component comp = e.getComponent();
            listeners.forEach((c, focusParent) -> {
                if (!(c instanceof JComponent)) {
                    return;
                }
                if (SwingUtilities.isDescendingFrom(comp, focusParent)) {
                    Object onFocusAction = ((JComponent) c).getClientProperty(KEY_FOCUS_ACTION);
                    if (onFocusAction instanceof RepaintAction) {
                        ((RepaintAction) onFocusAction).accept(c);
                    }
                }
            });
        }, AWTEvent.FOCUS_EVENT_MASK);
    }

    public static void setFocusParent(final JComponent c, final JComponent focusParent) {
        setFocusParent(c, focusParent, Component::repaint);
    }

    public static void setFocusParent(final JComponent c, final JComponent focusParent,
            final RepaintAction focusChangedAction) {
        if (c == null) {
            return;
        }
        c.putClientProperty(KEY_FOCUS_PARENT, focusParent);
        if (focusParent == null) {
            c.putClientProperty(KEY_FOCUS_ACTION, null);
            listeners.remove(c);
        } else {
            c.putClientProperty(KEY_FOCUS_ACTION, focusChangedAction);
            listeners.put(c, focusParent);
        }
    }

    public interface RepaintAction extends Consumer<Component> {
    }
}
