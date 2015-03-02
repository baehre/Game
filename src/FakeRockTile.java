public class FakeRockTile
    extends Tile
{

    public FakeRockTile(Sprite sprite)
    {
        super(sprite);
    }


    public void render(int x, int y, Screen screen)
    {
        screen.renderTile(x << 4, y << 4, this);
    }
}
