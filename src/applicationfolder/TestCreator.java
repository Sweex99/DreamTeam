package applicationfolder;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class TestCreator {

    private int randomName = 1000 + (int) (Math.random() * 1000000);
    private String fileName = randomName + ".tst";
    private String folderName = "created test files";
    private File createFile = new File(folderName, fileName);
    private String content = "<TST>";
    private int count = 0;

    private void tieUp(Label label, RadioButton radioButton) {
            radioButton.setSelected(true);
    }

    public void createFileWindow(Stage primaryStage) {
        Label questionText = new Label("Внесено питань: " + count);
        questionText.setStyle("-fx-text-fill: white");
        Label answerText1 = new Label("Відповідь 1:");
        answerText1.setStyle("-fx-text-fill: white");
        Label answerText2 = new Label("Відповідь 2:");
        answerText2.setStyle("-fx-text-fill: white");
        Label answerText3 = new Label("Відповідь 3:");
        answerText3.setStyle("-fx-text-fill: white");
        Label answerText4 = new Label("Відповідь 4:");
        answerText4.setStyle("-fx-text-fill: white");

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
        RadioButton isTrueVariant2 = new RadioButton();
        RadioButton isTrueVariant3 = new RadioButton();
        RadioButton isTrueVariant4 = new RadioButton();
        isTrueVariant1.setSelected(true);
        isTrueVariant1.setToggleGroup(variants);
        isTrueVariant2.setToggleGroup(variants);
        isTrueVariant3.setToggleGroup(variants);
        isTrueVariant4.setToggleGroup(variants);
        isTrueVariant1.setUserData(1);
        isTrueVariant2.setUserData(2);
        isTrueVariant3.setUserData(3);
        isTrueVariant4.setUserData(4);
        Button add = new Button("Add");
        Button save = new Button("Save");
        save.getStyleClass().add("save1");

        HBox answerBox = new HBox();

        Button back = new Button();
        back.getStyleClass().add("back");
        back.setShape(new Circle(6));

        back.setTranslateY(3);
        back.setTranslateX(3);

        TextArea question = new TextArea();
        question.getStyleClass().add("addQuestion");
        question.setWrapText(true);
        question.setMaxSize(400, 100);
        TextField answer1 = new TextField();
        answer1.setPrefWidth(250);
        answer1.getStyleClass().add("text-field");
        TextField answer2 = new TextField();
        answer2.setPrefWidth(250);
        answer2.getStyleClass().add("text-field");
        TextField answer3 = new TextField();
        answer3.setPrefWidth(250);
        answer3.getStyleClass().add("text-field");
        TextField answer4 = new TextField();
        answer4.setPrefWidth(250);
        answer4.getStyleClass().add("text-field");
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

        answerText1.setOnMouseClicked(event -> {
            tieUp(answerText1, isTrueVariant1);
        });
        answerText2.setOnMouseClicked(event -> {
            tieUp(answerText2, isTrueVariant2);
        });
        answerText3.setOnMouseClicked(event -> {
            tieUp(answerText3, isTrueVariant3);
        });
        answerText4.setOnMouseClicked(event -> {
            tieUp(answerText4, isTrueVariant4);
        });

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
                if(count >= 10){
                    save.setStyle("-fx-border-color:white;-fx-text-fill: #e2e2e2;");
                }
            }

            questionText.setText("Внесено питань: " + count);
        });

        save.setOnAction(event -> {
            if (count < 10) {
                WindowMessage.winAlert("Ви повинні внести 10 блоків");
            } else {
//                save.getStyleClass().add("button");
                File folder = new File(folderName);
                if (!folder.exists()) {
                    folder.mkdir();
                }

                writeFile();

                System.exit(0);
            }
        });
        back.setOnAction(event -> {
            primaryStage.close();
            mainMenu.appearanceMenu(primaryStage);
        });

        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("/css/style.css");
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

    private String writeText(int selectedVariant, String question, String ans1, String ans2, String ans3, String ans4) {
        switch (selectedVariant) {
            case 1:
                ans1 += "!true!";
                break;
            case 2:
                ans2 += "!true!";
                break;
            case 3:
                ans3 += "!true!";
                break;
            case 4:
                ans4 += "!true!";
                break;
        }
        String block = "\n" + question + "?\n" + ans1 + "\n" + ans2 + "\n" + ans3 + "\n" + ans4;
        return block;
    }
}