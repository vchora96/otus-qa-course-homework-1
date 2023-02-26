package ru.oshkin.extensions;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.oshkin.annotations.Driver;
import ru.oshkin.driver.impl.DriverFactory;
import ru.oshkin.listeners.MouseListener;

import java.io.ByteArrayInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


public class UIExtension implements BeforeEachCallback, AfterEachCallback, AfterTestExecutionCallback {

        private EventFiringWebDriver driver = null;

        @Override
        public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
                boolean testResult = extensionContext.getExecutionException().isPresent();
                if (testResult) {
                        Allure.addAttachment("Failed screenshot", new ByteArrayInputStream(((TakesScreenshot) driver)
                                .getScreenshotAs(OutputType.BYTES)));
                }
        }

        private Set<Field> getAnnotatedFields(Class<? extends Annotation> annotation, ExtensionContext extensionContext) {
                Set<Field> set = new HashSet<>();
                Class<?> testClass = extensionContext.getTestClass().get();
                while (testClass != null) {
                        for (Field field : testClass.getDeclaredFields()) {
                                if (field.isAnnotationPresent(annotation)) {
                                        set.add(field);
                                }
                        }
                        testClass = testClass.getSuperclass();
                }
                return set;
        }

        @Override
        public void beforeEach(ExtensionContext extensionContext) {
                driver = new DriverFactory().getDriver();
                EventFiringWebDriver eventRecorder = new EventFiringWebDriver(driver);
                eventRecorder.register(new MouseListener());
                Set<Field> fields = getAnnotatedFields(Driver.class, extensionContext);

                for (Field field : fields) {
                        if (field.getType().getName().equals(WebDriver.class.getName())) {
                                // AccessController.doPrivileged((PrivilegedAction<Void>)
                                // () -> {
                                try {
                                        field.setAccessible(true);
                                        field.set(extensionContext.getTestInstance().get(), eventRecorder);
                                } catch (IllegalAccessException e) {
                                        throw new Error(String.format("Could not access or set webdriver in field: %s - is this field public?", field), e);
                                }
                                // return null;
                                //}
                                //);
                        }
                }
        }

        @Override
        public void afterEach(ExtensionContext extensionContext) {
                if (driver != null) {
                        driver.close();
                        driver.quit();
                }
        }
}
