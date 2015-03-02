import java.util.Random;

public class Screen
{
    public int       width;
    public int       height;
    public int[]     screenPixels;
    public final int mapSize     = 64;
    public final int mapSizeMask = mapSize - 1;
    public int[]     tiles       = new int[mapSize * mapSize];
    private Random   random      = new Random();
    public int       xOffset;
    public int       yOffset;


    public Screen(int width, int height)
    {
        this.width = width;
        this.height = height;
        screenPixels = new int[width * height];

        for (int i = 0; i < mapSize * mapSize; i++)
        {
            tiles[i] = random.nextInt(0xffffff);
        }
    }


    public void clear()
    {
        for (int i = 0; i < screenPixels.length; i++)
        {
            screenPixels[i] = 0;
        }
    }


    public void renderSprite(
        int xPosition,
        int yPosition,
        Sprite sprite,
        boolean fixed)
    {
        if (fixed)
        {
            xPosition -= xOffset;
            yPosition -= yOffset;
        }

        for (int y = 0; y < sprite.getHeight(); y++)
        {
            int ya = y + yPosition;
            for (int x = 0; x < sprite.getWidth(); x++)
            {
                int xa = x + xPosition;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height)
                {
                    continue;
                }
                screenPixels[xa + ya * width] =
                    sprite.pixels[x + y * sprite.getWidth()];
            }

        }
    }


    public void renderSheet(
        int xPosition,
        int yPosition,
        SpriteSheet sheet,
        boolean fixed)
    {
        if (fixed)
        {
            xPosition -= xOffset;
            yPosition -= yOffset;
        }

        for (int y = 0; y < sheet.height; y++)
        {
            int ya = y + yPosition;
            for (int x = 0; x < sheet.width; x++)
            {
                int xa = x + xPosition;
                if (xa < 0 || xa >= width || ya < 0 || ya >= height)
                {
                    continue;
                }
                screenPixels[xa + ya * width] =
                    sheet.pixels[x + y * sheet.width];
            }

        }
    }


    public void renderTile(int xPosition, int yPosition, Tile tile)
    {
        xPosition = xPosition - xOffset;
        yPosition = yPosition - yOffset;
        for (int y = 0; y < tile.sprite.size; y++)
        {
            int yAbsolute = yPosition + y;
            for (int x = 0; x < tile.sprite.size; x++)
            {
                int xAbsolute = xPosition + x;
                if (xAbsolute < -tile.sprite.size || xAbsolute >= width
                    || yAbsolute < 0 || yAbsolute >= height)
                {
                    break;
                }
                if (xAbsolute < 0)
                {
                    xAbsolute = 0;
                }
                screenPixels[xAbsolute + yAbsolute * width] =
                    tile.sprite.pixels[x + y * tile.sprite.size];
            }
        }
    }


    public void renderProjectile(int xPosition, int yPosition, Projectile p)
    {
        xPosition = xPosition - xOffset;
        yPosition = yPosition - yOffset;
        for (int y = 0; y < p.getSpriteSize(); y++)
        {
            int yAbsolute = yPosition + y;
            for (int x = 0; x < p.getSpriteSize(); x++)
            {
                int xAbsolute = xPosition + x;
                if (xAbsolute < -p.getSpriteSize() || xAbsolute >= width
                    || yAbsolute < 0 || yAbsolute >= height)
                {
                    break;
                }
                if (xAbsolute < 0)
                {
                    xAbsolute = 0;
                }
                int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
                if (col != 0xffff00ff)
                {
                    screenPixels[xAbsolute + yAbsolute * width] = col;
                }

            }
        }
    }


    public void renderMob(int xPosition, int yPosition, Mob mob)
    {
        // -16 adjusts every mob rendered correctly... otherwise he goes
        // one tile through walls on the bottom and right
        // and is one tile away from left and up
        xPosition = xPosition - xOffset - 16;
        yPosition = yPosition - yOffset - 16;
        for (int y = 0; y < 16; y++)
        {
            int yAbsolute = yPosition + y;
            int ys = y;
            for (int x = 0; x < 16; x++)
            {
                int xAbsolute = xPosition + x;
                int xs = x;
                if (xAbsolute < -16 || xAbsolute >= width || yAbsolute < 0
                    || yAbsolute >= height)
                {
                    break;
                }
                if (xAbsolute < 0)
                {
                    xAbsolute = 0;
                }
                int col = mob.getSprite().pixels[xs + ys * 16];
                // can use this to change colors on the characters.
                if ((mob instanceof Chaser) && col == 0xffE2AB89)
                {
                    col = 0xffB97A57;
                }
                if ((mob instanceof Star) && col == 0xffE2AB89)
                {
                    col = 0xffFCDC3B;
                }
                // oxff00ff is pink. this is the background color we dont want
                // the extra two ff's is to really get rid of the pink
                if (col != 0xffff00ff)
                {
                    screenPixels[xAbsolute + yAbsolute * width] = col;
                }

            }
        }
    }


    public void renderMob(int xPosition, int yPosition, Sprite sprite)
    {
        // -16 adjusts every mob rendered correctly... otherwise he goes
        // one tile through walls on the bottom and right
        // and is one tile away from left and up
        xPosition = xPosition - xOffset - 16;
        yPosition = yPosition - yOffset - 16;
        for (int y = 0; y < 16; y++)
        {
            int yAbsolute = yPosition + y;
            int ys = y;
            for (int x = 0; x < 16; x++)
            {
                int xAbsolute = xPosition + x;
                int xs = x;
                if (xAbsolute < -16 || xAbsolute >= width || yAbsolute < 0
                    || yAbsolute >= height)
                {
                    break;
                }
                if (xAbsolute < 0)
                {
                    xAbsolute = 0;
                }
                int col = sprite.pixels[xs + ys * 16];
                // oxff00ff is pink. this is the background color we dont want
                // the extra two ff's is to really get rid of the pink
                if (col != 0xffff00ff)
                {
                    screenPixels[xAbsolute + yAbsolute * width] = col;
                }

            }
        }
    }


    public void setOffset(int xOffset, int yOffset)
    {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void drawRect(int xPosition, int yPosition, int width, int height, boolean fixed)
    {
        for(int x = xPosition; x<xPosition+width; x++)
        {
            //pixels[x]
        }
        for(int y = yPosition; y<yPosition+height; y++)
        {

        }
    }
}
