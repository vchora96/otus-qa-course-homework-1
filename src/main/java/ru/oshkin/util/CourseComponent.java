package ru.oshkin.util;

import org.openqa.selenium.WebElement;

import java.time.LocalDate;

public class CourseComponent {
    private final WebElement webElement;
    private final LocalDate startDate;

    public CourseComponent(WebElement webElement, LocalDate localDate) {
        this.webElement = webElement;
        this.startDate = localDate;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
}
