package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;


/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Getter
public class FriendUser {

    private Pane pane;

    private Label head;
    private Label name;

    public FriendUser(String userId,String userName,String userHead){

        pane = new Pane();
        pane.setId(userId);
        pane.setPrefWidth(250);
        pane.setPrefHeight(70);
        pane.getStyleClass().add("elementFriendUser");
        ObservableList<Node> children = pane.getChildren();

        head = new Label();
        head = new javafx.scene.control.Label();
        head.setPrefSize(50, 50);
        head.setLayoutX(15);
        head.setLayoutY(10);
        head.getStyleClass().add("elementFriendUser_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
        children.add(head);

        name = new Label();
        name = new Label();
        name.setPrefSize(200, 40);
        name.setLayoutX(80);
        name.setLayoutY(15);
        name.setText(userName);
        name.getStyleClass().add("elementFriendUser_name");
        children.add(name);
    }
}
