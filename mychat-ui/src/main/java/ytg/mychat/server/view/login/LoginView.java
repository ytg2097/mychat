package ytg.mychat.server.view.login;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import ytg.mychat.server.view.AbstractView;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-18
 **/
public class LoginView extends AbstractView {

    private static final String RESOURCE_NAME = "/fxml/login/login.fxml";
    private static final String ICON_URL = "/fxml/chat/img/head/logo.png";
    public Button login_close;
    public Button login_min;
    public Button login_button;
    public TextField userId;
    public PasswordField password;

    public LoginView() {
        super(RESOURCE_NAME,ICON_URL);
    }

    @Override
    protected void obtain() {
        login_min = $("login_min");
        login_close = $("login_close");
        userId = $("userId");
        password = $("password");
        login_button = $("login_button");
    }

    @Override
    public void initView() {
        initRoot();
    }
}
