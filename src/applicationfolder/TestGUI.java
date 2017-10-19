package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;

public class TestGUI extends AppLogic {
    private ArrayList<TestContent> arguments;
    private int i = 0;
    private int result = 0;
    boolean is_full_screen = false;

    public void playtest(Stage primaryStage, String fileName, boolean  is_resource) {
        BorderPane root = new BorderPane();
        BorderPane subroot = new BorderPane();
        HBox titlebox = new HBox(10);
        titlebox.setAlignment(Pos.BASELINE_LEFT);
        HBox questinbox = new HBox(10);
        questinbox.setAlignment(Pos.CENTER);
        VBox answerbox = new VBox(20);

        Button full_screen = new Button();
        full_screen.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/imgonline-com-ua-Resize-hO56OLqmia1.png"))));
        full_screen.setOnAction(event -> {
            if(is_full_screen) {
                primaryStage.setFullScreen(false);
                is_full_screen = false;
            }
            else {
                primaryStage.setFullScreen(true);
                is_full_screen = true;
            }
        });

        answerbox.setAlignment(Pos.CENTER);
        Text title = new Text();
        title.setTranslateX(10);
        title.setTranslateY(5);
        Text question = new Text();

        titlebox.getChildren().addAll(title);
        questinbox.getChildren().addAll(question);
        root.setTop(titlebox);
        root.setCenter(subroot);
        subroot.setTop(questinbox);
        RadioButton rb1 = new RadioButton();
        RadioButton rb2 = new RadioButton();
        RadioButton rb3 = new RadioButton();
        RadioButton rb4 = new RadioButton();
        ToggleGroup answers = new ToggleGroup();
        rb1.setToggleGroup(answers);
        rb2.setToggleGroup(answers);
        rb3.setToggleGroup(answers);
        rb4.setToggleGroup(answers);
        rb1.setUserData(1);
        rb2.setUserData(2);
        rb3.setUserData(3);
        rb4.setUserData(4);
        answerbox.getChildren().addAll(rb1, rb2, rb3, rb4);
        subroot.setCenter(answerbox);
        HBox submitbox = new HBox();
        Button submit = new Button("Submit");
        submit.setTranslateX(-10);
        submit.setTranslateY(-10);
        submitbox.getChildren().add(submit);
        submitbox.setAlignment(Pos.BASELINE_RIGHT);
        root.setBottom(submitbox);
        HBox progress = new HBox();
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.1);
        progress.setAlignment(Pos.CENTER);
        progress.getChildren().addAll(progressBar , full_screen);
        subroot.setBottom(progress);
        {
            arguments = testing(fileName, is_resource);
            nextScene(i, arguments, title, question, rb1, rb2, rb3, rb4);

            submit.setOnAction(event -> {
                int correct = (int)answers.getSelectedToggle().getUserData();
                if (correct == arguments.get(i).getCorrectAnswer()) {
                    result++;
                    if (i == 9) {
                        primaryStage.close();
                        finalscore(primaryStage);
                    } else {
                        progressBar.setProgress(progressBar.getProgress() + 0.1);
                        i++;
                        nextScene(i, arguments, title, question, rb1, rb2, rb3, rb4);
                    }
                } else {
                    if (i == 9) {
                        primaryStage.close();
                        finalscore(primaryStage);
                    } else {
                        progressBar.setProgress(progressBar.getProgress() + 0.1);
                        i++;
                        nextScene(i, arguments, title, question, rb1, rb2, rb3, rb4);
                    }
                }

            });
        }

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void nextScene(int i, ArrayList<TestContent> arguments, Text title, Text q, RadioButton rb1, RadioButton rb2, RadioButton rb3, RadioButton rb4) {
        title.setText("Питання №" + (i+1));
        rb1.setText(arguments.get(i).getAnswers(0));
        rb2.setText(arguments.get(i).getAnswers(1));
        rb3.setText(arguments.get(i).getAnswers(2));
        rb4.setText(arguments.get(i).getAnswers(3));
        rb1.setSelected(true);
        q.setText(movingline(arguments.get(i).getQuestion()));
    }

    private String movingline(String str) {
        StringBuffer sb = new StringBuffer(str);
        for (int i = 50; i < str.length(); i += 50) {
            sb.insert(i, "-\n");
        }
        return sb.toString();
    }

    public void finalscore(Stage finalStage) {
        String imageName = "";
        BorderPane root = new BorderPane();
        Button exit = new Button("Exit");
        Text score = new Text(result + "/10");
        Text statement = new Text();
        VBox boxImg = new VBox();
        boxImg.setAlignment(Pos.BASELINE_CENTER);
        HBox btnbox = new HBox();
        btnbox.setAlignment(Pos.TOP_CENTER);
        VBox scorebox = new VBox(10);
        scorebox.setAlignment(Pos.CENTER);
        btnbox.getChildren().add(exit);
        scorebox.getChildren().addAll(score, statement);

        if (result == 10) {
            imageName = "/images/brain.png";
            statement.setText("Ви справжній програміст!!!");
        } else if (result > 7) {
            imageName = "/images/congratulation.png";
            statement.setText("Вітаю!");
        } else if (result >= 5) {
            imageName = "/images/notbad.png";
            statement.setText("Непогано");
        } else if (result >= 2) {
            imageName = "/images/book.png";
            statement.setText("Вам варто ще багато вивчити...");
        } else {
            imageName = "/images/poop.png";
            statement.setText("Ви часом не гуманітарій?");
        }

        ImageView img = new ImageView(new Image(getClass().getResourceAsStream(imageName)));
        img.setFitHeight(128);
        img.setFitWidth(128);
        boxImg.getChildren().add(img);

        exit.setOnAction(event -> {
            System.exit(0);
        });

        root.setCenter(scorebox);
        root.setBottom(btnbox);
        root.setTop(boxImg);


        Scene scene = new Scene(root);

        scene.getStylesheets().add("/css/style.css");

        finalStage.setFullScreen(true);
        finalStage.setMinHeight(300);
        finalStage.setMinWidth(300);
        finalStage.setMaxHeight(300);
        finalStage.setMaxWidth(300);
        finalStage.setScene(scene);
        finalStage.show();
    }

}
