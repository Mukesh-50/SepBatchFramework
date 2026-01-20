package com.ots.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.ots.dataprovider.ConfigUtility;

public class BrowserFactory {

    static WebDriver driver = null;

    public static WebDriver getDriver() {
        return driver;
    }

    public static WebDriver startBrowser(String browser, String appUrl) {

        boolean isCloud = ConfigUtility.readProperty("cloud").equalsIgnoreCase("true");

        if (isCloud) 
        {
            driver = startRemote(browser);
        } else {
            driver = startLocal(browser);
        }

        
        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("pageloadTime"))));

        driver.manage().timeouts().scriptTimeout(
                Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("scriptTimeOut"))));

        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("implicitWait"))));

        driver.manage().window().maximize();
        driver.get(appUrl);

        return driver;
    }

    private static WebDriver startLocal(String browser) {

        if (browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("GC") || browser.equalsIgnoreCase("Google Chrome")) {

            ChromeOptions opt = new ChromeOptions();

            if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) 
            {
                opt.addArguments("--headless=new");
            }

            return new ChromeDriver(opt);

        } else if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("FF") || browser.equalsIgnoreCase("Mozila Firefox")) {

            return new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {

            return new EdgeDriver();

        } else if (browser.equalsIgnoreCase("Safari")) {

            return new SafariDriver();

        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser
                    + " | Supported: Chrome, Firefox, Edge, Safari");
        }
    }

    private static WebDriver startRemote(String browser) {

        String hubIp = ConfigUtility.readProperty("hubip");
        String hubPort = ConfigUtility.readProperty("hubport");
        String gridUrl = "http://" + hubIp + ":" + hubPort + "/wd/hub";

        try {
            if (browser.equalsIgnoreCase("Chrome") || browser.equalsIgnoreCase("GC") || browser.equalsIgnoreCase("Google Chrome")) {

                ChromeOptions opt = new ChromeOptions();

                if (ConfigUtility.readProperty("headless").equalsIgnoreCase("true")) {
                    opt.addArguments("--headless=new");
                }

                return new RemoteWebDriver(new URL(gridUrl), opt);

            } else if (browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("FF") || browser.equalsIgnoreCase("Mozila Firefox")) {

                FirefoxOptions opt = new FirefoxOptions();
                return new RemoteWebDriver(new URL(gridUrl), opt);

            } else if (browser.equalsIgnoreCase("Edge") || browser.equalsIgnoreCase("Microsoft Edge")) {

                EdgeOptions opt = new EdgeOptions();
                return new RemoteWebDriver(new URL(gridUrl), opt);

            } else if (browser.equalsIgnoreCase("Safari")) {

                
                SafariOptions opt = new SafariOptions();
                return new RemoteWebDriver(new URL(gridUrl), opt);

            } else {
                throw new IllegalArgumentException("Unsupported browser for Grid: " + browser
                        + " | Supported: Chrome, Firefox, Edge, Safari");
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Grid URL: " + gridUrl, e);
        }
    }
}
