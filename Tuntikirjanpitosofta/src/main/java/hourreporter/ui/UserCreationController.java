package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserCreationController {
    private ReporterGraphUI application;
    private UserService us;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField username;

    @FXML
    private TextField role;

    @FXML
    private TextField team;

    @FXML
    private CheckBox isTeamLead;

    @FXML
    private Label errorMessage;

    public void handleUserCreation(ActionEvent event) throws Exception {
        us.createUser(firstName.getText(), lastName.getText(), username.getText(), role.getText(), team.getText());
        if (us.getUser() != null) {
            firstName.setText("");
            lastName.setText("");
            username.setText("");
            role.setText("");
            team.setText("");
            proceedToMainPage();
        } else {
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
            errorMessage.setText("Username exists already! Please use another one.");
            new Thread(sleeper).start();
        }
    }

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService us) {
        this.us = us;
    }

    public void goBackToLandingPage() {
        application.setLandingPageScene();
    }

    public void proceedToMainPage() throws Exception {
        application.initializeMainPageScene();
        application.setMainPageScene();
    }

    public void exitButtonClicked() {
        System.exit(0);
    }

}
