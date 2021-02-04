package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import lombok.Getter;


/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Getter
public class FriendUserList {

    private Pane pane;
    private ListView<Pane> userListView;

    public FriendUserList(){

        pane = new Pane();
        pane.setId("friendUserList");
        pane.setPrefWidth(314);
        pane.setPrefHeight(0);
        pane.setLayoutX(-10);
        pane.getStyleClass().add("elementFriendUserList");
        ObservableList<Node> children = pane.getChildren();

        userListView = new ListView<>();
        userListView.setId("userListView");
        userListView.setPrefWidth(314);
        userListView.setPrefHeight(0); // 自动计算；userListView.setPrefHeight(70 * items.size() + 10);
        userListView.setLayoutX(-10);
        userListView.getStyleClass().add("elementFriendUser_listView");
        children.add(userListView);
    }
}
