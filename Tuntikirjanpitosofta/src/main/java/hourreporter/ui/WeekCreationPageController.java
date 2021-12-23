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

import java.io.IOException;

public class WeekCreationPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private Label errorMessage;

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
        userService.logout();
        application.setLandingPageScene();
    }

    @FXML
    private void quitProgram() {
        System.exit(0);
    }

    public void proceedToWeekSelection() throws Exception {
        application.initializeWeekSelectionScene();
        application.setWeekSelectionScene();
    }

    @FXML
    private void handleWeekCreation() throws Exception {
        if (weekInput.getText().matches("-?\\d+")
                && Integer.valueOf(weekInput.getText()) > 0
                && Integer.valueOf(weekInput.getText()) < 53) {
            int weekNumber = Integer.parseInt(weekInput.getText());
            userService.createWeek(weekNumber);
            application.initializeWeekModifyingScene();
            application.setWeekModificationScene();
        } else {
            printErrorMessage("Invalid input or something went wrong.");
        }
    }

    private void printErrorMessage(String error) {
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
        errorMessage.setText(error);
        new Thread(sleeper).start();
    }

    public void handleEnterPressed(KeyEvent k) throws Exception {
        if (k.getCode().equals(KeyCode.ENTER)) {
            handleWeekCreation();
        }
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

