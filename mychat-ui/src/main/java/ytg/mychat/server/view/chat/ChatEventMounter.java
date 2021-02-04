package ytg.mychat.server.view.chat;

import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import org.apache.commons.lang3.StringUtils;
import ytg.mychat.server.uitl.Ids;
import ytg.mychat.server.view.chat.data.GroupsData;
import ytg.mychat.server.view.chat.data.TalkBoxData;
import ytg.mychat.server.view.chat.element.friend.FriendLuckUser;
import ytg.mychat.server.view.chat.element.friend.FriendUser;
import ytg.mychat.server.view.chat.element.talk.Talk;

import java.util.Date;

/**
 * @description:
 * 会在项目启动时为view挂载一部分用户事件处理器
 * 同时对外提供一些方法用于在某些特定操作发生时挂载事件处理器
 * @author: yangtg
 * @create: 2021-01-18
 **/
public class ChatEventMounter {

    /**
     * 持有事件处理器, 以便在用户触发特定的事件时进行处理,
     * 例如发送信息按钮被按下事件, 通知事件处理器向服务器发送消息
     */
    private ChatEventHandler eventHandler;
    /**
     * 持有view对象  以便可以直接访问view中的元素
     * 例如访问view的关闭按钮, 绑定System.exit(0)动作
     */
    private ChatView view;
    /**
     * 持有聊天框实例, 以便在触发特定事件时可以联动触发其他动作
     * 例如发送信息按钮被按下事件, 在消息框右侧添加新的消息记录
     */
    private ChatMethod chatMethod;


    public ChatEventMounter(ChatMethod chatMethod, ChatView view, ChatEventHandler eventHandler) {

        this.chatMethod = chatMethod;
        this.view = view;
        this.eventHandler = eventHandler;

        mountFriendBarEvent();

        mountChatBarEvent();

        mountCloseButtonEvent();

        mountTxtAreaKeyEnterEvent();

        mountTouchSendEvent();

        mountFriendLuckActionEvent();

        mountFriendLuckSearchEvent();
    }


    /**
     * 消息发送事件
     */
    private void doSendMsgEvent() {

        MultipleSelectionModel<Pane> selectionModel = view.talkList.getSelectionModel();

        Pane selectedItem = selectionModel.getSelectedItem();
        TalkBoxData talkBoxData = (TalkBoxData) selectedItem.getUserData();

        String text = view.msgArea.getText();
        if (StringUtils.isEmpty(text.trim())){
            return ;
        }


        Date date = new Date();

        // 触发事件处理器  通知产生了新的消息
        eventHandler.doSendMsg(chatMethod.getUserInfo().getUserId(),talkBoxData.getTalkId(),talkBoxData.getTalkType(),text,date);

        // 更新聊天框, 加入新的消息box
        chatMethod.addTalkMsgRight(talkBoxData.getTalkId(),text,date,true,true,false);

        // 清空输入框
        view.msgArea.clear();
        String text1 = view.msgArea.getText();
        System.out.println(text1);
    }

    /**
     * 挂载消息输入框的回车按钮按下事件
     */
    public void mountTxtAreaKeyEnterEvent(){

        view.msgArea.setOnKeyPressed(event -> {

            if (event.getCode().equals(KeyCode.ENTER)){
                doSendMsgEvent();
                view.msgArea.setText("");
            }
        });
    }

    /**
     * 挂载发送按钮发送点击事件
     */
    public void mountTouchSendEvent(){

        view.touch_send.setOnMousePressed(event -> doSendMsgEvent());
    }

    /**
     * 设置退出事件处理
     */
    private void mountCloseButtonEvent() {

        view.chatCloseButton.setOnAction(event -> {

            // 发送退出消息给服务器
            eventHandler.doQuit();
            view.close();
            System.exit(0);
        });

        view.friendCloseButton.setOnAction(event -> {

            // 发送退出消息给服务器
            eventHandler.doQuit();
            view.close();
            System.exit(0);
        });
    }

    /**
     * 挂载chatBar的点击事件和鼠标滑入滑出事件
     */
    private void mountChatBarEvent() {

        view.chatBar.setOnAction(event -> {
            view.switchChatBar(true);
            view.switchFriendBar(false);
        });

        view.chatBar.setOnMouseEntered(event -> {
            boolean visible = view.chatPane.isVisible();
            if (visible){
                return;
            }
            view.chatBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_1.png')");
        });

        view.chatBar.setOnMouseExited(event -> {
            boolean visible = view.chatPane.isVisible();
            if (visible){
                return;
            }
            view.chatBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
        });
    }

    /**
     * 挂载friendBar的点击事件和鼠标滑入滑出事件
     */
    private void mountFriendBarEvent() {

        view.friendBar.setOnAction(event -> {

            view.switchChatBar(false);
            view.switchFriendBar(true);
        });

        view.friendBar.setOnMouseEntered(event -> {

            boolean visible = view.friendPane.isVisible();
            if (visible){
                return;
            }
            view.friendBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_1.png')");
        });

        view.friendBar.setOnMouseExited(event -> {
            boolean visible = view.friendPane.isVisible();
            if (visible){
                return;
            }
            view.friendBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
        });
    }

