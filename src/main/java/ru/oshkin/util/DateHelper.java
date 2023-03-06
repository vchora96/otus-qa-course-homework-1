package ru.oshkin.util;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateHelper {
        //Пример "С 7 марта"
        public static LocalDate toLocalDate(String date) {
                // Получаем день старта курса
                Pattern dayPattern = Pattern.compile("[0-3]?[0-9]");
                Matcher dayMatcher = dayPattern.matcher(date);
                int intDay = 1;
                if (dayMatcher.find()) {
                        intDay = Integer.parseInt(dayMatcher.group());
                }
                // Получаем месяц старта курса
                Pattern monthPattern = Pattern.compile("\\b(янв)|(фев)|(мар)|(апр)|(мая)|(июн)|(июл)|(авг)|(сен)|(окт)|(ноя)|(дек)");
                Matcher monthMatcher = monthPattern.matcher(date);
                int intMonthValue = 1;
                if (monthMatcher.find()) {
                        intMonthValue = Month.get(monthMatcher.group()).getNumber();
                }

                // Получаем год старта курса
                Pattern yearPattern = Pattern.compile("202[3-9]");
                Matcher yearMatcher = yearPattern.matcher(date);
                int intYearValue = LocalDate.now().getYear();
                if (yearMatcher.find()) {
                        intYearValue = Integer.parseInt(yearMatcher.group());
                }

                return LocalDate.of(
                        intYearValue,
                        intMonthValue,
                        intDay
                );
        }
}
