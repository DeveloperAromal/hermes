package com.example.ui.components;

import java.awt.Color;


public class UIColors {

    // Page / window background
    public static final Color BACKGROUND      = new Color(0x09, 0x09, 0x0B);

    // Card / surface background (inputs, panels)
    public static final Color CARD            = new Color(0x18, 0x18, 0x1B);

    // Borders
    public static final Color BORDER          = new Color(0x27, 0x27, 0x2A);
    public static final Color BORDER_HOVER    = new Color(0x3F, 0x3F, 0x46);

    // Focus ring (shadcn uses a soft ring around the border color)
    public static final Color RING            = new Color(0xD4, 0xD4, 0xD8);

    // Text
    public static final Color FOREGROUND      = new Color(0xFA, 0xFA, 0xFA);
    public static final Color MUTED_FOREGROUND = new Color(0xA1, 0xA1, 0xAA);
    public static final Color PLACEHOLDER     = new Color(0x71, 0x71, 0x7A);

    // Primary button (white bg, near-black text -- shadcn default variant)
    public static final Color PRIMARY         = new Color(0xFA, 0xFA, 0xFA);
    public static final Color PRIMARY_HOVER   = new Color(0xE4, 0xE4, 0xE7);
    public static final Color PRIMARY_FG      = new Color(0x18, 0x18, 0x1B);

    // Secondary / outline button
    public static final Color SECONDARY       = new Color(0x27, 0x27, 0x2A);
    public static final Color SECONDARY_HOVER = new Color(0x3F, 0x3F, 0x46);

    // Destructive
    public static final Color DESTRUCTIVE       = new Color(0xEF, 0x44, 0x44);
    public static final Color DESTRUCTIVE_HOVER = new Color(0xDC, 0x26, 0x26);

    // Accent used for toggles / checkboxes when "on"
    public static final Color ACCENT          = new Color(0xFA, 0xFA, 0xFA);

    public static final int RADIUS = 10; // shadcn ~0.5rem corner radius
}
