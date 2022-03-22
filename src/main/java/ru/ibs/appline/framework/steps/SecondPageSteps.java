package ru.ibs.appline.framework.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import ru.ibs.appline.framework.managers.PageManager;

import java.util.List;
import java.util.Map;

public class SecondPageSteps {

    private final PageManager pageManager = PageManager.getINSTANCE();

    @И("^Проверка что это вторая страница$")
    public void checkOpenPage() {
        pageManager.getSecondPage().checkOpenPage();
    }

    @И("^Проверяем поля формы:$")
    public void checkField(DataTable mapFieldsAndValue) {
        mapFieldsAndValue.asMap(String.class, Integer.class).forEach((key, value) ->
                pageManager.getSecondPage().checkField((String) key, (Integer) value));
    }

    @И("^Заполняем форму:$")
    public void fillForm(DataTable arg) {
        List<Map<String, String>> table = arg.asMaps(String.class, String.class);
        table.forEach(t -> {
            if (t.get("тип").equals("поле")) {
                int value = 0;
                try {
                    value = Integer.valueOf(t.get("значение"));
                } catch (NumberFormatException e) {
                    Assertions.fail("Для тип \"поле\" ожидалось число типа int, а получено " + t.get("значение"));
                }
                pageManager.getSecondPage().fillField(t.get("Наименование"), value);
            } else if (t.get("тип").equals("чекбокс")) {
                if (t.get("значение").equals("true")) {
                    pageManager.getSecondPage().fillCheckBox(t.get("Наименование"), true);
                } else if (t.get("значение").equals("false")) {
                    pageManager.getSecondPage().fillCheckBox(t.get("Наименование"), false);
                } else Assertions.fail("Ожидался \"true\" или \"false\", а получен " + t.get("тип"));
            } else Assertions.fail("Ожидался \"тип\" или \"чекбокс\", а получен " + t.get("тип"));
            System.out.print(t.get("Наименование"));
            System.out.println(t.get("значение"));
        });

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
