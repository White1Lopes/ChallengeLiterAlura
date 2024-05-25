package com.whitelopes.liter.helper;

import java.util.HashMap;
import java.util.Map;

public class LanguageMap {
    public static Map<Integer, String> languages()
    {
        var languages = new HashMap<Integer, String>();
        languages.put(1, "en");
        languages.put(2, "pt");
        languages.put(3, "fr");
        languages.put(4, "fi");
        return languages;
    }
}
