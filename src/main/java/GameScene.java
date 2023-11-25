import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

public class GameScene extends Scene{


    public KeyList keyListener;

    public GameScene(KeyList keyListener){
        this.keyListener= keyListener;
    }

    @Override
    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)){
            System.out.println("UP arrow is pressed");
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
    }
}
