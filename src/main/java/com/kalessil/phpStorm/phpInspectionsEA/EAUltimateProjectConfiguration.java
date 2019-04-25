package com.kalessil.phpStorm.phpInspectionsEA;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import com.kalessil.phpStorm.phpInspectionsEA.settings.StrictnessCategory;
import org.jdom.Element;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/*
 * This file is part of the Php Inspections (EA Extended) package.
 *
 * (c) Vladimir Reznichenko <kalessil@gmail.com>
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

@State(name = "EAUltimateProjectSettings", storages = @Storage(file = "$PROJECT_CONFIG_DIR$/ea_ultimate.xml"))
public class EAUltimateProjectConfiguration extends AbstractProjectComponent implements PersistentStateComponent<Element> {
    private boolean analyzeOnlyModifiedFiles            = false;
    private Map<StrictnessCategory, Boolean> categories = new LinkedHashMap<>();

    protected EAUltimateProjectConfiguration(Project project) {
        super(project);
        Arrays.stream(StrictnessCategory.values()).forEach(category -> this.categories.put(category, true));
    }

    @Nullable
    @Override
    public Element getState() {
        final Element categoriesNode = new Element("categories");
        Arrays.stream(StrictnessCategory.values()).forEach(category -> {
            final Element categoryNode     = new Element(category.name());
            final boolean isCategoryActive = categories.containsKey(category) && categories.get(category);
            categoryNode.setAttribute("enabled", isCategoryActive ? "yes" : "no");
            categoriesNode.addContent(categoryNode);
        });

        final Element settingsNode                 = new Element("settings");
        final Element analyzeOnlyModifiedFilesNode = new Element("ANALYZE_ONLY_MODIFIED_FILES");
        analyzeOnlyModifiedFilesNode.setAttribute("value", analyzeOnlyModifiedFiles ? "yes" : "no");
        settingsNode.addContent(analyzeOnlyModifiedFilesNode);

        final Element configuration = new Element("any-name-here");
        configuration.addContent(categoriesNode);
        configuration.addContent(settingsNode);

        return configuration;
    }

    @Override
    public void loadState(@NotNull Element state) {
        final Element categoriesNode = state.getChild("categories");
        if (categoriesNode != null) {
            categoriesNode.getChildren().forEach(categoryNode -> {
                final boolean isCategoryActive    = !"no".equals(categoryNode.getAttributeValue("enabled"));
                final StrictnessCategory category = StrictnessCategory.valueOf(categoryNode.getName());
                categories.put(category, isCategoryActive);
            });
        }

        final Element settingsNode = state.getChild("settings");
        if (settingsNode != null) {
            final Element analyzeOnlyModifiedFilesNode = state.getChild("ANALYZE_ONLY_MODIFIED_FILES");
            if (analyzeOnlyModifiedFilesNode != null) {
                analyzeOnlyModifiedFiles = "yes".equals(analyzeOnlyModifiedFilesNode.getAttributeValue("enabled"));
            }
        }
    }
}
