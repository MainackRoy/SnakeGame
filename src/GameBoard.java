import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameBoard extends JPanel implements ActionListener
{
    int width=400;
    int height=400;
    int x[]=new int [height*width];
    int y[]=new int [height*width];
    int dots=3;//size of the sanke
    int apple_x;
    int apple_y;
    int dot_size=10;
    Image apple;
    Image body;
    Image head;
    boolean leftDirection= true;
    boolean rightDirection= false;
    boolean upDirection=false;
    boolean downDirection=false;
    Timer timer;
    int DELAY=300;
    int RAND_POS=39;

    public  GameBoard()// creating a constructor;
    {
        addKeyListener(new KAdapter());
        setFocusable(true);
        setPreferredSize(new Dimension(width,height));//size of the Game Board
        setBackground(Color.BLACK);
        loadImages();
        initGame();
    }
    public  void initGame()
    {
        dots=3;
        for(int i =0;i<dots;i++)
        {
            x[i]=150+i*dot_size;
            y[i]=150;
        }
        timer= new Timer(DELAY,this);
        timer.start();
    }
    private void loadImages()
    {
        ImageIcon image_apple= new ImageIcon("src/resources/apple.png");
        apple=image_apple.getImage();//image for apple

        ImageIcon image_head=new ImageIcon("src/resources/head.png");
        head=image_head.getImage();//image for head

        ImageIcon image_body=new ImageIcon("src/resources/dot.png");
        body=image_body.getImage();//image for body
    }
    @Override
    public void paintComponent(Graphics graphics)
    {
        super.paintComponent(graphics);
        graphics.drawImage(apple,apple_x,apple_y,this);
        for(int i=0;i<dots;i++)
        {
            if(i==0)
            {
                graphics.drawImage(head,x[0],y[0],this);
            }
            else
            {
                graphics.drawImage(body,x[i],y[i],this);
            }
        }
        Toolkit.getDefaultToolkit().sync();//sync function the map thsi function to the timer
    }
    private void move()
    {
        for(int i=dots-1;i>0;i--)
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
        }
        if(leftDirection)
        {
            x[0]-=dot_size;
        }
        if(rightDirection)
            x[0]+=dot_size;
        if(upDirection)
            y[0]-=dot_size;
        if(downDirection)
            y[0]+=dot_size;
    }

    private void locateApple()
    {
        int r=(int)(Math.random()*(RAND_POS));
        apple_x=r*dot_size;

//        int r=(int)(Math.random());
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        move();
        repaint();
    }

    public class KAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent keyEvent)
        {
            int key=keyEvent.getKeyCode();
            if((key== KeyEvent.VK_LEFT)&&(!rightDirection))
            {
                leftDirection=true;
                upDirection=false;
                downDirection=false;
            }
            if((key== KeyEvent.VK_RIGHT)&&(!leftDirection))
            {
                rightDirection=true;
                upDirection=false;
                downDirection=false;
            }
            if((key== KeyEvent.VK_UP)&&(!downDirection))
            {
                leftDirection=false;
                upDirection=true;
                rightDirection=false;
            }
            if((key== KeyEvent.VK_DOWN)&&(!upDirection))
            {
                leftDirection=false;
                rightDirection=false;
                downDirection=true;
            }
        }
    }
}
