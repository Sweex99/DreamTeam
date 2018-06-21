package applicationfolder.menu;

import applicationfolder.utils.DataBaseDriver;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.apache.commons.io.IOUtils.readLines;
import static org.junit.Assert.*;

public class MainMenuTest extends ApplicationTest {
    private Stage stage;

    @After
    public void tearDown() throws Exception {
        File file = new File("database/users.xml");
        file.delete();
    }

    @Test
    public void shouldBeCorrectAllSizesAndTranslatesInAppearanceMenu() throws Exception {
        Button goTesting = lookup("#btnGoTesting").query();
        Button goStudy = lookup("#btnGoStudy").query();
        Button createTest = lookup("#btnCreateTest").query();
        Button user = lookup("#btnUser").query();
        Button exit = lookup("#btnExit").query();
        Button swapAccount = lookup("#btnSwapAccount").query();
        Button settings = lookup("#btnSettings").query();
        Label title = lookup("#lblTitle").query();
        Label label12 = lookup("#lblLabel12").query();
        Label testTesting = lookup("#lblTestTesting").query();
        Label testStudy = lookup("#lblTestStudy").query();
        Label testCreateTest = lookup("#lblTestCreateTest").query();
        Label testUser = lookup("#lblTestUser").query();

        assertEquals(-110, goTesting.getTranslateX(), 0);
        assertEquals(-130, goTesting.getTranslateY(), 0);

        assertEquals(120, goStudy.getTranslateX(), 0);
        assertEquals(-28, goStudy.getTranslateY(), 0);

        assertEquals(-110, createTest.getTranslateX(), 0);
        assertEquals(70, createTest.getTranslateY(), 0);

        assertEquals(120, user.getTranslateX(), 0);
        assertEquals(165, user.getTranslateY(), 0);

        assertEquals(-275, exit.getTranslateX(), 0);
        assertEquals(-289, exit.getTranslateY(), 0);

        assertEquals(-303, swapAccount.getTranslateX(), 0);
        assertEquals(-289, swapAccount.getTranslateY(), 0);

        assertEquals(-330, settings.getTranslateX(), 0);
        assertEquals(-289, settings.getTranslateY(), 0);

        assertEquals(-330, title.getTranslateX(), 0);
        assertEquals(-270, title.getTranslateY(), 0);

        assertEquals(-380, label12.getTranslateX(), 0);
        assertEquals(-290, label12.getTranslateY(), 0);

        assertEquals(85, testTesting.getTranslateX(), 0);
        assertEquals(-130, testTesting.getTranslateY(), 0);

        assertEquals(-68, testStudy.getTranslateX(), 0);
        assertEquals(-28, testStudy.getTranslateY(), 0);

        assertEquals(86, testCreateTest.getTranslateX(), 0);
        assertEquals(70, testCreateTest.getTranslateY(), 0);

        assertEquals(-68, testUser.getTranslateX(), 0);
        assertEquals(165, testUser.getTranslateY(), 0);
    }

