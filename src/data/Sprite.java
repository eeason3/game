package data;

import static helpers.Artist.QuickLoad;

import org.newdawn.slick.opengl.Texture;

public class Sprite {
	
	protected int x, y, width, height;
	protected boolean isSolid;
	protected Texture texture;
	
	public Sprite(int x, int y, int width, int height, boolean Solid){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isSolid = Solid;
		texture = QuickLoad("WoodTile");
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}
	public void setWidth(int width){
		this.width = width;
	}
	public void setHeight(int height){
		this.height = height;
	}

}
