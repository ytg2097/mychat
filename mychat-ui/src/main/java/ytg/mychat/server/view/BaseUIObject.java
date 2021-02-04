package ytg.mychat.server.view;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @description:
 *  抽象的窗口父类, 抽象了窗口的初始化操作和事件定义操作
 * @author: yangtg
 * @create: 2021-01-18
 **/
public abstract class BaseUIObject extends Stage {

    protected Parent root;

    private double xOffset;
    private double yOffset;

    public <T> T $(String id){

        return (T) root.lookup("#" + id);
    }

    public void clearSelectedItems(ListView<Pane>... listViews){

        for (ListView<Pane> listView : listViews) {
            listView.getSelectionModel().clearSelection();
        }
    }


    /**
     * 监听窗口拖动
     */
    public void initRoot(){

        // 鼠标按下事件  记录窗口的位置
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });

        // 鼠标拖拽事件  设置坐标
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });

        // 鼠标释放事件  恢复鼠标样式
        root.setOnMouseReleased(event -> root.setCursor(Cursor.DEFAULT));
    }

    /**
     * 初始化页面
     */
    public abstract void initView();


}
