package ru.oshkin.util;

import java.util.HashMap;
import java.util.Map;

public enum Month {
    JANUARY("янв", 1),
    FEBRUARY("фев", 2),
    MARCH("мар", 3),
    APRIL("апр", 4),
    MAY("май", 5),
    JUNE("июн", 6),
    JULY("июл", 7),
    AUGUST("авг", 8),
    SEPTEMBER("сен", 9),
    OCTOBER("окт", 10),
    NOVEMBER("ноя", 11),
    DECEMBER("дек", 12);

    private String title;
    private int number;

    private static final Map<String, Month> lookup = new HashMap<String,Month>();

    static {
        for (Month m: Month.values()){
            lookup.put(m.getTitle(), m);
        }
    }

    public static Month get(String abbreviation){
        return lookup.get(abbreviation);
    }

    Month(String title, int number) {
        this.title = title;
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public int getNumber() {
        return number;
    }
}
