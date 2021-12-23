package hourreporter.ui;

import hourreporter.domain.UserService;
import hourreporter.domain.Week;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.util.List;

public class WeekSelectionPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private TextField weekInput;

    @FXML
    private Label errorMessage;

    @FXML
    private Label createdWeeks;

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void proceedToWeekCreation() throws Exception {
        application.initializeWeekCreationScene();
        application.setWeekCreationScene();
    }

    @FXML
    private void goBackToLandingPage() {
        userService.logout();
        application.setLandingPageScene();
    }

    @FXML
    private void handleWeekSelection() throws Exception {
        if (weekInput.getText().matches("-?\\d+")
                && Integer.valueOf(weekInput.getText()) > 0
                && Integer.valueOf(weekInput.getText()) < 53
                && userService.loadSavedWeeksForUser(userService.getUser().getUserNumber()).getWeek(Integer.valueOf(weekInput.getText())) != null) {
            int weekNumber = Integer.parseInt(weekInput.getText());
            userService.openExistingWeek(weekNumber);
            application.initializeWeekModifyingScene();
            application.setWeekModificationScene();
        } else {
            printErrorMessage("Invalid input, selected week doesn't exist or something went wrong.");
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

    public void setCreatedWeeks() throws Exception {
        List<Week> weeks = userService.getAllWeeks(userService.getUser().getUserNumber());
        String displayedString = "";
        if (!weeks.isEmpty()) {
            if (weeks.size() == 1) {
                displayedString = "You have created week ";
                for (Week w : weeks) {
                    displayedString = displayedString + String.valueOf(w.getWeekNumber());
                }
                createdWeeks.setText(displayedString);
            } else {
                displayedString = "You have created weeks ";
                for (Week w : weeks) {
                    displayedString = displayedString + " " + String.valueOf(w.getWeekNumber()) + ", ";
                }
            }
            StringBuffer sb= new StringBuffer(displayedString);
            sb.deleteCharAt(sb.length()-1);
            sb.deleteCharAt(sb.length()-1);
            sb = sb.append('.');
            String newString = sb.toString();
            createdWeeks.setText(newString);
        } else {
            displayedString = "You have not yet created and saved any weeks.";
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

    @FXML
    public void handleEnterPressed(KeyEvent k) throws Exception {
        if (k.getCode().equals(KeyCode.ENTER)) {
            handleWeekSelection();
        }
    }

    @FXML
    private void quitProgram() {
        System.exit(0);
    }
}
