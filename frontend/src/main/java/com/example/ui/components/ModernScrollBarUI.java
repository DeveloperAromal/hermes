package com.example.ui.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * A thin, dark, no-arrow-button scrollbar so JScrollPane stops looking like
 * default Windows/Metal chrome next to the flat components.
 *
 * Usage:
 *   scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
 *   scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
 */
public class ModernScrollBarUI extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        this.thumbColor = UIColors.BORDER_HOVER;
        this.trackColor = UIColors.BACKGROUND;
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        return zeroSizeButton();
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return zeroSizeButton();
    }

    private JButton zeroSizeButton() {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(0, 0));
        button.setMinimumSize(new Dimension(0, 0));
        button.setMaximumSize(new Dimension(0, 0));
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        // No visible track -- keeps the sidebar background flat.
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (thumbBounds.isEmpty() || !c.isEnabled()) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int pad = 4;
        g2.setColor(UIColors.BORDER_HOVER);
        g2.fillRoundRect(
                thumbBounds.x + pad, thumbBounds.y,
                Math.max(thumbBounds.width - pad * 2, 2), thumbBounds.height,
                8, 8);

        g2.dispose();
    }
}