    @Test
    public void shouldBeCorrectAllSizesAndTranslatesInSettings() throws Exception {
        clickOn("#btnSettings");

        TextField youName = lookup("#txtYouName").query();
        Label labelName = lookup("#lblLabelName").query();
        TextField youLName = lookup("#txtYouLName").query();
        Label labelLName = lookup("#lblLabelLName").query();
        Label youData = lookup("#lblYouData").query();
        PasswordField youPassword = lookup("#pswYouPassword").query();
        PasswordField confirmPassword = lookup("#pswConfirmPassword").query();
        Label changeSystemData = lookup("#lblChangeSystemData").query();
        Label youPass = lookup("#lblYouPass").query();
        Label youConfPass = lookup("#lblYouConfPass").query();
        Label redLabelSystemData = lookup("#lblRedLabelSystemData").query();
        Label redLabelData = lookup("#lblRedLabelData").query();
        Label alertData = lookup("#lblAlertData").query();
        Button save = lookup("#btnSave").query();
        Button back = lookup("#btnBack").query();

        assertEquals(-200, youName.getTranslateX(), 0);
        assertEquals(-20, youName.getTranslateY(), 0);

        assertEquals(-300, labelName.getTranslateX(), 0);
        assertEquals(-50, labelName.getTranslateY(), 0);

        assertEquals(-200, youLName.getTranslateX(), 0);
        assertEquals(50, youLName.getTranslateY(), 0);

        assertEquals(-285, labelLName.getTranslateX(), 0);
        assertEquals(20, labelLName.getTranslateY(), 0);

        assertEquals(-200, youData.getTranslateX(), 0);
        assertEquals(-100, youData.getTranslateY(), 0);

        assertEquals(200, youPassword.getTranslateX(), 0);
        assertEquals(-22, youPassword.getTranslateY(), 0);

        assertEquals(200, confirmPassword.getTranslateX(), 0);
        assertEquals(50, confirmPassword.getTranslateY(), 0);

        assertEquals(200, changeSystemData.getTranslateX(), 0);
        assertEquals(-100, changeSystemData.getTranslateY(), 0);

        assertEquals(123, youPass.getTranslateX(), 0);
        assertEquals(-50, youPass.getTranslateY(), 0);

        assertEquals(140, youConfPass.getTranslateX(), 0);
        assertEquals(20, youConfPass.getTranslateY(), 0);

        assertEquals(200, redLabelSystemData.getTranslateX(), 0);

        assertEquals(-200, redLabelData.getTranslateX(), 0);

        assertEquals(-200, alertData.getTranslateY(), 0);

        assertEquals(200, save.getTranslateY(), 0);

        assertEquals(-430, back.getTranslateX(), 0);
        assertEquals(-280, back.getTranslateY(), 0);
    }

    @Test
    public void alertDataShouldBeNotVisible() throws Exception {
        clickOn("#btnSettings");

        Label alertData = lookup("#lblAlertData").query();
        assertFalse(alertData.isVisible());
    }

    @Test
    public void stageShouldBeNotResizable() throws Exception {
        clickOn("#btnSettings");

        assertFalse(stage.isResizable());
    }

    @Test
    public void shouldBeCorrectStyleOfLabels() throws Exception {
        clickOn("#btnSettings");

        Label alertData = lookup("#lblAlertData").query();
        final String expectedAlertDataStyle = "-fx-border-color: green;-fx-text-fill: green;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
        Label redLabelData = lookup("#lblRedLabelData").query();
        Label redLabelSystemData = lookup("#lblRedLabelSystemData").query();
        final String expectedRedLabelStyle = "-fx-border-width: 2px;-fx-border-color: white;-fx-pref-height: 250px;-fx-pref-width: 250px;";

        assertEquals(expectedAlertDataStyle, alertData.getStyle());
        assertEquals(expectedRedLabelStyle, redLabelData.getStyle());
        assertEquals(expectedRedLabelStyle, redLabelSystemData.getStyle());
    }

    @Test
    public void shouldRedirectToMainMenu() throws Exception {
        clickOn("#btnSettings");

        clickOn("#btnBack");
        assertEquals("Main menu", stage.getTitle());
    }

    @Test
    public void shouldChangeStyleWhenRedLabelDataClicked() throws Exception {
        clickOn("#btnSettings");

        final String expectedRedLabelDataStyle = "-fx-border-width: 2px;-fx-border-color: red;-fx-pref-height: 250px;-fx-pref-width: 250px;";
        final String expectedRedLabelSystemDataStyle = "-fx-border-width: 2px;-fx-border-color: white;-fx-pref-height: 250px;-fx-pref-width: 250px;";
        Label redLabelSystemData = lookup("#lblRedLabelSystemData").query();
        Label redLabelData = lookup("#lblRedLabelData").query();
        clickOn(redLabelData);

        assertEquals(expectedRedLabelDataStyle, redLabelData.getStyle());
        assertEquals(expectedRedLabelSystemDataStyle, redLabelSystemData.getStyle());
    }

    @Test
    public void shouldChangeStyleWhenRedLabelSystemDataClicked() throws Exception {
        clickOn("#btnSettings");

        final String expectedRedLabelDataStyle = "-fx-border-width: 2px;-fx-border-color: white;-fx-pref-height: 250px;-fx-pref-width: 250px;";
        final String expectedRedLabelSystemDataStyle = "-fx-border-width: 2px;-fx-border-color: red;-fx-pref-height: 250px;-fx-pref-width: 250px;";
        Label redLabelSystemData = lookup("#lblRedLabelSystemData").query();
        Label redLabelData = lookup("#lblRedLabelData").query();
        clickOn(redLabelSystemData);

        assertEquals(expectedRedLabelDataStyle, redLabelData.getStyle());
        assertEquals(expectedRedLabelSystemDataStyle, redLabelSystemData.getStyle());
    }

