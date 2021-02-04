package ytg.mychat.server.view.chat.data;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
@Data
@AllArgsConstructor
public class TalkBoxData {

    private String talkId;

    private Integer talkType;

    private String talkName;

    private String talkHead;

}
