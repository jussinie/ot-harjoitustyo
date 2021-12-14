package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.fxml.FXML;

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

}
