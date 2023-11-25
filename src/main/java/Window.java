import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable{
    public boolean isRunning=true;

    public static int currentState;
    public static Scene currentScene;

    public static KeyList keyListener=new KeyList();
    public static MouseList mouseListener= new MouseList();

    public Window (int width, int height, String title){
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(Window.keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        isRunning=true;
        Window.changeState(0);
    }


    public static void closeWindow(){

    }

    public static void changeState(int newState){
        Window.currentState=newState;
        switch(Window.currentState){
            case 0:
                Window.currentScene = new MenuScene(Window.keyListener, Window.mouseListener);
                break;
            case 1:
                Window.currentScene = new GameScene(Window.keyListener);
                break;
            default:
                System.out.println("Unknown scene.");
                Window.currentScene=null;
                break;
        }
    }

    public void update(double dt){
        Image dbImage=createImage(getWidth(), getHeight());
        Graphics dbg= dbImage.getGraphics();
        this.draw(dbg);
        getGraphics().drawImage(dbImage, 0, 0, this);

        currentScene.update(dt);
    }

    public void draw(Graphics g){
        Graphics2D g2= (Graphics2D)g;
        currentScene.draw(g);
    }

    @Override
    public void run() {
        double lastFrameTime=0.0;
        try{
            while(isRunning){
                double time= Time.getTime();
                double deltaTime=time-lastFrameTime;

                update (deltaTime);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
