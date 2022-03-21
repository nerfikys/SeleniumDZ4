package ru.ibs.appline.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.ibs.appline.framework.managers.PageManager;
import ru.ibs.appline.framework.pages.BasePage;

import java.util.List;

public class SecondPageSteps {

    private final PageManager pageManager = PageManager.getINSTANCE();

    @И("^Проверка что это вторая страница$")
    public void checkOpenPage() {
        pageManager.getSecondPage().checkOpenPage();
    }
    @И("^Проверяем поля формы:$")
    public void checkField(DataTable mapFieldsAndValue) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mapFieldsAndValue.asMap(String.class, Integer.class).forEach((key, value) ->
                pageManager.getSecondPage().checkField((String) key, (Integer) value));
    }

    @И("^Заполняем поля формы:$")
    public void fillFields(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, Integer.class).forEach((key, value) ->
                pageManager.getSecondPage().fillField((String) key, (Integer) value));
    }
    @И("^Заполняем чекбоксы формы:$")
    public void fillCheckBox(DataTable mapFieldsAndValue) {
        List<List<String>> rows = mapFieldsAndValue.asLists(String.class);
        for (List<String> columns : rows) {
            pageManager.getSecondPage().fillCheckBox(columns.get(0), Boolean.parseBoolean(columns.get(1)));
        }
    }
}
