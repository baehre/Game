public class GentlemanProjectile
    extends Projectile
{
    // higher is slower lower is faster
    public static final int projectileFireRate = 10;


    public GentlemanProjectile(double x, double y, double direction)
    {
        super(x, y, direction);
        projectileRange = 100;
        projectileDamage = 20;
        // dont want to exceed 5. 5 is really fast.
        projectileSpeed = 2;
        projectileSprite = Sprite.gentlemanProjectileSprite;

        newX = projectileSpeed * Math.cos(angle);
        newY = projectileSpeed * Math.sin(angle);
    }


    public void update()
    {

        // 8 is the size of the thing (it is a square)
        if (level.tileCollision((int)(x + newX), (int)(y + newY), 8, 4, 4))
        {
            // x,y,life,amount,level
            level.add(new ParticleSpawner((int)x, (int)y, 10, 10, level));
            remove();
        }
        move();
    }


    protected void move()
    {
        x += newX;
        y += newY;

        if (distance() > projectileRange)
        {
            remove();
        }
    }


    private double distance()
    {
        double dist = 0;
        dist =
            Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y)
                * (yOrigin - y)));
        return dist;
    }


    public void render(Screen screen)
    {
        // -15 is to fix it so it is in the middle of the character.
        screen.renderProjectile((int)x - 15, (int)y - 15, this);
    }
}
