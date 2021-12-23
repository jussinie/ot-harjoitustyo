package hourreporter.ui;

import hourreporter.domain.UserService;
import hourreporter.domain.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WeekModificationPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private TextField mondayHours;

    @FXML
    private TextField tuesdayHours;

    @FXML
    private TextField wednesdayHours;

    @FXML
    private TextField thursdayHours;

    @FXML
    private TextField fridayHours;

    @FXML
    private TextField saturdayHours;

    @FXML
    private TextField sundayHours;

    @FXML
    private Label nowModifying;

    @FXML
    private Label summaryLabel;

    @FXML
    private Label errorMessage;

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void proceedToWeekCreation() {
        setErrorMessageToEmpty();
        application.setWeekCreationScene();
    }

    public void proceedToWeekSelection() throws Exception {
        setErrorMessageToEmpty();
        application.initializeWeekSelectionScene();
        application.setWeekSelectionScene();
    }

    @FXML
    private void goBackToLandingPage() {
        setErrorMessageToEmpty();
        userService.logout();
        application.setLandingPageScene();
    }

    public void showWeeksHours() {
        nowModifying.setText("Now modifying Week " + userService.getWeek().getWeekNumber());
        mondayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Mon")));
        tuesdayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Tue")));
        wednesdayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Wed")));
        thursdayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Thu")));
        fridayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Fri")));
        saturdayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Sat")));
        sundayHours.setText(String.valueOf(userService.getWeek().getDaysHoursForWeek("Sun")));
        summaryLabel.setText("Your total hours for the week: " + userService.getWeek().countWorkHours() + " | Balance: " + (userService.getWeek().countWorkHours()-37.5));
    }

    public void saveWeeksHours() throws Exception {
        Week week = userService.getWeek();
        HashMap<String, String> hourInputForInspection = new HashMap<>();
        hourInputForInspection.put("Mon", mondayHours.getText());
        hourInputForInspection.put("Tue", tuesdayHours.getText());
        hourInputForInspection.put("Wed", wednesdayHours.getText());
        hourInputForInspection.put("Thu", thursdayHours.getText());
        hourInputForInspection.put("Fri", fridayHours.getText());
        hourInputForInspection.put("Sat", saturdayHours.getText());
        hourInputForInspection.put("Sun", sundayHours.getText());

        if (userService.inspectInput(hourInputForInspection)) {
            for (Map.Entry<String, String> entry : hourInputForInspection.entrySet()) {
                week.setDay(entry.getKey(), Double.parseDouble(entry.getValue()));
                userService.setWeek(week);
                userService.saveHours(week);
                showWeeksHours();
                errorMessage.setText("");
            }
        } else {
            errorMessage.setText("Please check your input. Hours have to be reported in form X.X");
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
            saveWeeksHours();
        }
    }

    @FXML
    private void quitProgram() {
        System.exit(0);
    }

    public void setErrorMessageToEmpty() {
        errorMessage.setText("");
    }
}
