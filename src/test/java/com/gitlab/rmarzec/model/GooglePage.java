package com.gitlab.rmarzec.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GooglePage extends BasePage {
    private final By acceptCookies = By.id("L2AGLb");
    private final By searchBox = By.name("q");
    private final By luckyButton = By.name("btnI");

    public GooglePage(WebDriver driver) {
        super(driver);
    }
    public void open() {
        driver.get("https://www.google.com/");
        waitForPageToLoad();
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
    }

    public void search(String text) {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        search.sendKeys(text);
    }

    public void luckySearch() {
        wait.until(ExpectedConditions.elementToBeClickable(luckyButton)).click();
    }
}
