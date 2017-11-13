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

import java.awt.*;
import java.net.URI;
import java.util.ArrayList;

public class TestGUI extends AppLogic {
    private ArrayList<TestContent> arguments;
    private ArrayList<String> links;
    private FileReading fileReading = new FileReading();
    private int i = 0;
    private int result = 0;
    private boolean isFullScreen = false;

    public void playtest(Stage primaryStage, String fileName, boolean  isResource) {
        BorderPane root = new BorderPane();
        BorderPane subroot = new BorderPane();
        HBox titleBox = new HBox(10);
        titleBox.setAlignment(Pos.BASELINE_LEFT);
        HBox questinBox = new HBox(10);
        questinBox.setAlignment(Pos.CENTER);
        VBox answerBox = new VBox(20);

        Button fullScreen = new Button();
        fullScreen.getStyleClass().add("fullScreen");
        fullScreen.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/resize.png"))));
        fullScreen.setOnAction(event -> {
            if(isFullScreen) {
                primaryStage.setFullScreen(false);
                isFullScreen = false;
            }
            else {
                primaryStage.setFullScreen(true);
                isFullScreen = true;
            }
        });

        answerBox.setAlignment(Pos.CENTER);
        Text title = new Text();
        title.setTranslateX(10);
        title.setTranslateY(5);
        Text question = new Text();

        titleBox.getChildren().addAll(title);
        questinBox.getChildren().addAll(question);
        root.setTop(titleBox);
        root.setCenter(subroot);
        subroot.setTop(questinBox);
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
        answerBox.getChildren().addAll(rb1, rb2, rb3, rb4);
        subroot.setCenter(answerBox);
        HBox submitBox = new HBox();
        Button submit = new Button("Submit");
        submit.setTranslateX(-10);
        submit.setTranslateY(-10);
        submitBox.getChildren().add(submit);
        submitBox.setAlignment(Pos.BASELINE_RIGHT);
        root.setBottom(submitBox);
        HBox progress = new HBox();
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.1);
        progress.setAlignment(Pos.CENTER);
        progress.getChildren().addAll(progressBar , fullScreen);
        subroot.setBottom(progress);
        {
            arguments = testing(fileName, isResource);
            nextScene(i, arguments, title, question, rb1, rb2, rb3, rb4);

            submit.setOnAction(event -> {
                int correct = (int)answers.getSelectedToggle().getUserData();
                if (correct == arguments.get(i).getCorrectAnswer()) {
                    result++;
                }
                    if (i == 9) {
                        primaryStage.close();
                        finalScore(primaryStage);
                    } else {
                        progressBar.setProgress(progressBar.getProgress() + 0.1);
                        i++;
                        nextScene(i, arguments, title, question, rb1, rb2, rb3, rb4);
                    }
            });
        }

        Scene scene = new Scene(root, 600, 600);
        scene.getStylesheets().add("/css/style.css");
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
        q.setText(movingLine(arguments.get(i).getQuestion()));
    }

    private String movingLine(String str) {
        StringBuffer sb = new StringBuffer(str);
        for (int i = 50; i < str.length(); i += 50) {
            sb.insert(i, "-\n");
        }
        return sb.toString();
    }

    public void finalScore(Stage primaryStage) {
        Stage finalStage = new Stage();
        String imageName;
        MainMenu mainMenu = new MainMenu();
        BorderPane root = new BorderPane();
        Button exit = new Button("Exit");
        Button idea = new Button();
        idea.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/images/idea.png"))));

        Text score = new Text(result + "/10");
        Text statement = new Text();
        VBox boxImg = new VBox();
        boxImg.setAlignment(Pos.BASELINE_CENTER);
        HBox btnBox = new HBox();
        btnBox.setAlignment(Pos.TOP_CENTER);
        VBox scoreBox = new VBox(10);
        scoreBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().add(exit);
        scoreBox.getChildren().addAll(score, statement);

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

        links = fileReading.fileText(getClass().getResourceAsStream("/texts/links.txt"));

        idea.setOnAction(event -> {
            int randIndex = (int)(Math.random() * links.size());
            try {
                URI uri = new URI(links.get(randIndex));
                Desktop.getDesktop().browse(uri);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            idea.setVisible(false);
        });

        exit.setOnAction(event -> {
            finalStage.close();
            mainMenu.appearanceMenu(primaryStage);
        });

        root.setCenter(scoreBox);
        root.setBottom(btnBox);
        root.setTop(boxImg);
        root.setRight(idea);


        Scene scene = new Scene(root, 300, 300);

        scene.getStylesheets().add("/css/style.css");

        finalStage.setResizable(false);
        finalStage.setScene(scene);
        finalStage.show();
    }
}