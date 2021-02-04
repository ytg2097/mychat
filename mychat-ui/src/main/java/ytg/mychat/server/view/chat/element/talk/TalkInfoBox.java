package ytg.mychat.server.view.chat.element.talk;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import lombok.Data;
import ytg.mychat.server.uitl.AutoSizeTool;

/**
 * @description:  表述的是每一条聊天消息
 *   包括:　
 *   　　　这一条消息的消息面板
 *          这一条消息的发送人的头像
 *          这一条消息的发送人的昵称
 *          这一条消息的发送箭头
 *          这一条消息的消息内容
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Data
@SuppressWarnings("all")
public class TalkInfoBox {

    private Pane pane;
    private Pane head;
    private Label nikeName;
    private Label infoContentArrow;
    private TextArea infoContent;

    public Pane left(String userNickName,String userHead,String msg){
        double height = AutoSizeTool.getHeight(msg);
        double width = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500,50 + height);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        head = new Pane();
        head.setPrefSize(50,50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        // 昵称
        nikeName = new Label();
        nikeName.setPrefSize(450, 20);
        nikeName.setLayoutX(75);
        nikeName.setLayoutY(5);
        nikeName.setText(userNickName);
        nikeName.getStyleClass().add("box_nikeName");
        children.add(nikeName);

        // 箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutX(75);
        infoContentArrow.setLayoutY(30);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        // 内容
        infoContent = new TextArea();
        infoContent.setPrefWidth(width);
        infoContent.setPrefHeight(height);
        infoContent.setLayoutX(80);
        infoContent.setLayoutY(30);
        infoContent.setWrapText(true);
        infoContent.setEditable(false);
        infoContent.setText(msg);
        infoContent.getStyleClass().add("box_infoContent_left");
        children.add(infoContent);

        return pane;

    }

    public Pane right(String userNickName, String userHead, String msg) {

        double height = AutoSizeTool.getHeight(msg);
        double width = AutoSizeTool.getWidth(msg);

        pane = new Pane();
        pane.setPrefSize(500, 50 + height);
        pane.setLayoutX(853);
        pane.setLayoutY(0);
        pane.getStyleClass().add("infoBoxElement");
        ObservableList<Node> children = pane.getChildren();

        // 头像
        head = new Pane();
        head.setPrefSize(50, 50);
        head.setLayoutX(770);
        head.setLayoutY(15);
        head.getStyleClass().add("box_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        // 箭头
        infoContentArrow = new Label();
        infoContentArrow.setPrefSize(5, 20);
        infoContentArrow.setLayoutX(755);
        infoContentArrow.setLayoutY(15);
        infoContentArrow.getStyleClass().add("box_infoContent_arrow");
        children.add(infoContentArrow);

        // 内容
        infoContent = new TextArea();
        infoContent.setPrefWidth(width);
        infoContent.setPrefHeight(height);
        infoContent.setLayoutX(755 - width);
        infoContent.setLayoutY(15);
        infoContent.setWrapText(true);
        infoContent.setEditable(false);
        infoContent.setText(msg);
        infoContent.getStyleClass().add("box_infoContent_right");
        children.add(infoContent);

        return pane;
    }
}
