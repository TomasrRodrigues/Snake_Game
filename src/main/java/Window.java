import javax.swing.JFrame;
import java.awt.*;

public class Window extends JFrame implements Runnable{
    public static Window window =null;
    public static boolean isRunning=true;

    public int currentState;
    public Scene currentScene;

    public KeyList keyListener=new KeyList();
    public MouseList mouseListener= new MouseList();

    public Window (int width, int height, String title){
        setSize(width, height);
        setTitle(title);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(keyListener);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);

        isRunning=true;
        changeState(0);
    }

    public static Window getWindow(){
        if (Window.window==null){
            Window.window=new Window(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, Constants.SCREEN_TITLE);
        }

        return Window.window;
    }

    public void closeWindow(){
        isRunning=false;
    }

    public void changeState(int newState){
        currentState=newState;
        switch(currentState){
            case 0:
                currentScene = new MenuScene(keyListener, mouseListener);
                break;
            case 1:
                currentScene = new GameScene(keyListener);
                break;
            default:
                System.out.println("Unknown scene.");
                currentScene=null;
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

        this.dispose();
    }
}
