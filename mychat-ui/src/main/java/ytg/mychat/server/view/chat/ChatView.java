package ytg.mychat.server.view.chat;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import ytg.mychat.server.uitl.CacheUtil;
import ytg.mychat.server.uitl.EqualsUtil;
import ytg.mychat.server.uitl.Ids;
import ytg.mychat.server.view.AbstractView;
import ytg.mychat.server.view.chat.element.friend.*;
import ytg.mychat.server.view.chat.element.talk.Talk;
import ytg.mychat.server.view.chat.data.RemindCount;
import ytg.mychat.view.chat.element.friend.*;

import java.util.Objects;

/**
 * @description:
 *  抽象的聊天视图, 内部持有视图中的各项按钮列表等元素
 *  同时对外提供一些方法用于操作视图
 * @author: yangtg
 * @create: 2021-01-18
 **/
public class ChatView extends AbstractView {

    private static final String RESOURCE_NAME = "/fxml/chat/chat.fxml";
    private static final String ICON_URL = "/fxml/chat/img/head/logo.png";

    private ChatEventHandler eventHandler;

    /**
     * 最左侧bar的好友图标
     */
    public Button friendBar;
    /**
     * 最左侧bar的聊天图标
     */
    public Button chatBar;
    /**
     * 微信聊天框包括聊天列表和对话框  不包括左侧bar
     */
    public Pane chatPane;
    /**
     * 微信好友框包括好友列表和好友信息框  不包括左侧bar
     */
    public Pane friendPane;
    /**
     * chatPane左侧的聊天列表   不包括搜索框
     */
    public ListView<Pane> talkList;
    /**
     * chatPane的关闭按钮
     */
    public Button chatCloseButton;
    /**
     * friendPane的关闭按钮
     */
    public Button friendCloseButton;
    /**
     * chatPane的对话框的消息输入文本域
     */
    public TextArea msgArea;
    /**
     * friendPane的好友信息框  其中包含好友的详细信息与发送信息按钮
     */
    public Pane friendDetailBox;
    /**
     * friendPane的好友列表
     */
    public ListView<Pane> friendList;
    /**
     * 新的朋友
     */
    public FriendLuck friendLuck;
    /**
     * 发送消息按钮
     */
    public Label touch_send;


    public ChatView(ChatEventHandler eventHandler) {

       super(RESOURCE_NAME,ICON_URL);
       this.eventHandler = eventHandler;
    }

    @Override
    protected void obtain() {

        this.friendBar = $("bar_friend");
        this.chatBar = $("bar_chat");
        this.chatPane = $("group_bar_chat");
        this.friendPane = $("group_bar_friend");
        this.talkList = $("talkList");
        this.chatCloseButton = $("group_bar_chat_close");
        this.friendCloseButton = $("group_bar_friend_close");
        this.msgArea = $("txt_input");
        this.touch_send = $("touch_send");
        this.friendDetailBox = $("content_pane_box");
        this.friendList = $("friendList");
    }

    @Override
    public void initView() {

        initRoot();

        // 好友列表添加新的朋友按钮
        initAddFriendLuck();
        // 好友列表添加公众号
        initSubscription();
        // 好友列表添加群组框
        initFriendGroupList();
        // 好友列表添加好友框
        initFriendList();
    }

    /**
     * 挂载群聊列表框
     */
    private void initFriendGroupList() {

        ObservableList<Pane> items = friendList.getItems();

        items.add(new FriendTag("群聊").getPane());

        items.add(new FriendGroupList().getPane());
    }

    /**
     * 挂载好友列表框
     */
    private void initFriendList() {

        ObservableList<Pane> items = friendList.getItems();

        items.add(new FriendTag("好友").getPane());

        items.add(new FriendUserList().getPane());
    }

    /**
     * 挂载公众号列表框
     */
    private void initSubscription() {

        ObservableList<Pane> items = friendList.getItems();
        items.add(new FriendTag("公众号").getPane());

        FriendSubscription subscription = new FriendSubscription();
        items.add(subscription.getPane());

        subscription.getPane().setOnMousePressed(event -> {

            // 点击公众号按钮时会取消之前在好友列表  群组列表的选中
            clearSelectedItems($("userListView"),$("groupListView"));
            setContentPaneBox("friend-subscription","公众号",subscription.getSubPane());
        });
    }

    /**
     * 初始化新朋友框
     */
    private void initAddFriendLuck() {

        ObservableList<Pane> items = friendList.getItems();
        items.add(new FriendTag("新的朋友").getPane());

        friendLuck = new FriendLuck();
        items.add(friendLuck.getPane());
    }

    /**
     * 切换friendBar显示是否高亮 同时控制friendPane是否隐藏
     * @param toggle
     */
    public void switchFriendBar(boolean toggle) {

        if (toggle){
            friendBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_2.png')");
            friendPane.setVisible(true);
        }else {

            friendBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/friend_0.png')");
            friendPane.setVisible(false);
        }
    }

