package ru.ibs.appline.framework.managers;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

import static ru.ibs.appline.framework.utils.PropsConst.*;

public class DriverManager {

    private WebDriver driver;

    private static DriverManager INSTANCE = null;

    private final TestPropManager props = TestPropManager.getINSTANCE();

    private DriverManager() {
    }

    public static DriverManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DriverManager();
        }
        return INSTANCE;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            initDriver();
        }
        return driver;
    }

    private void initDriver() {
        switch (props.getProperty(TYPE_BROWSER)) {
            case "remoteChrome":
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("84.0");
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", false);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://51.250.100.60:4444/wd/hub").toURL(),
                            capabilities
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "remoteOpera":
                DesiredCapabilities capabilitiesOpera = new DesiredCapabilities();
                capabilitiesOpera.setBrowserName("opera");
                capabilitiesOpera.setVersion("69.0");
                capabilitiesOpera.setCapability("enableVNC", true);
                capabilitiesOpera.setCapability("enableVideo", false);
                try {
                    driver = new RemoteWebDriver(
                            URI.create("http://51.250.100.60:4444/wd/hub").toURL(),
                            capabilitiesOpera
                    );
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty(PATH_CHROME_DRIVER_WIDOWS));
                driver = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", props.getProperty(PATH_EDGE_DRIVER_WIDOWS));
                EdgeDriverService service = EdgeDriverService.createDefaultService();
                driver = new EdgeDriver(service);
                break;
            case "opera":
                System.setProperty("webdriver.opera.driver", props.getProperty(PATH_OPERA_DRIVER_WIDOWS));
                driver = new OperaDriver();
                break;
            default:
                Assertions.fail("Типа браузера " + props.getProperty(TYPE_BROWSER) + " не существует во фреймворке");
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

}
