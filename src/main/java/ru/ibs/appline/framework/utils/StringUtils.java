package ru.ibs.appline.framework.utils;

public class StringUtils {
    public static Integer convertToInteger(String text) {
        return Integer.parseInt(text.replaceAll("\\D", ""));
    }
}
