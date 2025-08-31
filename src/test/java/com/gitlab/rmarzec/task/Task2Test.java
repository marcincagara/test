package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.WikipediaPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


public class Task2Test {

    @Test
    public void Task2Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver("chrome");
        WikipediaPage wikipediaPage = new WikipediaPage(webDriver);
        wikipediaPage.open();
        wikipediaPage.clickLanguageButton();
        for (WebElement webElement : wikipediaPage.getAllLanguages()) {
            String lang = webElement.getAttribute("outerText");
            System.out.println(lang);
            if (lang.equals("English")) {
                System.out.println(webElement.getAttribute("origin"));
            }
        }
    }

    @AfterTest
    public void quitDriver() {
        DriverFactory.quitDriver();
    }
}
