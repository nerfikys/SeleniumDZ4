package ru.ibs.appline.framework.utils;


import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Allure;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.appline.framework.managers.DriverManager;


public class CucumberListenerWithScreenshot extends AllureCucumber5Jvm {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        EventHandler<TestStepFinished> testStepFinishedEventHandler = new EventHandler<TestStepFinished>() {
            @Override
            public void receive(TestStepFinished testStepFinished) {
                if (testStepFinished.getResult().getStatus().is(Status.FAILED)) {
                    Allure.getLifecycle().addAttachment("Fail", "image/png", ".png", ((TakesScreenshot) DriverManager.getINSTANCE().getDriver()).getScreenshotAs(OutputType.BYTES));
                }
            }
        };
        publisher.registerHandlerFor(TestStepFinished.class, testStepFinishedEventHandler);
        super.setEventPublisher(publisher);
    }
}
