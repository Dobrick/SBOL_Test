package com.perfecttest.allure;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.File;
import java.io.IOException;
import static junit.framework.TestCase.*;

public class SimpleTest {
    public WebDriver driver;
    private ScreenshotHelper screenshotHelper;


    @Before
    public void openChrome() {
        driver = new FirefoxDriver();
        driver.get("http://sberbank.ru");
        screenshotHelper = new ScreenshotHelper();
    }

    @After
    public void saveScreenshotAndCloseBrowser() throws IOException {
        screenshotHelper.saveScreenshot("screenshot"+ driver.getTitle() +".png", driver);
        driver.close();
    }

    @Test
    public void openStartPage() {
        assertEquals("Title стартовой страницы должен быть «Сбербанк» - Частным клиентам", "«Сбербанк» - Частным клиентам",
                driver.getTitle());
    }

    @Test
    public void feedbackButtonTest() {
        if (driver.findElement(By.className("buttons_block_btn")) != null) {
            driver.findElement(By.className("buttons_block_btn")).click();
            assertEquals("После нажатия кнопки «Напишите нам» Title сменился на «Сбербанк» - Обсудить и оставить отзыв",
                    "«Сбербанк» - Обсудить и оставить отзыв", driver.getTitle());
        } else {
            assertFalse(false);
        }
    }

    @Test
    public void mobilebankPageTest() {
        driver.findElement(By.className("mobilebank")).click();
        assertEquals("Кнопка 'Мобильный банк' работает", "«Сбербанк России»  - Мобильный банк", driver.getTitle());
    }
}

class ScreenshotHelper {
    public void saveScreenshot(String screenshotFileName, WebDriver driver ) throws IOException{
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(screenshotFileName));
    }
}