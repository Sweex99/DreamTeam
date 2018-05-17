package applicationfolder.menu;

import applicationfolder.utils.DataBaseDriver;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.apache.commons.io.IOUtils.readLines;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCreatorTest extends ApplicationTest {
    private TestCreator testCreator;

    @After
    public void tearDown() throws Exception {
        File file = new File("database/users.xml");
        file.delete();
    }

    @Test
    public void shouldThrowAlertAboutNotFilledFields() throws Exception {
        clickOn("#btnAdd");
        clickOn("OK");
        //TODO: Check alerts
    }

    @Test
    public void shouldThrowAlertAboutCreatedLessThan10Blocks() throws Exception {
        clickOn("#btnSave");
        clickOn("OK");
        //TODO: Check alerts
    }

    @Test
    public void shouldRedirectToMainMenu() throws Exception {
        clickOn("#btnBack");
        //TODO: Check Main Menu
    }

    @Test
    public void shouldBeCorrectAllPositionsOfBoxes() throws Exception {
        for (int i = 1; i <= 4; i++) {
            HBox answerBox = (HBox) lookup("#lblAnswerText" + i).query().getParent();
            assertEquals(Pos.CENTER, answerBox.getAlignment());
        }
        VBox mainBox = (VBox) lookup("#lblQuestionText").query().getParent();
        assertEquals(Pos.CENTER, mainBox.getAlignment());
        HBox btnBox = (HBox) lookup("#btnSave").query().getParent();
        assertEquals(Pos.BOTTOM_RIGHT, btnBox.getAlignment());
    }

    @Test
    public void shouldBeCorrectAllSizesAndTranslates() throws Exception {
        for (int i = 1; i <= 4; i++) {
            TextField answer = lookup("#txtAnswer" + i).query();
            assertEquals(250, answer.getPrefWidth(), 0);
        }
        TextArea question = lookup("#txtQuestion").query();
        HBox btnBox = (HBox) lookup("#btnSave").query().getParent();
        Button back = lookup("#btnBack").query();
        assertTrue(question.isWrapText());
        assertEquals(400, question.getMaxWidth(), 0);
        assertEquals(100, question.getMaxHeight(), 0);
        assertEquals(-10, btnBox.getTranslateX(), 0);
        assertEquals(-10, btnBox.getTranslateY(), 0);
        assertEquals(3, back.getTranslateX(), 0);
        assertEquals(3, back.getTranslateY(), 0);
    }

    @Test
    public void shouldAnyRadioBeSelectedWhenRadioClicked() throws Exception {
        for (int i = 1; i <= 4; i++) {
            RadioButton radioButton = lookup("#rbIsTrueVariant" + i).query();
            clickOn(radioButton);
            assertTrue(radioButton.isSelected());
        }
    }

    @Test
    public void shouldAnyRadioBeSelectedWhenLabelClicked() throws Exception {
        for (int i = 1; i <= 4; i++) {
            Label label = lookup("#lblAnswerText" + i).query();
            RadioButton radioButton = lookup("#rbIsTrueVariant" + i).query();
            clickOn(label);
            assertTrue(radioButton.isSelected());
        }
    }

    @Test
    public void shouldAnyRadioContainsUniqueUserData() throws Exception {
        for (int i = 1; i <= 4; i++) {
            RadioButton radioButton = lookup("#rbIsTrueVariant" + i).query();
            assertEquals((i - 1), radioButton.getUserData());
        }
    }

    @Test
    public void shouldAnyRadioBeInSameToggleGroup() throws Exception {
        RadioButton firstRadioButton = lookup("#rbIsTrueVariant1").query();
        ToggleGroup expectedToggleGroup = firstRadioButton.getToggleGroup();
        for (int i = 2; i <= 4; i++) {
            RadioButton otherRadioButton = lookup("#rbIsTrueVariant" + i).query();
            assertEquals(expectedToggleGroup, otherRadioButton.getToggleGroup());
        }
    }

    @Test
    public void shouldAnyLabelHasSameColorStyle() throws Exception {
        final String expectedColorStyle = "-fx-text-fill: white";

        Label label = lookup("#lblQuestionText").query();
        assertEquals(expectedColorStyle, label.getStyle());
        for (int i = 1; i <= 4; i++) {
            label = lookup("#lblAnswerText" + i).query();
            assertEquals(expectedColorStyle, label.getStyle());
        }
    }

    @Test
    public void shouldIncreaseCountValueOfLabel() throws Exception {
        clickOn("#txtQuestion");
        write("Foo");

        for (int i = 1; i <= 4; i++) {
            clickOn("#txtAnswer" + i);
            write("bar");
        }

        clickOn("#btnAdd");
        Label label = lookup("#lblQuestionText").query();
        assertEquals("Внесено питань: 1", label.getText());
    }

    @Test
    public void shouldCreateTstFileWithCorrectContentAfterCreating10Blocks() throws Exception {
        for (int j = 0; j < 10; j++) {
            clickOn("#txtQuestion");
            write("Foo");

            for (int i = 1; i <= 4; i++) {
                clickOn("#txtAnswer" + i);
                write("bar");
            }

            clickOn("#rbIsTrueVariant" + ((j % 4) + 1));
            clickOn("#btnAdd");
        }
        Button save = lookup("#btnSave").query();
        assertEquals("-fx-border-color:white;-fx-text-fill: #e2e2e2;", save.getStyle());
        clickOn("#btnSave");

        List expectedContent = readLines(getClass().getResourceAsStream("/tst_mock.tst"), "UTF-8");
        List actualContent = readLines(new FileInputStream("created test files/" + testCreator.getFileName()), "UTF-8");
        assertEquals(expectedContent, actualContent);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.toFront();
        DataBaseDriver dataBaseDriver = new DataBaseDriver();
        dataBaseDriver.registration("test test", "test", "test");
        testCreator = new TestCreator();
        testCreator.createFileWindow(stage);
    }

    static {
        System.setProperty("testfx.robot", "glass");
        System.setProperty("testfx.headless", "true");
        System.setProperty("prism.order", "sw");
        System.setProperty("prism.text", "t2k");
        System.setProperty("prism.verbose", "true");
    }
}