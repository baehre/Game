import java.util.Random;

public class Entity
{
    protected double          x;
    protected double          y;
    private boolean        removed = false;
    protected Level        level;
    protected final Random random  = new Random();
    protected Sprite       sprite;


    public Entity()
    {

    }


    public Sprite getSprite()
    {
        return sprite;
    }


    public Entity(int x, int y, Sprite sprite)
    {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
    }


    public double getX()
    {
        return x;
    }


    public double getY()
    {
        return y;
    }


    public void update()
    {

    }


    public void render(Screen screen)
    {
        if (sprite != null)
        {
            screen.renderSprite((int)x, (int)y, sprite, true);
        }
    }


    public void remove()
    {
        // remove from level
        removed = true;
    }


    public boolean isRemoved()
    {
        return removed;
    }


    public void init(Level level)
    {
        this.level = level;
    }
}
