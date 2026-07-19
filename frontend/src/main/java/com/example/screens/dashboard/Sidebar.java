package com.example.screens.dashboard;

import com.example.ui.components.Icons;
import com.example.ui.components.ModernScrollBarUI;
import com.example.ui.components.NavRow;
import com.example.ui.components.UIColors;

import javax.swing.*;
import java.awt.*;
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
    private NavRow activeRow;

    public Sidebar(Consumer<String> onNavigate) {
        this.onNavigate = onNavigate;

        setPreferredSize(new Dimension(280, 1000));
        setLayout(new BorderLayout());
        setBackground(UIColors.BACKGROUND);

        add(buildHeader(), BorderLayout.NORTH);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        itemsPanel.setBackground(UIColors.BACKGROUND);
        itemsPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        JScrollPane scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setBackground(UIColors.BACKGROUND);

        scrollPane.getVerticalScrollBar().setUI(new ModernScrollBarUI());
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        add(scrollPane, BorderLayout.CENTER);

        buildNavItems(loadTabs(), itemsPanel, 0);
    }

    public Sidebar() {
        this(route -> System.out.println("Navigate to: " + route));
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(UIColors.BACKGROUND);
        header.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, UIColors.BORDER),
                BorderFactory.createEmptyBorder(18, 20, 18, 20)
        ));

        JLabel brand = new JLabel("Malscope");
        brand.setAlignmentX(Component.LEFT_ALIGNMENT);
        brand.setForeground(UIColors.FOREGROUND);
        brand.setFont(brand.getFont().deriveFont(Font.BOLD, 17f));

        JLabel subtitle = new JLabel("Malware Analysis Console");
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitle.setForeground(UIColors.MUTED_FOREGROUND);
        subtitle.setFont(subtitle.getFont().deriveFont(11f));

        JPanel textStack = new JPanel();
        textStack.setOpaque(false);
        textStack.setLayout(new BoxLayout(textStack, BoxLayout.Y_AXIS));
        textStack.add(brand);
        textStack.add(Box.createVerticalStrut(3));
        textStack.add(subtitle);

        header.add(textStack, BorderLayout.WEST);
        return header;
    }

    private void buildNavItems(List<NavItem> items, JPanel container, int depth) {
        for (NavItem item : items) {
            NavRow row = new NavRow(Icons.kindForLiteral(item.icon), item.label, depth, item.hasChildren());
            container.add(wrapRow(row, depth));

            if (item.hasChildren()) {
                JPanel childWrapper = new JPanel();
                childWrapper.setLayout(new BoxLayout(childWrapper, BoxLayout.Y_AXIS));
                childWrapper.setOpaque(false);
                childWrapper.setVisible(false);

                buildNavItems(item.children, childWrapper, depth + 1);
                container.add(childWrapper);

                row.onClick(() -> {
                    boolean expanding = !childWrapper.isVisible();
                    childWrapper.setVisible(expanding);
                    row.setExpanded(expanding);
                    childWrapper.revalidate();
                    childWrapper.repaint();
                });
            } else {
                row.onClick(() -> {
                    if (activeRow != null) {
                        activeRow.setActive(false);
                    }
                    row.setActive(true);
                    activeRow = row;
                    onNavigate.accept(item.route);
                });
            }
        }
    }

    /** Adds outer margin + depth-based indentation around a NavRow pill. */
    private JPanel wrapRow(NavRow row, int depth) {
        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.setOpaque(false);
        wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        wrapper.setBorder(BorderFactory.createEmptyBorder(2, 10 + depth * 18, 2, 10));
        wrapper.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        wrapper.add(row, BorderLayout.CENTER);
        return wrapper;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sidebar Preview");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 760);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            frame.add(new Sidebar(), BorderLayout.WEST);

            JPanel content = new JPanel();
            content.setBackground(UIColors.CARD);
            frame.add(content, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }
}