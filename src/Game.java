import java.util.Random;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game
    extends Canvas
    implements Runnable
{
    private static int    width     = 300;
    private static int    height    = 168;
    private static int    scale     = 3;
    private boolean       isRunning = false;
    private JFrame        jFrame;
    private Thread        myThread;
    private BufferedImage myImage   = new BufferedImage(
                                        width,
                                        height,
                                        BufferedImage.TYPE_INT_RGB);
    private int[]         myPixels  = ((DataBufferInt)myImage.getRaster()
                                        .getDataBuffer()).getData();
    private Screen        screen;
    public static String  title     = "A Baehre Bad Game";
    private Keyboard      key;
    private Level         level;
    private Player        player;


    public Game()
    {
        Dimension dimension = new Dimension(scale * width, scale * height);
        setPreferredSize(dimension);
        screen = new Screen(width, height);
        jFrame = new JFrame();
        key = new Keyboard();
        Mouse mouse = new Mouse();
        addKeyListener(key);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        level = Level.spawn;
        //because we are adjusting the character add 1 to both x and y for
        // correct coordinate
        TileCoordinate playerSpawn = new TileCoordinate(2, 2);
        player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
        level.add(player);
    }


    public synchronized void start()
    {
        isRunning = true;
        myThread = new Thread(this, "Show");
        myThread.start();
    }


    public synchronized void stop()
    {
        isRunning = false;
        try
        {
            myThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }


    public static int getWindowWidth()
    {
        return width * scale;
    }


    public static int getWindowHeight()
    {
        return height * scale;
    }


    @Override
    public void run()
    {
        long time = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double nanoSecond = 1000000000.0 / 60.0;
        double change = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        while (isRunning)
        {
            long currentTime = System.nanoTime();
            change += (currentTime - time) / nanoSecond;
            time = currentTime;
            while (change > 0)
            {
                update();
                updates++;
                change--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                jFrame.setTitle(title + " - FPS: " + frames);
                updates = 0;
                frames = 0;
            }
        }
        stop();
    }


    public void update()
    {
        key.update();
        level.update();
    }


    public void render()
    {
        BufferStrategy strategy = getBufferStrategy();
        if (strategy == null)
        {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        double xScroll = player.getX() - screen.width / 2;
        double yScroll = player.getY() - screen.height / 2;

        level.render((int)xScroll, (int)yScroll, screen);

        // can use this to limit the view of a player. (fog of war?)
        // if fixed==true then the rectangle is one spot on teh map
        // if fixed == false then the rectangle follows the player

        // Sprite sprite = new Sprite(5, 10, 0x00ffff);
        // screen.renderSprite(width - 100, 50, sprite, true);
        // the commented out code belwo can make a particley field thing
        // looks kinda cool. maybe tele pad?

        // Random random = new Random();
        // for (int i = 0; i < 100; i++)
        // {
        // int x = random.nextInt(20);
        // int y = random.nextInt(20);

        // screen.renderSprite(width - 100 + x, 50 + y, sprite, true);
        // }

        for (int j = 0; j < myPixels.length; j++)
        {
            myPixels[j] = screen.screenPixels[j];
        }

        Graphics a = strategy.getDrawGraphics();
        // all graphics stuff between here and dispose

        a.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
        // ends graphics stuff
        a.dispose();
        strategy.show();
    }


    public static void main(String[] args)
    {
        Game game = new Game();
        game.jFrame.setResizable(false);
        game.jFrame.setTitle(title);
        game.jFrame.add(game);
        game.jFrame.pack();
        game.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.jFrame.setLocationRelativeTo(null);
        game.jFrame.setVisible(true);
        game.start();
    }

}