    @Test
    public void shouldShowErrorAboutFieldsAreNotChanged() throws Exception {
        clickOn("#btnSettings");

        final String expectedAlertDataStyle = "-fx-border-color: red;-fx-text-fill: red;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
        Label alertData = lookup("#lblAlertData").query();
        clickOn("#btnSave");

        assertTrue(alertData.isVisible());
        assertEquals("Ви не ввели нові дані", alertData.getText());
        assertEquals(expectedAlertDataStyle, alertData.getStyle());
    }

    @Test
    public void shouldChangePasswordAndShowAcceptedLabelAboutDataChanging() throws Exception {
        clickOn("#btnSettings");

        final String xmlName = "database/users.xml";
        final String expectedAlertDataStyle = "-fx-border-color: green;-fx-text-fill: green;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
        List<String> xmlBeforeChanging = readLines(new FileInputStream(xmlName), "UTF-8");
        Label alertData = lookup("#lblAlertData").query();
        clickOn("#pswYouPassword");
        write("pass");
        clickOn("#pswConfirmPassword");
        write("pass");
        clickOn("#btnSave");
        List<String> xmlAfterChanging = readLines(new FileInputStream(xmlName), "UTF-8");

        assertTrue(alertData.isVisible());
        assertEquals("Ваші дані збережені", alertData.getText());
        assertEquals(expectedAlertDataStyle, alertData.getStyle());
        assertNotEquals(xmlBeforeChanging, xmlAfterChanging);
    }

    @Test
    public void shouldChangeNicknameAndShowAcceptedLabelAboutDataChanging() throws Exception {
        clickOn("#btnSettings");

        final String xmlName = "database/users.xml";
        final String expectedAlertDataStyle = "-fx-border-color: green;-fx-text-fill: green;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
        List<String> xmlBeforeChanging = readLines(new FileInputStream(xmlName), "UTF-8");
        Label alertData = lookup("#lblAlertData").query();
        clickOn("#txtYouName");
        write("New");
        clickOn("#txtYouLName");
        write("New");
        clickOn("#btnSave");
        List<String> xmlAfterChanging = readLines(new FileInputStream(xmlName), "UTF-8");

        assertTrue(alertData.isVisible());
        assertEquals("Ваші дані збережені", alertData.getText());
        assertEquals(expectedAlertDataStyle, alertData.getStyle());
        assertNotEquals(xmlBeforeChanging, xmlAfterChanging);
    }

    @Test
    public void shouldChangePasswordAndNicknameAndShowAcceptedLabelAboutDataChanging() throws Exception {
        clickOn("#btnSettings");

        final String xmlName = "database/users.xml";
        final String expectedAlertDataStyle = "-fx-border-color: green;-fx-text-fill: green;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
        List<String> xmlBeforeChanging = readLines(new FileInputStream(xmlName), "UTF-8");
        Label alertData = lookup("#lblAlertData").query();
        clickOn("#txtYouName");
        write("New");
        clickOn("#txtYouLName");
        write("New");
        clickOn("#pswYouPassword");
        write("pass");
        clickOn("#pswConfirmPassword");
        write("pass");
        clickOn("#btnSave");
        List<String> xmlAfterChanging = readLines(new FileInputStream(xmlName), "UTF-8");

        assertNotEquals(xmlBeforeChanging, xmlAfterChanging);
        assertTrue(alertData.isVisible());
        assertEquals("Ваші дані збережені", alertData.getText());
        assertEquals(expectedAlertDataStyle, alertData.getStyle());
    }

    @Test
    public void labelsShouldBeWrapText() throws Exception {
        Label testTesting = lookup("#lblTestTesting").query();
        Label testStudy = lookup("#lblTestStudy").query();
        Label testCreateTest = lookup("#lblTestCreateTest").query();
        Label testUser = lookup("#lblTestUser").query();

        assertTrue(testTesting.isWrapText());
        assertTrue(testStudy.isWrapText());
        assertTrue(testCreateTest.isWrapText());
        assertTrue(testUser.isWrapText());
    }

