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

/**
 * This method is responsible of all the actions in the login page.
 */
public class LoginPageController {
    private ReporterGraphUI application;
    private UserService userService;

    @FXML
    private Label errorMessage;

    @FXML
    private TextField username;

    /**
     * This method takes user input (username) and tries to execute method login() in UserService.
     * If the user is found method proceedToMainPage() is executed and main page is presented for the user.
     * @throws Exception
     */
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

    /**
     * Method to proceed with the input if Enter key is pressed.
     * @param k
     * @throws Exception
     */
    public void handleEnterPressed(KeyEvent k) throws Exception {
        if (k.getCode().equals(KeyCode.ENTER)) {
            handleLogin();
        }
    }

    /**
     * Method to inject the ReporterGraphUI Application.
     * @param application Instance of ReporterGraphUI.
     */
    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    /**
     * Method to inject userService instance to this class.
     * @param userService instance of UserService class.
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Method to proceed to the main page of the application, i.e. setting the main page scene.
     * @throws Exception
     */
    @FXML
    public void proceedToMainPage() throws Exception {
        application.initializeMainPageScene();
        application.setMainPageScene();
    }

    /**
     * Method to return to the landing page.
     */
    @FXML
    private void goBackToLandingPage() {
        application.setLandingPageScene();
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

    /**
     * Method to quit the program.
     */
    @FXML
    private void quitProgram() {
        System.exit(0);
    }
}
