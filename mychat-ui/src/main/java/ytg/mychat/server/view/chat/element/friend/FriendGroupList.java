package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import lombok.Data;


/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Data
public class FriendGroupList {

    private Pane pane;

    private ListView<Pane> groupList;

    public FriendGroupList(){

        pane = new Pane();

        pane.setId("friendGroupList");
        pane.setPrefWidth(314);
        // 自动计算；groupListView.setPrefHeight(70 * items.size() + 10);
        pane.setPrefHeight(0);
        pane.setLayoutX(-10);
        pane.getStyleClass().add("elementFriendGroupList");
        ObservableList<Node> children = pane.getChildren();

        groupList = new ListView<>();
        groupList.setId("groupListView");
        groupList.setPrefWidth(314);
        groupList.setPrefHeight(0);
        groupList.setLayoutX(-10);
        groupList.getStyleClass().add("elementFriendGroupList_listView");
        children.add(groupList);
    }
}
