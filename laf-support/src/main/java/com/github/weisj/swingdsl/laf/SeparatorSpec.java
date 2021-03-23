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

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;

import javax.swing.Icon;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SeparatorSpec<T, K> {

    private final @Nullable T provided;
    private final @Nullable K defaultImplSpec;

    public SeparatorSpec(@Nullable T provided, @Nullable K defaultImplSpec) {
        if (provided == null && defaultImplSpec == null) {
            throw new IllegalArgumentException(
                    "Either a custom implementation or the default implementation spec must be provided");
        }
        this.provided = provided;
        this.defaultImplSpec = defaultImplSpec;
    }

    public boolean providesCustomComponent() {
        return provided != null;
    }

    @Nullable
    public T getProvided() {
        return provided;
    }

    @Nullable
    public K getDefaultImplSpec() {
        return defaultImplSpec;
    }

    public static class Default {
        private final @Nullable DefaultSupplier<Color> colorSupplier;

        public Default(@Nullable DefaultSupplier<Color> colorSupplier) {
            this.colorSupplier = colorSupplier;
        }

        @NotNull
        public Color getColor() {
            return colorSupplier != null ? colorSupplier.get(true) : Color.BLACK;
        }

        @NotNull
        public Color getDisabledColor() {
            return colorSupplier != null ? colorSupplier.get(true) : Color.GRAY;
        }

    }

    public static class DefaultCollapsible extends Default {
        private final @Nullable DefaultSupplier<Icon> collapsedIconSupplier;
        private final @Nullable DefaultSupplier<Icon> expandedIconSupplier;

        public DefaultCollapsible(@Nullable DefaultSupplier<Color> colorSupplier,
                @Nullable DefaultSupplier<Icon> collapsedIconSupplier,
                @Nullable DefaultSupplier<Icon> expandedIconSupplier) {
            super(colorSupplier);
            this.collapsedIconSupplier = collapsedIconSupplier;
            this.expandedIconSupplier = expandedIconSupplier;
        }

        @NotNull
        public Icon getCollapsedIcon() {
            return collapsedIconSupplier != null
                    ? collapsedIconSupplier.get(true)
                    : new ArrowRight(getColor());
        }

        @NotNull
        public Icon getExpandedIcon() {
            return expandedIconSupplier != null
                    ? expandedIconSupplier.get(true)
                    : new ArrowDown(getColor());
        }

        @NotNull
        public Icon getDisabledCollapsedIcon() {
            return collapsedIconSupplier != null
                    ? collapsedIconSupplier.get(false)
                    : new ArrowRight(getDisabledColor());
        }

        @NotNull
        public Icon getDisabledExpandedIcon() {
            return expandedIconSupplier != null
                    ? expandedIconSupplier.get(false)
                    : new ArrowDown(getDisabledColor());
        }
    }

    private static class ArrowDown extends ArrowIcon {

        private ArrowDown(@NotNull Color color) {
            super(color);
        }

        @Override
        protected @NotNull Path2D createPath() {
            Path2D p = new Path2D.Float(Path2D.WIND_EVEN_ODD);
            p.moveTo(8, 6);
            p.lineTo(12.5, 11);
            p.lineTo(3.5, 11);
            p.closePath();
            p.transform(new AffineTransform(1, 0, 0, -1, 0, 17));
            return p;
        }
    }

    private static class ArrowRight extends ArrowIcon {

        private ArrowRight(@NotNull Color color) {
            super(color);
        }

        @NotNull
        protected Path2D createPath() {
            Path2D p = new Path2D.Float(Path2D.WIND_EVEN_ODD);
            p.moveTo(8.5, 5.5);
            p.lineTo(13, 10.5);
            p.lineTo(4, 10.5);
            p.closePath();
            p.transform(new AffineTransform(0, -1, -1, 0, 16.5, 16.5));
            return p;
        }

    }

    private abstract static class ArrowIcon implements Icon {

        private final @NotNull Color color;
        private final @NotNull Path2D path;

        private ArrowIcon(@NotNull Color color) {
            this.color = color;
            this.path = createPath();
        }

        @NotNull
        protected abstract Path2D createPath();

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.translate(x, y);
            ((Graphics2D) g).fill(path);
        }

        @Override
        public int getIconWidth() {
            return 16;
        }

        @Override
        public int getIconHeight() {
            return 16;
        }
    }
}
