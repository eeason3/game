package helpers;

import static helpers.Artist.LoadTexture;
import static org.lwjgl.opengl.GL11.*;

import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	
	public static final int WIDTH = 900, HEIGHT = 600;
	public static int XOFFSET = 0, YOFFSET = 0;
	public static Texture background;
	
	public static void BeginSession(){
		Display.setTitle("Game");
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	/*public static void loadBackground(String name){
		background = QuickLoad(name);
	}*/
	
	public static void DrawQuad(int x, int y, int width, int height){
		glBegin(GL_QUADS);
		glVertex2f(x,y);
		glVertex2f(x+width, y);
		glVertex2f(x+width, y+height);
		glVertex2f(x, y+height);
		glEnd();
	}
	
	public static void DrawLine(int init_x, int init_y, int fin_x, int fin_y){
		glBegin(GL_LINES);
		glVertex2f(init_x, init_y);
		glVertex2f(fin_x, fin_y);
		glEnd();
	}
	
	public static void DrawTexture(Texture texture, int x, int y, int width, int height){
		texture.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0 ,0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static Texture LoadTexture(String path, String fileType){
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	
	public static Texture QuickLoad(String Name){
		return LoadTexture("res/"+Name+".png", "PNG");
	}
}
