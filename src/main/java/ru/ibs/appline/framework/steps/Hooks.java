
package ru.ibs.appline.framework.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.appline.framework.managers.DriverManager;
import ru.ibs.appline.framework.managers.InitManager;


public class Hooks {

    @Before
    public void before() {
        InitManager.initFramework();
    }


    @After
    public void after(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) DriverManager.getINSTANCE().getDriver())
                    .getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png","Error");
        }
        InitManager.quitFramework();
    }
}