import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MenuScene extends Scene{

    public KeyList keyListener;
    public MouseList mouseListener;
    public BufferedImage background, title, play, playPressed, settings, settingsPressed, exit, exitPressed;
    public Rectangles playRectangle, settingsRectangle, exitRectangle, titleRectangle;

    public BufferedImage playCurrentImage, settingsCurrentImage, exitCurrentImage;

    public MenuScene(KeyList keyListener, MouseList mouseListener){
        this.keyListener= keyListener;
        this.mouseListener= mouseListener;

        try{
            BufferedImage backigroundji = ImageIO.read(new File("Images/Main_Menu_BackGround.png"));
            BufferedImage spritesheet = ImageIO.read(new File("Images/Snake_title.png"));
            BufferedImage spritesheet1 = ImageIO.read(new File("Images/PlayButton_NotPressed.png"));
            BufferedImage spritesheet2 = ImageIO.read(new File("Images/PlayButton_Pressed.png"));
            BufferedImage spritesheet3 = ImageIO.read(new File("Images/SettingsButton_NotPressed.png"));
            BufferedImage spritesheet4 = ImageIO.read(new File("Images/SettingsButton_Pressed.png"));
            BufferedImage spritesheet5 = ImageIO.read(new File("Images/ExitButton_NotPressed.png"));
            BufferedImage spritesheet6 = ImageIO.read(new File("Images/ExitButton_Pressed.png"));

            background=backigroundji.getSubimage(0,0, Constants.SCREEN_WIDTH,Constants.SCREEN_HEIGHT);
            title=spritesheet.getSubimage(200, 0,400, 400 );
            play=spritesheet1.getSubimage(300, 200,200, 100 );
            playPressed=spritesheet2.getSubimage(300, 200,200, 100 );
            settings=spritesheet3.getSubimage(280, 300,240, 100);
            settingsPressed=spritesheet4.getSubimage(280, 300,240, 100);
            exit=spritesheet5.getSubimage(300, 380,200, 100 );
            exitPressed=spritesheet6.getSubimage(300, 380,200, 100 );

        } catch(Exception e){
            e.printStackTrace();
        }

        playCurrentImage=play;
        settingsCurrentImage=settings;
        exitCurrentImage=exit;

        titleRectangle= new Rectangles(200, 10, 400, 400);
        playRectangle= new Rectangles(300, 200, 200, 100);
        settingsRectangle= new Rectangles(300, 300, 200, 100);
        exitRectangle= new Rectangles(300, 380, 200, 100);

    }

    @Override
    public void update(double dt) {
        if (mouseListener.getX()>=playRectangle.x && mouseListener.getX()<=playRectangle.x+playRectangle.width  &&
                mouseListener.getY()>=playRectangle.y && mouseListener.getY()<=playRectangle.y+playRectangle.height){
            playCurrentImage=playPressed;
            if (mouseListener.isPressed){
                Window.changeState(1);
            }
        }
        else{
            playCurrentImage=play;
        }

        if (mouseListener.getX()>=settingsRectangle.x && mouseListener.getX()<=settingsRectangle.x+settingsRectangle.width  &&
                mouseListener.getY()>=settingsRectangle.y && mouseListener.getY()<=settingsRectangle.y+settingsRectangle.height){
            settingsCurrentImage=settingsPressed;
        }
        else{
            settingsCurrentImage=settings;
        }

        if (mouseListener.getX()>=exitRectangle.x && mouseListener.getX()<=exitRectangle.x+exitRectangle.width  &&
                mouseListener.getY()>=exitRectangle.y && mouseListener.getY()<=exitRectangle.y+exitRectangle.height){
            exitCurrentImage=exitPressed;
            if (mouseListener.isPressed){
                Window.closeWindow();
            }
        }
        else{
            exitCurrentImage=exit;
        }
    }

    @Override
    public void draw(Graphics g) {
        //g.setColor(Color.GREEN);
        //g.fillRect(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);

        g.drawImage(background, 0, 0, null);

        g.drawImage(title, 200, 10, 400, 400, null);
        g.drawImage(playCurrentImage, 300, 200, 200, 100, null);
        g.drawImage(settingsCurrentImage, 300, 300, 200, 100, null);
        g.drawImage(exitCurrentImage, 300, 380, 200, 100, null);


    }
}
