public class TileCoordinate
{
    private int       x;
    private int       y;
    private final int tileSize = 16;


    public TileCoordinate(int x, int y)
    {
        this.x = x * tileSize;
        this.y = y * tileSize;
    }


    public int getX()
    {
        return x;
    }


    public int getY()
    {
        return y;
    }


    public int[] getXAndY()
    {
        int[] a = new int[2];
        a[0] = x;
        a[1] = y;
        return a;
    }
}
