package ru.ibs.appline.framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class StartPage extends BasePage {

    @FindBy(xpath = "//button[@class='kitt-cookie-warning__close']")
    private WebElement cookiesBtnClose;

    @FindBy(xpath = "//h2[contains(@class,'ps-carousel__header ps-carousel__header_hidden_mob')]")
    private WebElement webElementTitle;

    @FindBy(xpath = "//li[@class='kitt-top-menu__item kitt-top-menu__item_first']")
    private List<WebElement> firstMenu;

    private List<WebElement> secondMenu;

    public void closeCookies() {
        waitToClickable(cookiesBtnClose).click();
    }


    public void checkOpenPage() {
        Assertions.assertEquals("Рекомендовано для вас", waitUtilElementToBeVisible(webElementTitle).getText(), "Текст заголовка не соответветствует ожидаемому");
    }

    public void turnFirstMenu(String nameMenu) {
        boolean flagFind = false;
        waitUtilElementToBeVisible(firstMenu.get(firstMenu.size() - 1));
        for (WebElement menu : firstMenu) {
            if (menu.getText().contains(nameMenu)) {
                waitToClickable(menu).click();
                secondMenu = menu.findElements(By.xpath(".//a[@class='kitt-top-menu__link kitt-top-menu__link_second']"));
                flagFind = true;
                break;
            }
        }
        Assertions.assertTrue(flagFind, "Не был найден элемент меню с " + nameMenu);
    }

    public void turnSecondMenu(String nameMenu) {
        boolean flagFind = false;
        waitUtilElementToBeVisible(secondMenu.get(secondMenu.size() - 1));
        for (WebElement menu : secondMenu) {
            if (menu.getText().contains(nameMenu)) {
                waitToClickable(menu).click();
                flagFind = true;
                break;
            }
        }
        Assertions.assertTrue(flagFind, "Не был найден элемент подменю с " + nameMenu);
    }
}
