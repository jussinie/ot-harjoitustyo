package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

/**
 * Controller class to handle actions on main page of the application.
 */
public class MainPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private Label welcomeText;

    /**
     * Method to inject the ReporterGraphUI Application.
     * @param application Instance of ReporterGraphUI.
     */
    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    /**
     * Method to inject userService instance to this class.
     * @param userService instance of UserService class.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to set week selection scene.
     * @throws Exception
     */
    @FXML
    private void selectWeekSelectionScene() throws Exception {
        application.initializeWeekSelectionScene();
        application.setWeekSelectionScene();
    }

    /**
     * Method to set landing page scene.
     */
    @FXML
    private void goBackToLandingPage() {
        userService.logout();
        application.setLandingPageScene();
    }

    /**
     * Method to set week creation scene.
     * @throws Exception
     */
    @FXML
    private void selectWeekCreationScene() throws Exception {
        application.initializeWeekCreationScene();
        application.setWeekCreationScene();
    }
    /**
     * Method to quit the program.
     */
    @FXML
    private void quitProgram() {
        System.exit(0);
    }

    @FXML
    public void openWebpage() {
        String url = "https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/kayttoohje.md";
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method sets welcome text that user sees when login is succesful.
     */
    public void setWelcomeText() {
        welcomeText.setText("Welcome, " + userService.getUser().getFirstName() + " " + userService.getUser().getLastName() + "\n"
                + "Select from the top left what you want to do.");
    }
}
