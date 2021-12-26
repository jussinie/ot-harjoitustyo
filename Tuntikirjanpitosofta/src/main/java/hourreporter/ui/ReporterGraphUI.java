package hourreporter.ui;

import hourreporter.dao.DatabaseSelector;
import hourreporter.dao.UserDao;
import hourreporter.dao.WeekDao;
import hourreporter.domain.UserService;
import javafx.application.Application;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Class for the graphical UI.
 */
public class ReporterGraphUI extends Application {
    private Stage window;
    private Scene landingPageScene;
    private Scene userCreationScene;
    private Scene loginPageScene;
    private Scene mainPageScene;
    private Scene weekCreationScene;
    private Scene weekSelectionScene;
    private Scene weekModificationScene;
    private UserService us;

    @Override
    public void init() throws Exception {
        DatabaseSelector dbs = new DatabaseSelector();
        String connectionString = dbs.getConnectionString(String.valueOf(this.getParameters()));
        UserDao ud = new UserDao(connectionString);
        WeekDao wd = new WeekDao(connectionString);
        us = new UserService(ud, wd);

        // Create Scene for landing page
        FXMLLoader landingPageLoader = new FXMLLoader(getClass().getResource("/landingPage.fxml"));
        Parent landingPagePane = landingPageLoader.load();
        LandingPageController landingPageController = landingPageLoader.getController();
        // injektoi tässä sovelluslogiikka LPC.setUserService(userService);
        landingPageController.setApplication(this);
        landingPageScene = new Scene(landingPagePane);

        // Create Scene for user creation page
        FXMLLoader userCreationLoader = new FXMLLoader(getClass().getResource("/userCreation.fxml"));
        Parent userCreationPane = userCreationLoader.load();
        UserCreationController userCreationController = userCreationLoader.getController();
        userCreationController.setUserService(us);
        userCreationController.setApplication(this);
        userCreationScene = new Scene(userCreationPane);

        // Create Scene for login page
        FXMLLoader loginPageLoader = new FXMLLoader(getClass().getResource("/loginPage.fxml"));
        Parent loginPagePane = loginPageLoader.load();
        LoginPageController loginPageController = loginPageLoader.getController();
        loginPageController.setUserService(us);
        loginPageController.setApplication(this);
        loginPageScene = new Scene(loginPagePane);
    }

    @Override
    public void start(Stage window) {
        this.window = window;
        window.setTitle("Hour Reporter");
        setLandingPageScene();
        window.show();
    }

    /**
     * This method sets
     */
    public void setLandingPageScene() {
        window.setScene(landingPageScene);
    }

    public void setUserCreationScene() {
        window.setScene(userCreationScene);
    }

    public void setLoginPageScene() {
        window.setScene(loginPageScene);
    }

    public void setMainPageScene() {
        window.setScene(mainPageScene);
    }

    public void setWeekCreationScene() {
        window.setScene(weekCreationScene);
    }

    public void setWeekSelectionScene() {
        window.setScene(weekSelectionScene);
    }

    public void setWeekModificationScene() {
        window.setScene(weekModificationScene);
    }

    public void initializeMainPageScene() throws Exception {
        // Create Scene for main page
        FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("/hourReporterMainPage.fxml"));
        Parent mainPagePane = mainPageLoader.load();
        MainPageController mainPageController = mainPageLoader.getController();
        mainPageController.setUserService(us);
        mainPageController.setApplication(this);
        mainPageController.setWelcomeText();
        mainPageScene = new Scene(mainPagePane);
    }

    public void initializeWeekCreationScene() throws Exception {
        // Create Scene for creating new weeks but use main page controller
        FXMLLoader weekCreationPageLoader = new FXMLLoader(getClass().getResource("/weekCreationPage.fxml"));
        Parent weekCreationPane = weekCreationPageLoader.load();
        WeekCreationPageController weekCreationPageController = weekCreationPageLoader.getController();
        weekCreationPageController.setUserService(us);
        weekCreationPageController.setApplication(this);
        weekCreationScene = new Scene(weekCreationPane);
    }

    public void initializeWeekSelectionScene() throws Exception {
        // Create Scene for creating new weeks but use main page controller
        FXMLLoader weekSelectionPageLoader = new FXMLLoader(getClass().getResource("/weekSelectionPage.fxml"));
        Parent weekSelectionPane = weekSelectionPageLoader.load();
        WeekSelectionPageController weekSelectionPageController = weekSelectionPageLoader.getController();
        weekSelectionPageController.setUserService(us);
        weekSelectionPageController.setApplication(this);
        weekSelectionPageController.setCreatedWeeks();
        weekSelectionScene = new Scene(weekSelectionPane);
    }

    public void initializeWeekModifyingScene() throws Exception {
        // Create Scene for creating new weeks but use main page controller
        FXMLLoader weekModificationPageLoader = new FXMLLoader(getClass().getResource("/weekModificationPage.fxml"));
        Parent weekModificationPane = weekModificationPageLoader.load();
        WeekModificationPageController weekModificationPageController = weekModificationPageLoader.getController();
        weekModificationPageController.setUserService(us);
        weekModificationPageController.setApplication(this);
        weekModificationPageController.showWeeksHours();
        weekModificationScene = new Scene(weekModificationPane);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
