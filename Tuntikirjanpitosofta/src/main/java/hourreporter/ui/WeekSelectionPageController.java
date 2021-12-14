package hourreporter.ui;

import hourreporter.domain.UserService;
import hourreporter.domain.Week;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class WeekSelectionPageController {
    private ReporterGraphUI application;
    private UserService userService;
    private List<Week> weeks;

    @FXML
    private TextField weekInput;

    @FXML
    private Label errorMessage;

    @FXML
    private Label createdWeeks;

    @FXML
    private ListView<String> listView;

    public ObservableList<String> weekNumbers =
            FXCollections.observableArrayList("test", "shit", "1", "2", "3");

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void proceedToWeekCreation() throws Exception {
        application.setWeekCreationScene();
    }


    @FXML
    private void goBackToLandingPage() {
        userService.logout();
        application.setLandingPageScene();
    }

    @FXML
    private void handleWeekSelection() throws Exception {
        if (weekInput.getText().matches("-?\\d+")) {
            int weekNumber = Integer.parseInt(weekInput.getText());
            userService.openExistingWeek(userService, weekNumber);
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

    public void setWeekList() throws Exception {
        listView = new ListView<String>(weekNumbers);
        //listView.setItems((ObservableList) weekNumbers);
        listView.setOrientation(Orientation.HORIZONTAL);
        listView.setCellFactory(ComboBoxListCell.forListView(weekNumbers));
        List<Week> weeks = userService.getAllWeeks();
        List<String> helper = new ArrayList<>();
        for (Week w : weeks) {
            helper.add(String.valueOf(w.getWeekNumber()));
        }
        //weekNumbers.addAll(helper);
        //List<String> values = Arrays.asList(helper);
        listView.setItems(FXCollections.observableList(helper));
    }

    public void setCreatedWeeks() throws Exception {
        List<Week> weeks = userService.getAllWeeks();
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

    /*
    public void initialize() throws Exception {
        //List<Week> weeks = userService.getAllWeeks();
        List<String> helper = new ArrayList<>();
        for (Week w : weeks) {
            helper.add(String.valueOf(w.getWeekNumber()));
        }
        //weekNumbers.addAll(helper);
        //List<String> values = Arrays.asList(helper);
        listView.setItems(FXCollections.observableList(helper));
    } */

    @FXML
    private void quitProgram() {
        System.exit(0);
    }
}
