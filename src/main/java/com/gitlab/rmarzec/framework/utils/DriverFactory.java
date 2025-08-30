package com.gitlab.rmarzec.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public WebDriver initDriver(String browser) {
        WebDriver webDriver;
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.getInstance(FirefoxDriver.class).driverVersion("0.36.0").setup();
            webDriver = new FirefoxDriver();
            tlDriver.set(webDriver);
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");
            options.addArguments("--disable-notifications");
            webDriver = new ChromeDriver(options);
            tlDriver.set(webDriver);
        }
        return getDriver();
    }

    public static synchronized void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
