package hourreporter.ui;

import hourreporter.domain.UserService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
/**
 * This class contains all the methods and actions for the user creation page.
 */
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
    private Label errorMessage;

    /**
     * This method takes user input and tries to create a new User instance using method createUser in UserService class.
     * If succesful, main page is shown for the user. If not succesful, user sees an error message.
     * @throws Exception
     */
    public void handleUserCreation() throws Exception {
        String result = us.createUser(firstName.getText(), lastName.getText(), username.getText(), role.getText(), team.getText());
        if (result.equals("ok")) {
            firstName.setText("");
            lastName.setText("");
            username.setText("");
            role.setText("");
            team.setText("");
            proceedToMainPage();
        } else if (result.equals("checkInput")) {
            printErrorMessage("First, last and username must be at least 2 characters long.");
        } else if (result.equals("userExists")) {
            printErrorMessage("Username exists already! Please use another one.");
        }
    }

    /**
     * This method is used to print an error message if the user cannot be created.
     * @param error parameter that is outputted from handleUserCreation() method.
     */
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

    /**
     * Method to proceed with the input if Enter key is pressed.
     * @param k
     * @throws Exception
     */
    public void handleEnterPressed(KeyEvent k) throws Exception {
        if (k.getCode().equals(KeyCode.ENTER)) {
            handleUserCreation();
        }
    }

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void setUserService(UserService us) {
        this.us = us;
    }

    /**
     * Method to return to the landing page.
     */
    public void goBackToLandingPage() {
        application.setLandingPageScene();
    }

    public void proceedToMainPage() throws Exception {
        application.initializeMainPageScene();
        application.setMainPageScene();
    }

    /**
     * Method to quit the program.
     */
    @FXML
    private void quitProgram() {
        System.exit(0);
    }

    /**
     * Method to open user manual in default browser.
     */
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
