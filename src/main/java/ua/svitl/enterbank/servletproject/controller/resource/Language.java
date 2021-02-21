package ua.svitl.enterbank.servletproject.controller.resource;

import java.util.HashSet;
import java.util.Set;

public class Language {
    private final Set<String> languages = new HashSet<>();

    public Language() {
        languages.add("en_GB");
        languages.add("uk_UA");
    }

    public boolean isLangExist(String string) {
        return languages.contains(string);
    }
}
