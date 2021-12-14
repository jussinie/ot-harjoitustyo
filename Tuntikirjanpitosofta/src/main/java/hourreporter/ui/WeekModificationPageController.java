package hourreporter.ui;

import hourreporter.domain.UserService;
import hourreporter.domain.Week;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

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

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void proceedToWeekCreation() throws Exception {
        application.setWeekCreationScene();
    }

    public void proceedToWeekSelection() throws Exception {
        application.initializeWeekSelectionScene();
        application.setWeekSelectionScene();
    }

    @FXML
    private void goBackToLandingPage() {
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
        week.setDay("Mon", Double.parseDouble(mondayHours.getText()));
        week.setDay("Tue", Double.parseDouble(tuesdayHours.getText()));
        week.setDay("Wed", Double.parseDouble(wednesdayHours.getText()));
        week.setDay("Thu", Double.parseDouble(thursdayHours.getText()));
        week.setDay("Fri", Double.parseDouble(fridayHours.getText()));
        week.setDay("Sat", Double.parseDouble(saturdayHours.getText()));
        week.setDay("Sun", Double.parseDouble(sundayHours.getText()));
        userService.setWeek(week);
        userService.saveHours(week);
        showWeeksHours();
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
    private void quitProgram() {
        System.exit(0);
    }
}
