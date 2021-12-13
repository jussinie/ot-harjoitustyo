package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private Label errorMessage;

    @FXML
    private Label welcomeText;

    @FXML
    private TextField weekInput;

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    private void selectWeekSelectionScene() throws Exception {
        application.initializeWeekSelectionScene();
        application.setWeekSelectionScene();
    }

    @FXML
    private void goBackToLandingPage() {
        application.setLandingPageScene();
    }

    @FXML
    private void selectWeekCreationScene() throws Exception {
        application.initializeWeekCreationScene();
        application.setWeekCreationScene();
    }

    @FXML
    private void exitButtonClicked() {
        System.exit(0);
    }

    public void setWelcomeText() {
        welcomeText.setText("Welcome, " + userService.getUser().getFirstName() + " " + userService.getUser().getLastName());
    }
}
