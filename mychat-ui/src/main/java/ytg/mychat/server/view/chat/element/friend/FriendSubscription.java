package ytg.mychat.server.view.chat.element.friend;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.TextAlignment;
import lombok.Getter;


/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-20
 **/
@Getter
public class FriendSubscription {

    private Pane pane;

    private Label name;

    private Label head;

    private Pane subPane;

    public FriendSubscription(){

        pane = new Pane();
        pane.getStyleClass().add("elementFriendSubscription");
        pane.setPrefSize(270,70);

        ObservableList<Node> children = pane.getChildren();

        head = new Label();
        head.setPrefSize(50,50);
        head.setLayoutX(15);
        head.setLayoutY(10);
        head.getStyleClass().add("elementFriendSubscription_head");
        children.add(head);

        // 名称区域
        name = new Label();
        name.setPrefSize(200,40);
        name.setLayoutX(80);
        name.setLayoutY(15);
        name.setText("公众号");
        name.getStyleClass().add("elementFriendSubscription_name");
        children.add(name);

        // 初始化未装载
        subPane = new Pane();
        subPane.setPrefSize(850, 560);
        subPane.setStyle("-fx-background-color:transparent;");
        ObservableList<Node> subPaneChildren = subPane.getChildren();

        Button gzh_button = new Button();
        gzh_button.setPrefSize(65,65);
        gzh_button.setLayoutX(110);
        gzh_button.setLayoutY(30);
        gzh_button.setStyle("-fx-background-color: transparent;" +
                "-fx-background-radius: 0px;" +
                "-fx-border-width: 50px;" +
                "-fx-background-image: url('/fxml/login/img/system/bugstack_logo.png');");
        subPaneChildren.add(gzh_button);

        Label gzh_label = new Label();
        gzh_label.setPrefSize(150,20);
        gzh_label.setLayoutX(95);
        gzh_label.setLayoutY(100);
        gzh_label.setText("bugstack虫洞栈");
        gzh_label.setStyle("-fx-background-color: transparent;-fx-border-width: 0; -fx-text-fill: #999999;" +
                "-fx-font-size: 14px;");
        gzh_label.setTextAlignment(TextAlignment.CENTER);
        subPaneChildren.add(gzh_label);
    }
}
