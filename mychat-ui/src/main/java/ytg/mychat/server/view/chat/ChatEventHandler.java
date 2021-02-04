package ytg.mychat.server.view.chat;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
public interface ChatEventHandler {

    /**
     * 聊天退出操作
     */
    void doQuit();

    /**
     * 发送消息
     *
     * @param userId   to userId
     * @param talkId   对话id
     * @param talkType 对话框类型  0 好友  1群组
     * @param msg      消息内容
     * @param msgDate  发送时间
     */
    void doSendMsg(String userId, String talkId, Integer talkType, String msg, Date msgDate);

    /**
     * 开启与好友发送消息  点击发送消息时触发  -> 添加到对话框, 选中, 展示对话列表
     * @param userid
     * @param userFriendId
     */
    void doAddTalkUser(String userid,String userFriendId);

    /**
     * 开启群聊
     * @param userId
     * @param groupId
     */
    void doAddTalkGroup(String userId,String groupId);

    /**
     * 删除指定对话框
     * @param userId
     * @param talkId
     */
    void doDelTalkUser(String userId,String talkId);

    /**
     * 查询用户添加到列表
     * @param userId
     * @param listView
     */
    void addFriendLuck(String userId, ListView<Pane> listView);

    /**
     * 查找好友
     * @param userId
     * @param text
     */
    void addFriendLuckSearch(String userId,String text);

    /**
     * 添加好友
     * @param userId
     * @param friendId
     */
    void addFriendUser(String userId,String friendId);

}
