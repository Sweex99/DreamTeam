package applicationfolder.menu;

import applicationfolder.utils.DataBaseDriver;
import applicationfolder.utils.WindowMessage;
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
    private MenuLanguage menuLanguage = new MenuLanguage();
    private TestCreator testCreator = new TestCreator();
    private TestGUI testGUI = new TestGUI();
    private WindowStudy windowStudy = new WindowStudy();
    private VBox vBox = new VBox();
    private DataBaseDriver dataBaseDriver = new DataBaseDriver();

    private static final String SETTING_LABEL_GREEN_STYLE = "-fx-border-color: green;-fx-text-fill: green;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
    private static final String SETTING_LABEL_RED_STYLE = "-fx-border-color: red;-fx-text-fill: red;-fx-pref-width: 400px;-fx-pref-height: 40px;-fx-padding: 0 0 0 55px";
    private static final String SETTING_RED_LABEL = "-fx-border-width: 2px;-fx-border-color: white;-fx-pref-height: 250px;-fx-pref-width: 250px;";
    private static final String SETTING_LABEL_RED = "-fx-border-width: 2px;-fx-border-color: red;-fx-pref-height: 250px;-fx-pref-width: 250px;";
    private static final String ERROR_LABEL_STYLE = "-fx-border-color: red;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: red;-fx-padding: 0 0 0 55";
    private static final String CONFIRM_SAVE_DATA = "Ваші дані збережені";
    private static final String REGISTRATION_LABEL_STYLE = "-fx-text-fill: #e2e2e2;-fx-font-size: 11pt;";
    private static final String MAIN_FIELD_STYLE_CLASS = "field";
    private static final String MAIN_PANE_STYLE_CLASS = "background";
    private static final String MAIN_MENU_BUTTON_STYLE_CLASS = "button";
    private static final String PATH_ICON_LOGO  = "images/main_icon3.png";
    private static final String PATH_STYLE_SHEETS = "/css/style.css";



    public void setting(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getStyleClass().add(MAIN_PANE_STYLE_CLASS);

        String[] splitNick;
        splitNick = dataBaseDriver.getNickname().split(" ");

        Button back = new Button();
        back.setId("btnBack");
        back.getStyleClass().add("back");
        back.setShape(new Circle(6));

        Button save = new Button("Зберегти");
        save.setId("btnSave");

        Label alertData = new Label(CONFIRM_SAVE_DATA);
        alertData.setId("lblAlertData");
        alertData.setStyle(SETTING_LABEL_GREEN_STYLE);
        alertData.setVisible(false);

        Label redLabelData = new Label();
        redLabelData.setId("lblRedLabelData");
        redLabelData.setStyle(SETTING_RED_LABEL);

        Label redLabelSystemData = new Label();
        redLabelSystemData.setId("lblRedLabelSystemData");
        redLabelSystemData.setStyle(SETTING_RED_LABEL);

        Label youData = new Label("Ваші дані");
        youData.setId("lblYouData");

        PasswordField youPassword = new PasswordField();
        youPassword.setId("pswYouPassword");
        youPassword.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label youPass = new Label("Ваш пароль");
        youPass.setId("lblYouPass");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setId("pswConfirmPassword");
        confirmPassword.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label youConfPass = new Label("Повторіть Пароль");
        youConfPass.setId("lblYouConfPass");

        Label changeSystemData = new Label("Системні дані");
        changeSystemData.setId("lblChangeSystemData");

        TextField youName = new TextField(splitNick[0]);
        youName.setId("txtYouName");
        youName.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label labelName = new Label("Ім'я");
        labelName.setId("lblLabelName");

        TextField youLName = new TextField(splitNick[1]);
        youLName.setId("txtYouLName");
        youLName.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label labelLName = new Label("Прізвище");
        labelLName.setId("lblLabelLName");

        youName.setTranslateX(-200);
        youName.setTranslateY(-20);
        labelName.setTranslateX(-300);
        labelName.setTranslateY(-50);

        youLName.setTranslateX(-200);
        youLName.setTranslateY(50);
        labelLName.setTranslateX(-285);
        labelLName.setTranslateY(20);

        youData.setTranslateX(-200);
        youData.setTranslateY(-100);

        youPassword.setTranslateX(200);
        youPassword.setTranslateY(-22);

        confirmPassword.setTranslateX(200);
        confirmPassword.setTranslateY(50);

        changeSystemData.setTranslateY(-100);
        changeSystemData.setTranslateX(200);

        youPass.setTranslateX(123);
        youPass.setTranslateY(-50);

        youConfPass.setTranslateY(20);
        youConfPass.setTranslateX(140);

        redLabelSystemData.setTranslateX(200);
        redLabelData.setTranslateX(-200);

        alertData.setTranslateY(-200);

        save.setTranslateY(200);

        back.setTranslateX(-430);
        back.setTranslateY(-280);

        back.setOnAction(event -> {
            primaryStage.close();
            appearanceMenu(primaryStage);
        });
        save.setOnAction(event -> {
            if (youName.getText().equals(splitNick[0]) && youLName.getText().equals(splitNick[1])) {
                if (youPassword.getText().equals("") && confirmPassword.getText().equals("")) {
                    alertData.setText("Ви не ввели нові дані");
                    alertData.setStyle(SETTING_LABEL_RED_STYLE);
                    alertData.setVisible(true);
                } else {
                    dataBaseDriver.editPassword(confirmPassword.getText());
                    alertData.setText(CONFIRM_SAVE_DATA);
                    alertData.setStyle(SETTING_LABEL_GREEN_STYLE);
                    alertData.setVisible(true);
                }
            } else {
                dataBaseDriver.editNickname(youName.getText() + " " + youLName.getText());
                if (youPassword.getText().equals("") && confirmPassword.getText().equals("")) {
                    alertData.setText(CONFIRM_SAVE_DATA);
                    alertData.setStyle(SETTING_LABEL_GREEN_STYLE);
                    alertData.setVisible(true);
                } else {
                    dataBaseDriver.editPassword(confirmPassword.getText());
                    alertData.setText(CONFIRM_SAVE_DATA);
                    alertData.setStyle(SETTING_LABEL_GREEN_STYLE);
                    alertData.setVisible(true);
                }
            }
        });

        redLabelData.setOnMouseClicked(event -> {
            redLabelData.setStyle(SETTING_LABEL_RED);
            redLabelSystemData.setStyle(SETTING_RED_LABEL);
        });
        redLabelSystemData.setOnMouseClicked(event -> {
            redLabelData.setStyle(SETTING_RED_LABEL);
            redLabelSystemData.setStyle(SETTING_LABEL_RED);
        });

        Image ico = new Image(PATH_ICON_LOGO);
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Settings");
        primaryStage.setFullScreen(false);

        root.getChildren().addAll(save, redLabelSystemData, youConfPass, youPass, confirmPassword, youPassword, alertData, redLabelData, youData, changeSystemData, youLName, youName, labelLName, labelName, back);
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setScene(scene);
        primaryStage.show();


        scene.getStylesheets().add(PATH_STYLE_SHEETS);
    }

    private void registration(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getStyleClass().add(MAIN_PANE_STYLE_CLASS);

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
        inputFName.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label firstName = new Label("Ваше Ім'я");
        firstName.setStyle(REGISTRATION_LABEL_STYLE);

        TextField inputLName = new TextField();
        inputLName.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label lastName = new Label("Прізвище");
        lastName.setStyle(REGISTRATION_LABEL_STYLE);

        TextField inputLogin = new TextField();
        inputLogin.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label labelLogin = new Label("Логін");
        labelLogin.setStyle(REGISTRATION_LABEL_STYLE);

        PasswordField inputPassword = new PasswordField();
        inputPassword.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label labelPassword = new Label("Пароль");
        labelPassword.setStyle(REGISTRATION_LABEL_STYLE);

        Button next = new Button("Дальше");
        next.getStyleClass().add("login");
        next.setStyle("-fx-background-color: #1d1d1d;");
        next.setDefaultButton(true);

        Label errorsLabel = new Label();
        errorsLabel.setStyle(ERROR_LABEL_STYLE);
        errorsLabel.setVisible(false);

        Label secretQuestion = new Label("Секретне питання: ");
        secretQuestion.setStyle(REGISTRATION_LABEL_STYLE);
        secretQuestion.setVisible(false);

        Label answerQuestion = new Label("Відповідь на питання: ");
        answerQuestion.setStyle(REGISTRATION_LABEL_STYLE);
        answerQuestion.setVisible(false);

        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Ваша улюблена марка автомобіля",
                        "Назва вашого улюбленця",
                        "Дівоче прізвище матері",
                        "Ваша улюблена річ",
                        "Ім'я вашого дідуся",
                        "Ім'я вашої бабусі"
                );
        final ComboBox comboBox = new ComboBox(options);
        comboBox.setValue("-Виберіть питання-");
        comboBox.setVisible(false);

        TextField answer = new TextField();
        answer.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
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

        back.setOnAction(event -> authorization(primaryStage) );
        next.setOnMouseEntered(event -> next.setStyle("-fx-background-color: #3a3a3a;"));
        next.setOnMouseExited(event -> next.setStyle("-fx-background-color: #1d1d1d"));

        errorsLabel.setText("Заповніть всі поля форми реєстрації");
        errorsLabel.setStyle("-fx-border-color: green;-fx-pref-height: 35px;-fx-pref-width: 300px;-fx-text-fill: green;-fx-padding: 0 0 0 45");
        errorsLabel.setVisible(true);

        next.setOnAction(event -> {
            if ((inputFName.getText().trim().isEmpty()) || inputLName.getText().trim().isEmpty() || inputLogin.getText().trim().isEmpty() || inputPassword.getText().trim().isEmpty()) {
                errorsLabel.setText("Деякі поля у формі не заповненні");
                errorsLabel.setStyle(ERROR_LABEL_STYLE);
                errorsLabel.setVisible(true);
            } else {
                if (dataBaseDriver.searchPerson(inputLogin.getText())) {
                    errorsLabel.setText("Логін який ви ввели вже існує");
                    errorsLabel.setStyle(ERROR_LABEL_STYLE);
                    errorsLabel.setVisible(true);
                } else {
                    redLabelOne.setTranslateX(0);

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
                            errorsLabel.setStyle(ERROR_LABEL_STYLE);
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

                            dataBaseDriver.registration(inputFName.getText() + " " + inputLName.getText(), inputLogin.getText(), inputPassword.getText());
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

        scene.getStylesheets().add(PATH_STYLE_SHEETS);
        Image ico = new Image(PATH_ICON_LOGO);
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Registration");
        primaryStage.setScene(scene);
    }

    public void authorization(Stage primaryStage) {

        StackPane root = new StackPane();

        root.getStyleClass().add(MAIN_PANE_STYLE_CLASS);

        TextField loginField = new TextField();
        Label loginLabel = new Label("Логін: ");
        loginField.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        loginLabel.setStyle(REGISTRATION_LABEL_STYLE);

        PasswordField passwordField = new PasswordField();
        passwordField.getStyleClass().add(MAIN_FIELD_STYLE_CLASS);
        Label passwordLabel = new Label("Пароль: ");
        passwordLabel.setStyle(REGISTRATION_LABEL_STYLE);

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
        errorsLabel.setStyle(ERROR_LABEL_STYLE);
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

        loginButton.setOnMouseEntered(event -> loginButton.setStyle("-fx-background-color: #3a3a3a;"));
        loginButton.setOnMouseExited(event -> loginButton.setStyle("-fx-background-color: #1d1d1d"));

        loginButton.setOnAction((ActionEvent event) -> {
            loginButton.setStyle("-fx-background-color: #3a3a3a; -fx-text-fill: #1d1d1d");
            if ((loginField.getText().trim().isEmpty()) || passwordField.getText().trim().isEmpty()) {
                errorsLabel.setText("Деякі поля у формі не заповнені");
                errorsLabel.setVisible(true);
            } else {
                errorsLabel.setVisible(false);
                String logIn = loginField.getText();
                String password = passwordField.getText();

                if (dataBaseDriver.authorization(logIn, password)) {
                    primaryStage.close();
                    appearanceMenu(primaryStage);
                } else {
                    errorsLabel.setText("Нажаль! Користувача не знайдено");
                    errorsLabel.setVisible(true);
                }
            }

        });
        registration.setOnAction(event -> registration(primaryStage));

        root.getChildren().addAll(errorsLabel, login, loginButton, loginField, loginLabel, passwordField, passwordLabel, registration);

        final Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add(PATH_STYLE_SHEETS);
        Image ico = new Image(PATH_ICON_LOGO);
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Log in");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void appearanceMenu(Stage primaryStage) {
        StackPane root = new StackPane();
        root.getStyleClass().add(MAIN_PANE_STYLE_CLASS);

        vBox.getStyleClass().add("accountInfo");

        Button goTesting = new Button("Go Testing");
        goTesting.setId("btnGoTesting");
        Label testTesting = new Label("Перейшовши по цій кнопці ви порпте на меню. Де ви можете вибрати мову по якій хочете пройти тест");
        testTesting.setId("lblTestTesting");
        testTesting.getStyleClass().add("labelTesting");
        goTesting.getStyleClass().add(MAIN_MENU_BUTTON_STYLE_CLASS);

        Button goStudy = new Button("Go study");
        goStudy.setId("btnGoStudy");
        Label testStudy = new Label("Чогось не знаєте? Перейдіть по цій кнопці, та виберіть мову і тему яку ви не розумієте");
        testStudy.setId("lblTestStudy");
        goStudy.getStyleClass().add(MAIN_MENU_BUTTON_STYLE_CLASS);
        testStudy.getStyleClass().add("labelStudy");

        Button createTest = new Button("Create Test");
        createTest.setId("btnCreateTest");
        Label testCreateTest = new Label("Бажаєте провірити друга? Можете створити свої тести і дати пройти його своєму другові)))");
        testCreateTest.setId("lblTestCreateTest");
        createTest.getStyleClass().add(MAIN_MENU_BUTTON_STYLE_CLASS);
        testCreateTest.getStyleClass().add("labelTesting");

        Button exit = new Button("");
        exit.setId("btnExit");
        exit.getStyleClass().add("exit");

        Button settings = new Button("");
        settings.setId("btnSettings");
        settings.getStyleClass().add("settings");

        Button swapAccount = new Button("");
        swapAccount.setId("btnSwapAccount");
        swapAccount.getStyleClass().add("swapAccount");

        Button user = new Button("User file Test");
        user.setId("btnUser");
        Label testUser = new Label("Якщо у вас є готовий файл з тестами! То ви можете загрузити в програму перейшовши по цій кнопці");
        testUser.setId("lblTestUser");
        user.getStyleClass().add(MAIN_MENU_BUTTON_STYLE_CLASS);
        testUser.getStyleClass().add("labelStudy");

        Label label12 = new Label();
        label12.setId("lblLabel12");
        label12.setStyle("-fx-text-fill: white;");
        label12.getStyleClass().add("label2");
        label12.setText(dataBaseDriver.getNickname());
        label12.setOnMouseClicked(event -> WindowMessage.winInfo("Кількість проведених тестувань: " + dataBaseDriver.getTestings() + "\n"
                    + "Відсоток правильний відповідей: " + dataBaseDriver.getPercent() + " %"));
        Label title = new Label();
        title.setId("lblTitle");
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
            primaryStage.close();
            menuLanguage.menuLanguageBackground(dataBaseDriver, primaryStage);
        });
        goStudy.setOnAction(event -> {
            primaryStage.close();
            windowStudy.startStudy(dataBaseDriver, primaryStage);
        });
        createTest.setOnAction(event -> testCreator.createFileWindow(primaryStage));
        exit.setOnAction(event -> {
            primaryStage.close();
            System.exit(0);
        });
        user.setOnAction(event -> {
            String pathOfFile = userTest.getPathOfChosenFile(primaryStage);
            if (pathOfFile != null) {
                testGUI.playTest(dataBaseDriver, primaryStage, pathOfFile, false);
            }
        });
        settings.setOnMouseEntered(event -> {
            title.setText("Настройки");
            title.setTranslateX(-330);
        });
        settings.setOnMouseExited(event -> title.setText(""));
        settings.setOnAction(event -> setting(primaryStage));
        swapAccount.setOnAction(event -> authorization(primaryStage));
        swapAccount.setOnMouseEntered(event -> {
            title.setText("Зміна Користувача");
            title.setTranslateX(-300);
        });
        swapAccount.setOnMouseExited(event -> title.setText(""));
        exit.setOnMouseEntered(event -> {
            title.setText("Вихід");
            title.setTranslateX(-275);
            exit.setStyle("-fx-background-color: transparent");
        });
        exit.setOnMouseExited(event -> title.setText(""));

        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add(PATH_STYLE_SHEETS);
        Image ico = new Image(PATH_ICON_LOGO);
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Main menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

