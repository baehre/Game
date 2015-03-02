public class Dummy
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

    private int            time         = 0;
    private double         xa           = 0;
    private double         ya           = 0;
    private double         speed        = 0.8;


    // coordinate to spawn at
    public Dummy(int x, int y)
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
            //move(xa, ya);
            walking = true;
        }
        else
        {
            walking = false;
        }
    }


    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int)x, (int)y, sprite);
    }

}
