package data;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class Player extends Sprite{
	
	private int JumpSpeed = 0;
	private int Speed = 4;
	private int FallSpeed = 0;
	private final int MaxFall = 16;
	
	public Player(int x, int y, int width, int height, TileGrid map) {
		super(x, y, width, height, true, map);
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
		System.out.println(Grounded() + ": " + FallSpeed);
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
						y+=(32-y%32)%32;
					else 
						y-=y%32;
					FallSpeed = 0;
				}
		}else {
			FallSpeed = 0;
			if(Keyboard.isKeyDown(Keyboard.KEY_W)){
				FallSpeed = -10;
			}
		}
		
		if(x < 0)
			x=0;
		if(x>map.getX()*32-width)
			x=map.getX()*32-width;
		
		XOFFSET = -x+WIDTH/2+16;
		YOFFSET = -y+HEIGHT/2+16;
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
	
	
	
}
