package ru.ibs.appline.framework.steps;

import io.cucumber.java.ru.И;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ibs.appline.framework.managers.PageManager;

public class StartPageSteps {

    private final PageManager pageManager = PageManager.getINSTANCE();
    private static final Logger logger = LoggerFactory.getLogger(StartPageSteps.class);

    @И("^Закрываем куки$")
    public void closeCookies() {
        logger.info("Закрываем куки");
        pageManager.getStartPage().closeCookies();
    }

    @И("^Проверка что это стартовая страница$")
    public void checkOpenPage() {
        logger.info("Проверка стартовой страницы");
        pageManager.getStartPage().checkOpenPage();
    }

    @И("^Выбираем \"(.+)\" в главном меню$")
    public void selectBaseMenu(String nameMenu) {
        logger.info("Выбор " + nameMenu + " в главном меню");
        pageManager.getStartPage().turnFirstMenu(nameMenu);
    }

    @И("^Выбираем \"(.+)\" в подменю главного меню$")
    public void closeCookiesDialog(String nameSubMenu) {
        logger.info("Выбор " + nameSubMenu + " в подменю");
        pageManager.getStartPage().turnSecondMenu(nameSubMenu);
    }

}
