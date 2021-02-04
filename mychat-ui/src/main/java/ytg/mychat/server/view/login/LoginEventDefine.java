package ytg.mychat.server.view.login;

/**
 * @description:
 * 在这里为设置登陆窗口中每个按钮的钩子函数
 * 钩子函数可能在初始化时设置, 也可能在之后的某个时刻设置
 * @author: yangtg
 * @create: 2021-01-18
 **/
public class LoginEventDefine {

    private LoginEventHandler eventHandler;
    private LoginView view;

    public LoginEventDefine(LoginView view, LoginEventHandler eventHandler ) {
        this.view = view;
        this.eventHandler = eventHandler;

        setMin();
        setQuit();
        setLoginEvent();
    }

    private void setLoginEvent() {

        view.login_button.setOnAction(event -> eventHandler.doLoginCheck(view.userId.getText(),view.password.getText()));
    }

    private void setQuit() {

        view.login_close.setOnAction(event -> {
            view.close();
            System.exit(0);
        });
    }

    private void setMin() {

        view.login_min.setOnAction(event -> view.setIconified(true));
    }
}
