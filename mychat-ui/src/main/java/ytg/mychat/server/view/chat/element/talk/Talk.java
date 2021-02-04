package ytg.mychat.server.view.chat.element.talk;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import lombok.Data;
import ytg.mychat.server.uitl.DateUtil;
import ytg.mychat.server.uitl.Ids;
import ytg.mychat.server.view.chat.data.TalkUserData;
import ytg.mychat.server.view.chat.data.RemindCount;
import ytg.mychat.server.view.chat.data.TalkBoxData;

import java.util.Date;
import java.util.Optional;

/**
 * @description: 对话缩略框
 *  包含:
 *      一个聊天的面板
 *      聊天对象的头像
 *      聊天对象的昵称
 *      与聊天对象的对后一条消息
 *      与聊天对象的对后一条消息发送时间
 *      这一条聊天的未读提醒
 *      这一条聊天的删除按钮
 *      这一条聊天的历史对话信息  infoBoxList
 *
 * @author: yangtg
 * @create: 2021-01-19
 **/
@Data
public class Talk {

    /**
     * 对话面板
     */
    private Pane pane;
    /**
     * 头像
     */
    private Label head;
    /**
     * 昵称
     */
    private Label nikeName;
    /**
     * 最后一条消息
     */
    private Label msgSketch;
    /**
     * 消息时间
     */
    private Label msgDate;
    /**
     * 消息提醒
     */
    private Label msgRemind;
    /**
     * 对话框删除按钮
     */
    private Button deleteButton;
    /**
     * 历史消息
     */
    private ListView<Pane> infoBoxList;

    public Talk(String talkId, Integer talkType, String talkName, String talkHead, String talkSketch, Date talkDate){

        pane = new Pane();
        pane.setId(Ids.TalkId.createTalkPaneId(talkId));
        pane.setUserData(new TalkBoxData(talkId,talkType,talkName,talkHead));
        pane.setPrefSize(270,80);
        pane.getStyleClass().add("talkElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像区域
        head = new Label();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("element_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", talkHead));
        children.add(head);

        // 昵称区域
        nikeName = new Label();
        nikeName.setPrefSize(140, 25);
        nikeName.setLayoutX(80);
        nikeName.setLayoutY(15);
        nikeName.setText(talkName);
        nikeName.getStyleClass().add("element_nikeName");
        children.add(nikeName);

        // 信息简述
        msgSketch = new Label();
        msgSketch.setId(Ids.TalkId.createMsgSketchId(talkId));
        msgSketch.setPrefSize(200, 25);
        msgSketch.setLayoutX(80);
        msgSketch.setLayoutY(40);
        msgSketch.getStyleClass().add("element_msgSketch");
        children.add(msgSketch);

        // 信息时间
        msgDate = new Label();
        msgDate.setId(Ids.TalkId.createMsgDataId(talkId));
        msgDate.setPrefSize(60, 25);
        msgDate.setLayoutX(220);
        msgDate.setLayoutY(15);
        msgDate.getStyleClass().add("element_msgData");
        children.add(msgDate);
        // 填充；信息简述 & 信息时间
        fillMsgSketch(talkSketch, talkDate);

        // 消息提醒
        msgRemind = new Label();
        msgRemind.setPrefSize(15, 15);
        msgRemind.setLayoutX(60);
        msgRemind.setLayoutY(5);
        msgRemind.setUserData(new RemindCount());
        msgRemind.setText("");
        msgRemind.setVisible(false);
        msgRemind.getStyleClass().add("element_msgRemind");
        children.add(msgRemind);

        // 删除对话框按钮
        deleteButton = new Button();
        deleteButton.setVisible(false);
        deleteButton.setPrefSize(4, 4);
        deleteButton.setLayoutY(26);
        deleteButton.setLayoutX(-8);
        deleteButton.getStyleClass().add("element_delete");
        children.add(deleteButton);

        infoBoxList = new ListView<>();
        infoBoxList.setId(Ids.TalkId.createInfoBoxListId(talkId));
        infoBoxList.setUserData(new TalkUserData(talkName,talkHead));
        infoBoxList.setPrefSize(850,560);
        infoBoxList.getStyleClass().add("infoBoxStyle");
    }

    /**
     * 更新对话缩略框的最后一条消息
     * @param talkSketch
     * @param talkDate
     */
    public void fillMsgSketch(String talkSketch, Date talkDate) {

        if (null != talkSketch){
            if (talkSketch.length() > 30){
                talkSketch = talkSketch.substring(0,30);
            }
            msgSketch.setText(talkSketch);
        }

        talkDate = Optional.ofNullable(talkDate).orElse(new Date());
        msgDate.setText(DateUtil.format(talkDate));
    }

    public void clearMsgSketch(){
        msgSketch.setText("");
    }

    public String talkId(){

        return pane.getUserData().toString();
    }

    /**
     * 新增一条聊天消息
     * @param infoBoxPane
     * @param msg
     * @param date
     */
    public void addInfoBox(Pane infoBoxPane,String msg,Date date){

        infoBoxList.getItems().add(infoBoxPane);
        infoBoxList.scrollTo(infoBoxPane);
        fillMsgSketch(msg,date);
    }

    /**
     * 新增一条左侧的聊天消息
     */
    public void addLeft(String nickName,String head,String msg,Date msgDate){


        Pane left = new TalkInfoBox().left(nickName, head, msg);
        infoBoxList.getItems().add(left);
        infoBoxList.scrollTo(left);
        fillMsgSketch(nickName + ":" + msg, msgDate);
    }

    public void addLeft(String msg,Date msgDate){

        TalkUserData talkUserData = (TalkUserData) infoBoxList.getUserData();
        addLeft(talkUserData.getTalkName(),talkUserData.getTalkHead(),msg, msgDate);
    }

    public void clearMsg() {

        getInfoBoxList().getItems().clear();
        clearMsgSketch();
    }
}
