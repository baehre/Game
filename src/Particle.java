public class Particle
    extends Entity
{
    private Sprite   sprite;
    private int      life;
    protected double xa;
    protected double ya;
    protected double za;
    protected double xx;
    protected double yy;
    protected double zz;
    private int      time = 0;


    public Particle(int x, int y, int life)
    {
        this.x = x;
        this.y = y;
        // randomizes when they disappear
        this.life = life + (random.nextInt(15) - 10);
        // this makes it so they all disappear at the same time
        // this.life = life;
        this.xx = x;
        this.yy = y;
        sprite = Sprite.particle0;

        // generates a number between -1 and 1 with a bell curve for SD of 0
        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
        this.zz = random.nextFloat() + 2.0;
    }


    public void update()
    {
        time++;
        // just a big number so the int doesnt get HUGE
        if (time >= 7500)
        {
            time = 0;
        }
        if (time > life)
        {
            remove();
        }
        za -= 0.1;

        if (zz < 0)
        {
            zz = 0;
            // change direction of particles
            za *= -0.55;
            xa *= 1.0;
            ya *= 0.4;
        }
        move(xx + xa, (yy + ya) + (zz + za));
    }


    private void move(double x, double y)
    {
        if (collision(x, y))
        {
            // spread of particles
            this.xa *= -0.5;
            this.ya *= -0.5;
            this.za *= -0.5;
        }
        this.xx += xa;
        this.yy += ya;
        this.zz += za;
    }


    public boolean collision(double x, double y)
    {
        boolean solid = false;
        for (int i = 0; i < 4; i++)
        {
            // checks corners
            double xt = (x - i % 2 * 16) / 16;
            double yt = (y - i / 2 * 16) / 16;
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


    public void render(Screen screen)
    {
        // subtractions are to re-adjust for the particles bouncing off wall
        screen.renderSprite((int)xx - 2, (int)yy - (int)zz - 1, sprite, true);
    }
}
