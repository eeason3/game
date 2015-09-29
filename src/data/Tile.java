package data;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Tile {
	
	public Texture texture;
	public TileType type;
	
	public Tile(TileType type) {
		this.type = type;
		this.texture = QuickLoad(type.TextureName);
		
	}

	public Texture getTex() {
		return texture;
	}

	public boolean isSolid() {
		return type.Solid;
	}
	
	/*public void Draw(){
		int xPos = x+XOFFSET;
		int yPos = y+YOFFSET;
		if(type.Visible && xPos > -32 && xPos < WIDTH && yPos > -32 && yPos < HEIGHT){
			texture.bind();
			glTranslatef(xPos, yPos, 0);
			glBegin(GL_QUADS);
			glTexCoord2f(0 ,0);
			glVertex2f(0, 0);
			glTexCoord2f(1, 0);
			glVertex2f(32, 0);
			glTexCoord2f(1, 1);
			glVertex2f(32, 32);
			glTexCoord2f(0, 1);
			glVertex2f(0, 32);
			glEnd();
			glLoadIdentity();
		}
	}*/
}
