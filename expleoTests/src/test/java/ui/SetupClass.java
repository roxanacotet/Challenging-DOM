package ui;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import static ui.Driver.getChromeDriver;

public class SetupClass {

    public static final String BASE_URL = "https://the-internet.herokuapp.com/challenging_dom";
    public static final  String PAGE_TITLE = "Challenging DOM";
    public static final String gitLinkPage = "https://github.com/saucelabs/the-internet";
    static WebDriver driver;

    @BeforeAll
    static void setup() {
        driver = getChromeDriver();
        driver.get(BASE_URL);
    }

    @AfterAll
    static void cleanup(){
        driver.quit();
    }
}