    /**
     * 切换chatBar显示是否高亮 同时控制chatPane是否隐藏
     * @param toggle
     */
    public void switchChatBar( boolean toggle) {

        if (toggle){
            chatBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_2.png')");
            chatPane.setVisible(true);
        }else {
            chatBar.setStyle("-fx-background-image: url('/fxml/chat/img/system/chat_0.png')");
            chatPane.setVisible(false);
        }
    }

    /**
     * 清空未读消息
     * @param talkId
     */
    public void clearRemind(String talkId){

        Talk talkElement = CacheUtil.talkMap.get(talkId);
        if (Objects.nonNull(talkElement)){
            Label msgRemind = talkElement.getMsgRemind();
            msgRemind.setUserData(new RemindCount());
            msgRemind.setVisible(false);
        }
    }

    /**
     * 向群聊列表中添加一个群聊
     * @param groupId
     * @param groupName
     * @param groupHead
     */
    public FriendGroup addFriendGroup(String groupId, String groupName, String groupHead){

        FriendGroup friendGroup = new FriendGroup(groupId,groupName,groupHead);
        Pane pane = friendGroup.getGroupPane();

        ListView<Pane> groupListView = $("groupListView");

        ObservableList<Pane> items = groupListView.getItems();
        items.add(pane);
        groupListView.setPrefHeight(80 * items.size());
        Pane friendGroupList = $("friendGroupList");
        friendGroupList.setPrefHeight(80 * items.size());
        return friendGroup;

    }


    /**
     * 更新对话框列表的元素位置 并选中
     * @param talkType  对话框类型  0 壕友  1群组
     * @param talkPane  对话框面板元素
     * @param msgRemind  消息提醒面板
     * @param idxFirst   是否设置首位
     * @param selected   是否选中
     * @param isRemind   是否提醒
     */
    public void updateTalkListIdxAndSelected(int talkType, Pane talkPane, Label msgRemind, Boolean idxFirst, Boolean selected, Boolean isRemind) {

        if (talkList.getItems().isEmpty()){
            addTalkToFirstAndSelected(talkType, talkPane, msgRemind, idxFirst, selected, isRemind);
            return;
        }

        Pane firstTalkPane = talkList.getItems().get(0);
        // 查看这个对话是否已经在首位
        if (EqualsUtil.equalsTalkElement(firstTalkPane,talkPane)){

            Pane selectedItem = talkList.getSelectionModel().getSelectedItem();
            // 如果
            if (Objects.nonNull(selectedItem) && EqualsUtil.equalsTalkElement(selectedItem,talkPane)){
                clearRemind(msgRemind);
            }
                isRemind(msgRemind,talkType,isRemind);
        }else {
            if (idxFirst){
                talkList.getItems().remove(talkPane);
                talkList.getItems().add(0,talkPane);
            }
            if(selected){
                talkList.getSelectionModel().select(talkPane);
            }
            isRemind(msgRemind,talkType,isRemind);
        }
    }


    /**
     * 填充对话列表和对话框名称
     * @param id    用户或者群组的id
     * @param name  用户或者群组的名称
     * @param pane  展示面板
     */
    public void setContentPaneBox(String id, String name, Node pane) {

        friendDetailBox.setUserData(id);
        friendDetailBox.getChildren().clear();
        friendDetailBox.getChildren().add(pane);

        Label label = $("content_name");
        label.setText(name);
    }

    /**
     * 清空未读消息
     * @param msgRemind
     */
    private void clearRemind(Label msgRemind) {

        msgRemind.setVisible(false);
        msgRemind.setUserData(new RemindCount());
    }

    /**
     * 添加到对话列表的首位并选中  同时增加未读数量
     * @param talkType
     * @param talkPane
     * @param msgRemind
     * @param idxFirst
     * @param selected
     * @param isRemind
     */
    private void addTalkToFirstAndSelected(int talkType, Pane talkPane, Label msgRemind, Boolean idxFirst, Boolean selected, Boolean isRemind) {

        if (idxFirst){
            talkList.getItems().add(0,talkPane);
        }
        if(selected){
            talkList.getSelectionModel().select(talkPane);
        }
        isRemind(msgRemind,talkType,isRemind);
    }

    private void isRemind(Label msgRemind, int talkType, Boolean isRemind) {

        if (!isRemind){
            return;
        }
        msgRemind.setVisible(true);
        // 群组只显示小红点
        if (1 == talkType){
            return;
        }

        RemindCount count = (RemindCount) msgRemind.getUserData();
        if (count.getCount() > 99){
            msgRemind.setText("99+");
            return;
        }

        int i = count.incrementAndGet();
        msgRemind.setText(String.valueOf(i));
    }

