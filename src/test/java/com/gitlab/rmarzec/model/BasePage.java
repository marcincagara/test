package com.gitlab.rmarzec.model;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver drive) {
        this.driver = drive;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForPageToLoad() {
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public void clickWhenReady(By locator) {
        int attempts = 0;
        int maxAttempts = 5;

        while (attempts < maxAttempts) {
            try {
                WebElement el = wait.until(
                        ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator))
                );
                el.click();
                return;

            } catch (StaleElementReferenceException | TimeoutException e) {
                attempts++;
            }
        }
        throw new RuntimeException("Not able to click element: " + locator);
    }
}
