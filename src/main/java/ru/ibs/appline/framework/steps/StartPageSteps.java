package ru.ibs.appline.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.appline.framework.managers.PageManager;

public class StartPageSteps {

    private final PageManager pageManager = PageManager.getINSTANCE();

    @И("^Закрываем куки$")
    public void closeCookies() {
        pageManager.getStartPage().closeCookies();
    }

    @И("^Проверка что это стартовая страница$")
    public void checkOpenPage() {
        pageManager.getStartPage().checkOpenPage();
    }

    @И("^Выбираем \"(.+)\" в главном меню$")
    public void selectBaseMenu(String nameMenu) {
        pageManager.getStartPage().turnFirstMenu(nameMenu);
    }

    @И("^Выбираем \"(.+)\" в подменю главного меню$")
    public void closeCookiesDialog(String nameSubMenu) {
        pageManager.getStartPage().turnSecondMenu(nameSubMenu);
    }

}
