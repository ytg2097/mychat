package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Data;
import ytg.mychat.server.uitl.Ids;
import ytg.mychat.server.view.chat.data.GroupsData;


/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Data
public class FriendGroup {

    private Pane groupPane;

    private Label groupName;

    private Label groupHead;

    public FriendGroup(String id,String name,String head){

        groupPane = new Pane();

        groupPane.setId(Ids.TalkId.createFriendGroupId(id));
        groupPane.setUserData(new GroupsData(id,name,head));
        groupPane.setPrefHeight(70);
        groupPane.setPrefWidth(250);
        groupPane.getStyleClass().add("elementFriendGroup");

        ObservableList<Node> children = groupPane.getChildren();

        groupName = new Label();
        groupName.setPrefSize(200, 40);
        groupName.setLayoutX(80);
        groupName.setLayoutY(15);
        groupName.setText(name);
        groupName.getStyleClass().add("elementFriendGroup_name");
        children.add(groupName);

        groupHead = new Label();
        groupHead.setPrefSize(50, 50);
        groupHead.setLayoutX(15);
        groupHead.setLayoutY(10);
        groupHead.getStyleClass().add("elementFriendGroup_head");
        groupHead.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", head));
        children.add(groupHead);
    }
}
