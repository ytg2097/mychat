package ytg.mychat.server;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ytg.mychat.server.view.chat.ChatController;
import ytg.mychat.server.view.chat.ChatEventHandler;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-16
 **/
public class Main extends javafx.application.Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        ChatController chat = new ChatController(new ChatEventHandler() {
            @Override
            public void doQuit() {
                System.out.println("发送退出信号");
            }

            @Override
            public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Date msgDate) {

            }

            @Override
            public void doAddTalkUser(String userid, String userFriendId) {

            }

            @Override
            public void doAddTalkGroup(String userId, String groupId) {

            }

            @Override
            public void doDelTalkUser(String userId, String talkId) {

            }

            @Override
            public void addFriendLuck(String userId, ListView<Pane> listView) {

            }

            @Override
            public void addFriendLuckSearch(String userId, String text) {

            }

            @Override
            public void addFriendUser(String userId, String friendId) {

            }
        });
        chat.show();
//        LoginController login = new LoginController((userId, password) -> {
//
//
//        });
//        login.show();
        chat.show();
        chat.setUserInfo("1000001", "杨同港", "02_50");
        // 模拟测试
        chat.addTalkBox(-1, 0, "1000004", "张硕", "04_50", null, null, false);
        chat.addTalkMsgUserLeft("1000004", "哈哈哈哈",  new Date(), true, false, true);
        chat.addTalkMsgUserLeft("1000004", "f_23",  new Date(), true, false, true);

        chat.addTalkMsgRight("1000004", "嗬嗬嗬嗬", new Date(), true, true, false);

        chat.addTalkBox(-1, 0, "1000002", "狗蛋", "03_50", "秋风扫过树叶落，哪有棋盘哪有我", new Date(), false);
        chat.addTalkMsgUserLeft("1000002", "厉害",  new Date(), true, false, true);
        chat.addTalkMsgRight("1000002", "还行", new Date(), true, true, false);

        // 群组
        chat.addFriendGroup("5307397", "五连鞭", "group_1");
        chat.addFriendGroup("5307392", "没正事", "group_2");
        chat.addFriendGroup("5307399", "母鸡的产后护理", "group_3");

        // 群组 - 对话框
        chat.addTalkBox(0, 1, "5307397", "五连鞭", "group_1", "", new Date(), true);
        chat.addTalkMsgRight("5307397", "还行",  new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        chat.addTalkMsgGroupLeft("5307397", "1000002", "杨同港", "01_50", "挺赛",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000003", "狗蛋", "03_50", "哦哦哦哦哦哦哦哦",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "详细信息",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgGroupLeft("5307397", "1000004", "张硕", "04_50", "f_25",  new Date(), true, false, true);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        chat.addTalkMsgRight("5307397", "f_14",  new Date(), true, true, false);
        // 好友
        chat.addFriendUser(false, "1000004", "张硕", "04_50");
        chat.addFriendUser(false, "1000001", "杨同港", "02_50");
        chat.addFriendUser(false, "1000002", "狗蛋", "03_50");



    }

    public static void main(String[] args) {
        launch(args);
    }
}
