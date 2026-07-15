package com.example.ui.components;

import javax.swing.*;
import java.awt.*;

public class RoundedCheckBox extends JCheckBox {

    public RoundedCheckBox(String text) {
        super(text);
        setOpaque(false);
        setFocusPainted(false);
        setForeground(UIColors.FOREGROUND);
        setFont(getFont().deriveFont(Font.PLAIN, 13f));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setIconTextGap(10);
        setIcon(new BoxIcon(false));
        setSelectedIcon(new BoxIcon(true));
    }

    /** Small 18x18 rounded box icon, drawn manually so it matches the palette. */
    private static class BoxIcon implements Icon {
        private final boolean checked;
        private static final int SIZE = 18;

        BoxIcon(boolean checked) {
            this.checked = checked;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(checked ? UIColors.ACCENT : UIColors.CARD);
            g2.fillRoundRect(x, y, SIZE, SIZE, 5, 5);

            g2.setColor(checked ? UIColors.ACCENT : UIColors.BORDER);
            g2.drawRoundRect(x, y, SIZE - 1, SIZE - 1, 5, 5);

            if (checked) {
                g2.setColor(UIColors.PRIMARY_FG);
                g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
                g2.drawLine(x + 4, y + 9, x + 7, y + 13);
                g2.drawLine(x + 7, y + 13, x + 14, y + 4);
            }

            g2.dispose();
        }

        @Override
        public int getIconWidth() {
            return SIZE;
        }

        @Override
        public int getIconHeight() {
            return SIZE;
        }
    }
}
