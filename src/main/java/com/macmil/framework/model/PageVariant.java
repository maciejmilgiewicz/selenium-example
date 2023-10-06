package com.macmil.framework.model;

/**
 * Represents conditions that a page variant needs to meet
 */
@FunctionalInterface
public interface PageVariant {

    /**
     * Applies conditions that need to be met by a page variant
     * @return result showing if conditions were met
     */
    boolean applies();
}
