package ytg.mychat.server.view.login;


/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-18
 **/
public interface LoginEventHandler {


    /**
     * 登陆检查
     *
     * @param userId
     * @param password
     */
    void doLoginCheck(String userId, String password);
}
