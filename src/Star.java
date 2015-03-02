import java.util.List;

public class Star
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
    private List<Node>     path         = null;
    private int            time         = 0;


    public Star(int x, int y)
    {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.dummySprite;
    }


    private void move()
    {
        xa = 0;
        ya = 0;

        // possible issue the casting of int
        int playerX = (int)level.getPlayerAt(0).getX();
        int playerY = (int)level.getPlayerAt(0).getY();
        Vector2i start = new Vector2i((int)getX() >> 4, (int)getY() >> 4);
        Vector2i destination = new Vector2i(playerX >> 4, playerY >> 4);
        List<Player> players = level.getPlayers(this, 70);
        if (players.size() > 0)
        {
            if (time % 3 == 0)
            {
                path = level.findPath(start, destination);
            }
            if (path != null)
            {
                if (path.size() > 0)
                {
                    Vector2i vect = path.get(path.size() - 1).tile;
                    if (x < vect.getX() << 4)
                    {
                        xa++;
                    }
                    if (x > vect.getX() << 4)
                    {
                        xa--;
                    }
                    if (y < vect.getY() << 4)
                    {
                        ya++;
                    }
                    if (y > vect.getY() << 4)
                    {
                        ya--;
                    }
                }
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
        time++;
        move();
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
    }


    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int)x, (int)y, this);
    }

}
