package com.example.ui.screens.dashboard;

import com.example.ui.components.UIColors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.function.Consumer;

public class Sidebar extends JPanel {

    public static class NavItem {
        final String id;
        final String label;
        final String icon;
        final String route;
        final List<NavItem> children;

        public NavItem(String id, String label, String icon, String route, List<NavItem> children) {
            this.id = id;
            this.label = label;
            this.icon = icon;
            this.route = route;
            this.children = children;
        }

        public NavItem(String id, String label, String icon, String route) {
            this(id, label, icon, route, null);
        }

        boolean hasChildren() {
            return children != null && !children.isEmpty();
        }
    }

    private final Consumer<String> onNavigate;
    private final JPanel itemsPanel;

    public Sidebar(Consumer<String> onNavigate) {
        this.onNavigate = onNavigate;

        setPreferredSize(new Dimension(300, 1000));
        setLayout(new BorderLayout());
        setBackground(UIColors.BACKGROUND);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(UIColors.BACKGROUND);

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(UIColors.BACKGROUND);

        add(scrollPane, BorderLayout.CENTER);

        buildNavItems(loadTabs(), itemsPanel, 0);
    }

    public Sidebar() {
        this(route -> System.out.println("Navigate to: " + route));
    }

    private void buildNavItems(List<NavItem> items, JPanel container, int depth) {
        for (NavItem item : items) {
            container.add(createNavRow(item, depth));

            if (item.hasChildren()) {
                JPanel childWrapper = new JPanel();
                childWrapper.setLayout(new BoxLayout(childWrapper, BoxLayout.Y_AXIS));
                childWrapper.setBackground(UIColors.BACKGROUND);
                childWrapper.setVisible(false);

                buildNavItems(item.children, childWrapper, depth + 1);
                container.add(childWrapper);

                JPanel finalRow = (JPanel) container.getComponent(container.getComponentCount() - 2);
                finalRow.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        childWrapper.setVisible(!childWrapper.isVisible());
                        childWrapper.revalidate();
                        childWrapper.repaint();
                    }
                });
            }
        }
    }

    private JPanel createNavRow(NavItem item, int depth) {
        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(UIColors.BACKGROUND);
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        row.setBorder(BorderFactory.createEmptyBorder(6, 16 + depth * 20, 6, 16));
        row.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel iconLabel = new JLabel(iconGlyphFor(item.icon));
        iconLabel.setFont(iconLabel.getFont().deriveFont(depth == 0 ? 16f : 13f));
        iconLabel.setForeground(Color.LIGHT_GRAY);
        iconLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JLabel textLabel = new JLabel(item.label);
        textLabel.setForeground(depth == 0 ? Color.WHITE : Color.LIGHT_GRAY);
        textLabel.setFont(textLabel.getFont().deriveFont(depth == 0 ? Font.BOLD : Font.PLAIN, depth == 0 ? 14f : 13f));

        JPanel left = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        left.setOpaque(false);
        left.add(iconLabel);
        left.add(textLabel);

        row.add(left, BorderLayout.WEST);

        if (item.hasChildren()) {
            JLabel chevron = new JLabel("\u25BE");
            chevron.setForeground(Color.GRAY);
            row.add(chevron, BorderLayout.EAST);
        }

        if (!item.hasChildren()) {
            row.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    onNavigate.accept(item.route);
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    row.setBackground(UIColors.CARD);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    row.setBackground(UIColors.BACKGROUND);
                }
            });
        }

        return row;
    }

    private String iconGlyphFor(String literal) {
        if (literal == null) return "•";
        if (literal.contains("dashboard")) return "▦";
        if (literal.contains("file-upload")) return "⬆";
        if (literal.contains("format-list")) return "☰";
        if (literal.contains("magnify")) return "🔍";
        if (literal.contains("file-tree")) return "🌳";
        if (literal.contains("lan")) return "🌐";
        if (literal.contains("robot")) return "🤖";
        if (literal.contains("shield")) return "🛡";
        if (literal.contains("file-document")) return "📄";
        if (literal.contains("text-box")) return "📋";
        if (literal.contains("account")) return "👤";
        if (literal.contains("cog")) return "⚙";
        return "•";
    }

    private List<NavItem> loadTabs() {
        return List.of(
            new NavItem("dashboard", "Dashboard", "mdi2v-view-dashboard-outline", "/dashboard"),
            new NavItem("samples", "Sample Management", "mdi2f-file-upload-outline", "/samples", List.of(
                new NavItem("sample-upload", "Upload Sample", null, "/samples/upload"),
                new NavItem("sample-list", "All Samples", null, "/samples/list")
            )),
            new NavItem("analysis-queue", "Analysis Queue", "mdi2f-format-list-checks", "/analysis/queue"),
            new NavItem("analysis-details", "Analysis Details", "mdi2m-magnify-scan", "/analysis/details", List.of(
                new NavItem("static-results", "Static Analysis", null, "/analysis/details/static"),
                new NavItem("disassembly-view", "Disassembly Viewer", null, "/analysis/details/disassembly"),
                new NavItem("yara-matches", "YARA Matches", null, "/analysis/details/yara")
            )),
            new NavItem("process-tree", "Process Tree Viewer", "mdi2f-file-tree-outline", "/analysis/process-tree"),
            new NavItem("network-activity", "Network Activity", "mdi2l-lan-connect", "/analysis/network"),
            new NavItem("ai-findings", "AI Findings", "mdi2r-robot-outline", "/analysis/ai-findings", List.of(
                new NavItem("ioc-extraction", "IOC Extraction", null, "/analysis/ai-findings/iocs"),
                new NavItem("malware-classification", "Malware Classification", null, "/analysis/ai-findings/classification"),
                new NavItem("attck-mapping", "ATT&CK Mapping", null, "/analysis/ai-findings/attck")
            )),
            new NavItem("threat-intel", "Threat Intelligence", "mdi2s-shield-search", "/threat-intel"),
            new NavItem("reports", "Report Viewer", "mdi2f-file-document-outline", "/reports"),
            new NavItem("logs", "System Logs", "mdi2t-text-box-search-outline", "/logs"),
            new NavItem("user-management", "User Management", "mdi2a-account-multiple-outline", "/admin/users"),
            new NavItem("settings", "Settings", "mdi2c-cog-outline", "/settings")
        );
    }
}