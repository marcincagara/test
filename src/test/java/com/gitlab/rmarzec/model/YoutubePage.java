package com.gitlab.rmarzec.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class YoutubePage extends BasePage {
    private final By acceptCookies = By.xpath("//button[contains(@aria-label, 'Zaakceptuj')]");
    private final By shortIcon = By.xpath("//*[text()='Shorts']");
    private final By shortChannel = By.xpath("//*[contains(@class, 'ytReelChannelBarViewModelChannelName')]");
    private final By logoButton = By.id("logo");
    private final By search = By.xpath("//*[@role='combobox']");
    private final By channels = By.tagName("ytd-video-renderer");
    private final By channelName = By.id("text-container");
    private final By videoTitle= By.id("video-title");
    private final By filmDuration = By.cssSelector("span.ytd-thumbnail-overlay-time-status-renderer");

    public YoutubePage(WebDriver drive) {
        super(drive);
    }

    public void open() {
        driver.get("https://www.youtube.com/");
        waitForPageToLoad();
    }

    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
    }

    public void clickShortIcon() {
        clickWhenReady(shortIcon);
    }

    public String getShortChannelName() {
        return wait.until(ExpectedConditions.elementToBeClickable(shortChannel)).getText();
    }

    public void clickLogoButton() {
        wait.until(ExpectedConditions.elementToBeClickable(logoButton)).click();
    }

    public void searchFor(String searchText) {
        wait.until(ExpectedConditions.elementToBeClickable(search)).sendKeys(searchText + Keys.ENTER);
    }

    public List<WebElement> getChannels(int size) {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(channels, size));
        return driver.findElements(channels).stream().limit(size).toList();
    }

    public List<YTTile> fillYtList() {
        List<YTTile> ytTileList = new ArrayList<YTTile>();
        List<WebElement> getVideos = getChannels(12);
        for (WebElement webElement : getVideos) {
            YTTile yTTile = new YTTile();
            yTTile.setChannel(webElement.findElement(channelName).getAttribute("innerText").trim());
            yTTile.setTitle(webElement.findElement(videoTitle).getText());
            String duration = "live";
            try {
                duration = webElement.findElement(filmDuration).getAttribute("innerText").trim();
                yTTile.setLength(duration);
            } catch (Exception e) {
                yTTile.setLength(duration);
            }
            ytTileList.add(yTTile);
        }
        return ytTileList;
    }
}
