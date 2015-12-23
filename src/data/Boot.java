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
	
	public Player player;
	public int Gravity = 1;
	public int PlayerSpeed = 5;
	public TileGrid map;	
	private int lastFPS;
	private int fps = 0;
	private Pickup[] pickups = new Pickup[10];
	
	public Boot(){
		
		BeginSession();
		lastFPS = (int) getTime();
		
		map = new TileGrid(/*"../res/Map1.txt"*/);
		player = new Player(0,700,32,64,map);
		XOFFSET = -player.x+WIDTH/2+16;
		YOFFSET = -player.y+HEIGHT/2+16;
		//loadBackground("SpiderBackground");
		
		/*for (int i = 0; i < pickups.length; i++){
			int xPos = (int)(Math.random()*map.getX()*32)-32;
			int yPos = (int)(Math.random()*map.getY()*32)+32;
			pickups[i] = new Pickup(xPos,yPos,32,32,false,map);
		}*/
		
		while(!Display.isCloseRequested()){
			
			player.move();
			/*for (Pickup p: pickups){
				p.move();
				glColor3f(0.0f,0.0f,0.0f);
				DrawTexture(QuickLoad("WoodTile"),p.x+XOFFSET, p.y+YOFFSET, p.width, p.height);
			}*/
			
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
		glColor3f(1.0f,1.0f,1.0f);
		//DrawQuad(0,0,WIDTH,HEIGHT);
		map.Draw();
		glColor3f(0.0f,0.0f,0.0f);
		DrawQuad(player.x+XOFFSET, player.y+YOFFSET, player.width, player.height);
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
