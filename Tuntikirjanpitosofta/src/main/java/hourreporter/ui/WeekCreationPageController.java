package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class WeekCreationPageController {
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
    private void goBackToLandingPage() {
        application.setLandingPageScene();
    }

    public void proceedToWeekSelection() throws Exception {
        application.initializeWeekSelectionScene();
        application.setWeekSelectionScene();
    }

    @FXML
    private void handleWeekCreation() throws Exception {
        if (weekInput.getText().matches("-?\\d+")) {
            int weekNumber = Integer.parseInt(weekInput.getText());
            userService.createWeek(userService, weekNumber);
            System.out.println("Week created!");
            application.initializeWeekModifyingScene();
            application.setWeekModificationScene();
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
            errorMessage.setText("Invalid input or something went wrong.");
            new Thread(sleeper).start();
        }
    }

    @FXML
    private void exitButtonClicked() {
        System.exit(0);
    }

}

