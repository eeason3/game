package data;

import static helpers.Artist.QuickLoad;

import org.newdawn.slick.opengl.Texture;

public class Sprite {
	
	protected int x, y, width, height;
	protected boolean isSolid;
	protected Texture texture;
	protected TileGrid map;
	
	public Sprite(int x, int y, int width, int height, boolean Solid, TileGrid map){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		isSolid = Solid;
		texture = QuickLoad("WoodTile");
		this.map = map;
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
	
	protected boolean Collision(int xMove){
		int xPos = (x+xMove)/32;
		int yStart = y/32;
		int yEnd = (y+height-1)/32;
		if(xMove > 0){
			xPos = (x+xMove+width-1)/32;
		}
		Tile[] tiles = map.getTilesY(yStart, yEnd, xPos);
		for (Tile t : tiles){
			if (t.isSolid()){
				return true;
			}
		}
		return false;
	}
	
	protected boolean CollisionY(int yMove){
		int yPos = (y+yMove)/32;
		int xStart = x/32;
		int xEnd = (x+width-1)/32;
		if(yMove > 0){
			yPos = (y+yMove+height-1)/32;
		}
		Tile[] tiles = map.getTilesX(xStart, xEnd, yPos);
		for (Tile t : tiles){
			if (t.isSolid()){
				return true;
			}
		}
		return false;
	}
	
	protected boolean Grounded(){
		return CollisionY(1);
	}
}
