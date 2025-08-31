package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.YTTile;
import com.gitlab.rmarzec.model.YoutubePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class Task4Test {

    @Test
    public void Task4Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver("chrome");
        YoutubePage youtubePage = new YoutubePage(webDriver);
        youtubePage.open();
        youtubePage.acceptCookies();
        youtubePage.clickShortIcon();
        System.out.println(youtubePage.getShortChannelName());
        youtubePage.clickLogoButton();
        youtubePage.searchFor("Live");
        List<WebElement> getVideos = youtubePage.getChannels(12);
        //Lista kafelkow
        List<YTTile> ytTileList = new ArrayList<YTTile>();

        fillYtList(getVideos, ytTileList);
        ytTileList.stream().filter(it -> !it.getLength().equals("live")).forEach(yTTile -> {
            System.out.println("title " + yTTile.getTitle() + "length " + yTTile.getLength());
        });
    }

    private static void fillYtList(List<WebElement> getVideos, List<YTTile> ytTileList) {
        for (WebElement webElement : getVideos) {
            YTTile yTTile = new YTTile();
            yTTile.setChannel(webElement.findElement(By.id("text-container")).getAttribute("innerText").trim());
            yTTile.setTitle(webElement.findElement(By.id("video-title")).getText());
            String duration = "live";
            try {
                duration = webElement.findElement(By.cssSelector("span.ytd-thumbnail-overlay-time-status-renderer")).getAttribute("innerText").trim();
                yTTile.setLength(duration);
            } catch (Exception e) {
                yTTile.setLength("live");
            }
            ytTileList.add(yTTile);
        }
    }

    @AfterTest
    public void quitDriver() {
        DriverFactory.quitDriver();
    }
}
