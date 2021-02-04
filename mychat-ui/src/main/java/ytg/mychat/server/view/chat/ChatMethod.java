package ytg.mychat.server.view.chat;

import ytg.mychat.server.view.chat.data.UserData;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-18
 **/
public interface ChatMethod {

    void show();

    void min();

    /**
     * 设置用户信息
     * @param userId
     * @param userNickName
     * @param userHead
     */
    void setUserInfo(String userId,String userNickName,String userHead);

    UserData getUserInfo();

    /**
     * 填充对话框
     * @param talkIdx  对话框位置   0:首位  默认-1
     * @param talkType 对话框类型   0:好友  1:群组
     * @param talkId   对话框id
     * @param talkName  对话框名称
     * @param talkHead   对话框头像
     * @param talkSketch  对话框最后一条信息
     * @param talkDate   对话框最后通信时间
     * @param selected   是否选中  true选中  false 不选中
     */
    void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate,boolean selected);

    /**
     * 填充对话框消息   好友的消息
     * @param talkId   对话框id
     * @param msg      消息
     * @param msgDate  发送时间
     * @param idxFirst  是否设置首位
     * @param selected  是否选中
     * @param isRemind  是否提醒
     */
    void addTalkMsgUserLeft(String talkId,String msg,Date msgDate,Boolean idxFirst,Boolean selected,Boolean isRemind);

    /**
     * 填充对话框消息   群组的消息
     * @param talkId   对话框id
     * @param userId   发言用户id
     * @param userNickName   发言用户昵称
     * @param userHead   发言用户头像
     * @param msg      消息
     * @param msgDate  发送时间
     * @param idxFirst  是否设置首位
     * @param selected  是否选中
     * @param isRemind  是否提醒
     */
    void addTalkMsgGroupLeft(String talkId,String userId,String userNickName,String userHead,String msg,Date msgDate,Boolean idxFirst,Boolean selected,Boolean isRemind);

    /**
     * 填充对话框消息
     * @param talkId   对话框id
     * @param msg     xiaoxi
     * @param msgDate  消息发送时间
     * @param idxFirst  是否设置首位
     * @param selected   是否选中
     * @param isRemind   是否提醒
     */
    void addTalkMsgRight(String talkId,String msg,Date msgDate,Boolean idxFirst,Boolean selected,Boolean isRemind);

    /**
     * 新增群组
     * @param groupId     群组id
     * @param groupName   群组名称
     * @param groupHead   群组头像
     */
    void addFriendGroup(String groupId,String groupName,String groupHead);

    /**
     * 新增好友
     * @param selected      是否选中
     * @param userId        好友Id
     * @param userNickname  好友昵称
     * @param userHead      好友头像
     */
    void addFriendUser(boolean selected,String userId,String userNickname,String userHead);

    /**
     * 缘分好友 | 默认添加10个好友
     *
     * @param userId       好友ID
     * @param userNickName 好友昵称
     * @param userHead     好友头像
     * @param status       状态；0添加、1允许、2已添加
     */
    void addLuckFriend(String userId, String userNickName, String userHead, Integer status);


}
