package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

public class TestCreator {
    private String pathname = "text.tst";
    private File created_file = new File(pathname);
    private String content = "<TST>";
    private int count = 0;

    public void create_window (Stage stage) {
        BorderPane root = new BorderPane();
        VBox mainBox = new VBox(15);
        mainBox.setAlignment(Pos.CENTER);
        HBox btnBox = new HBox(5);
        btnBox.setAlignment(Pos.BOTTOM_RIGHT);
        btnBox.setTranslateX(-10);
        btnBox.setTranslateY(-10);
        Button add = new Button("Add");
        Button save = new Button("Save");
        TextField question = new TextField();
        question.setMaxWidth(400);
        TextField answers1 = new TextField();
        answers1.setMaxWidth(250);
        TextField answers2 = new TextField();
        answers2.setMaxWidth(250);
        TextField answers3 = new TextField();
        answers3.setMaxWidth(250);
        TextField answers4 = new TextField();
        answers4.setMaxWidth(250);
        btnBox.getChildren().addAll(add, save);
        mainBox.getChildren().addAll(question, answers1, answers2, answers3, answers4);
        root.setBottom(btnBox);
        root.setCenter(mainBox);

        add.setOnAction(event -> {
            if(question.getText().equals("") && answers1.getText().equals("") && answers2.getText().equals("") && answers3.getText().equals("") && answers4.getText().equals("")) {
                WindowMessage.winAlert("Error!");
            }
            else {
                content += writeText(question.getText(), answers1.getText(), answers2.getText(), answers3.getText(), answers4.getText());
                question.setText("");
                answers1.setText("");
                answers2.setText("");
                answers3.setText("");
                answers4.setText("");
                count++;
            }
        });

        save.setOnAction(event -> {
            if(count < 10) {
                WindowMessage.winAlert("Error!");
            }
            else {
                writeFile();
                System.exit(0);
            }
        });


        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void writeFile () {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(created_file, true))) {
            bw.write(content);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String writeText(String question, String ans1, String ans2, String ans3, String ans4) {
        String block = "\n" + question + "?\n" + ans1 + "\n" + ans2 + "\n" + ans3 + "\n" + ans4;
        return block;
    }
}
