package hourreporter.ui;

public class LandingPageController {
    private ReporterGraphUI application;


    public void goToLogin() {
        application.setLoginPageScene();
    }

    public void goToUserCreation() {
        application.setUserCreationScene();
    }

    public void setApplication(ReporterGraphUI application) {
        this.application = application;
    }

    public void exitButtonClicked() {
        System.exit(0);
    }

}
