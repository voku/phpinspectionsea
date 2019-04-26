package com.kalessil.phpStorm.phpInspectionsEA;

import com.intellij.openapi.options.Configurable;
import com.kalessil.phpStorm.phpInspectionsEA.gui.OptionsComponent;
import com.kalessil.phpStorm.phpInspectionsEA.settings.ComparisonStyle;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/*
 * This file is part of the Php Inspections (EA Extended) package.
 *
 * (c) Vladimir Reznichenko <kalessil@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

public class EAUltimateApplicationConfiguration implements Configurable {
    private boolean COMPARISON_STYLE_REGULAR;
    private boolean COMPARISON_STYLE_YODA;

    @Nullable
    @Override
    public JComponent createComponent() {
        final EAUltimateApplicationSettings settings = EAUltimateApplicationSettings.getInstance();
        final ComparisonStyle comparisonStyle        = settings.getComparisonStyle();
        COMPARISON_STYLE_REGULAR                     = comparisonStyle == ComparisonStyle.REGULAR;
        COMPARISON_STYLE_YODA                        = comparisonStyle == ComparisonStyle.YODA;

        return OptionsComponent.create(component -> {
            /* comparison style */
            component.addPanel("Comparison code style", panel ->
                panel.delegateRadioCreation(radio -> {
                    radio.addOption("Regular comparison style", COMPARISON_STYLE_REGULAR, (isSelected) -> COMPARISON_STYLE_REGULAR = isSelected);
                    radio.addOption("Yoda comparison style", COMPARISON_STYLE_YODA, (isSelected) -> COMPARISON_STYLE_YODA = isSelected);
                }));
        });
    }

    @Override
    public boolean isModified() {
        final EAUltimateApplicationSettings settings = EAUltimateApplicationSettings.getInstance();
        return COMPARISON_STYLE_YODA != (settings.getComparisonStyle() == ComparisonStyle.YODA);
    }

    @Override
    public void apply() {
        final EAUltimateApplicationSettings settings = EAUltimateApplicationSettings.getInstance();
        settings.setComparisonStyle(COMPARISON_STYLE_REGULAR ? ComparisonStyle.REGULAR : ComparisonStyle.YODA);
    }

    @Override
    public void reset() {
        /* nothing should happen here as losing settings here extremely frustrating */
    }

    @Override
    public void disposeUIResources() {
        /* nothing to dispose so far */
    }

    @Nls
    @Override
    public String getDisplayName() {
        return "Php Inspections (EA Ultimate)";
    }

    @Nullable
    @NonNls
    public String getHelpTopic() {
        return null;
    }
}
