package applicationfolder.menu;

import applicationfolder.utils.WindowMessage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCreator {
    private SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
    private String dateName = formatForDateNow.format(new Date());
    private String fileName = dateName + ".tst";
    private String folderName = "created test files";
    private File createFile = new File(folderName, fileName);
    private String content = "<TST>";
    private int count = 0;

    private void tieUp(RadioButton radioButton) {
        radioButton.setSelected(true);
    }

    public void createFileWindow(Stage primaryStage) {
        final String whiteColorStyle = "-fx-text-fill: white";
        final String textFieldStyle = "text-field";

        Label questionText = new Label("Внесено питань: " + count);
        questionText.setId("lblQuestionText");
        questionText.setStyle(whiteColorStyle);
        Label answerText1 = new Label("Відповідь 1:");
        answerText1.setId("lblAnswerText1");
        answerText1.setStyle(whiteColorStyle);
        Label answerText2 = new Label("Відповідь 2:");
        answerText2.setId("lblAnswerText2");
        answerText2.setStyle(whiteColorStyle);
        Label answerText3 = new Label("Відповідь 3:");
        answerText3.setId("lblAnswerText3");
        answerText3.setStyle(whiteColorStyle);
        Label answerText4 = new Label("Відповідь 4:");
        answerText4.setId("lblAnswerText4");
        answerText4.setStyle(whiteColorStyle);

        MainMenu mainMenu = new MainMenu();
        BorderPane root = new BorderPane();
        VBox mainBox = new VBox(15);
        mainBox.setAlignment(Pos.CENTER);
        HBox btnBox = new HBox(5);
        btnBox.setAlignment(Pos.BOTTOM_RIGHT);
        btnBox.setTranslateX(-10);
        btnBox.setTranslateY(-10);
        HBox answerBox1 = new HBox(10);
        answerBox1.setAlignment(Pos.CENTER);
        HBox answerBox2 = new HBox(10);
        answerBox2.setAlignment(Pos.CENTER);
        HBox answerBox3 = new HBox(10);
        answerBox3.setAlignment(Pos.CENTER);
        HBox answerBox4 = new HBox(10);
        answerBox4.setAlignment(Pos.CENTER);
        ToggleGroup variants = new ToggleGroup();
        RadioButton isTrueVariant1 = new RadioButton();
        isTrueVariant1.setId("rbIsTrueVariant1");
        RadioButton isTrueVariant2 = new RadioButton();
        isTrueVariant2.setId("rbIsTrueVariant2");
        RadioButton isTrueVariant3 = new RadioButton();
        isTrueVariant3.setId("rbIsTrueVariant3");
        RadioButton isTrueVariant4 = new RadioButton();
        isTrueVariant4.setId("rbIsTrueVariant4");
        isTrueVariant1.setSelected(true);
        isTrueVariant1.setToggleGroup(variants);
        isTrueVariant2.setToggleGroup(variants);
        isTrueVariant3.setToggleGroup(variants);
        isTrueVariant4.setToggleGroup(variants);
        isTrueVariant1.setUserData(0);
        isTrueVariant2.setUserData(1);
        isTrueVariant3.setUserData(2);
        isTrueVariant4.setUserData(3);
        Button add = new Button("Add");
        add.setId("btnAdd");
        Button save = new Button("Save");
        save.setId("btnSave");
        save.getStyleClass().add("save1");

        HBox answerBox = new HBox();

        Button back = new Button();
        back.setId("btnBack");
        back.getStyleClass().add("back");
        back.setShape(new Circle(6));

        back.setTranslateY(3);
        back.setTranslateX(3);

        TextArea question = new TextArea();
        question.setId("txtQuestion");
        question.getStyleClass().add("addQuestion");
        question.setWrapText(true);
        question.setMaxSize(400, 100);
        TextField answer1 = new TextField();
        answer1.setId("txtAnswer1");
        answer1.setPrefWidth(250);
        answer1.getStyleClass().add(textFieldStyle);
        TextField answer2 = new TextField();
        answer2.setId("txtAnswer2");
        answer2.setPrefWidth(250);
        answer2.getStyleClass().add(textFieldStyle);
        TextField answer3 = new TextField();
        answer3.setId("txtAnswer3");
        answer3.setPrefWidth(250);
        answer3.getStyleClass().add(textFieldStyle);
        TextField answer4 = new TextField();
        answer4.setId("txtAnswer4");
        answer4.setPrefWidth(250);
        answer4.getStyleClass().add(textFieldStyle);
        answerBox1.getChildren().addAll(answerText1, isTrueVariant1, answer1);
        answerBox2.getChildren().addAll(answerText2, isTrueVariant2, answer2);
        answerBox3.getChildren().addAll(answerText3, isTrueVariant3, answer3);
        answerBox4.getChildren().addAll(answerText4, isTrueVariant4, answer4);
        answerBox.getChildren().addAll(answerBox1, answerBox2);
        btnBox.getChildren().addAll(save);
        mainBox.getChildren().addAll(questionText, question, answerBox1, answerBox2, answerBox3, answerBox4, add);
        root.setBottom(btnBox);

        root.setCenter(mainBox);
        root.setTop(back);
        root.getStyleClass().add("background");

        answerText1.setOnMouseClicked(event -> tieUp(isTrueVariant1));
        answerText2.setOnMouseClicked(event -> tieUp(isTrueVariant2));
        answerText3.setOnMouseClicked(event -> tieUp(isTrueVariant3));
        answerText4.setOnMouseClicked(event -> tieUp(isTrueVariant4));

        add.setOnMouseClicked(event -> {
            int selectedVariant = (int) variants.getSelectedToggle().getUserData();

            if (question.getText().equals("") || answer1.getText().equals("") || answer2.getText().equals("") || answer3.getText().equals("") || answer4.getText().equals("")) {
                WindowMessage.winAlert("Ви повинні запонити всі строки");
            } else {
                content += writeText(selectedVariant, question.getText(), answer1.getText(), answer2.getText(), answer3.getText(), answer4.getText());
                isTrueVariant1.setSelected(true);
                question.setText("");
                answer1.setText("");
                answer2.setText("");
                answer3.setText("");
                answer4.setText("");
                count++;
                if (count >= 10) {
                    save.setStyle("-fx-border-color:white;-fx-text-fill: #e2e2e2;");
                }
            }

            questionText.setText("Внесено питань: " + count);
        });

        save.setOnAction(event -> {
            if (count < 10) {
                WindowMessage.winAlert("Ви повинні внести 10 блоків");
            } else {
                File folder = new File(folderName);
                if (!folder.exists()) {
                    folder.mkdir();
                }
                writeFile();
                primaryStage.close();
                mainMenu.appearanceMenu(primaryStage);
            }
        });
        back.setOnAction(event -> {
            primaryStage.close();
            mainMenu.appearanceMenu(primaryStage);
        });

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("/css/style.css");
        primaryStage.setTitle("Файл зі своїми тестами");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void writeFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(createFile, true))) {
            bw.write(content);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String writeText(int selectedVariant, String question, String... answers) {
        final String trueTarget = "!true!";

        answers[selectedVariant] += trueTarget;
        return "\n" + question + "?\n" + answers[0] + "\n" + answers[1] + "\n" + answers[2] + "\n" + answers[3];
    }
}