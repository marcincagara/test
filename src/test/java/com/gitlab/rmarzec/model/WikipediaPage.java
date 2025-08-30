package com.gitlab.rmarzec.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class WikipediaPage extends BasePage {
    private final By languageButton = By.id("p-lang-btn-checkbox");
    private final By allLanguages = By.cssSelector("div.vector-menu-content a.interlanguage-link-target");

    public WikipediaPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://pl.wikipedia.org/wiki/Wiki");
        waitForPageToLoad();
    }

    public void clickLanguageButton() {
        driver.findElement(languageButton).click();
    }

    public List<WebElement> getAllLanguages() {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allLanguages));    }
}
