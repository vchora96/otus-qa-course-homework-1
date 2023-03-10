package ru.oshkin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ru.oshkin.data.TimeStart;
import ru.oshkin.util.CourseComponent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static ru.oshkin.util.DateHelper.toLocalDate;

public class CoursePage {
        public WebDriver driver;

        private WebElement element;

        public CoursePage(WebDriver driver) {
                this.driver = driver;
        }

        public CoursePage getCourseByName(String name) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                acceptCookies();
                List<WebElement> courses = driver.findElements(By.cssSelector(".lessons__new-item-image_full"));
                for (WebElement course : courses) {
                        String text = course.getAttribute("alt");
                        if (text.contains(name)) {
                                element = course;
                                return this;

                        }
                }
                throw new UnsupportedOperationException("Не удалось найти курс:" + name);
        }

        private void acceptCookies() {
                List<WebElement> cookies = driver.findElements(By.cssSelector(".cookies__button"));
                if (cookies.size() > 0) {
                        cookies.get(0).click();
                }
        }

        public CoursePage getCourseByStartTime(TimeStart time) {
                driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                //Список курсов с датами начала обучения
                List<CourseComponent> courses = new ArrayList<>();

                //популярные курсы
                List<WebElement> popCourses = driver.findElements(By.cssSelector(".lessons__new-item-start"));
                for (WebElement popCourse : popCourses) {
                        String startTime = popCourse.getText();
                        LocalDate localDate = toLocalDate(startTime);
                        courses.add(new CourseComponent(popCourse, localDate));
                }

                // специализации
                List<WebElement> specializations = driver.findElements(By.cssSelector(".lessons__new-item-time"));
                //пропускаем 3 первых блока (так как они относятся к популярным курсам)
                List<WebElement> webElements = specializations.subList(3, specializations.size());

                for (WebElement specialization : webElements) {
                        String startTime = specialization.getText();
                        LocalDate localDate = toLocalDate(startTime);
                        courses.add(new CourseComponent(specialization, localDate));
                }

                element = courses.stream().reduce((x, y) -> {
                        switch (time) {
                                case LATEST:
                                        if (x.getStartDate().isAfter(y.getStartDate())) {
                                                return x;
                                        } else {
                                                return y;
                                        }
                                case EARLIEST:
                                        if (x.getStartDate().isBefore(y.getStartDate())) {
                                                return x;
                                        } else {
                                                return y;
                                        }
                                default:
                                        throw new UnsupportedOperationException("Ошибка при определении типа поиска");
                        }
                }).orElseThrow(UnsupportedOperationException::new).getWebElement();
                return this;
        }

        public void moveAndClick() {
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click(element).build().perform();
        }
}
