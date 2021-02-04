package ytg.mychat.server.view.chat.data;

import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
@Data
public class TalkUserData {

    private String talkName;

    private String talkHead;

    public TalkUserData(String talkName, String talkHead) {

        this.talkName = talkName;
        this.talkHead = talkHead;
    }
}
