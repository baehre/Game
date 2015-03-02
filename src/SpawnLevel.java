import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class SpawnLevel
    extends Level
{

    public SpawnLevel(String path)
    {
        super(path);
    }


    protected void loadLevel(String path)
    {
        try
        {
            BufferedImage image =
                ImageIO.read(SpawnLevel.class.getResource(path));
            // w = width is the same as getWidth() and same for height
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            levelTiles = new int[w * h];
            image.getRGB(0, 0, w, h, levelTiles, 0, w);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("loadLevel failed");
        }
        // for (int i = 0; i < 5; i++)
        // {
        // add(new Dummy(6, 6));
        // add(new Star(5, 5));
        // add(new Dummy(4, 4));
        // add(new Shooter(10, 10));
        add(new Star(10, 10));
        // }
        // for (int i = 0; i < 5; i++)
        // {
        // add(new Dummy(6, 6));
        // }
    }


    protected void generateLevel()
    {

    }
}
