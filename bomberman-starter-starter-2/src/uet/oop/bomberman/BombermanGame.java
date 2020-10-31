package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.Frame;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;


public class BombermanGame extends Application  {

	public static final int WIDTH = 31;
	public static final int HEIGHT = 13;

	
	private GraphicsContext gc;
	private Canvas canvas;
	private List<Entity> entities = new ArrayList<>();
	private List<Entity> stillObjects = new ArrayList<>();
	private Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
	public boolean goLeft = false, goRight = false, goUp = false, goDown = false;
	
	public static void main(String[] args) {
		Application.launch(BombermanGame.class);
	}

	@Override
	public void start(Stage stage) throws InvocationTargetException {
		// Tao Canvas
		canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
		gc = canvas.getGraphicsContext2D();
		// Tao root container
		Group root = new Group();
		root.getChildren().add(canvas);
		
		// Tao scene
		Scene scene = new Scene(root);

		// Them scene vao stage
		stage.setScene(scene);
		stage.show();

		createMap();

		entities.add(bomberman);
		
    	AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long l) {
				render();
				update();
			}
		};
		timer.start();
		
	}
	
	

	public void createMap() {
		InputStream in = null;
		
		try {
			in = new FileInputStream(new File("C:\\Users\\Administrator\\Downloads\\bomberman-starter-starter-2\\res\\levels\\MapLevel1.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		char map[][] = new char[HEIGHT][WIDTH];
		for (int i = 0; i < HEIGHT; i++) {
			try {
				String temp = br.readLine();
				for (int j = 0; j < temp.length(); j++) {
					map[i][j] = temp.charAt(j);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		for (int i = 0; i < HEIGHT; i++) {
			for (int j = 0; j < WIDTH; j++) {
				Entity object = null;
				switch(map[i][j]) {
					case '#':
						object = new Wall(j, i, Sprite.wall.getFxImage());
						break;
					case '*':
						object = new Wall(j, i, Sprite.brick.getFxImage());
						break;
					case 'x':
						object = new Wall(j, i, Sprite.portal.getFxImage());
						break;
					case 'p':
						object = new Wall(j, i, Sprite.bomb.getFxImage());
						break;
					case '1':
						object = new Wall(j, i, Sprite.balloom_left1.getFxImage());
						break;
					default:
						object = new Grass(j, i, Sprite.grass.getFxImage());
						break;
					
				}
				stillObjects.add(object);
					
			}
		}
		
	}

	public void update() {
		entities.forEach(Entity::update);
	}

	public void render() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		stillObjects.forEach(g -> g.render(gc));
		entities.forEach(g -> g.render(gc));
	}

}
