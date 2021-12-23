package hourreporter.ui;

import javafx.fxml.FXML;

import java.io.IOException;

public class LandingPageController {
    private ReporterGraphUI application;

    public void goToLogin() {
        application.setLoginPageScene();
    }

    public void goToUserCreation() {
        application.setUserCreationScene();
    }

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

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

}
