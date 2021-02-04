package ytg.mychat.server.view.chat.data;

import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Data
public class UserData {

    private String userId;

    private String userNickName;

    private String userHead;

    public UserData(String userId, String userNickName, String userHead) {
        this.userId = userId;
        this.userNickName = userNickName;
        this.userHead = userHead;
    }
}
