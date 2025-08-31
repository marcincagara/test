package com.gitlab.rmarzec.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class W3SchoolsPage extends BasePage{

    private final String expectedUrl = "https://www.w3schools.com/tags/tag_select.asp";
    private final By acceptCookies = By.id("accept-choices");
    private final By tryIt = By.cssSelector("a.w3-btn[href*='tryit.asp']");
    private final By iframe = By.id("iframeResult");
    private final By header = By.tagName("h1");
    private final By selectTag = By.tagName("select");

    public W3SchoolsPage(WebDriver driver) {
    super(driver);
    }

    public void validateUrl() {
        if (!driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("Inny adres: " + driver.getCurrentUrl());
            driver.get(expectedUrl);
        }
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
    }

    public void openTryItYourself() {
        wait.until(ExpectedConditions.elementToBeClickable(tryIt)).click();
        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }
    }

    public String getHeaderText() {
        driver.switchTo().frame(wait.until(ExpectedConditions.visibilityOfElementLocated(iframe)));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(header)).getText();
    }

    public String selectOpel() {
        Select select = new Select(driver.findElement(selectTag));
        select.selectByVisibleText("Opel");
        WebElement selected = select.getFirstSelectedOption();
        return selected.getText() + " | value=" + selected.getAttribute("value");
    }
}
