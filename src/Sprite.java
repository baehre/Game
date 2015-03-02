public class Sprite
{
    public final int      size;
    private int           x;
    private int           y;
    public int[]          pixels;
    protected SpriteSheet sheet;
    private int           width;
    private int           height;

    // important. makes tiles
    public static Sprite  grassSprite               = new Sprite(
                                                        16,
                                                        0,
                                                        0,
                                                        SpriteSheet.levelTiles);
    public static Sprite  voidSprite                =
                                                        new Sprite(
                                                            16,
                                                            0xffff6600);

    public static Sprite  flowerSprite              = new Sprite(
                                                        16,
                                                        0,
                                                        1,
                                                        SpriteSheet.levelTiles);

    public static Sprite  rockSprite                = new Sprite(
                                                        16,
                                                        0,
                                                        2,
                                                        SpriteSheet.levelTiles);

    public static Sprite  fakeRockSprite            = new Sprite(
                                                        16,
                                                        0,
                                                        2,
                                                        SpriteSheet.levelTiles);

    public static Sprite  gentlemanProjectileSprite =
                                                        new Sprite(
                                                            16,
                                                            0,
                                                            0,
                                                            SpriteSheet.gentlemanProjectileTiles);

    // particles
    public static Sprite  particle0                 = new Sprite(3, 0xffff0000);

    public static Sprite  dummySprite             =
                                                        new Sprite(
                                                            16,
                                                            0,
                                                            0,
                                                            SpriteSheet.sumoWrestlerSpriteSheet);


    public Sprite(int size, int x, int y, SpriteSheet sheet)
    {
        this.size = size;
        pixels = new int[size * size];
        this.width = size;
        this.height = size;
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }


    protected Sprite(SpriteSheet sheet, int width, int height)
    {
        if (width == height)
        {
            size = width;
        }
        else
        {
            size = -1;
        }

        this.width = width;
        this.height = height;
        this.sheet = sheet;
        pixels = new int[width * height];
    }


    public Sprite(int width, int height, int color)
    {
        size = -1;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        setColor(color);
    }


    public Sprite(int size, int color)
    {
        this.size = size;
        this.width = size;
        this.height = size;
        pixels = new int[size * size];
        setColor(color);
    }


    public Sprite(int[] spritePixels, int width, int height)
    {
        if (width == height)
        {
            size = width;
        }
        else
        {
            size = -1;
        }
        this.width = width;
        this.height = height;
        pixels = spritePixels;
    }


    private void setColor(int color)
    {
        for (int i = 0; i < width * height; i++)
        {
            pixels[i] = color;
        }
    }


    public int getWidth()
    {
        return width;
    }


    public int getHeight()
    {
        return height;
    }


    private void load()
    {
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                pixels[x + y * width] =
                    sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
            }
        }
    }
}