    /**
     * 为群聊挂载一个鼠标点击事件, 点击时将聊天室并填充到右侧contentBox中
     * @param groupPane
     * @param chatRoom
     * @return
     */
    public void mountGroupFriendMousePressedEvent(Pane groupPane,Pane chatRoom) {

        groupPane.setOnMousePressed(event -> {

            view.clearSelectedItems(view.$("friendList"),view.$("userListView"));
            GroupsData groupsData = (GroupsData) groupPane.getUserData();
            view.setContentPaneBox(groupsData.getGroupId(),groupsData.getGroupName(),chatRoom);
            eventHandler.doAddTalkGroup(chatMethod.getUserInfo().getUserId(),groupsData.getGroupId());
        });
    }

    /**
     * 为私聊挂载一个鼠标点击事件, 点击时将聊天室并填充到右侧contentBox中
     * @param friend
     * @param chatRoom
     * @return
     */
    public void mountFriendMousePressedEvent(FriendUser friend, Pane chatRoom) {

        Pane pane = friend.getPane();
        pane.setOnMousePressed(event -> {

            view.clearSelectedItems(view.$("friendList"),view.$("userListView"));
            view.setContentPaneBox(pane.getId(),friend.getName().getText(),chatRoom);
            eventHandler.doDelTalkUser(chatMethod.getUserInfo().getUserId(),pane.getId());
        });
    }

    /**
     * 为群聊的  发送消息 按钮挂载一个点击事件 -> 开启新的群聊
     * @param sendMsgButton
     * @param groupId
     * @param groupName
     * @param groupHead
     */
    public void mountGroupFriendSendMsgActionEvent(Button sendMsgButton, String groupId, String groupName, String groupHead) {

        sendMsgButton.setOnAction(event -> {

            chatMethod.addTalkBox(0,1,groupId,groupName,groupHead,null,null,true);
            view.switchChatBar(true);
            view.switchFriendBar(false);
        });
    }

    /**
     * 为好友详情面板的  发送消息 按钮挂载一个点击事件 -> 开启新的私聊
     * @param sendMsgButton
     * @param userId
     * @param userNickname
     * @param userHead
     */
    public void mountFriendSendMsgActionEvent(Button sendMsgButton, String userId, String userNickname, String userHead) {

        sendMsgButton.setOnAction(event -> {

            chatMethod.addTalkBox(0,0,userId,userNickname,userHead,null,null,true);

            view.switchChatBar(true);
            view.switchFriendBar(false);
        });
    }

    /**
     * 挂载聊天删除事件
     * @param talk
     */
    public void mountTalkBoxDeleteEvent(Talk talk) {

        talk.getDeleteButton().setOnMouseClicked(event -> {

            view.talkList.getItems().remove(talk.getPane());
            view.clearInfoBox();
            talk.clearMsg();
            // 可以显示下一个会话  todo
            eventHandler.doDelTalkUser(chatMethod.getUserInfo().getUserId(), Ids.TalkId.analysisTalkPaneId(talk.getPane().getId()));
        });
    }

    /**
     * 挂载对话框事件
     * @param talk
     */
    public void mountTalkBoxEvent(Talk talk) {

        Pane pane = talk.getPane();
        pane.setOnMousePressed(event -> {
            view.fillInfoBox(talk, talk.getNikeName().getText());
            view.clearRemind(Ids.TalkId.analysisTalkPaneId(pane.getId()));
        });

        pane.setOnMouseEntered(event -> talk.getDeleteButton().setVisible(true));
        pane.setOnMouseExited(event -> talk.getDeleteButton().setVisible(false));
    }

    /**
     * 挂载 新的朋友  点击事件
     */
    public void mountFriendLuckActionEvent(){

        view.friendLuck.getPane().setOnMousePressed(event -> {

            Pane friendLuckPane = view.friendLuck.getFriendLuckPane();
            view.setContentPaneBox("friend-luck","新的朋友",friendLuckPane);
            // 点击公众号按钮时会清空之前在好友列表  群组列表的选中
            view.clearSelectedItems(view.$("userListView"),view.$("groupListView"));
            ListView<Pane> listView = view.friendLuck.getFriendLuckListView();
            listView.getItems().clear();
            eventHandler.addFriendLuck(chatMethod.getUserInfo().getUserId(),listView);
        });
    }

    /**
     * 挂载 新的朋友 搜索事件
     */
    public void mountFriendLuckSearchEvent(){

        view.friendLuck.getFriendLuckSearch().setOnKeyPressed(event -> {

            if (event.getCode().equals(KeyCode.ENTER)){
                String text = view.friendLuck.getFriendLuckSearch().getText();
                if (null == text){
                    text = "";
                }
                text = text.trim();
                if (text.length() > 30){
                    text = text.substring(0,30);
                }
                eventHandler.addFriendLuckSearch(chatMethod.getUserInfo().getUserId(),text);
            }
        });
    }

    /**
     * 挂载缘分好友的  添加 按钮点击事件
     * @param friendLuckUser
     */
    public void mountAddLuckFriendEvent(FriendLuckUser friendLuckUser) {

        friendLuckUser.getStatusLabel().setOnMousePressed(event -> {
            eventHandler.addFriendUser(chatMethod.getUserInfo().getUserId(), friendLuckUser.getPane().getId());
        });
    }
}
