package ru.oshkin.support;

import org.openqa.selenium.WebDriver;
import ru.oshkin.driver.impl.WebDriverFactory;

//@ScenarioScoped
public class GuiceScoped {
    public WebDriver driver = new WebDriverFactory().newDriver();
}
