package utils;

import driver.MainDriver;
import io.qameta.allure.Allure;
import io.qameta.allure.listener.TestLifecycleListener;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.TestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class AllureScreenshotListener implements TestLifecycleListener {
    @Override
    public void beforeTestStop(TestResult result) {
        if (result.getStatus() == Status.FAILED || result.getStatus() == Status.BROKEN) {
            try {
                WebDriver driver = MainDriver.getDriver();
                if (driver != null) {
                    byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
                }
            } catch (Exception e) {
                System.err.println("Не вдалося зробити скріншот: " + e.getMessage());
            }
        }
    }
}
