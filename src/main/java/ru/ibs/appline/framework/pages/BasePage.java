package ru.ibs.appline.framework.pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.ibs.appline.framework.managers.DriverManager;
import ru.ibs.appline.framework.managers.PageManager;
import ru.ibs.appline.framework.managers.TestPropManager;
import ru.ibs.appline.framework.utils.PropsConst;

import java.time.Duration;

public class BasePage {

    protected final TestPropManager props = TestPropManager.getINSTANCE();
    protected DriverManager driverManager = DriverManager.getINSTANCE();
    protected PageManager pageManager = PageManager.getINSTANCE();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(),
            Duration.ofSeconds(Integer.parseInt(props.getProperty(PropsConst.DURATION_TIMEOUT))));

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    /**
     * Явное ожидание того что элемент станет кликабельным
     *
     * @param webElement - веб элемент который мы ожидаем что будет  виден на странице
     */
    protected WebElement waitToClickable(WebElement webElement) {
        WebElement x = wait.until(ExpectedConditions.elementToBeClickable(webElement));

        return x;
    }

    /**
     * Явное ожидание того что элемент станет видемым
     *
     * @param element - веб элемент который мы ожидаем что будет  виден на странице
     */
    protected WebElement waitUtilElementToBeVisible(WebElement element) {
        WebElement x = wait.until(ExpectedConditions.visibilityOf(element));
        return x;
    }
}
