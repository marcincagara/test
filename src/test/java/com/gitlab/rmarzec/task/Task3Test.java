package com.gitlab.rmarzec.task;

import com.gitlab.rmarzec.framework.utils.DriverFactory;
import com.gitlab.rmarzec.model.GooglePage;
import com.gitlab.rmarzec.model.W3SchoolsPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Task3Test {

    @Test
    public void Task3Test() {
        DriverFactory driverFactory = new DriverFactory();
        WebDriver webDriver = driverFactory.initDriver("chrome");
        GooglePage googlePage = new GooglePage(webDriver);
        W3SchoolsPage w3SchoolsPage = new W3SchoolsPage(webDriver);
        googlePage.open();
        googlePage.acceptCookies();
        googlePage.search("HTML select tag - W3Schools");
        googlePage.luckySearch();
        w3SchoolsPage.validateUrl();
        w3SchoolsPage.acceptCookies();
        w3SchoolsPage.openTryItYourself();
        System.out.println("Header: " + w3SchoolsPage.getHeaderText());
        System.out.println("Selected: " + w3SchoolsPage.selectOpel());

    }

    @AfterTest
    public void quitDriver() {
        DriverFactory.quitDriver();
    }
}
