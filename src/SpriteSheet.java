import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpriteSheet
{
    private String            path;
    public final int          size;
    public int[]              pixels;
    public final int          width;
    public final int          height;
    // 256 is 16x16 grid
    public static SpriteSheet levelTiles               =
                                                           new SpriteSheet(
                                                               "spriteSheets/levelElements.png",
                                                               256);
    public static SpriteSheet characterTiles           =
                                                           new SpriteSheet(
                                                               "spriteSheets/characterSprites.png",
                                                               256);

    // 48 is a 9x9 grid
    public static SpriteSheet gentlemanProjectileTiles =
                                                           new SpriteSheet(
                                                               "spriteSheets/gentlemanProjectileSprites.png",
                                                               48);

    public static SpriteSheet gentlemanSpriteSheet     =
                                                           new SpriteSheet(
                                                               "spriteSheets/gentlemanSprite.png",
                                                               64,
                                                               48);

    public static SpriteSheet afroKidSpriteSheet       =
                                                           new SpriteSheet(
                                                               "spriteSheets/afroKidSprite.png",
                                                               64,
                                                               48);

    public static SpriteSheet sumoWrestlerSpriteSheet  =
                                                           new SpriteSheet(
                                                               "spriteSheets/sumoWrestlerSprite.png",
                                                               64,
                                                               48);

    public static SpriteSheet gentleman_down           =
                                                           new SpriteSheet(
                                                               gentlemanSpriteSheet,
                                                               0,
                                                               0,
                                                               1,
                                                               3,
                                                               16);

    public static SpriteSheet gentleman_up             =
                                                           new SpriteSheet(
                                                               gentlemanSpriteSheet,
                                                               1,
                                                               0,
                                                               1,
                                                               3,
                                                               16);

    public static SpriteSheet gentleman_right          =
                                                           new SpriteSheet(
                                                               gentlemanSpriteSheet,
                                                               2,
                                                               0,
                                                               1,
                                                               2,
                                                               16);

    public static SpriteSheet gentleman_left           =
                                                           new SpriteSheet(
                                                               gentlemanSpriteSheet,
                                                               3,
                                                               0,
                                                               1,
                                                               2,
                                                               16);

    public static SpriteSheet afroKid_down             = new SpriteSheet(
                                                           afroKidSpriteSheet,
                                                           0,
                                                           0,
                                                           1,
                                                           3,
                                                           16);

    public static SpriteSheet afroKid_up               = new SpriteSheet(
                                                           afroKidSpriteSheet,
                                                           1,
                                                           0,
                                                           1,
                                                           3,
                                                           16);

    public static SpriteSheet afroKid_right            = new SpriteSheet(
                                                           afroKidSpriteSheet,
                                                           2,
                                                           0,
                                                           1,
                                                           2,
                                                           16);

    public static SpriteSheet afroKid_left             = new SpriteSheet(
                                                           afroKidSpriteSheet,
                                                           3,
                                                           0,
                                                           1,
                                                           2,
                                                           16);

    public static SpriteSheet sumoWrestler_down        =
                                                           new SpriteSheet(
                                                               sumoWrestlerSpriteSheet,
                                                               0,
                                                               0,
                                                               1,
                                                               3,
                                                               16);

    public static SpriteSheet sumoWrestler_up          =
                                                           new SpriteSheet(
                                                               sumoWrestlerSpriteSheet,
                                                               1,
                                                               0,
                                                               1,
                                                               3,
                                                               16);

    public static SpriteSheet sumoWrestler_right       =
                                                           new SpriteSheet(
                                                               sumoWrestlerSpriteSheet,
                                                               2,
                                                               0,
                                                               1,
                                                               2,
                                                               16);

    public static SpriteSheet sumoWrestler_left        =
                                                           new SpriteSheet(
                                                               sumoWrestlerSpriteSheet,
                                                               3,
                                                               0,
                                                               1,
                                                               2,
                                                               16);

    private Sprite[]          sprites;


    public SpriteSheet(String path, int size)
    {
        this.path = path;
        this.size = size;
        width = size;
        height = size;
        pixels = new int[size * size];
        load();
    }


    public SpriteSheet(String path, int width, int height)
    {
        size = -1;
        this.path = path;
        this.width = width;
        this.height = height;
        pixels = new int[width * height];
        load();
    }


    public SpriteSheet(
        SpriteSheet sheet,
        int x,
        int y,
        int width,
        int height,
        int spriteSize)
    {
        int xx = x * spriteSize;
        int yy = y * spriteSize;
        int w = width * spriteSize;
        int h = height * spriteSize;
        this.width = w;
        this.height = h;

        if (width == height)
        {
            size = width;
        }
        else
        {
            size = -1;
        }

        pixels = new int[w * h];
        for (int y0 = 0; y0 < h; y0++)
        {
            int yPosition = yy + y0;
            for (int x0 = 0; x0 < w; x0++)
            {
                int xPosition = xx + x0;
                pixels[x0 + y0 * w] =
                    sheet.pixels[xPosition + yPosition * sheet.width];
            }
        }

        int frame = 0;
        sprites = new Sprite[width * height];
        // first two for going through all the sprites for one direction
        for (int ya = 0; ya < height; ya++)
        {
            for (int xa = 0; xa < width; xa++)
            {
                int[] spritePixels = new int[spriteSize * spriteSize];
                for (int y0 = 0; y0 < spriteSize; y0++)
                {
                    for (int x0 = 0; x0 < spriteSize; x0++)
                    {
                        spritePixels[x0 + y0 * spriteSize] =
                            pixels[(x0 + xa * spriteSize)
                                + (y0 + ya * spriteSize) * this.width];

                    }
                }
                Sprite newSprite =
                    new Sprite(spritePixels, spriteSize, spriteSize);
                sprites[frame++] = newSprite;
            }
        }
    }


    private void load()
    {
        try
        {
            BufferedImage image =
                ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public Sprite[] getSprites()
    {
        return sprites;
    }
}
