package ru.oshkin.pages;

import org.openqa.selenium.WebDriver;
import ru.oshkin.annotations.UrlPrefix;

@UrlPrefix("/")
public class MainPage extends AnyPageAbs<MainPage> {
        public MainPage(WebDriver driver) {
                super(driver);
        }
}
