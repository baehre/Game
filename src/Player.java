import java.util.List;

public class Player
    extends Mob
{
    private Keyboard       input;
    private Sprite         sprite;
    private int            animate      = 0;
    private boolean        walking      = false;

    private int            fireRate     = 0;
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


    public Player(int x, int y, Keyboard input)
    {
        this.x = x;
        this.y = y;
        this.input = input;
        fireRate = GentlemanProjectile.projectileFireRate;
    }


    public Player(Keyboard input)
    {
        this.input = input;
    }


    // if you change the values added to ya and xa
    // you can speed up or slow down the player
    // you can also change it in other things that are moving
    public void update()
    {
        // the speed of the character. can be less than 1.s
        double speed = 1.4;
        if (walking)
        {
            animSprite.update();
        }
        else
        {
            animSprite.setFrame(0);
        }
        if (GentlemanProjectile.projectileFireRate > 0)
        {
            fireRate--;
        }
        double xa = 0;
        double ya = 0;
        if (animate < 7500)
        {
            animate++;
        }
        else
        {
            animate = 0;
        }
        if (input.up)
        {
            ya -= speed;
            animSprite = up;
        }
        else if (input.down)
        {
            ya += speed;
            animSprite = down;
        }
        if (input.left)
        {
            xa -= speed;
            animSprite = left;
        }
        else if (input.right)
        {
            xa += speed;
            animSprite = right;
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
        clear();
        updateShooting();
    }


    private void updateShooting()
    {
        if (Mouse.getButton() == 3 && fireRate <= 0)
        {
            // add 20
            double dx = Mouse.getX() - Game.getWindowWidth() / 2 + 20;
            double dy = Mouse.getY() - Game.getWindowHeight() / 2 + 20;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = GentlemanProjectile.projectileFireRate;
        }
    }


    public void render(Screen screen)
    {
        sprite = animSprite.getSprite();
        screen.renderMob((int)x, (int)y, sprite);
    }


    private void clear()
    {
        for (int i = 0; i < level.getProjectiles().size(); i++)
        {
            Projectile a = level.getProjectiles().get(i);
            if (a.isRemoved())
            {
                level.getProjectiles().remove(i);
            }
        }
    }
}
