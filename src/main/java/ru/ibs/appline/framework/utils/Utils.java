package ru.ibs.appline.framework.utils;

public class Utils {
    public static Integer convertToInteger(String text) {
        return Integer.parseInt(text.replaceAll("\\D", ""));
    }


    public static String convertToString(Integer i) {
        String iStr = i.toString();
        String result = "";
        int j = iStr.length();
        int k = j % 3;
        if (k > 0) {
            result = iStr.substring(0, k);
            for (int l = 0; l < j / 3; l++) {
                result = result + " " + iStr.substring(l * 3 + k, (l + 1) * 3 + k);
            }
        } else {
            for (int l = 0; l < j / 3; l++) {
                result = result + " " + iStr.substring(l * 3 - 1, (l + 1) * 3);
            }
        }
        return result;
    }
}
