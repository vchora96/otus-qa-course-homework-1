package ru.oshkin.courses.hooks;


import org.junit.jupiter.api.AfterEach;
import ru.oshkin.support.GuiceScoped;

public class Hooks {

    //@Inject
    private GuiceScoped guiceScoped;

    @AfterEach
    public void afterScenario() {
        if (guiceScoped.driver != null) {
            guiceScoped.driver.close();
            guiceScoped.driver.quit();
        }
    }
}
