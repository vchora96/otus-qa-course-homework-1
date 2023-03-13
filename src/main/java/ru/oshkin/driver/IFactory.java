package ru.oshkin.driver;

import org.openqa.selenium.WebDriver;
import ru.oshkin.exceptions.BrowserNotSupported;

public interface IFactory {

  WebDriver newDriver() throws BrowserNotSupported;

}
