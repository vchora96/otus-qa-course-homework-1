package ui.courses;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.oshkin.annotations.Driver;
import ru.oshkin.extensions.UIExtension;
import ru.oshkin.pages.CoursePage;
import ru.oshkin.pages.MainPage;


@ExtendWith(UIExtension.class)
public class CourseNameTest {

    @Driver
    public WebDriver driver;

    @Test
    public void getCourseByName() {
        new MainPage(driver).open();
        new CoursePage(driver)
                .getCourseByName("BI-аналитика")
                .moveAndClick();
    }
}
