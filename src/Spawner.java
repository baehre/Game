public class Spawner
    extends Entity
{
    public enum Type
    {
        MOB,
        PARTICLE;
    }

    private Type  type;
    protected int total;


    public Spawner(int x, int y, Type type, int amount, Level level)
    {
        init(level);
        this.x = x;
        this.y = y;
        this.type = type;
    }

    //so shooter wont keep shooting the wall
    public void update()
    {
        if ((total -= 1) <= 0)
        {
            remove();
        }
    }
}
