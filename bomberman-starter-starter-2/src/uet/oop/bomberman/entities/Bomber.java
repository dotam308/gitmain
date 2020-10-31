package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.input.Keyboard;

public class Bomber extends Entity {
    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {  
    	if (Keyboard.right) x++;
    	System.out.println(x);
    }
    
}
