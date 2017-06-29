import java.awt.*;

import java.awt.event.*;
import java.applet.*;
import javax.swing.*;

public class start extends Applet implements MouseListener,MouseMotionListener,ActionListener
{
    int size = 10;
    int numMines =30;
    board b;
    boolean button;
    int sob = 0;
    boolean firstClick = true;
    Graphics bg;
    Image offscreen;

    int Px=-1;
    int Py=-1;

    Panel p = new Panel();
    Button z;Button z1;Button z2;
    Label MinesLeft;
    boolean initialize;
    public start()
    {

    }

    public void init(){
        b = new board(size,numMines);
        sob = 30;
        setSize(sob*size,(sob*size)+50);
        repaint();
        addMouseListener(this);
        addMouseMotionListener(this);
        offscreen = createImage(sob*size,sob*size);
        bg=offscreen.getGraphics();

        setLayout(new BorderLayout(3,3));
        p.setBackground(Color.gray);
        add(p,BorderLayout.SOUTH);
        MinesLeft  = new Label("Mines Left: " + numMines+ "  ");
        p.add(MinesLeft);
        z = new Button("Easy");
        z1 = new Button("Medium");
        z2 = new Button("Hard");
        p.add(z);p.add(z1);p.add(z2);
        z.addActionListener(this);z1.addActionListener(this);z2.addActionListener(this);
        showStatus("done");
    }

    public void update(Graphics g)
    {
        MinesLeft.setText("Mines Left: " + b.minesLeft(false)+ "  ");
        paint(g);
    }

    public void paint(Graphics g){

        bg.clearRect(0,0,getWidth(),getHeight());
        //NUMBERS AND MINES
        for(int i = 0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(b.board[i][j]==-1)
                {
                    Image img = getImage(getDocumentBase(),"mine.png");
                    bg.drawImage(img,sob*j,sob*i,sob,sob,this);
                }
                else
                {
                    bg.setColor(Color.white);
                    Image img = getImage(getDocumentBase(),b.board[i][j] +".png");
                    bg.drawImage(img,sob*j,sob*i,sob,sob,this);
                }
            }
        }
        // COVER
        if(b.dead){
            g.drawImage(offscreen,0,0,this);
            return;
        }

        for(int i = 0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(b.reveal[i][j]==0)
                {
                    Image img;

                    if(i==Px&&j==Py)
                    {
                        img = getImage(getDocumentBase(),"tileP.png");
                    }
                    else
                    {
                        img = getImage(getDocumentBase(),"tile.png");
                    }
                    bg.drawImage(img,sob*j,sob*i,sob,sob,this);
                }
                else if(b.reveal[i][j]==-1)
                {
                    Image img = getImage(getDocumentBase(),"flag.png");
                    bg.drawImage(img,sob*j,sob*i,sob,sob,this);
                }
                else if(b.reveal[i][j]==2)
                {
                    Image img = getImage(getDocumentBase(),"qMark.png");
                    bg.drawImage(img,sob*j,sob*i,sob,sob,this);
                }
            }
        }

        if((b.minesLeft(true)==0)&&!firstClick)
        {
            g.setColor(Color.red);
            setFont(new Font("timesnewroman",Font.PLAIN,(int)((numMines*sob)*0.25)));
            g.drawString("You Win!",getWidth()/10,getHeight()/(2/3));
        }
        g.drawImage(offscreen,0,0,this);
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseClicked(MouseEvent e){}

    public void mousePressed(MouseEvent e){
        if(b.dead)
            return;
        int x = e.getX();
        int y = e.getY();

        Px = (y/sob);
        Py = (x/sob);
        repaint();
    }

    public void mouseReleased(MouseEvent e){
        if(b.dead)
            return;

        int x = e.getX();
        int y = e.getY();

        switch(e.getModifiers()) {
            case InputEvent.BUTTON1_MASK: 
            {
                if(firstClick)
                {
                    b.initialize((x/sob),(y/sob));
                    firstClick = false;
                }
                b.clicked((y/sob),(x/sob));
                repaint();     
                break;
            }

            case InputEvent.BUTTON3_MASK:
            {
                b.flagged((y/sob),(x/sob));
                repaint();
                break;
            }

        }

        Px=-1;
        Py=-1;
    }   

    public void mouseDragged(MouseEvent e){
        int x = e.getX();
        int y = e.getY();

        Px = (y/sob);
        Py = (x/sob);
        repaint();
    }

    public void mouseMoved(MouseEvent e){}

    public void reset(int str){
        switch(str)
        {
            case 1:
            size=10;
            numMines=(int)(20.0/100.0*size*size);
            sob = 30;
            break;
            case 2:
            size=20;
            numMines=(int)(20.0/100.0*size*size);
            sob = 30;
            break;
            case 3:
            size=30;
            numMines=(int)(20.0/100.0*size*size);
            sob = 20;
            break;
        }
        setSize(sob*size,(sob*size)+50);
        b = new board(size,numMines);
        firstClick = true;
        offscreen = createImage(sob*size,sob*size);
        bg=offscreen.getGraphics();
        repaint();
    }

    public void actionPerformed(ActionEvent e)
    {
        String str = e.getActionCommand();
        if(str.equals("Easy"))
            reset(1);
        if(str.equals("Medium"))
            reset(2);
        if(str.equals("Hard"))
            reset(3);
    }
} 