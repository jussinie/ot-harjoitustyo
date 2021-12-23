package hourreporter.ui;

import javafx.fxml.FXML;

import java.io.IOException;

/**
 * Controller class presenting all the methods for the landing page of the application.
 */
public class LandingPageController {

    /**
     * An instance of ReporterGraphUi to enable setting different scenes from the landing page.
     */
    private ReporterGraphUI application;

    /**
     * Method to set the login page scene to stage.
     */
    public void goToLogin() {
        application.setLoginPageScene();
    }

    /**
     * Method to set user creation page scene to stage.
     */
    public void goToUserCreation() {
        application.setUserCreationScene();
    }

    /**
     * Method to inject the ReporterGraphUI Application.
     * @param application
     */
    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    /**
     * Method to quit the program.
     */
    @FXML
    private void quitProgram() {
        System.exit(0);
    }

    /**
     * Method to open user manual in default browser.
     */
    @FXML
    public void openWebpage() {
        String url = "https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/kayttoohje.md";
        try {
            new ProcessBuilder("x-www-browser", url).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