    @Test
    public void nicknameSpaceShouldBeTestValueAndCorrectStyle() throws Exception {
        Label label12 = lookup("#lblLabel12").query();
        assertEquals("test test", label12.getText());
        assertEquals("-fx-text-fill: white;", label12.getStyle());
    }

    @Test
    public void titleShouldBeCorrectStyle() throws Exception {
        Label label12 = lookup("#lblTitle").query();
        assertEquals("-fx-text-fill: white", label12.getStyle());
    }

    @Test
    public void shouldThrowInfoAboutTesting() throws Exception {
            clickOn("#lblLabel12");
            assertNotNull(lookup("Кількість проведених тестувань: 0\nВідсоток правильний відповідей: 0 %").query());
            clickOn("OK");
    }

    @Test
    public void shouldChangeValuesOfTitleWhenMouseEnteredIntoSettings() throws Exception {
        Label title = lookup("#lblTitle").query();
        moveTo("#btnSettings");

        assertEquals("Настройки", title.getText());
        assertEquals(-330, title.getTranslateX(), 0);
        assertEquals(-270, title.getTranslateY(), 0);
    }

    @Test
    public void shouldChangeValuesOfTitleWhenMouseExitedFromSettings() throws Exception {
        Label title = lookup("#lblTitle").query();
        moveTo("#btnSettings");
        moveBy(0, 40);

        assertEquals("", title.getText());
    }

    @Test
    public void shouldChangeValuesOfTitleWhenMouseEnteredIntoSwapAccount() throws Exception {
        Label title = lookup("#lblTitle").query();
        moveTo("#btnSwapAccount");

        assertEquals("Зміна Користувача", title.getText());
        assertEquals(-300, title.getTranslateX(), 0);
        assertEquals(-270, title.getTranslateY(), 0);
    }

    @Test
    public void shouldChangeValuesOfTitleWhenMouseExitedFromSwapAccount() throws Exception {
        Label title = lookup("#lblTitle").query();
        moveTo("#btnSwapAccount");
        moveBy(0, 40);

        assertEquals("", title.getText());
    }

    @Test
    public void shouldChangeValuesOfTitleWhenMouseEnteredIntoExit() throws Exception {
        Label title = lookup("#lblTitle").query();
        Button exit = lookup("#btnExit").query();
        moveTo(exit);

        assertEquals("Вихід", title.getText());
        assertEquals(-275, title.getTranslateX(), 0);
        assertEquals(-270, title.getTranslateY(), 0);
        assertEquals("-fx-background-color: transparent", exit.getStyle());
    }

    @Test
    public void shouldChangeValuesOfTitleWhenMouseExitedFromExit() throws Exception {
        Label title = lookup("#lblTitle").query();
        moveTo("#btnExit");
        moveBy(0, 40);

        assertEquals("", title.getText());
    }

    @Test
    public void shouldRedirectToRegistration() throws Exception {
        clickOn("#btnSwapAccount");
        clickOn("#btnRegistration");
        assertEquals("Registration", stage.getTitle());
    }

    @Test
    public void shouldRedirectToMenuLanguage() throws Exception {
        clickOn("#btnGoTesting");
        assertEquals("Меню вибору мов", stage.getTitle());
    }

    @Test
    @Ignore
    public void shouldRedirectToStudy() throws Exception {
        clickOn("#btnGoStudy");
        //assertEquals("Навчання", stage.getTitle());
        //TODO: Solve it
    }

    @Test
    public void shouldRedirectToCreateTest() throws Exception {
        clickOn("#btnCreateTest");
        assertEquals("Файл зі своїми тестами", stage.getTitle());
    }

    @Test
    public void shouldRedirectToSettings() throws Exception {
        clickOn("#btnSettings");
        assertEquals("Settings", stage.getTitle());
    }

    @Test
    public void shouldRedirectToSwapAccount() throws Exception {
        clickOn("#btnSwapAccount");
        assertEquals("Log in", stage.getTitle());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.toFront();
        this.stage = stage;
        DataBaseDriver dataBaseDriver = new DataBaseDriver();
        dataBaseDriver.registration("test test", "test", "test");
        MainMenu mainMenu = new MainMenu();
        mainMenu.appearanceMenu(stage);
    }

    static {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.verbose", "true");
    }
}