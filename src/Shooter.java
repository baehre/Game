import java.util.Random;
import java.util.List;

public class Shooter
    extends Mob
{
    private int            time         = 0;
    private int            xa           = 0;
    private int            ya           = 0;

    private SpriteSheet    mySheetDown  = SpriteSheet.sumoWrestler_down;
    private SpriteSheet    mySheetUp    = SpriteSheet.sumoWrestler_up;
    private SpriteSheet    mySheetLeft  = SpriteSheet.sumoWrestler_left;
    private SpriteSheet    mySheetRight = SpriteSheet.sumoWrestler_right;
    private AnimatedSprite down         = new AnimatedSprite(
                                            mySheetDown,
                                            16,
                                            16,
                                            3);
    private AnimatedSprite up           = new AnimatedSprite(
                                            mySheetUp,
                                            16,
                                            16,
                                            3);
    private AnimatedSprite right        = new AnimatedSprite(
                                            mySheetRight,
                                            16,
                                            16,
                                            2);
    private AnimatedSprite left         = new AnimatedSprite(
                                            mySheetLeft,
                                            16,
                                            16,
                                            2);

    private AnimatedSprite animSprite   = down;
    private Entity         rand         = null;


    public Shooter(int x, int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummySprite;
    }


    public void update()
    {

        time++;
        if (time > 7500)
        {
            time = 0;
        }
        // time % 60 == 0 is once per second.
        if (time % (random.nextInt(60) + 30) == 0)
        {
            xa = random.nextInt(3) - 1;
            ya = random.nextInt(3) - 1;
        }
        if (walking)
        {
            animSprite.update();
        }
        else
        {
            animSprite.setFrame(0);
        }
        if (ya < 0)
        {

            animSprite = up;
            direction = Direction.UP;
        }
        else if (ya > 0)
        {
            direction = Direction.DOWN;
            animSprite = down;
        }
        if (xa < 0)
        {
            direction = Direction.LEFT;
            animSprite = left;
        }
        else if (xa > 0)
        {
            direction = Direction.RIGHT;
            animSprite = right;
        }

        if (xa != 0 || ya != 0)
        {
            // move(xa, ya);
            walking = true;
        }
        else
        {
            walking = false;
        }
        // shoot closest
        // shootClosest();
        // shoot random
        shootRandom();
    }


    private void shootRandom()
    {
        // if you put the if statemtn around the whoel thing you can affect how
        // fast he shoots
        if (time % 60 == 0)
        {
            List<Entity> entities = level.getEntities(this, 500);
            entities.add(level.getClientPlayer());
            int index = random.nextInt(entities.size());
            rand = entities.get(index);
        }
        if (rand != null)
        {
            if (!(rand instanceof ParticleSpawner)
                || !(rand instanceof Particle))
            {
                double dx = rand.getX() - x;
                double dy = rand.getY() - y;
                double direction = Math.atan2(dy, dx);
                shoot(x, y, direction);
            }
        }

    }


    private void shootClosest()
    {
        double min = 0;
        Entity closest = null;
        List<Entity> entities = level.getEntities(this, 500);
        entities.add(level.getClientPlayer());
        for (int i = 0; i < entities.size(); i++)
        {
            Entity e = entities.get(i);
            double distance =
                Vector2i.getDistance(
                    new Vector2i((int)x, (int)y),
                    new Vector2i((int)e.getX(), (int)e.getY()));
            if (i == 0 || distance < min)
            {
                if (!(e instanceof ParticleSpawner) || !(e instanceof Particle))
                {
                    min = distance;
                    closest = e;
                }
            }
        }
        if (closest != null)
        {
            double dx = closest.getX() - x;
            double dy = closest.getY() - y;
            double direction = Math.atan2(dy, dx);
            shoot(x, y, direction);
        }
    }


    public void render(Screen screen)
    {
        screen.renderSprite(5, 5, new Sprite(80,80,0xff0000), true);
        sprite = animSprite.getSprite();
        screen.renderMob((int)x, (int)y, this);
    }

}
