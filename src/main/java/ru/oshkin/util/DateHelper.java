package ru.oshkin.util;

import java.time.LocalDate;

public class DateHelper {
    public static LocalDate toLocalDate(String date) {
        String[] elements = date.split(" ");
        switch (elements.length) {
            case 3:
                //получаем стартовую дату для популярных курсов
                int popCourseStartDay = Integer.parseInt(elements[1]);
                String popCourseStartMonth = elements[2].substring(0, 3);
                int intMonthValue =  Month.get(popCourseStartMonth).getNumber();
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
                        Month.get(specializationStartMonth).getNumber(), //"фев" -> 2
                        specializationStartDay
                );
            case 6:
                int year = Integer.parseInt(elements[2]);
                return LocalDate.of(
                        year,
                        Month.get(elements[1].substring(0, 3)).getNumber(), //"фев" -> 2
                        1
                );
            default:
                throw new UnsupportedOperationException("Не удалось распознать дату: " + date);
        }
    }
}
