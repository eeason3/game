package data;

public enum TileType {

	Wood("WoodTile", true),
	Sky("SkyTile", false),
	Grass("GrassTile", true),
	Empty(false),
	Dirt("DirtTile", true),
	Sand("SandTile", true);

	String TextureName;
	boolean Solid;
	boolean Visible;

	TileType(String TextureName, boolean Solid){
		this.TextureName = TextureName;
		this.Solid = Solid;
		Visible = true;
	}
	TileType(boolean Solid){
		this.TextureName = "SkyTile";
		this.Solid = Solid;
		Visible = false;
	}
}
