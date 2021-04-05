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
package com.github.weisj.swingdsl.laf;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListModel;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComponentFactoryDelegate implements ComponentFactory {

    private final ComponentFactory delegate;

    public ComponentFactoryDelegate(final ComponentFactory delegate) {
        this.delegate = delegate;
    }

    public ComponentFactory getDelegate() {
        return delegate;
    }

    @Override
    public @NotNull WrappedComponent<JLabel> createLabel(@NotNull TextProperty text, Icon icon) {
        return getDelegate().createLabel(text, icon);
    }

    @Override
    public @NotNull WrappedComponent<JButton> createButton(@NotNull TextProperty text, Icon icon) {
        return getDelegate().createButton(text, icon);
    }

    @Override
    public @NotNull WrappedComponent<JCheckBox> createCheckBox(@NotNull TextProperty text, @Nullable Icon icon) {
        return getDelegate().createCheckBox(text, icon);
    }

    @Override
    public @NotNull WrappedComponent<JRadioButton> createRadioButton(@NotNull TextProperty text, @Nullable Icon icon) {
        return getDelegate().createRadioButton(text, icon);
    }

    @Override
    public @NotNull WrappedComponent<JScrollPane> createScrollPane(@NotNull JComponent content) {
        return getDelegate().createScrollPane(content);
    }

    @Override
    public @NotNull <T> WrappedComponent<JList<T>> createList(@NotNull ListModel<T> content) {
        return getDelegate().createList(content);
    }

    @Override
    public @NotNull WrappedComponent<JSplitPane> createSplitPane(@NotNull JComponent left, @NotNull JComponent right) {
        return getDelegate().createSplitPane(left, right);
    }

    @Override
    public @NotNull SeparatorSpec<JComponent, SeparatorSpec.Default> createSeparatorComponent(
            @Nullable TextProperty label) {
        return getDelegate().createSeparatorComponent(label);
    }

    @Override
    public @NotNull SeparatorSpec<CollapsibleComponent, SeparatorSpec.DefaultCollapsible> createCollapsibleSeparatorComponent(
            @Nullable TextProperty label) {
        return getDelegate().createCollapsibleSeparatorComponent(label);
    }
}
