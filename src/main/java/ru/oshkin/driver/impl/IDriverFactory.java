package ru.oshkin.driver.impl;

import org.openqa.selenium.WebDriver;
import ru.oshkin.exceptions.DriverTypeNotSupported;


public interface IDriverFactory {
        WebDriver getDriver() throws DriverTypeNotSupported;
}
