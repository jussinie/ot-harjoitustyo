package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField username;

    public void handleLogin() throws Exception {
        userService.login(username.getText());
        if (userService.getUser() != null) {
            username.setText("");
            proceedToMainPage();
        }
        // Could not work this threading in Java FX out myself.
        // This technique for waiting was loaned from here: https://stackoverflow.com/questions/26454149/make-javafx-wait-and-continue-with-code
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                errorMessage.setText("");
            }
        });
        errorMessage.setText("Username not found or something went wrong.");
        new Thread(sleeper).start();
    }

    public void handleEnterPressed(KeyEvent k) throws Exception {
        if (k.getCode().equals(KeyCode.ENTER)) {
            handleLogin();
        }
    }

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @FXML
    public void proceedToMainPage() throws Exception {
        application.initializeMainPageScene();
        application.setMainPageScene();
    }

    @FXML
    private void goBackToLandingPage() {
        application.setLandingPageScene();
    }

    @FXML
    private void quitProgram() {
        System.exit(0);
    }
}
