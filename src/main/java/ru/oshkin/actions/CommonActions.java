package ru.oshkin.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.oshkin.waiters.StandardWaiter;

public abstract class CommonActions<T> {

        protected WebDriver driver;
        protected StandardWaiter standardWaiter;

        public CommonActions(WebDriver driver) {
                this.driver = driver;
                PageFactory.initElements(driver, this);

                standardWaiter = new StandardWaiter(driver);
        }
}
