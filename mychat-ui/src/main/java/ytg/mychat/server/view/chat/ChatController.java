package ytg.mychat.server.view.chat;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import ytg.mychat.server.uitl.CacheUtil;
import ytg.mychat.server.uitl.Ids;
import ytg.mychat.server.view.chat.data.UserData;
import ytg.mychat.server.view.chat.element.friend.FriendLuckUser;
import ytg.mychat.server.view.chat.element.friend.FriendUser;
import ytg.mychat.server.view.chat.element.talk.Talk;
import ytg.mychat.server.view.chat.element.talk.TalkInfoBox;
import ytg.mychat.server.view.chat.data.GroupsData;
import ytg.mychat.server.view.chat.element.friend.FriendGroup;

import java.util.Date;
import java.util.Objects;

/**
 * @description: 窗口的方法:  提供给外部调用  比如切换显示状态
 * @author: yangtg
 * @create: 2021-01-18
 **/
public class ChatController implements ChatMethod {

    private ChatView view;
    private ChatEventMounter eventMounter;
    private UserData currentUser;

    public ChatController(ChatEventHandler eventHandler) {
        this.view = new ChatView(eventHandler);
        this.eventMounter = new ChatEventMounter(this, view, eventHandler);


    }

    @Override
    public void show() {
        view.show();
    }

    @Override
    public void min() {
        view.setIconified(true);
    }

    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {

        this.currentUser = new UserData(userId, userNickName, userHead);
    }

    @Override
    public UserData getUserInfo() {
        return currentUser;
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, boolean selected) {

        Talk talkElement = CacheUtil.talkMap.get(talkId);
        if (Objects.nonNull(talkElement)) {

            view.selectTalkBoxIfFound(talkId,talkIdx,selected);
            return;
        }

        Talk talk = new Talk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        view.addTalkBox(talk,talkIdx,selected);

        eventMounter.mountTalkBoxEvent(talk);
        eventMounter.mountTalkBoxDeleteEvent(talk);
    }

    @Override
    public void addTalkMsgUserLeft(String talkId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {

        Talk talk = CacheUtil.talkMap.get(talkId);

        talk.addLeft(msg,msgDate);

        view.updateTalkListIdxAndSelected(0, talk.getPane(), talk.getMsgRemind(), idxFirst, selected, isRemind);

        view.fillInfoBox(talk, talk.getNikeName().getText());
    }

    @Override
    public void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {

        if (userId.equals(currentUser.getUserId())) {
            return;
        }

        Talk talk = CacheUtil.talkMap.get(talkId);
        if (null == talk) {
            Pane groupChat = view.$(Ids.TalkId.createFriendGroupId(talkId));
            if (null == groupChat) {
                return;
            }
            GroupsData groupsData = (GroupsData) groupChat.getUserData();
            // 添加新的群聊会话
            addTalkBox(0, 1, talkId, groupsData.getGroupName(), groupsData.getGroupHead(), userNickName + ":" + msg, msgDate, false);
            talk = CacheUtil.talkMap.get(talkId);
        }

        talk.addLeft(userNickName, userHead, msg, msgDate);

        view.updateTalkListIdxAndSelected(1, talk.getPane(), talk.getMsgRemind(), idxFirst, selected, isRemind);

        view.fillInfoBox(talk, talk.getNikeName().getText());
    }

    @Override
    public void addTalkMsgRight(String talkId, String msg, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {

        Talk talk = CacheUtil.talkMap.get(talkId);

        Pane right = new TalkInfoBox().right(currentUser.getUserNickName(), currentUser.getUserHead(), msg);

        talk.addInfoBox(right, msg, msgDate);

        view.updateTalkListIdxAndSelected(0, talk.getPane(), talk.getMsgRemind(), idxFirst, selected, isRemind);
    }

    @Override
    public void addFriendGroup(String groupId, String groupName, String groupHead) {

        FriendGroup friendGroup = view.addFriendGroup(groupId, groupName, groupHead);

        Pane chatRoom = view.newGroupFriendChatRoom();

        eventMounter.mountGroupFriendMousePressedEvent(friendGroup.getGroupPane(), chatRoom);
        eventMounter.mountGroupFriendSendMsgActionEvent((Button) chatRoom.getChildren().get(0), groupId, groupName, groupHead);

        view.setContentPaneBox(groupId, groupName, chatRoom);
    }

    @Override
    public void addFriendUser(boolean selected, String userId, String userNickname, String userHead) {

        FriendUser friend = view.addFriendUser(selected, userId, userNickname, userHead);

        Pane chatRoom = view.newFriendChatRoom(userId);
        eventMounter.mountFriendSendMsgActionEvent((Button) chatRoom.getChildren().get(0), userId, userNickname, userHead);
        eventMounter.mountFriendMousePressedEvent(friend, chatRoom);

        view.setContentPaneBox(userId, userNickname, chatRoom);
    }

    @Override
    public void addLuckFriend(String userId, String userNickName, String userHead, Integer status) {

        FriendLuckUser friendLuckUser = new FriendLuckUser(userId, userNickName, userHead, status);
        view.addLuckFriend(friendLuckUser);
        eventMounter.mountAddLuckFriendEvent(friendLuckUser);
    }
}
