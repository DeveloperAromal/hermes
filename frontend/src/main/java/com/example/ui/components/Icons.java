package com.example.ui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class Icons {

    public enum Kind {
        DASHBOARD, UPLOAD, LIST, SEARCH, TREE, NETWORK, BOT,
        SHIELD, DOCUMENT, LOGS, USERS, SETTINGS, DOT
    }

    public static Kind kindForLiteral(String literal) {
        if (literal == null) return Kind.DOT;
        if (literal.contains("dashboard")) return Kind.DASHBOARD;
        if (literal.contains("file-upload")) return Kind.UPLOAD;
        if (literal.contains("format-list")) return Kind.LIST;
        if (literal.contains("magnify")) return Kind.SEARCH;
        if (literal.contains("file-tree")) return Kind.TREE;
        if (literal.contains("lan")) return Kind.NETWORK;
        if (literal.contains("robot")) return Kind.BOT;
        if (literal.contains("shield")) return Kind.SHIELD;
        if (literal.contains("file-document")) return Kind.DOCUMENT;
        if (literal.contains("text-box")) return Kind.LOGS;
        if (literal.contains("account")) return Kind.USERS;
        if (literal.contains("cog")) return Kind.SETTINGS;
        return Kind.DOT;
    }

    public static Icon icon(Kind kind, int size, Color color) {
        return new LineIcon(kind, size, color);
    }

    public static Icon icon(Kind kind, int size) {
        return new LineIcon(kind, size, null);
    }

    private static class LineIcon implements Icon {
        private final Kind kind;
        private final int size;
        private final Color fixedColor;

        LineIcon(Kind kind, int size, Color fixedColor) {
            this.kind = kind;
            this.size = size;
            this.fixedColor = fixedColor;
        }

        @Override
        public int getIconWidth() {
            return size;
        }

        @Override
        public int getIconHeight() {
            return size;
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

            g2.translate(x, y);
            g2.scale(size / 24.0, size / 24.0);
            g2.setColor(fixedColor != null ? fixedColor : c.getForeground());
            g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

            switch (kind) {
                case DASHBOARD: drawDashboard(g2); break;
                case UPLOAD: drawUpload(g2); break;
                case LIST: drawList(g2); break;
                case SEARCH: drawSearch(g2); break;
                case TREE: drawTree(g2); break;
                case NETWORK: drawNetwork(g2); break;
                case BOT: drawBot(g2); break;
                case SHIELD: drawShield(g2); break;
                case DOCUMENT: drawDocument(g2); break;
                case LOGS: drawLogs(g2); break;
                case USERS: drawUsers(g2); break;
                case SETTINGS: drawSettings(g2); break;
                case DOT: drawDot(g2); break;
            }

            g2.dispose();
        }

        // -- individual icon paths, all on a 24x24 grid --

        private void drawDashboard(Graphics2D g2) {
            g2.draw(round(3, 3, 8, 9));
            g2.draw(round(13, 3, 8, 5));
            g2.draw(round(13, 10, 8, 11));
            g2.draw(round(3, 14, 8, 7));
        }

        private void drawUpload(Graphics2D g2) {
            Path2D p = new Path2D.Double();
            p.moveTo(12, 16.5); p.lineTo(12, 3.5);
            p.moveTo(7, 8.5); p.lineTo(12, 3.5); p.lineTo(17, 8.5);
            g2.draw(p);
            Path2D tray = new Path2D.Double();
            tray.moveTo(4, 14.5);
            tray.lineTo(4, 18.5);
            tray.curveTo(4, 19.6, 4.9, 20.5, 6, 20.5);
            tray.lineTo(18, 20.5);
            tray.curveTo(19.1, 20.5, 20, 19.6, 20, 18.5);
            tray.lineTo(20, 14.5);
            g2.draw(tray);
        }

        private void drawList(Graphics2D g2) {
            for (double yy : new double[] {6, 12, 18}) {
                g2.draw(new java.awt.geom.Line2D.Double(9, yy, 21, yy));
                g2.draw(new Ellipse2D.Double(3.2, yy - 1, 2, 2));
            }
        }

        private void drawSearch(Graphics2D g2) {
            g2.draw(new Ellipse2D.Double(3, 3, 12, 12));
            g2.draw(new java.awt.geom.Line2D.Double(15.2, 15.2, 21, 21));
        }

        private void drawTree(Graphics2D g2) {
            Path2D p = new Path2D.Double();
            p.moveTo(6, 5); p.lineTo(6, 19);
            p.moveTo(6, 7); p.lineTo(13, 7);
            p.moveTo(6, 19); p.lineTo(13, 19);
            g2.draw(p);
            g2.draw(new Ellipse2D.Double(4, 3, 4, 4));
            g2.draw(new Ellipse2D.Double(13, 5, 4, 4));
            g2.draw(new Ellipse2D.Double(13, 17, 4, 4));
        }

        private void drawNetwork(Graphics2D g2) {
            g2.draw(new Ellipse2D.Double(3, 3, 18, 18));
            g2.draw(new java.awt.geom.Line2D.Double(3, 12, 21, 12));
            g2.draw(new java.awt.geom.Ellipse2D.Double(8, 3, 8, 18));
        }

        private void drawBot(Graphics2D g2) {
            g2.draw(round(4, 8, 16, 12));
            g2.draw(new java.awt.geom.Line2D.Double(12, 8, 12, 4));
            g2.draw(new Ellipse2D.Double(10.5, 2, 3, 3));
            g2.fill(new Ellipse2D.Double(8.5, 13, 2, 2));
            g2.fill(new Ellipse2D.Double(13.5, 13, 2, 2));
            g2.draw(new java.awt.geom.Line2D.Double(1, 13, 4, 13));
            g2.draw(new java.awt.geom.Line2D.Double(20, 13, 23, 13));
        }

        private void drawShield(Graphics2D g2) {
            Path2D p = new Path2D.Double();
            p.moveTo(12, 2.5);
            p.lineTo(19.5, 5.5);
            p.lineTo(19.5, 11.5);
            p.curveTo(19.5, 17, 16.2, 20, 12, 21.5);
            p.curveTo(7.8, 20, 4.5, 17, 4.5, 11.5);
            p.lineTo(4.5, 5.5);
            p.closePath();
            g2.draw(p);
        }

        private void drawDocument(Graphics2D g2) {
            Path2D p = new Path2D.Double();
            p.moveTo(6, 2.5); p.lineTo(14.5, 2.5); p.lineTo(19, 7); p.lineTo(19, 21.5);
            p.lineTo(6, 21.5); p.closePath();
            p.moveTo(14.5, 2.5); p.lineTo(14.5, 7); p.lineTo(19, 7);
            g2.draw(p);
            for (double yy : new double[] {12, 15.5, 19}) {
                g2.draw(new java.awt.geom.Line2D.Double(9, yy, 16, yy));
            }
        }

        private void drawLogs(Graphics2D g2) {
            g2.draw(round(3, 3, 18, 18));
            Path2D chevron = new Path2D.Double();
            chevron.moveTo(7, 9); chevron.lineTo(10, 12); chevron.lineTo(7, 15);
            g2.draw(chevron);
            g2.draw(new java.awt.geom.Line2D.Double(12, 15, 17, 15));
        }

        private void drawUsers(Graphics2D g2) {
            g2.draw(new Ellipse2D.Double(6, 4, 6, 6));
            g2.draw(new Arc2D.Double(2, 13, 14, 9, 20, 140, Arc2D.OPEN));
            g2.draw(new Ellipse2D.Double(15, 6, 5, 5));
            g2.draw(new Arc2D.Double(13, 14, 11, 8, 20, 130, Arc2D.OPEN));
        }

        private void drawSettings(Graphics2D g2) {
            g2.draw(new Ellipse2D.Double(8.5, 8.5, 7, 7));
            for (int i = 0; i < 8; i++) {
                double angle = Math.toRadians(i * 45);
                double innerR = 8, outerR = 11.5;
                double x1 = 12 + innerR * Math.cos(angle), y1 = 12 + innerR * Math.sin(angle);
                double x2 = 12 + outerR * Math.cos(angle), y2 = 12 + outerR * Math.sin(angle);
                g2.draw(new java.awt.geom.Line2D.Double(x1, y1, x2, y2));
            }
        }

        private void drawDot(Graphics2D g2) {
            g2.fill(new Ellipse2D.Double(10, 10, 4, 4));
        }

        private java.awt.geom.RoundRectangle2D round(double x, double y, double w, double h) {
            return new java.awt.geom.RoundRectangle2D.Double(x, y, w, h, 4, 4);
        }
    }
}