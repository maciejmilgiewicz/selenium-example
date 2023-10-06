package com.macmil.utilities.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * General regex utilities
 */
public class RegexUtilities {

    /**
     * Searches for patterns within text
     * @param text text to search through
     * @param regex regex pattern to apply
     * @return List of String objects matching the regex pattern
     */
    public static List<String> findTextPatterns(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        List<String> results = new ArrayList<>();
        while(matcher.find()) {
            results.add(matcher.group());
        }
        return results;
    }
}
