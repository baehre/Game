public class Debug
{
    private Debug()
    {

    }


    public static void drawRect(
        Screen screen,
        int x,
        int y,
        int width,
        int height,
        boolean fixed)
    {
        screen.drawRect(x, y, width, height, fixed);
    }
}
