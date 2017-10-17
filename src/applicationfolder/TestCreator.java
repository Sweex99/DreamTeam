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
        VBox mainbox = new VBox(15);
        mainbox.setAlignment(Pos.CENTER);
        HBox btnbox = new HBox(5);
        btnbox.setAlignment(Pos.BOTTOM_RIGHT);
        btnbox.setTranslateX(-10);
        btnbox.setTranslateY(-10);
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
        btnbox.getChildren().addAll(add, save);
        mainbox.getChildren().addAll(question, answers1, answers2, answers3, answers4);
        root.setBottom(btnbox);
        root.setCenter(mainbox);

        add.setOnAction(event -> {
            if(question.getText().equals("") && answers1.getText().equals("") && answers2.getText().equals("") && answers3.getText().equals("") && answers4.getText().equals("")) {
                winalert("Error!");
            }
            else {
                content += write_text(question.getText(), answers1.getText(), answers2.getText(), answers3.getText(), answers4.getText());
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
                winalert("Error!");
            }
            else {
                write_file();
                System.exit(0);
            }
        });


        Scene scene = new Scene(root, 600, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void write_file () {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(created_file, true))) {
            bw.write(content);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private String write_text(String question, String ans1, String ans2, String ans3, String ans4) {
        String block = "\n" + question + "?\n" + ans1 + "\n" + ans2 + "\n" + ans3 + "\n" + ans4;
        return block;
    }

    private void winalert (String alert) {
        Stage stage = new Stage();
        Pane message = new Pane();
        Text warning = new Text(alert);
        warning.setTranslateX(50);
        warning.setTranslateY(25);
        Button exit = new Button("OK");
        exit.setTranslateX(58);
        exit.setTranslateY(40);
        message.getChildren().addAll(exit, warning);
        exit.setOnAction(event -> {
            stage.close();
        });
        Scene scn = new Scene(message, 150, 100);
        stage.setScene(scn);
        stage.show();
    }
}
