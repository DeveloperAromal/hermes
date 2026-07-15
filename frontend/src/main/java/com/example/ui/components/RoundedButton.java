package com.example.ui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class RoundedButton extends JButton {

    public enum Variant { PRIMARY, SECONDARY, OUTLINE, DESTRUCTIVE, GHOST }

    private final Variant variant;
    private boolean hover = false;
    private boolean pressedState = false;

    public RoundedButton(String text) {
        this(text, Variant.PRIMARY);
    }

    public RoundedButton(String text, Variant variant) {
        super(text);
        this.variant = variant;

        setFont(getFont().deriveFont(Font.PLAIN, 13f));
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(new EmptyBorder(9, 18, 9, 18));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setForeground(textColor());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pressedState = true;
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressedState = false;
                repaint();
            }
        });
    }

    private Color textColor() {
        switch (variant) {
            case PRIMARY:
                return UIColors.PRIMARY_FG;
            case DESTRUCTIVE:
                return UIColors.FOREGROUND;
            default:
                return UIColors.FOREGROUND;
        }
    }

    private Color fillColor() {
        switch (variant) {
            case PRIMARY:
                return pressedState || hover ? UIColors.PRIMARY_HOVER : UIColors.PRIMARY;
            case SECONDARY:
                return pressedState || hover ? UIColors.SECONDARY_HOVER : UIColors.SECONDARY;
            case DESTRUCTIVE:
                return pressedState || hover ? UIColors.DESTRUCTIVE_HOVER : UIColors.DESTRUCTIVE;
            case OUTLINE:
            case GHOST:
            default:
                return hover ? new Color(255, 255, 255, 18) : new Color(0, 0, 0, 0);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(fillColor());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), UIColors.RADIUS, UIColors.RADIUS);

        g2.dispose();
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        if (variant != Variant.OUTLINE) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(hover ? UIColors.BORDER_HOVER : UIColors.BORDER);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, UIColors.RADIUS, UIColors.RADIUS);

        g2.dispose();
    }
}
