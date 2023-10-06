package com.macmil.framework.model;

import com.typesafe.config.ConfigFactory;

import java.util.List;

/**
 * Page variants reader
 */
public final class PageVariantReader {
    private static final String PAGEVARIANTS = "pagevariants/pagevariants";

    private PageVariantReader() {}

    /**
     * Reads configuration file described in PAGEVARIANTS
     * @param page base class of the page from page object model for which variants are needed
     * @return page variants
     */
    public static List<String> getPageVariants(String page) {
        return ConfigFactory.parseResourcesAnySyntax(PAGEVARIANTS).getStringList(page);
    }
}
