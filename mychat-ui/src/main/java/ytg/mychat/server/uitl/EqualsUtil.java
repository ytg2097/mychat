package ytg.mychat.server.uitl;

import javafx.scene.layout.Pane;
import ytg.mychat.server.view.chat.data.TalkBoxData;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
public class EqualsUtil {

    public static boolean equalsTalkElement(Pane a,Pane b){

        TalkBoxData talkBoxData = (TalkBoxData) a.getUserData();
        TalkBoxData talkBoxData1 = (TalkBoxData) b.getUserData();
        return talkBoxData.getTalkId().equals(talkBoxData1.getTalkId());
    }
}
