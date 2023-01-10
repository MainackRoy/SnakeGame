import javax.swing.*;

public class SnakeGame extends  JFrame//Snake game will inherit all the properties of JFrame
{
    private GameBoard board;
public SnakeGame() //Creating Constructor
{
    board=new GameBoard();
    add(board);
    setResizable(false);
    pack();//it will pack the size of the board
    setBounds(100,100,800,600);
    setVisible(true);
}
public static void main(String[] args)
{
    JFrame snakeGame=new SnakeGame();//Initializing the constructor(where Jframe is the parent class)
}
}
