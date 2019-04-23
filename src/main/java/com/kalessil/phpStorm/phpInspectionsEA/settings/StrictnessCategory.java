package com.kalessil.phpStorm.phpInspectionsEA.settings;

import org.jetbrains.annotations.NotNull;

public enum StrictnessCategory {
    STRICTNESS_CATEGORY_SECURITY("Security"),
    STRICTNESS_CATEGORY_PROBABLE_BUGS("Probable bugs"),
    STRICTNESS_CATEGORY_PERFORMANCE("Performance"),
    STRICTNESS_CATEGORY_ARCHITECTURE("Architecture"),
    STRICTNESS_CATEGORY_CONTROL_FLOW("Control flow"),
    STRICTNESS_CATEGORY_LANGUAGE_LEVEL_MIGRATION("Language level migration"),
    STRICTNESS_CATEGORY_CODE_STYLE("Code style"),
    STRICTNESS_CATEGORY_UNUSED("Unused"),

    STRICTNESS_CATEGORY_CONFUSING_CONSTRUCTS("Confusing constructs");

    private final String value;

    StrictnessCategory(@NotNull String value) {
        this.value = value;
    }
}
