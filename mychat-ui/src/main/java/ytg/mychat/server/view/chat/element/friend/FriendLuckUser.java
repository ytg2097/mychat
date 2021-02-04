package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-21
 **/
@Data
public class FriendLuckUser {

    private Pane pane;

    private Label id;
    private Label head;
    private Label name;
    private Label statusLabel;
    private Label line;

    public FriendLuckUser(String userId,String userNickName,String userHead,Integer status){

        pane = new Pane();
        pane.setUserData(userId);
        pane.setPrefSize(250,70);
        pane.getStyleClass().add("elementFriendLuckUser");
        ObservableList<Node> children = pane.getChildren();

        head = new Label();
        head.setPrefSize(50,50);
        head.setLayoutX(125);
        head.setLayoutY(10);
        head.getStyleClass().add("elementFriendLuckUser_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')",userHead));
        children.add(head);

        name = new Label();
        name.setPrefSize(200,30);
        name.setLayoutX(190);
        name.setLayoutY(10);
        name.setText(userNickName);
        name.getStyleClass().add("elementFriendLuckUser_name");
        children.add(name);

        id = new Label();
        id.setPrefSize(200, 20);
        id.setLayoutX(190);
        id.setLayoutY(40);
        id.setText(userId);
        id.getStyleClass().add("elementFriendLuckUser_id");
        children.add(id);

        line = new Label();
        line.setPrefSize(582,1);
        line.setLayoutX(125);
        line.setLayoutY(50);
        line.getStyleClass().add("elementFriendLuck_line");
        children.add(line);

        statusLabel = new Label();
        statusLabel.setPrefSize(56,30);
        statusLabel.setLayoutX(650);
        statusLabel.setLayoutY(20);

        String statusText = "添加";
        if (1 == status){
            statusText = "允许";
        }else if (2 == status){
            statusText = "已添加";
        }

        statusLabel.setText(statusText);
        statusLabel.setUserData(status);
        statusLabel.getStyleClass().add("elementFriendLuckUser_statusLabel_" + status);
        children.add(statusLabel);
    }
}
