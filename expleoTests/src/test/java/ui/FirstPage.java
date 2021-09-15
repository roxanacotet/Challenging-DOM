package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ui.pages.FirstPage.highlightMethod;

public class FirstPage extends SetupClass {

    @Test
    void verifyPageTitle() {

        WebElement pageTitle = driver.findElement(By.cssSelector("#content > div > h3"));

        highlightMethod(driver, pageTitle);

        assertEquals(PAGE_TITLE, pageTitle.getText());
    }

    @Test
    void verifyThatTheBlueButtonIsDisplayedAndClickable() {

        String listButtonsLocator = "#content > div > div > div > div.large-2.columns";
        List<WebElement> buttonList = driver.findElements(By.cssSelector(listButtonsLocator));
        WebElement blueButton = null;
        for (WebElement button : buttonList) {
            blueButton = button.findElement(new By.ByClassName("button"));
        }

        highlightMethod(driver, blueButton);
        if (blueButton != null) {
            blueButton.click();
        }
    }


    @Test
    void verifyThatTheRedButtonIsDisplayedAndClickable() {

        String listButtonsLocator = "#content > div > div > div > div.large-2.columns";
        List<WebElement> buttonList = driver.findElements(By.cssSelector(listButtonsLocator));
        WebElement redButton = null;
        for (WebElement button : buttonList) {
            redButton = button.findElement(new By.ByClassName("alert"));
        }

        highlightMethod(driver, redButton);
        if (redButton != null) {
            redButton.click();
        }
    }

    @Test
    void verifyThatTheGreenButtonIsDisplayedAndClickable() {

        String listButtonsLocator = "#content > div > div > div > div.large-2.columns";
        List<WebElement> buttonList = driver.findElements(By.cssSelector(listButtonsLocator));
        WebElement greenButton = null;
        for (WebElement button : buttonList) {
            greenButton = button.findElement(new By.ByClassName("success"));
        }

        highlightMethod(driver, greenButton);
        if (greenButton != null) {
            greenButton.click();
        }
    }

    @Test
    void verifyThatEditAndDeleteButtonsRedirectToTheEditPage() {

        String editAndDeleteButtons = "//*[@id=\"content\"]/div/div/div/div[2]/table/tbody/tr";

        WebElement editButton;
        WebElement deleteButton;

        for (int i = 1; i <= 10; i++) {
            String editButtonLocator = editAndDeleteButtons + "[" + i + "]/td[7]/a[1]";
            String deleteButtonLocator = editAndDeleteButtons + "[" + i + "]/td[7]/a[2]";
            editButton = driver.findElement(By.xpath(editButtonLocator));
            deleteButton = driver.findElement(By.xpath(deleteButtonLocator));
            highlightMethod(driver, editButton);
            editButton.click();
            assertEquals(driver.getCurrentUrl(), BASE_URL + "#edit");

            highlightMethod(driver, deleteButton);
            deleteButton.click();
            assertEquals(driver.getCurrentUrl(), BASE_URL + "#delete");
        }
    }

    @Test
    void verifyTableIsDisplayed() {
        String tableLocator = "#content > div > div > div > div.large-10.columns > table";
        WebElement table = driver.findElement(By.cssSelector(tableLocator));

        highlightMethod(driver, table);
        assertTrue(table.isDisplayed());
    }

    @Test
    void countTableRows() {
        int count = 0;
        String tableLocator = "#content > div > div > div > div.large-10.columns > table > tbody > tr";
        List<WebElement> tableRows = driver.findElements(By.cssSelector(tableLocator));
        for (WebElement row : tableRows) {
            highlightMethod(driver, row);
            count++;
        }
        assertEquals(10, count);
    }

    @Test
    void countTableColumns() {
        int count = 0;
        String tableLocator = "//*[@id=\"content\"]/div/div/div/div[2]/table/thead/tr/th";
        List<WebElement> tableRows = driver.findElements(By.xpath(tableLocator));
        for (WebElement row : tableRows) {
            highlightMethod(driver, row);
            count++;
        }
        assertEquals(7, count);
    }

    @Test
    void verifyThatGitHubLinkGoesToTheCorrectPage() {

        String gitLinkLocator = "/html/body/div[2]/a/img";
        WebElement gitLink = driver.findElement(By.xpath(gitLinkLocator));

        highlightMethod(driver, gitLink);
        gitLink.click();
        String actualUrl = driver.getCurrentUrl();

        assertEquals(gitLinkPage.toString(), actualUrl);

        driver.get(BASE_URL);
    }

    @Test
    void verifyAnswerSectionIsDisplayed() {
        WebElement answerSection = driver.findElement(new By.ById("canvas"));
        highlightMethod(driver, answerSection);
        assertTrue(answerSection.isDisplayed());
    }

    @Test
    void verifyAnswerNumberCodeChangesWhenPageIsRefreshed() {
        String answerSection = driver.findElement(By.xpath("//div[@id=\"content\"]/script")).getAttribute("innerHTML");
        String answerCode = answerSection.substring(answerSection.indexOf("Answer"), answerSection.lastIndexOf("'"));
        driver.navigate().refresh();
        answerSection = driver.findElement(By.xpath("//div[@id=\"content\"]/script")).getAttribute("innerHTML");
        String secondAnswerCode = answerSection.substring(answerSection.indexOf("Answer"), answerSection.lastIndexOf("'"));
        assert !answerCode.equals(secondAnswerCode) : "The answer code number did not change";
    }
}
