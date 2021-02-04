package ytg.mychat.server.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-18
 **/
public abstract class AbstractView extends BaseUIObject {

    public AbstractView(String resourceName,String iconUrl){

       init(resourceName,iconUrl);
    }

    private void init(String resourceName, String iconUrl ){

        initRoot(resourceName);
        if (iconUrl != null){

            this.getIcons().add(new Image(iconUrl));
        }
        this.obtain();
        this.initView();
    }

    private void initRoot(String resourceName) {
        try {
            root = FXMLLoader.load(getClass().getResource(resourceName));
        }catch (Exception e){
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
    }

    protected abstract void obtain();

}
