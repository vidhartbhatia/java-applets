import java.awt.*;
import java.awt.event.* ;
import java.applet.Applet ;
import java.awt.Graphics ;
import java.awt.Image ;
import java.io.File ;
import java.io.IOException ;
import javax.imageio.ImageIO ;

public class StartingPoint1 extends Applet 
implements Runnable, KeyListener{
    Ball1 b ;
    TopWalls tw ;
    TopWalls tw2 ;
    BottomWalls bw ;
    BottomWalls bw2 ;
    public int start = 0 ;
    Image pic ;
    Image back ;

    public void init(){
        setSize(400, 500) ;
        addKeyListener(this) ;
                     
        pic = getImage(getDocumentBase(), "Flappy.png") ;
        back = getImage(getDocumentBase(), "Back.png") ;
       
    }

    public void start(){
        b = new Ball1(100, 150) ;
        tw = new TopWalls(425, 0) ;
        tw2 = new TopWalls(650,0) ;
        bw = new BottomWalls(425, 800) ;
        bw2 = new BottomWalls(650, 800) ;
        Thread thread = new Thread(this) ;
        thread.start() ;
    }

    public void run(){
        while(true){
            b.update(this) ;
            tw.update(this, b) ;
            tw2.update(this, b) ;
            bw.update(this,b,tw) ;
            bw2.update(this,b,tw2) ;
            repaint() ;
            try{
                Thread.sleep(21) ;
            }
            catch(InterruptedException e){
                e.printStackTrace() ;
            }

        }
    }
    public void stop(){
    }

    public void paint( Graphics g ){
        g.drawImage(back, 0, 0 , this) ;
        tw.paint(g) ;
        tw2.paint(g) ;
        bw.paint(g) ;
        bw2.paint(g) ;
       
        g.drawImage(pic, b.x, b.y, this) ;
        g.setColor(Color.white) ;
        Font f = new Font("impact", Font.BOLD,40) ;
        g.setFont(f) ;
        g.drawString(""+TopWalls.score, 200, 50) ;
    }

    public void keyPressed( KeyEvent e ){
        if(start < 1){
            if(e.getKeyCode() == 32){
                b.start() ;
                tw.makeWalls() ;
                tw2.makeWalls() ;
                bw.makeWalls(tw) ;
                bw2.makeWalls(tw2) ;
                start++ ;
            }
        }

        if(e.getKeyCode() == 32){
            b.jump() ;
        }
        if(e.getKeyCode() == 32){
            if(b.dead){
                b.respawn() ;
                tw.respawn(425) ;
                tw2.respawn(650) ;
                bw.respawn(425, tw) ;
                bw2.respawn(650, tw2) ;
            }

        }
    }

    public void keyTyped( KeyEvent e ){

    }

    public void keyReleased( KeyEvent e ){
    }
}