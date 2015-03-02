import java.util.List;

public class Chaser
    extends Mob
{

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

    private double         xa           = 0;
    private double         ya           = 0;
    private double         speed        = 1.0;


    public Chaser(int x, int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummySprite;
    }


    private void move()
    {
        xa = 0;
        ya = 0;

        List<Player> players = level.getPlayers(this, 50);
        if (players.size() > 0)
        {
            Player player = players.get(0);
            if ((int)x < (int)player.getX())
            {
                xa += speed;
                // System.out.println("XA: " + xa);
            }
            else if ((int)x > (int)player.getX())
            {
                xa -= speed;
                // System.out.println("XA: " + xa);
            }
            if ((int)y < (int)player.getY())
            {
                ya += speed;
                // System.out.println("YA: " + ya);
            }
            else if ((int)y > (int)player.getY())
            {
                ya -= speed;
                // System.out.println("YA: " + ya);
            }
        }

        if (xa != 0 || ya != 0)
        {
            move(xa, ya);
            walking = true;
        }
        else
        {
            walking = false;
        }
    }


    public void update()
    {
        move();
        if (walking)
        {
            // System.out.println("CHASER WALKING");
            animSprite.update();
        }
        else
        {
            // System.out.println("CHASER NOT WALKING");
            animSprite.setFrame(0);
        }
        if (ya < 0)
        {

            animSprite = up;
            direction = Direction.UP;
        }
        if (ya > 0)
        {
            direction = Direction.DOWN;
            animSprite = down;
        }
        if (xa < 0)
        {
            direction = Direction.LEFT;
            animSprite = left;
        }
        if (xa > 0)
        {
            direction = Direction.RIGHT;
            animSprite = right;
        }
    }


    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int)x, (int)y, this);
    }

}
