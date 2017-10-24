package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

public class TestCreator {
    private String fileName = "test.tst";
    private String folderName = "created test files";
    private File createFile = new File(folderName, fileName);
    private String content = "<TST>";
    private int count = 0;

    public void createFileWindow (Stage stage) {
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
        isTrueVariant1.setSelected(true);
        RadioButton isTrueVariant2 = new RadioButton();
        RadioButton isTrueVariant3 = new RadioButton();
        RadioButton isTrueVariant4 = new RadioButton();
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
        TextField question = new TextField();
        question.setMaxWidth(400);
        TextField answer1 = new TextField();
        //answer1.setPrefSize(200, 100);
        answer1.setMaxWidth(250);
        TextField answer2 = new TextField();
        answer2.setMaxWidth(250);
        TextField answer3 = new TextField();
        answer3.setMaxWidth(250);
        TextField answer4 = new TextField();
        answer4.setMaxWidth(250);
        answerBox1.getChildren().addAll(isTrueVariant1, answer1);
        answerBox2.getChildren().addAll(isTrueVariant2, answer2);
        answerBox3.getChildren().addAll(isTrueVariant3, answer3);
        answerBox4.getChildren().addAll(isTrueVariant4, answer4);
        btnBox.getChildren().addAll(add, save);
        mainBox.getChildren().addAll(question, answerBox1, answerBox2, answerBox3, answerBox4);
        root.setBottom(btnBox);
        root.setCenter(mainBox);

        add.setOnAction(event -> {
            int selectedVariant = (int)variants.getSelectedToggle().getUserData();

            if(question.getText().equals("") || answer1.getText().equals("") || answer2.getText().equals("") || answer3.getText().equals("") || answer4.getText().equals("")) {
                WindowMessage.winAlert("Error!");
            }
            else {
                content += writeText(selectedVariant, question.getText(), answer1.getText(), answer2.getText(), answer3.getText(), answer4.getText());
                isTrueVariant1.setSelected(true);
                //question.setText("");
                //answer1.setText("");
                //answer2.setText("");
                //answer3.setText("");
                //answer4.setText("");
                count++;
            }
        });

        save.setOnAction(event -> {
            if(count < 1) {
                WindowMessage.winAlert("Error!");
            }
            else {
                File folder = new File(folderName);
                if (!folder.exists()) {
                    folder.mkdir();
                }

                writeFile();

                System.exit(0);
            }
        });


        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void writeFile () {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(createFile, true))) {
            bw.write(content);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String writeText(int selectedVariant, String question, String ans1, String ans2, String ans3, String ans4) {
        switch(selectedVariant) {
            case 1: ans1 += "!true!";
            break;
            case 2: ans2 += "!true!";
            break;
            case 3: ans3 += "!true!";
            break;
            case 4: ans4 += "!true!";
            break;
        }
        String block = "\n" + question + "?\n" + ans1 + "\n" + ans2 + "\n" + ans3 + "\n" + ans4;
        return block;
    }
}