package data;

public class Pickup extends Sprite {
	
	private int FallSpeed = 0;
	private final int MaxFall = 16;

	public Pickup(int x, int y, int width, int height, boolean Solid, TileGrid map) {
		super(x, y, width, height, Solid, map);
	}
	
	public void move(){
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
		}
	}

}
