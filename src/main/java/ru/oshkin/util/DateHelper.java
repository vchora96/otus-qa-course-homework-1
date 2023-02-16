package ru.oshkin.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DateHelper {
    public static LocalDate toLocalDate(String date) {
        Map<String, Integer> months = new HashMap<>();
        months.put("янв", 1);
        months.put("фев", 2);
        months.put("мар", 3);
        months.put("апр", 4);
        months.put("май", 5);
        months.put("июн", 6);
        months.put("июл", 7);
        months.put("авг", 8);
        months.put("сен", 9);
        months.put("окт", 10);
        months.put("ноя", 11);
        months.put("дек", 12);
        String[] elements = date.split(" ");
        switch (elements.length) {
            case 3:
                //получаем стартовую дату для популярных курсов
                int popCourseStartDay = Integer.parseInt(elements[1]);
                String popCourseStartMonth = elements[2].substring(0, 3);
                Integer intMonthValue = months.get(popCourseStartMonth);
                return LocalDate.of(
                        LocalDate.now().getYear(),
                        intMonthValue,
                        popCourseStartDay
                );
            case 4:
                //получаем стартовую дату начала специализаций
                int specializationStartDay = Integer.parseInt(elements[0]);
                String specializationStartMonth = elements[1].substring(0, 3);//фев
                return LocalDate.of(
                        LocalDate.now().getYear(),
                        months.get(specializationStartMonth), //"фев" -> 2
                        specializationStartDay
                );
            case 6:
                int year = Integer.parseInt(elements[2]);
                return LocalDate.of(
                        year,
                        months.get(elements[1].substring(0, 3)), //"фев" -> 2
                        1
                );
            default:
                throw new UnsupportedOperationException("Не удалось распознать дату: " + date);
        }
    }
}
