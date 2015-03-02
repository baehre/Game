import java.util.List;
import java.util.ArrayList;

public abstract class Mob
    extends Entity
{
    // 0 north 1 east 2 south 3 west
    protected boolean walking = false;


    protected enum Direction
    {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    protected Direction direction;


    public void move(double xa, double ya)
    {
        if (xa != 0 && ya != 0)
        {
            move(xa, 0);
            move(0, ya);
            return;
        }
        if (xa > 0)
        {
            direction = Direction.RIGHT;
        }
        if (xa < 0)
        {
            direction = Direction.LEFT;
        }
        if (ya > 0)
        {
            direction = Direction.DOWN;
        }
        if (ya < 0)
        {
            direction = Direction.UP;
        }

        while (xa != 0)
        {
            if (Math.abs(xa) > 1)
            {
                if (!collision(abs(xa), ya))
                {
                    this.x += abs(xa);
                }
                xa -= abs(xa);
            }
            else
            {
                if (!collision(abs(xa), ya))
                {
                    this.x += xa;
                }
                xa = 0;
            }
        }
        while (ya != 0)
        {
            if (Math.abs(ya) > 1)
            {
                if (!collision(xa, abs(ya)))
                {
                    this.y += abs(ya);
                }
                ya -= abs(ya);
            }
            else
            {
                if (!collision(xa, abs(ya)))
                {
                    this.y += ya;
                }
                ya = 0;
            }
        }
    }


    private int abs(double value)
    {
        if (value < 0)
        {
            return -1;
        }
        return 1;
    }


    public abstract void update();


    public abstract void render(Screen screen);


    protected void shoot(double x, double y, double dir)
    {
        Projectile a = new GentlemanProjectile(x, y, dir);
        level.add(a);

    }


    private boolean collision(double xa, double ya)
    {
        boolean solid = false;
        for (int i = 0; i < 4; i++)
        {
            // the i equation bit affects collision mess with values.

            // DONT TOUCH.
            double xt = ((x + xa) - i % 2 * 15 - 6) / 16;

            // DONT TOUCH.
            double yt = ((y + ya) - i / 2 * 15 - 2) / 16;
            int ix = (int)Math.ceil(xt);
            int iy = (int)Math.ceil(yt);
            if (i % 2 == 0)
            {
                ix = (int)Math.floor(xt);
            }
            if (i / 2 == 0)
            {
                iy = (int)Math.floor(yt);
            }
            if (level.getTile(ix, iy).solid())
            {
                solid = true;
            }
        }
        return solid;
    }
}