package applicationfolder.menu;

import applicationfolder.utils.AppLogic;
import applicationfolder.utils.DataBaseDriver;
import applicationfolder.utils.TestContent;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.awt.*;
import java.net.URI;
import java.util.List;
import static org.apache.commons.io.IOUtils.readLines;

public class TestGUI extends AppLogic {
    private DataBaseDriver dataBaseDriver;
    private int i = 0;
    private int result = 0;

    public void playTest(DataBaseDriver dataBaseDriver, Stage primaryStage, String fileName, boolean isResource) {
        this.dataBaseDriver = dataBaseDriver;
        MenuLanguage main = new MenuLanguage();
        BorderPane root = new BorderPane();
        root.getStyleClass().add("background");
        BorderPane subroot = new BorderPane();
        Button back = new Button();
        back.getStyleClass().add("back");
        back.setTranslateY(3);
        back.setTranslateX(3);
        back.setShape(new Circle(6));

        HBox titleBox = new HBox(10);


        titleBox.setAlignment(Pos.BASELINE_LEFT);

        BorderPane questionBox = new BorderPane();
        questionBox.setStyle("-fx-max-height: 50px;-fx-max-width: 300px;");
        questionBox.setTranslateY(50);
        questionBox.setTranslateX(340);

        Text question = new Text();
        question.setText("Ви готові до найскладнішого тесту у вашому житті???)))");
        question.setStyle("-fx-border-width: 2px;-fx-border-color: red;-fx-max-height: 50px;-fx-max-width: 200px;");
        question.setWrappingWidth(400);
        question.wrappingWidthProperty().bind(questionBox.widthProperty());
        question.setFill(Color.WHITE);

        VBox answerBox = new VBox(20);

        answerBox.setAlignment(Pos.CENTER);
        Text title = new Text();
        title.setTranslateX(10);
        title.setTranslateY(5);

        back.setOnAction(event -> {
            main.menuLanguageBackground(dataBaseDriver, primaryStage);
        });

        titleBox.getChildren().addAll(title);
        questionBox.getChildren().addAll(question);

        root.setTop(back);
        root.setCenter(subroot);

        ToggleGroup group = new ToggleGroup();
        ToggleButton rb1 = new ToggleButton();
        rb1.setToggleGroup(group);
        rb1.getStyleClass().add("button");
        rb1.setTextOverrun(OverrunStyle.CLIP);
        rb1.setWrapText(true);
        ToggleButton rb2 = new ToggleButton();
        rb2.setToggleGroup(group);
        rb2.getStyleClass().add("button");
        rb2.setTextOverrun(OverrunStyle.CLIP);
        rb2.setWrapText(true);
        ToggleButton rb3 = new ToggleButton();
        rb3.setToggleGroup(group);
        rb3.getStyleClass().add("button");
        rb3.setTextOverrun(OverrunStyle.CLIP);
        rb3.setWrapText(true);
        ToggleButton rb4 = new ToggleButton();
        rb4.setToggleGroup(group);
        rb4.getStyleClass().add("button");
        rb4.setTextOverrun(OverrunStyle.CLIP);
        rb4.setWrapText(true);
        rb1.setUserData(1);
        rb2.setUserData(2);
        rb3.setUserData(3);
        rb4.setUserData(4);

        rb1.setTranslateX(-195);
        rb1.setTranslateY(119);

        rb2.setTranslateX(210);
        rb2.setTranslateY(55);

        rb3.setTranslateX(-197);
        rb3.setTranslateY(195);

        rb4.setTranslateX(210);
        rb4.setTranslateY(130);

        Label label1 = new javafx.scene.control.Label();
        label1.getStyleClass().add("redLine");
        Label label2 = new Label();
        label2.getStyleClass().add("redLine");

        label1.setTranslateY(-125);
        label1.setTranslateX(10);
        label2.setTranslateY(12);
        label2.setTranslateX(10);

        Label label3 = new Label();
        label3.getStyleClass().add("redLine1");
        Label label4 = new Label();
        label4.getStyleClass().add("redLine1");

        label3.setTranslateY(-135);
        label3.setTranslateX(210);
        label4.setTranslateY(-175);
        label4.setTranslateX(-195);

        Label youAnswer = new Label();
        youAnswer.setStyle("-fx-text-fill: #e2e2e2;-fx-pref-height: 70px;-fx-max-width: 250px;");
        youAnswer.setWrapText(true);
        youAnswer.setMaxWidth(Double.MAX_VALUE);
        youAnswer.setAlignment(Pos.CENTER);

        youAnswer.setTranslateX(13);
        youAnswer.setTranslateY(270);

        subroot.setTop(questionBox);
        answerBox.getChildren().addAll(youAnswer, rb1, rb2, rb3, rb4, label1, label2, label3, label4);
        subroot.setCenter(answerBox);

        HBox submitBox = new HBox();
        Button submit = new Button("Ви готові???");
        submit.setWrapText(true);
        submit.setDefaultButton(true);
        submit.getStyleClass().add("submit");
        submit.setTextOverrun(OverrunStyle.CLIP);
        submit.setStyle("-fx-background-color: #1d1d1d;");
        submit.setUserData(1);
        submit.setTranslateX(-10);
        submit.setTranslateY(-10);
        submitBox.getChildren().add(submit);
        submitBox.setAlignment(Pos.BASELINE_RIGHT);
        root.setBottom(submitBox);
        HBox progress = new HBox();
        ProgressBar progressBar = new ProgressBar();

        progressBar.setProgress(0.1);
        progress.setAlignment(Pos.CENTER);
        progress.getChildren().addAll(progressBar);
        subroot.setBottom(progress);

        submit.setOnMouseEntered(event -> {
            submit.setStyle("-fx-background-color: #3a3a3a");
        });
        submit.setOnMouseExited(event -> {
            submit.setStyle("-fx-background-color: #1d1d1d");
        });
        {
            submit.setOnAction(event -> {
                submit.getStyleClass().add("nextQuestion");
                submit.setText("Підтвердити (Enter)");
                Correct(youAnswer, primaryStage, fileName, isResource, title, progressBar, question, group, rb1, rb2, rb3, rb4, submit);
            });
        }
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add("/css/style.css");
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);
        primaryStage.setTitle("");
        primaryStage.setMinWidth(600);
        primaryStage.setMinHeight(600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void Correct(Label youAnswer, Stage primaryStage, String fileName, boolean isResource
            , Text title, ProgressBar progressBar, Text question, ToggleGroup group, ToggleButton rb1,
                         ToggleButton rb2, ToggleButton rb3, ToggleButton rb4, Button submit) {
        List<TestContent> arguments = testing(fileName, isResource);
        nextScene(youAnswer, i, arguments, title, question, rb1, rb2, rb3, rb4);
        int correct = (int)group.getSelectedToggle().getUserData();

        submit.setOnAction(event -> {
            clickButton(false, rb1, rb2, rb3, rb4);
            youAnswer.setText("");
            if(correct > 0) {
                if (correct == arguments.get(i).getCorrectAnswer()) {
                    result++;
                }
                if (i >= 9) {

                    finalScore(primaryStage);
                } else {
                    progressBar.setProgress(progressBar.getProgress() + 0.1);
                    i++;
                    nextScene(youAnswer, i, arguments, title, question, rb1, rb2, rb3, rb4);
                }
            }
        });
    }

    private void nextScene(Label youAnswer, int i, List<TestContent> arguments, Text title, Text q, ToggleButton rb1, ToggleButton rb2, ToggleButton rb3, ToggleButton rb4) {
        title.setText("Питання №" + (i + 1));
        rb1.setText(arguments.get(i).getAnswers(0));
        rb2.setText(arguments.get(i).getAnswers(1));
        rb3.setText(arguments.get(i).getAnswers(2));
        rb4.setText(arguments.get(i).getAnswers(3));
        youAnswer(youAnswer, rb1, rb2, rb3, rb4);
        rb1.setSelected(true);
        q.setText(movingLine(arguments.get(i).getQuestion()));
    }

    private void youAnswer(Label youAnswer, ToggleButton rb1, ToggleButton rb2, ToggleButton rb3, ToggleButton rb4) {
        rb1.setOnAction(event -> {
            youAnswer.setText(rb1.getText());
            clickButton(true, rb1, rb2, rb3, rb4);
        });
        rb2.setOnAction(event -> {
            youAnswer.setText(rb2.getText());
            clickButton(true, rb2, rb1, rb3, rb4);
        });
        rb3.setOnAction(event -> {
            youAnswer.setText(rb3.getText());
            clickButton(true, rb3, rb1, rb2, rb4);
        });
        rb4.setOnAction(event -> {
            youAnswer.setText(rb4.getText());
            clickButton(true, rb4, rb2, rb3, rb1);
        });
    }

    private void clickButton(boolean bool, ToggleButton...rb){
        if(bool) {
            rb[0].setStyle("-fx-background-color: white;-fx-text-fill: #1d1d1d;");
            rb[1].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
            rb[2].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
            rb[3].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
        }
        else{
            rb[0].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
            rb[1].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
            rb[2].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
            rb[3].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white;");
        }
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
        MenuLanguage mainMenu = new MenuLanguage();
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

        idea.setOnAction(event -> {

            try {
                List<String> links = readLines(getClass().getResourceAsStream("/texts/links.txt"), "UTF-8");
                int randIndex = (int) (Math.random() * links.size());
                URI uri = new URI(links.get(randIndex));
                Desktop.getDesktop().browse(uri);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            idea.setVisible(false);
        });

        exit.setOnAction(event -> {
            dataBaseDriver.updateStatistic(result);
            mainMenu.menuLanguageBackground(dataBaseDriver, primaryStage);
            finalStage.close();
        });

        root.setCenter(scoreBox);
        root.setBottom(btnBox);
        root.setTop(boxImg);
        root.setRight(idea);

        Scene scene = new Scene(root, 300, 300);
        finalStage.initOwner(primaryStage);
        finalStage.initModality(Modality.WINDOW_MODAL);
        scene.getStylesheets().add("/css/style.css");
        primaryStage.setResizable(false);
        finalStage.initStyle(StageStyle.UNDECORATED);
        finalStage.setResizable(false);
        finalStage.setScene(scene);
        finalStage.show();
    }
}