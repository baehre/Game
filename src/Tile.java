public class Tile
{
    public int              x;
    public int              y;
    public Sprite           sprite;
    public static Tile      grassTile     = new GrassTile(Sprite.grassSprite);
    public static Tile      voidTile      = new VoidTile(Sprite.voidSprite);
    public static Tile      flowerTile    = new FlowerTile(Sprite.flowerSprite);
    public static Tile      rockTile      = new RockTile(Sprite.rockSprite);
    public static Tile      fakeRockTile  = new FakeRockTile(
                                              Sprite.fakeRockSprite);

    // these are the colors for makign a map
    public static final int colorGrass    = 0xff00ff00;
    public static final int colorFlower   = 0xffffff00;
    public static final int colorRock     = 0xff000000;
    public static final int colorFakeRock = 0xff808487;


    public Tile(Sprite sprite)
    {
        this.sprite = sprite;
    }


    public void render(int x, int y, Screen screen)
    {

    }


    public boolean solid()
    {
        return false;
    }
}
