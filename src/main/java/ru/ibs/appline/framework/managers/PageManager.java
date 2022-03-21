package ru.ibs.appline.framework.managers;

import ru.ibs.appline.framework.pages.SecondPage;
import ru.ibs.appline.framework.pages.StartPage;

public class PageManager {
    private static PageManager INSTANCE = null;

    private StartPage startPage;
    private SecondPage secondPage;

    private PageManager() {
    }

    public static PageManager getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PageManager();
        }
        return INSTANCE;
    }

    public void dellPageManager() {
        INSTANCE = null;
    }

    public StartPage getStartPage() {
        if (startPage == null) {
            startPage = new StartPage();
        }
        return startPage;
    }

    public SecondPage getSecondPage() {
        if (secondPage == null) {
            secondPage = new SecondPage();
        }
        return secondPage;
    }

}
