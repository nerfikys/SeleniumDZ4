package ru.ibs.appline.framework;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm"},
        glue = {"ru/ibs/appline/framework/steps"},
        features = {"src/test/resources/"},
        tags = {"@Test"}
)
public class CucumberRunner {}