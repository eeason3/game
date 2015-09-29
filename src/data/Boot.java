package data;

import java.io.IOException;
import java.util.ArrayList;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import static org.lwjgl.opengl.GL11.*;
import static helpers.Artist.*;
import static helpers.Clock.*;

public class Boot {
	
	public ArrayList<Tile> Tiles = new ArrayList<Tile>();
	public Player player;
	public int Gravity = 1;
	public int PlayerSpeed = 5;
	public TileGrid map;
	private int lastFPS;
	private int fps = 0;
	
	
	public Boot(){
		
		BeginSession();
		lastFPS = (int) getTime();
		
		map = new TileGrid(/*"../res/Map1.txt"*/);
		player = new Player(0,0,32,64,map);
		//loadBackground("SpiderBackground");
		
		while(!Display.isCloseRequested()){
			
			player.move();
			
			render();
			
			updateFPS();
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	public void render(){
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); 
		//DrawTexture(background,0,0,WIDTH*3/2,HEIGHT*3/2);
		glColor3f(1.0f, 1.0f, 1.0f);  //uncomment this
		//DrawQuad(0,0,WIDTH,HEIGHT);
		map.Draw();
		glColor3f(0.0f, 0.0f, 0.0f);
		DrawQuad(player.x + XOFFSET, player.y + YOFFSET, player.width, player.height);
		}
	
	public void keyInput(){
		
	}
	
	public void updateFPS() {
	    if (getTime() - lastFPS > 1000) {
	        Display.setTitle("Game: " + fps); 
	        fps = 0; //reset the FPS counter
	        lastFPS += 1000; //add one second
	    }
	    fps++;
	}

	
	public static void main(String[] args) throws IOException{
		new Boot();
	}
	
}
