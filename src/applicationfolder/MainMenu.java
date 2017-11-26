package applicationfolder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainMenu {
    private UserTest userTest = new UserTest();
    private ApplicationSound sound = new ApplicationSound();
    private MenuLanguage menuLanguage = new MenuLanguage();
    private TestCreator testCreator = new TestCreator();
    private TestGUI testGUI = new TestGUI();
    private WindowStudy windowStudy = new WindowStudy();
    private VBox vBox = new VBox();
    private DataBaseDriver dataBaseDriver = new DataBaseDriver();

    public void registration(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getStyleClass().add("background");

        Button back = new Button();
        back.getStyleClass().add("back");
        back.setShape(new Circle(6));

        Button oneStep = new Button("Крок 1");
        oneStep.getStyleClass().add("step1");
        Label redLabelOne = new Label();
        redLabelOne.getStyleClass().add("redLine1");
        redLabelOne.setStyle("-fx-pref-width: 250px;");

        Button secondStep = new Button("Крок 2");
        secondStep.getStyleClass().add("step2");

        Button thirdStep = new Button("Крок 3");
        thirdStep.getStyleClass().add("step3");

        TextField inputFName = new TextField();
        inputFName.getStyleClass().add("field");

        Label firstName = new Label("Ваше Ім'я");
        firstName.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");

        TextField inputLName = new TextField();
        inputLName.getStyleClass().add("field");

        Label lastName = new Label("Прізвище");
        lastName.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");

        TextField inputLogin = new TextField();
        inputLogin.getStyleClass().add("field");

        Label labelLogin = new Label("Логін");
        labelLogin.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");

        PasswordField inputPassword = new PasswordField();
        inputPassword.getStyleClass().add("field");

        Label labelPassword = new Label("Пароль");
        labelPassword.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");

        Button next = new Button("Дільше");
        next.getStyleClass().add("login");
        next.setStyle("-fx-background-color: #1d1d1d;");
        next.setDefaultButton(true);

        Label errorsLabel = new Label();
        errorsLabel.setStyle("-fx-border-color: red;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: red;-fx-padding: 0 0 0 55");
        errorsLabel.setVisible(false);

        Label secretQuestion = new Label("Секретне питання: ");
        secretQuestion.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");
        secretQuestion.setVisible(false);

        Label answerQuestion = new Label("Відповідь на питання: ");
        answerQuestion.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");
        answerQuestion.setVisible(false);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Ваша улюблена марка автомобіля",
                        "Назва вашого улюбленця",
                        "Дівоча фамілія матері",
                        "Ваша улюблена річ",
                        "Ім'я вашого дідуся",
                        "Ім'я вашої бабусі"
                );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setValue("-Виберіть питання-");
        comboBox.setVisible(false);

        TextField answer = new TextField();
        answer.getStyleClass().add("field");
        answer.setVisible(false);

        Button finishRegistration = new Button("Завершити Реєстрацію");
        finishRegistration.getStyleClass().add("finishRegistration");
        finishRegistration.setStyle("-fx-border-color: transparent");
        finishRegistration.setVisible(false);

        Label finishText = new Label("Реєстрацію Завершено.");
        finishText.setStyle("-fx-text-fill: green;-fx-font-size: 25px");
        ImageView img = new ImageView(new Image(getClass().getResourceAsStream("/images/goodRegistration.png")));
        finishText.setVisible(false);
        img.setVisible(false);

        img.setTranslateX(-150);
        finishText.setTranslateX(15);

        finishRegistration.setTranslateY(100);

        secretQuestion.setTranslateX(-195);

        answerQuestion.setTranslateX(-195);
        answerQuestion.setTranslateY(50);

        answer.setTranslateY(50);

        labelPassword.setTranslateY(50);
        labelPassword.setTranslateX(90);

        labelLogin.setTranslateX(90);

        inputPassword.setTranslateX(250);
        inputPassword.setTranslateY(50);

        inputLogin.setTranslateX(250);

        errorsLabel.setTranslateY(-70);

        redLabelOne.setTranslateX(-305);
        redLabelOne.setTranslateY(-170);

        firstName.setTranslateX(-350);

        lastName.setTranslateX(-350);
        lastName.setTranslateY(50);

        inputLName.setTranslateY(50);
        inputLName.setTranslateX(-190);

        inputFName.setTranslateX(-190);

        next.setTranslateY(120);

        back.setTranslateX(-430);
        back.setTranslateY(280);

        oneStep.setTranslateX(-310);
        oneStep.setTranslateY(-232);

        secondStep.setTranslateY(-232);
        secondStep.setTranslateX(0);

        thirdStep.setTranslateY(-232);
        thirdStep.setTranslateX(310);

        back.setOnAction(event -> {
            authorization(primaryStage);
        });
        next.setOnMouseEntered(event -> {
            next.setStyle("-fx-background-color: #3a3a3a;");
        });
        next.setOnMouseExited(event -> {
            next.setStyle("-fx-background-color: #1d1d1d");
        });

        errorsLabel.setText("Заповніть всі поля форми реєстрації");
        errorsLabel.setStyle("-fx-border-color: green;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: green;-fx-padding: 0 0 0 45");
        errorsLabel.setVisible(true);

        next.setOnAction(event -> {
            if ((inputFName.getText().trim().isEmpty()) || inputLName.getText().trim().isEmpty() || inputLogin.getText().trim().isEmpty() || inputPassword.getText().trim().isEmpty()) {
                errorsLabel.setText("Деякі поля у формі не заповненні");
                errorsLabel.setStyle("-fx-border-color: red;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: red;-fx-padding: 0 0 0 55");
                errorsLabel.setVisible(true);
            } else {
                if(dataBaseDriver.searchPerson(inputLogin.getText())){
                    errorsLabel.setText("Логін який ви ввели вже існує");
                    errorsLabel.setStyle("-fx-border-color: red;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: red;-fx-padding: 0 0 0 55");
                    errorsLabel.setVisible(true);
                }
                else {
                    redLabelOne.setTranslateX(0);
                    firstName.setText("Login");
                    lastName.setText("Password");

                    errorsLabel.setText("Заповніть всі поля форми реєстрації");
                    errorsLabel.setStyle("-fx-border-color: green;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: green;-fx-padding: 0 0 0 45");
                    errorsLabel.setVisible(true);

                    inputLogin.setVisible(false);
                    labelLogin.setVisible(false);
                    inputPassword.setVisible(false);
                    inputFName.setVisible(false);
                    inputLName.setVisible(false);
                    labelPassword.setVisible(false);
                    firstName.setVisible(false);
                    lastName.setVisible(false);

                    comboBox.setVisible(true);
                    secretQuestion.setVisible(true);
                    answerQuestion.setVisible(true);

                    answer.setVisible(true);
                    next.setOnAction(event1 -> {
                        if (answer.getText().trim().isEmpty() || comboBox.getValue() == "-Виберіть питання-") {
                            errorsLabel.setText("Деякі поля у формі не заповненні");
                            errorsLabel.setStyle("-fx-border-color: red;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: red;-fx-padding: 0 0 0 55");
                            errorsLabel.setVisible(true);
                        } else {
                            errorsLabel.setVisible(false);
                            secretQuestion.setVisible(false);
                            answer.setVisible(false);
                            answerQuestion.setVisible(false);
                            comboBox.setVisible(false);
                            next.setVisible(false);
                            redLabelOne.setTranslateX(305);
                            finishRegistration.setVisible(true);
                            finishText.setVisible(true);
                            img.setVisible(true);

                            dataBaseDriver.registration(inputFName.getText().toString() + " " + inputLName.getText().toString(), inputLogin.getText().toString(), inputPassword.getText().toString());
                        }
                    });
                }
            }
        });

        finishRegistration.setOnAction(event -> {
            primaryStage.close();
            authorization(primaryStage);
        });

        root.getChildren().addAll(img, finishText, finishRegistration, answerQuestion, secretQuestion, answer, comboBox, labelLogin, labelPassword, inputLogin, inputPassword, errorsLabel, oneStep, secondStep, thirdStep, back, next, inputFName, inputLName, firstName, lastName, redLabelOne);

        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Registration");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void authorization(Stage primaryStage) {
        StackPane root = new StackPane();

        root.getStyleClass().add("background");

        TextField loginField = new TextField();
        Label loginLabel = new Label("Логін: ");
        loginField.getStyleClass().add("field");
        loginLabel.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add("field");
        Label passwordLabel = new Label("Пароль: ");
        passwordLabel.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;");

        Button loginButton = new Button("Вхід");
        loginButton.setDefaultButton(true);
        loginButton.setStyle("-fx-background-color: #1d1d1d;");
        loginButton.setFocusTraversable(false);
        loginButton.getStyleClass().add("login");
        Button registration = new Button("Реєстрація");
        registration.getStyleClass().add("registration");
        registration.setTextOverrun(OverrunStyle.CLIP);

        Label login = new Label("Логін");
        login.setStyle("-fx-text-fill: #e2e2e2;-fx-font-size: 20pt;");

        Label errorsLabel = new Label();
        errorsLabel.setStyle("-fx-border-color: red;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: red;-fx-padding: 0 0 0 55");
        errorsLabel.setVisible(false);

        loginField.setTranslateY(-40);
        loginButton.setTranslateY(50);
        loginButton.setTranslateX(-73);
        registration.setTranslateY(50);
        registration.setTranslateX(47);

        passwordLabel.setTranslateX(-145);
        loginLabel.setTranslateX(-140);
        loginLabel.setTranslateY(-40);

        login.setTranslateY(-100);

        errorsLabel.setTranslateY(120);

        loginButton.setOnMouseEntered(event -> {
            loginButton.setStyle("-fx-background-color: #3a3a3a;");
        });
        loginButton.setOnMouseExited(event -> {
            loginButton.setStyle("-fx-background-color: #1d1d1d");
        });

        loginButton.setOnAction((ActionEvent event) -> {
            loginButton.setStyle("-fx-background-color: #3a3a3a; -fx-text-fill: #1d1d1d");
            if ((loginField.getText().trim().isEmpty()) || passwordField.getText().trim().isEmpty()) {
                errorsLabel.setText("Деякі поля у формі не заповнені");
                errorsLabel.setVisible(true);
            } else {
                errorsLabel.setVisible(false);

                String log_in = loginField.getText();
                String password = passwordField.getText();

                if (dataBaseDriver.authorization(log_in, password)) {
                    primaryStage.close();
                    appearanceMenu(primaryStage);
                } else {
                    errorsLabel.setText("Нажаль! Користувача не знайдено");
                    errorsLabel.setVisible(true);
                }
            }

        });
        registration.setOnAction(event -> {
            registration(primaryStage);
        });

        root.getChildren().addAll(errorsLabel, login, loginButton, loginField, loginLabel, passwordField, passwordLabel, registration);

        final Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Log in");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void appearanceMenu(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getStyleClass().add("background");
        String userName = "";
        if(dataBaseDriver.userName == null);
        else {
            userName = dataBaseDriver.userName;
        }
        vBox.getStyleClass().add("accountInfo");

        Button goTesting = new Button("Go Testing");
        Label testTesting = new Label("Перейшовши по цій кнопці ви порпте на меню. Де ви можете вибрати мову по якій хочете пройти тест");
        testTesting.getStyleClass().add("labelTesting");
        goTesting.getStyleClass().add("button");

        Button goStudy = new Button("Go study");
        Label testStudy = new Label("Чогось не знаєте? Перейдіть по цій кнопці, та виберіть мову і тему яку ви не розумієте");
        goStudy.getStyleClass().add("button");
        testStudy.getStyleClass().add("labelStudy");

        Button createTest = new Button("Create Test");
        Label testCreateTest = new Label("Бажаєте провірити друга? Можете створити свої тести і дати пройти його своєму другові)))");
        createTest.getStyleClass().add("button");
        testCreateTest.getStyleClass().add("labelTesting");

        Button exit = new Button("");
        exit.getStyleClass().add("exit");

        Button settings = new Button("");
        settings.getStyleClass().add("settings");

        Button swapAccount = new Button("");
        swapAccount.getStyleClass().add("swapAccount");

        Button user = new Button("User file Test");
        Label testUser = new Label("Якщо у вас є готовий файл з тестами! То ви можете загрузити в програму перейшовши по цій кнопці");
        user.getStyleClass().add("button");
        testUser.getStyleClass().add("labelStudy");

        Label label12 = new Label();
        label12.setStyle("-fx-text-fill: white;");
        label12.getStyleClass().add("label2");
        label12.setText(userName);
        Label title = new Label();
        title.setStyle("-fx-text-fill: white");

        exit.defaultButtonProperty().bind(exit.focusedProperty());

        root.getChildren().addAll(testTesting);
        root.getChildren().addAll(testStudy);
        root.getChildren().addAll(testCreateTest);
        root.getChildren().addAll(testUser);
        root.getChildren().addAll(label12);
        root.getChildren().addAll(title);

        testTesting.setWrapText(true);
        testStudy.setWrapText(true);
        testCreateTest.setWrapText(true);
        testUser.setWrapText(true);

        title.setTranslateX(-330);
        title.setTranslateY(-270);

        label12.setTranslateX(-380);
        label12.setTranslateY(-290);

        goTesting.setTranslateX(-110);
        goTesting.setTranslateY(-130);
        testTesting.setTranslateX(85);
        testTesting.setTranslateY(-130);

        goStudy.setTranslateX(120);
        goStudy.setTranslateY(-28);
        testStudy.setTranslateX(-68);
        testStudy.setTranslateY(-28);

        createTest.setTranslateX(-110);
        createTest.setTranslateY(70);
        testCreateTest.setTranslateX(86);
        testCreateTest.setTranslateY(70);

        user.setTranslateX(120);
        user.setTranslateY(165);
        testUser.setTranslateX(-68);
        testUser.setTranslateY(165);

        exit.setTranslateY(-289);
        exit.setTranslateX(-275);

        swapAccount.setTranslateY(-289);
        swapAccount.setTranslateX(-303);

        settings.setTranslateY(-289);
        settings.setTranslateX(-330);

        root.getChildren().addAll(goTesting, goStudy, createTest, user, exit, settings, swapAccount);

        goTesting.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            menuLanguage.menuLanguageBackground(primaryStage);
        });
        goStudy.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            windowStudy.startStudy(primaryStage);
        });
        createTest.setOnAction(event -> {
            sound.clickSound();
            testCreator.createFileWindow(primaryStage);
        });
        exit.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            System.exit(0);
        });
        user.setOnAction(event -> {
            String pathOfFile = userTest.getPathOfChosenFile(primaryStage);
            System.out.print(pathOfFile);
            if (pathOfFile != null) {
                testGUI.playTest(primaryStage, pathOfFile, false);
        }
        });
        settings.setOnMouseEntered(event -> {
            title.setText("Настройки");
            title.setTranslateX(-330);
            title.setTranslateY(-270);
        });
        settings.setOnMouseExited(event -> {
            title.setText("");
        });
        settings.setOnAction(event -> {
            WindowMessage.winAlert("Даний функціонал у розробці. Дочекайтеся наступного оновлення))");
        });
        swapAccount.setOnAction(event -> {
            authorization(primaryStage);
        });
        swapAccount.setOnMouseEntered(event -> {
            title.setText("Зміна Користувача");
            title.setTranslateX(-300);
            title.setTranslateY(-270);
        });
        swapAccount.setOnMouseExited(event -> {
            title.setText("");
        });
        exit.setOnMouseEntered(event -> {
            title.setText("Вихід");
            title.setTranslateX(-275);
            title.setTranslateY(-270);
            exit.setStyle("-fx-background-color: transparent");
        });
        exit.setOnMouseExited(event -> {
            title.setText("");
        });

        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Main Menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

