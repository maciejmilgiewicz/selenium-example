package com.macmil.framework.webdriver.browser;

import org.openqa.selenium.Dimension;

import java.util.Optional;

/**
 * Defines browser viewport dimension for multiple browser sizes to test reactive websites
 */
public enum BrowserViewport {
    DESKTOP(),
    MOBILE(new Dimension(768, 945));

    private Dimension dimension;

    BrowserViewport() {}

    BrowserViewport(Dimension dimension) { this.dimension = dimension; }

    public Optional<Dimension> getDimention() {
        return Optional.ofNullable(dimension);
    }
}
