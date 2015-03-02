import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard
    implements KeyListener
{
    private boolean[] keys = new boolean[120];
    public boolean    up;
    public boolean    down;
    public boolean    left;
    public boolean    right;


    public void update()
    {
        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];
    }


    @Override
    public void keyTyped(KeyEvent e)
    {
    }


    @Override
    public void keyPressed(KeyEvent e)
    {
        keys[e.getKeyCode()] = true;
    }


    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
    }

}
