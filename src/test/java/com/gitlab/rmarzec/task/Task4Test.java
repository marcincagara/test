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
        //Lista kafelkow
        List<YTTile> ytTileList = new ArrayList<YTTile>();

        ytTileList = youtubePage.fillYtList();
        ytTileList.stream().filter(it -> !it.getLength().equals("live")).forEach(yTTile -> {
            System.out.println("title " + yTTile.getTitle() + "length " + yTTile.getLength());
        });
    }

    @AfterTest
    public void quitDriver() {
        DriverFactory.quitDriver();
    }
}
