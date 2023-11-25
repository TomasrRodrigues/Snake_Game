import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

public class GameScene extends Scene{

    public Rectangles background, foreground;
    public KeyList keyListener;

    public GameScene(KeyList keyListener){
        this.keyListener= keyListener;

        background=new Rectangles(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        foreground=new Rectangles(24, 48, 24*31, 24*22);


    }

    @Override
    public void update(double dt) {
        if (keyListener.isKeyPressed(KeyEvent.VK_UP)){
            System.out.println("UP arrow is pressed");
        }
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(background.x, background.y, background.width, background.height));

        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(foreground.x, foreground.y, foreground.width, foreground.height));
        //g.setColor(Color.BLUE);
        //g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

    }
}
