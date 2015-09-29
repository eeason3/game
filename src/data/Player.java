package data;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Player extends Sprite{
	
	private int JumpSpeed = 0;
	private TileGrid map;
	private int Speed = 4;
	private int FallSpeed = 0;
	private final int MaxFall = 16;
	
	public Player(int x, int y, int width, int height, TileGrid map) {
		super(x, y, width, height, true);
		this.map = map;
	}
	
	public void move(){
		if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			//boolean collision = map.getTile((x-Speed)/32, y/32).type.Solid;
			//boolean collision2 = map.getTile((x-Speed)/32, (y+31)/32).type.Solid;
			if(!Collision(-Speed)){
				x-=Speed;
			}else {
				x-=x%32;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			//boolean collision = map.getTile((x+31+Speed)/32, y/32).type.Solid;
			//boolean collision2 = map.getTile((x+31+Speed)/32, (y+31)/32).type.Solid;
			if(!Collision(Speed)){
				x+=Speed;
			}else {
				x+=x%32;
			}
		}
		if (FallSpeed != 0 || !Grounded()){
			if (FallSpeed < MaxFall)
				FallSpeed++;
				/*boolean collision = map.getTile(x/32, (y+height-1+FallSpeed)/32).type.Solid;
				boolean collision2 = map.getTile((x+width-1)/32, (y+height-1+FallSpeed)/32).type.Solid;
				*/
				if(!CollisionY(FallSpeed)){
					y+=FallSpeed;
				}else {
					if(FallSpeed > 0)
						y+=(32-y%32);
					else 
						y-=y%32;
					FallSpeed = 0;
				}
		}else {
			FallSpeed = 0;
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				FallSpeed = -20;
			}
		}
		
		XOFFSET = -x+WIDTH/2+16;
		YOFFSET = -y+HEIGHT/2+16;
	}
	
	private boolean Grounded(){
		boolean collision = map.getTile(x/32, (y+height)/32).type.Solid;
		boolean collision2 = map.getTile((x+width-1)/32, (y+height)/32).type.Solid;
		if (collision || collision2) 
			return true;
		else
			return false;
	}
	public int getJump(){
		return JumpSpeed;
	}
	public void setJump(int jump){
		JumpSpeed = jump;
	}
	public void changeX(int x){
		this.x += x;
	}
	public void changeY(int y){
		this.y += y;
	}
	public void changeJump(int jump){
		JumpSpeed += jump;
	}
	
	private boolean Collision(int xMove){
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
	
	private boolean CollisionY(int yMove){
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
	
}
