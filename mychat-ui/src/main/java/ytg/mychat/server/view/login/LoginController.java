package ytg.mychat.server.view.login;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-18
 **/
public class LoginController implements LoginMethod{

    private LoginView loginView;

    private LoginEventDefine loginEventDefine;

    public LoginController(LoginEventHandler eventHandler) {
        this.loginView = new LoginView();
        this.loginEventDefine = new LoginEventDefine(loginView,eventHandler);
    }


    @Override
    public void doShow() {
        loginView.show();
    }

    @Override
    public void doLoginError() {

    }

    @Override
    public void doLoginSuccess() {

    }
}
