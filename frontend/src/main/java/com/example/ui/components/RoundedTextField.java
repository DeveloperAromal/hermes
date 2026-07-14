package com.example.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


public class RoundedTextField extends JTextField {

    private String placeholder = "";
    private boolean focused = false;

    public RoundedTextField(int columns) {
        super(columns);

        setOpaque(false);
        setBorder(new EmptyBorder(9, 14, 9, 14));

        setBackground(UIColors.CARD);
        setForeground(UIColors.FOREGROUND);
        setCaretColor(UIColors.FOREGROUND);
        setSelectionColor(new Color(255, 255, 255, 40));
        setFont(getFont().deriveFont(Font.PLAIN, 13f));

        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                focused = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                focused = false;
                repaint();
            }
        });
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), UIColors.RADIUS, UIColors.RADIUS);

        g2.dispose();
        super.paintComponent(g);

        if (getText().isEmpty() && !placeholder.isEmpty()) {
            Graphics2D pg = (Graphics2D) g.create();
            pg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            pg.setColor(UIColors.PLACEHOLDER);
            pg.setFont(getFont());
            FontMetrics fm = pg.getFontMetrics();
            int textY = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
            pg.drawString(placeholder, getInsets().left, textY);
            pg.dispose();
        }
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (focused) {
            g2.setColor(new Color(UIColors.RING.getRed(), UIColors.RING.getGreen(), UIColors.RING.getBlue(), 70));
            g2.setStroke(new BasicStroke(3f));
            g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, UIColors.RADIUS, UIColors.RADIUS);
        }

        g2.setColor(focused ? UIColors.RING : UIColors.BORDER);
        g2.setStroke(new BasicStroke(1f));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIColors.RADIUS, UIColors.RADIUS);

        g2.dispose();
    }
}
