package com.example.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class NavRow extends JPanel {

    private final JLabel iconLabel;
    private final JLabel textLabel;
    private final JLabel chevronLabel;
    private final Color baseTextColor;
    private final boolean expandable;

    private boolean hovered = false;
    private boolean active = false;

    private static final String CHEVRON_COLLAPSED = "\u25B8";
    private static final String CHEVRON_EXPANDED = "\u25BE";

    public NavRow(Icons.Kind iconKind, String label, int depth, boolean expandable) {
        this.expandable = expandable;

        setOpaque(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        baseTextColor = depth == 0 ? UIColors.FOREGROUND : UIColors.MUTED_FOREGROUND;

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        left.setOpaque(false);

        int iconSize = depth == 0 ? 17 : (iconKind == Icons.Kind.DOT ? 8 : 15);
        iconLabel = new JLabel(Icons.icon(iconKind, iconSize));
        iconLabel.setForeground(baseTextColor);
        left.add(iconLabel);

        textLabel = new JLabel(label);
        textLabel.setFont(textLabel.getFont().deriveFont(
                depth == 0 ? Font.BOLD : Font.PLAIN,
                depth == 0 ? 13.5f : 12.5f));
        textLabel.setForeground(baseTextColor);
        left.add(textLabel);

        add(left, BorderLayout.WEST);

        chevronLabel = new JLabel(CHEVRON_COLLAPSED);
        chevronLabel.setForeground(UIColors.MUTED_FOREGROUND);
        chevronLabel.setFont(chevronLabel.getFont().deriveFont(10f));
        if (expandable) {
            add(chevronLabel, BorderLayout.EAST);
        }

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hovered = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hovered = false;
                repaint();
            }
        });
    }

    public void onClick(Runnable action) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                action.run();
            }
        });
    }

    public void setActive(boolean active) {
        this.active = active;
        Color c = active ? UIColors.FOREGROUND : baseTextColor;
        textLabel.setForeground(c);
        iconLabel.setForeground(c); 
        repaint();
    }

    public boolean isActive() {
        return active;
    }

    public void setExpanded(boolean expanded) {
        if (expandable) {
            chevronLabel.setText(expanded ? CHEVRON_EXPANDED : CHEVRON_COLLAPSED);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (active) {
            g2.setColor(UIColors.SIDEBAR_ACTIVE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

            g2.setColor(UIColors.FOREGROUND);
            g2.fillRoundRect(0, 4, 3, Math.max(getHeight() - 8, 0), 3, 3);
        } else if (hovered) {
            g2.setColor(UIColors.SIDEBAR_HOVER);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
        }

        g2.dispose();
        super.paintComponent(g);
    }
}