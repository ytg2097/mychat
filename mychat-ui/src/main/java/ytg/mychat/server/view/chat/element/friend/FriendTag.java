package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import lombok.Getter;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Getter
public class FriendTag {

    private Pane pane;

    public FriendTag(String tagText){

        pane = new Pane();
        pane.setPrefSize(270,24);
        pane.setStyle("-fx-background-color: transparent;");
        ObservableList<Node> children = pane.getChildren();

        Button label = new Button();
        label.setPrefSize(260,24);
        label.setLayoutX(5);
        label.setText(tagText);
        label.getStyleClass().add("element_friend_tag");
        children.add(label);
    }
}