    /**
     * 新建一个群聊 聊天室  稍后添加到contentBox
     * @return
     */
    public Pane newGroupFriendChatRoom() {

        Pane chatRoom = new Pane();
        chatRoom.setPrefSize(850,560);
        chatRoom.getStyleClass().add("friendGroupDetailContent");

        ObservableList<Node> children = chatRoom.getChildren();
        Button sendMsgButton = new Button();
        sendMsgButton.setText("发送消息");
        sendMsgButton.setLayoutX(337);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setPrefSize(176,50);
        sendMsgButton.getStyleClass().add("friendGroupSendMsgButton");
        children.add(sendMsgButton);
        return chatRoom;
    }

    /**
     * 新建一个好友私聊聊天室
     * @param friendId
     * @return
     */
    public Pane newFriendChatRoom(String friendId){

        // 好友聊天内容框, 里面是群组消息, 点击按钮的时候填充
        Pane chatRoom = new Pane();
        chatRoom.setPrefSize(850, 560);
        chatRoom.getStyleClass().add("friendUserDetailContent");
        ObservableList<Node> children = chatRoom.getChildren();
        Button sendMsgButton = new Button();
        sendMsgButton.setId(friendId);
        sendMsgButton.getStyleClass().add("friendUserSendMsgButton");
        sendMsgButton.setPrefSize(176, 50);
        sendMsgButton.setLayoutX(337);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("发送消息");
        children.add(sendMsgButton);

        return chatRoom;
    }

    /**
     * 添加一个新的好友
     *
     * @param selected
     * @param userId
     * @param userNickname
     * @param userHead
     * @return
     */
    public FriendUser addFriendUser(boolean selected, String userId, String userNickname, String userHead) {

        FriendUser friend = new FriendUser(userId, userNickname, userHead);
        Pane pane = friend.getPane();
        ListView<Pane> userListView = $("userListView");
        ObservableList<Pane> items = userListView.getItems();
        items.add(pane);
        userListView.setPrefHeight(80 * items.size());
        Pane friendUserList = $("friendUserList");
        friendUserList.setPrefHeight(80 * items.size());
        if (selected) {
            userListView.getSelectionModel().select(pane);
        }
        return friend;
    }

    /**
     * 将这一次的对话显示的右侧的消息框中
     * 如果已经正在显示的话就不再处理
     * 填充对话框消息内容
     *
     * @param talk     对话框元素
     * @param talkName 对话框名称
     */
    public void fillInfoBox(Talk talk, String talkName) {

        String talkId = talk.talkId();
        Pane info_pane_box = $("info_pane_box");

        String boxUserId = (String) info_pane_box.getUserData();
        if (talkId.equals(boxUserId)) {
            return;
        }
        ListView<Pane> infoBoxList = talk.getInfoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(infoBoxList);

        Label info_name = $("info_name");
        info_name.setText(talkName);

    }

    /**
     * 清空最左侧
     */
    public void clearInfoBox() {

        ((Pane) $("info_pane_box")).getChildren().clear();
        ((Pane) $("info_pane_box")).setUserData(null);
        ((Label) $("info_name")).setText("");
    }

    /**
     * 新增对话框
     * @param talk
     * @param talkIdx
     * @param selected
     */
    public void addTalkBox(Talk talk, int talkIdx, boolean selected) {

        Pane pane = talk.getPane();
        CacheUtil.talkMap.put(Ids.TalkId.analysisTalkPaneId(pane.getId()), talk);
        ObservableList<Pane> items = talkList.getItems();
        if (talkIdx >= 0) {
            items.add(talkIdx, pane);
        } else {
            items.add(pane);
        }

        if (selected) {
            talkList.getSelectionModel().select(pane);
        }

        fillInfoBox(talk,talk.getNikeName().getText());
    }

    /**
     * 查看对话框是否存在  如果填充到聊天室并选中
     * @param talkId
     * @param talkIdx
     * @param selected
     */
    public void selectTalkBoxIfFound(String talkId, int talkIdx, boolean selected) {

        Talk talkElement = CacheUtil.talkMap.get(talkId);
        Node talk = talkList.lookup("#" + Ids.TalkId.createTalkPaneId(talkId));
        if (Objects.isNull(talk)) {
            talkList.getItems().add(talkIdx, talkElement.getPane());
            // 填充大对话框
            fillInfoBox(talkElement, talkElement.getNikeName().getText());
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElement.getPane());
        }
        clearRemind(talkId);
        fillInfoBox(talkElement, talkElement.getNikeName().getText());
    }

    /**
     * 添加一个新的缘分好友到缘分好友列表
     * @param friendLuckUser
     */
    public void addLuckFriend(FriendLuckUser friendLuckUser) {

        Pane pane = friendLuckUser.getPane();
        // 添加到好友列表
        ListView<Pane> friendLuckListView = $("friendLuckListView");
        ObservableList<Pane> items = friendLuckListView.getItems();
        items.add(pane);
    }
}
