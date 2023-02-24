package ui.courses;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import ru.oshkin.annotations.Driver;
import ru.oshkin.extensions.UIExtension;
import ru.oshkin.pages.CoursePage;
import ru.oshkin.pages.MainPage;
import ru.oshkin.util.TimeStart;

@ExtendWith(UIExtension.class)
public class StartTimeCoursesTest {

    @Driver
    public WebDriver driver;

    @Test
    public void getEarliestCourse() {
        new MainPage(driver).open();
        new CoursePage(driver)
                .getCourseByStartTime(TimeStart.EARLIEST)
                //   .highlightElement()
                .moveAndClick();
    }

    @Test
    public void getLatestCourse() {
        new MainPage(driver).open();
        new CoursePage(driver)
                .getCourseByStartTime(TimeStart.LATEST)
                //   .highlightElement()
                .moveAndClick();
    }

}
