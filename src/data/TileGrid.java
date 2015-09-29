package data;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class TileGrid {
	
	public Tile[][] map;
	private int x, y;
	private Texture[] textures = new Texture[3]; //was 2
	
	public TileGrid(){
		GenerateMap();
		
		textures[0] = QuickLoad("GrassTile");
		textures[1] = QuickLoad("SkyTile");
		textures[2] = QuickLoad("DirtTile"); //added this
	}
	
	public TileGrid(String name){
		InputStream resourceAsStream = this.getClass().getResourceAsStream("../res/Map1.txt");
		Scanner MapReader = new Scanner(resourceAsStream);
		MapReader.useDelimiter(",");
		
		x = Integer.parseInt(MapReader.next());
		y = Integer.parseInt(MapReader.next());
		
		map = new Tile[y][x];
		String _tile = "";
		
		for(int i = 0; i < y; i++){
			MapReader.nextLine();
			for(int j = 0; j < x; j++){
				_tile = MapReader.next();
				if(_tile.equals("S")){
					map[i][j] = new Tile(TileType.Empty);
				}else{
					map[i][j] = new Tile(TileType.Grass);
				}
			}
		}
		MapReader.close();
		
		textures[0] = QuickLoad("GrassTile");
		textures[1] = QuickLoad("SkyTile");
	}
	
	public void Draw(){
		for(int xPos = -1; xPos < WIDTH/32+1; xPos++){
			for(int yPos = -1; yPos < HEIGHT/32+2; yPos++){
				//System.out.println("(" + (yPos-YOFFSET/32) + "," + (xPos-XOFFSET/32) + ")");
				if(yPos-YOFFSET/32 > y-1 || yPos-YOFFSET/32 < 0 || xPos-XOFFSET/32 > x-1 || xPos-XOFFSET/32 < 0){
					DrawTexture(textures[1], xPos*32+XOFFSET%32,yPos*32+YOFFSET%32,32,32);
					//System.out.println("*");
				} else {
					DrawTexture(getTile(xPos - XOFFSET/32, yPos - YOFFSET/32).texture, xPos*32+XOFFSET%32,yPos*32+YOFFSET%32,32,32);
				}
			}
		}
	}
	
	public Tile getTile(int x, int y){
		if(y > this.y-1 || y < 0 || x > this.x -1 || x < 0){
			return new Tile(TileType.Empty);
		}else{
			return map[y][x];
		}
	}

    public Tile[] getTilesX(int xStart, int xEnd, int yPos){
        Tile[] tiles = new Tile[xEnd-xStart+1];
        for (int i = 0; i < xEnd-xStart+1; i++){
            tiles[i] = getTile(xStart+i, yPos);
        }
        return tiles;
    }
    public Tile[] getTilesY(int yStart, int yEnd, int xPos){
        Tile[] tiles = new Tile[yEnd-yStart+1];
        for (int i = 0; i < yEnd-yStart+1; i++){
            tiles[i] = getTile(xPos, yStart+i);
        }
        return tiles;
    }
	
	public void GenerateMap() {
		x = 500; //was 100
		y = 30;
		map = new Tile[y][x];

		//generates empty map
		for (int Tx = 0; Tx < x; Tx++){
			for (int Ty = 0; Ty < y; Ty++){
				map[Ty][Tx] = new Tile(TileType.Empty);
			}
		}

		flatMap(0, 20);
		platformMap(20, 80);
		semiFlatMap(80, 120);
		mountainMap(120, 150);
		flatMap(150, 170);
		beachMap(170, 200);
		flatMap(200, 220);
		mountainMap(220, 300);
		flatMap(300, 310);
		pitfallMap(310, 400);
		flatMap(400, 420);
		mountainMap(420, 490);
		flatMap(490, 500);
	}

	private void randomMap(int startX, int endX) {
		for (int Tx = startX; Tx < endX; Tx++){ //for every x-value in map
			double r; //initialize random
			int deltY = 1;
			map[y-deltY][Tx] = new Tile(TileType.Dirt); //map at 29 x++ make tile (forms base)

			r = Math.random();
			while (r > .2 && deltY < 20 && (deltY < 5 || map[y-deltY + 4][Tx - 1].type != TileType.Empty)) {
				deltY++;
				map[y-deltY][Tx] = new Tile(TileType.Dirt);
				r = Math.random();
			}
			map[y-deltY][Tx] = new Tile(TileType.Grass);

		}
	}

	private void flatMap(int startX, int endX) {
		for (int Tx = startX; Tx < endX; Tx++){ //for every x-value in map
			int deltY = 1;
			map[y-deltY][Tx] = new Tile(TileType.Grass); //map at 29 x++ make tile (forms base)
		}
	}

	private void pitfallMap(int startX, int endX) {
		int pitCount = 0;
		for (int Tx = startX; Tx < endX; Tx++){ //for every x-value in map
			double r; //initialize random
			int deltY = 1;

			r = Math.random();
			while ((pitCount > 3) ||(r > .3 && deltY < 4)) {
				map[y-deltY][Tx] = new Tile(TileType.Dirt);
				deltY++;
				r = Math.random();
				pitCount = 0;
			}
			pitCount++;
			//map[y-deltY][Tx] = new Tile(TileType.Grass);
		}
	}

	private void semiFlatMap(int startX, int endX) {
		for (int Tx = startX; Tx < endX; Tx++){ //for every x-value in map
			double r; //initialize random
			int deltY = 1;
			map[y-deltY][Tx] = new Tile(TileType.Dirt); //map at 29 x++ make tile (forms base)

			r = Math.random();
			while (r > .2 && deltY < 4) {
				deltY++;
				map[y-deltY][Tx] = new Tile(TileType.Dirt);
				r = Math.random();
			}
			map[y-deltY][Tx] = new Tile(TileType.Grass);
		}
	}

	private void beachMap(int startX, int endX) {
		for (int Tx = startX; Tx < endX; Tx++){ //for every x-value in map
			int deltY = 1;

			double r = Math.random();
			if (r < .1 && map[y-deltY][Tx - 1].type != TileType.Empty) {
				for (int tempEndX = Tx + 2 + (int) (Math.random() * 5); Tx < tempEndX; Tx++) {
                    map[y-deltY][Tx] = new Tile(TileType.Sand);
                    map[y-deltY-1][Tx] = new Tile(TileType.Sand);
                }
                Tx--;
			} else {
				map[y-deltY][Tx] = new Tile(TileType.Sand);
			}
		}
	}

    private void platformMap(int startX, int endX) {
        int height = 5;
        for (int Tx = startX; Tx < endX; Tx++) { //for every x-value in map
            height = height + (int) ((Math.random()-.5) * 4);


            int length = (int) (Math.random() * 10);
            if (Tx + length > endX) {
                length = endX - Tx;
            }
            if (height < 1) {
                height = 1;
            }
            for (int tempEndX = Tx + length; Tx < tempEndX; Tx++) {
                map[y-height][Tx] = new Tile(TileType.Sand);
                map[y-1][Tx] = new Tile(TileType.Grass);
            }
            Tx--;

            map[y-1][Tx] = new Tile(TileType.Grass); //map at 29 x++ make tile (forms base)
        }
    }

	private void mountainMap(int startX, int endX) {
		int currX = startX;
		int r;
		while (currX < endX) {
			r = (int) (Math.random() * (endX - currX));

			if (r == 0) {
				r = 1;
			} else if (r > y) {
                r = 25;
            }
			makeMountain(currX, currX + r);
			currX = currX + r;
		}
	}
	private void makeMountain(int startX, int endX) {
		int height = 1;
		for (int Tx = startX; Tx < endX; Tx++){ //for every x-value in map
			int deltY = 1;
			map[y-deltY][Tx] = new Tile(TileType.Dirt); //map at 29 x++ make tile (forms base)

			while (deltY <= height) {
				deltY++;
				map[y-deltY][Tx] = new Tile(TileType.Dirt);
			}
			map[y-deltY][Tx] = new Tile(TileType.Grass);
			if (Tx -startX < (endX - startX) /2) {
				height++;
			} else {
				height--;
			}
		}
	}
	
}
