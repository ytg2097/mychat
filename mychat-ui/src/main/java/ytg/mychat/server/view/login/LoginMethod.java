package ytg.mychat.server.view.login;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-18
 **/
public interface LoginMethod {

    void doShow();

    void doLoginError();

    void doLoginSuccess();
}
