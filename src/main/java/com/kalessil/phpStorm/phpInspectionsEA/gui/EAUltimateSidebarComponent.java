package com.kalessil.phpStorm.phpInspectionsEA.gui;

import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowAnchor;
import com.intellij.openapi.wm.ToolWindowManager;
import com.kalessil.phpStorm.phpInspectionsEA.EAUltimateApplicationConfiguration;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.components.AbstractProjectComponent;

import javax.swing.*;

/*
 * This file is part of the Php Inspections (EA Extended) package.
 *
 * (c) Vladimir Reznichenko <kalessil@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

public class EAUltimateSidebarComponent extends AbstractProjectComponent {
    final private String TOOL_WINDOW_ID = "PHP Inspections";
    final private ToolWindowManager windowManager;

    protected EAUltimateSidebarComponent(@NotNull Project project) {
        super(project);
        this.windowManager = ToolWindowManager.getInstance(project);
    }

    @Override
    public void projectOpened() {
        if (!this.isInstantiated()) {
            final ToolWindow window = this.windowManager.registerToolWindow(TOOL_WINDOW_ID, this.buildPanel(), ToolWindowAnchor.RIGHT);
            window.setIcon(new ImageIcon(this.getClass().getResource("/logo_15x15.png")));
            window.setTitle("project settings");
        }
    }

    @NotNull
    private JPanel buildPanel() {
        return OptionsComponent.create(component -> {
            component.addPanel("License status",              panel -> {});
            component.addPanel("Settings management",         panel ->
                    panel.addHyperlink(
                            "File / Settings / Php Inspections (EA Ultimate)",
                            (event) -> ShowSettingsUtil.getInstance().showSettingsDialog(this.myProject, EAUltimateApplicationConfiguration.class)
                    )
            );
            component.addPanel("Strictness level", panel -> {
                panel.addCheckbox("Security (level 0)",                 true, (isSelected) -> {});
                panel.addCheckbox("Probable bugs (level 1)",            true, (isSelected) -> {});
                panel.addCheckbox("Performance (level 2)",              true, (isSelected) -> {});
                panel.addCheckbox("Architecture (level 3)",             true, (isSelected) -> {});
                panel.addCheckbox("Control flow (level 4)",             true, (isSelected) -> {});
                panel.addCheckbox("Language level migration (level 5)", true, (isSelected) -> {});
                panel.addCheckbox("Code style (level 6)",               true, (isSelected) -> {});
                panel.addCheckbox("Unused (level 7)",                   true, (isSelected) -> {});
            });
        });
    }

    @Override
    public void projectClosed() {
        if (this.isInstantiated()) {
            this.windowManager.unregisterToolWindow(TOOL_WINDOW_ID);
        }
    }

    private boolean isInstantiated() {
        return this.windowManager.getToolWindow(TOOL_WINDOW_ID) != null;
    }
}