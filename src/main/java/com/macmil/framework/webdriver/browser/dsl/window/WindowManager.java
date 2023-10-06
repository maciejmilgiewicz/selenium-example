package com.macmil.framework.webdriver.browser.dsl.window;

import com.google.common.collect.Iterables;
import lombok.AllArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Manages browser windows (tabs)
 */
@AllArgsConstructor
public class WindowManager {
    private static final long NEW_WINDOW_WAIT = 10;
    private static final long TIMEOUT = 20;

    private WebDriver driver;

    /**
     * Opens given URL in the browser and closes all other open windows
     * @param url URL to open
     */
    public void open(String url) {
        driver.get(url);
        closeOtherWindows();
    }

    private void closeOtherWindows() {
        String currentWindow = driver.getWindowHandle();
        driver.getWindowHandles().stream()
                .filter(w -> !w.equals(currentWindow))
                .forEach(w -> {
                    driver.switchTo().window(w);
                    driver.close();
                });
        driver.switchTo().window(currentWindow);
    }

    /**
     * Switches to the new browser window (tab)
     */
    public void switchToNewWindow() {
        driver.switchTo().window(Iterables.getLast(driver.getWindowHandles()));
    }

    /**
     * Waits for the new browser window (tab) to open
     */
    public void waitForNewWindow() {
        new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT)).until(d -> !d.getWindowHandle().equals(Iterables.getLast(d.getWindowHandles())));
        waitForWindowAction(NEW_WINDOW_WAIT);
    }

    /**
     * Waits for an arbitrary amount of time
     * @param seconds number of seconds to wait
     */
    public void waitForWindowAction(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
