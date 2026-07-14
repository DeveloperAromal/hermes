package com.example.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToggleSwitch extends JComponent {

    private boolean selected = false;
    private final int width = 40;
    private final int height = 22;

    public ToggleSwitch() {
        setPreferredSize(new Dimension(width, height));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setOpaque(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setSelected(!selected);
            }
        });
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
        firePropertyChange("selected", !selected, selected);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Color track = selected ? UIColors.ACCENT : UIColors.SECONDARY;
        g2.setColor(track);
        g2.fillRoundRect(0, 0, width, height, height, height);

        int knobDiameter = height - 4;
        int knobX = selected ? width - knobDiameter - 2 : 2;
        g2.setColor(selected ? UIColors.PRIMARY_FG : UIColors.MUTED_FOREGROUND);
        g2.fillOval(knobX, 2, knobDiameter, knobDiameter);

        g2.dispose();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }
}
