package applicationfolder.menu;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCreatorTest extends ApplicationTest {
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

        for(int i = 1; i <= 4; i++) {
            clickOn("#txtAnswer" + i);
            write("bar");
        }

        clickOn("#btnAdd");
        Label label = lookup("#lblQuestionText").query();
        assertEquals("Внесено питань: 1", label.getText());
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.toFront();
        TestCreator testCreator = new TestCreator();
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