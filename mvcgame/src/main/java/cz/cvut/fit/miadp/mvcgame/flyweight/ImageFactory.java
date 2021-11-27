package cz.cvut.fit.miadp.mvcgame.flyweight;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class ImageFactory {
    static Map<String, Image> images = new HashMap<>();

    public Image getImage(String path) {
        Image img = images.get(path);

        if (img == null) {
            img = new Image(path);
            images.put(path, img);
        }

        return img;
    }
}
