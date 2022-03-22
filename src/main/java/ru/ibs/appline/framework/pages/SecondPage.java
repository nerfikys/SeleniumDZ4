package ru.ibs.appline.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.appline.framework.utils.Utils;

import java.util.List;

public class SecondPage extends BasePage {

    @FindBy(xpath = "//div[@class='kit-grid kit-grid_fixed']//h1")
    private List<WebElement> titlses;

    public void checkOpenPage() {
        Assertions.assertTrue(waitUtilElementToBeVisible(titlses.get(1)).getText().contains("готовые квартиры"), "Текст заголовка не соответветствует ожидаемому");
    }

    @FindBy(xpath = "//iframe")
    private WebElement iframe;

    @FindBy(xpath = "//label[@class='dc-input__label-6-1-0']")
    private List<WebElement> labels;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'collapsible-discount')]")
    private List<WebElement> discountOptions;

    @FindBy(xpath = "//div[@data-e2e-id='mland-calculator/main-results-block']//li")
    private List<WebElement> infoText;

    @FindBy(xpath = "//div[contains(@data-e2e-id,'result-income-required')]")
    private WebElement info;


    public void checkField(String key, int value) {
        boolean flagNotFound = true;
        Actions actions = new Actions(driverManager.getDriver());
        actions.moveToElement(iframe, 1, 1).build().perform();
        driverManager.getDriver().switchTo().frame(iframe);
        waitUtilElementToBeVisible(infoText.get(infoText.size() - 1));
        for (WebElement element : infoText) {
            String[] text = element.getText().split("\\n");
            if (text[0].contains(key)) {
                WebElement element1 = element.findElement(By.xpath(".//span//span"));
                String strValue = Utils.convertToString(value);
                try {
                    wait.until(ExpectedConditions.textToBePresentInElement(element1, strValue));
                } catch (TimeoutException e) {
                    Assertions.assertEquals(value, Utils.convertToInteger(element1.getText()), "Действительное значение " + text[0] + " не совадает с ожидаемым");
                }
                flagNotFound = false;
                break;
            }
        }
        if (flagNotFound) {
            String[] text = info.getText().split("\\n");
            if (text[0].contains(key)) {
                Assertions.assertEquals(value, Utils.convertToInteger(text[1]), "Действительное значение " + text[0] + " не совадает с ожидаемым");
                flagNotFound = false;
            }
        }
        Assertions.assertFalse(flagNotFound, "Элемент " + key + " не был найден");
        driverManager.getDriver().switchTo().defaultContent();
    }

    public void fillField(String key, int value) {
        boolean flagFound = false;
        Actions actions = new Actions(driverManager.getDriver());
        actions.moveToElement(iframe, 1, 1).build().perform();
        driverManager.getDriver().switchTo().frame(iframe);
        for (WebElement label : labels) {
            waitUtilElementToBeVisible(labels.get(labels.size() - 1));
            if (label.getText().contains(key)) {
                WebElement input = label.findElement(By.xpath("./../input"));
                input.sendKeys(Keys.chord(Keys.CONTROL, "a"), "" + value);
                flagFound = true;
                Assertions.assertEquals(Utils.convertToInteger(input.getAttribute("value")), value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        Assertions.assertTrue(flagFound, "Элемент " + key + " не был найден");
        driverManager.getDriver().switchTo().defaultContent();
    }

    public void fillCheckBox(String key, boolean value) {
        boolean flagFound = false;
        Actions actions = new Actions(driverManager.getDriver());
        actions.moveToElement(iframe).build().perform();
        driverManager.getDriver().switchTo().frame(iframe);
        for (WebElement label : discountOptions) {
            waitUtilElementToBeVisible(labels.get(labels.size() - 1));
            if (label.getText().contains(key)) {
                WebElement input = label.findElement(By.xpath(".//label"));
                if (value & !input.getAttribute("class").contains("checked")) {
                    waitToClickable(input).click();
                } else if ((!value) & (input.getAttribute("class").contains("checked"))) {
                    waitToClickable(input).click();
                }
                flagFound = true;
                break;
            }
        }
        Assertions.assertTrue(flagFound, "Элемент " + key + " не был найден");
        driverManager.getDriver().switchTo().defaultContent();
    }
}
