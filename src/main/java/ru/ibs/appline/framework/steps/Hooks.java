
package ru.ibs.appline.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ibs.appline.framework.managers.InitManager;


public class Hooks {
    private static final Logger logger = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void before() {
        logger.info("Запуск браузера");
        InitManager.initFramework();
    }

    @After
    public static void After() {
        logger.info("Отключение браузера");
        InitManager.quitFramework();
    }
}