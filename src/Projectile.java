import java.util.Random;

public abstract class Projectile
    extends Entity
{
    protected final double xOrigin;
    protected final double yOrigin;
    protected double    angle;
    protected Sprite    projectileSprite;
    protected double    newX;
    protected double    newY;
    protected double    projectileSpeed;
    protected double    projectileRange;
    protected double    projectileDamage;
    protected double    x;
    protected double    y;
    protected double distance;
    protected final Random random = new Random();


    public Projectile(double x, double y, double direction)
    {
        xOrigin = x;
        yOrigin = y;
        angle = direction;
        this.x = x;
        this.y = y;
    }


    public Sprite getSprite()
    {
        return projectileSprite;
    }


    public int getSpriteSize()
    {
        return projectileSprite.size;
    }


    protected void move()
    {

    }
}
