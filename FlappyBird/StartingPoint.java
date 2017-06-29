import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class StartingPoint extends Applet 
    implements Runnable, KeyListener{
        Ball b;
        Platform p;
        boolean[] keys;
        
        public void init(){
            setSize(500, 500);
            addKeyListener(this);
            setBackground( Color.black );
            keys = new boolean[200];
        }
        
        public void start(){
            b = new Ball(250, 450);
            p = new Platform();
            Thread thread = new Thread(this);
            thread.start();
        }
        
        public void run(){
            while(true){
               
                if(keys[39]){
                    b.moveRight();
                }
                if(keys[37]){
                    b.moveLeft();
                }
                if(keys[38]){
                    b.jump();
                }
                 b.update(this);
                p.update(this, b);
            repaint();
            try{
                Thread.sleep(21);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            
            showStatus(" "+b.grounded+"  "+b.stopping);
        }
        }
        
        public void stop(){
        }
        
        public void paint( Graphics g ){
            p.paint(g);
            b.paint(g);
        }
        
        public void keyPressed( KeyEvent e ){
            if(e.getKeyCode() == 39){
               keys[39] = true;
            }
                
             if(e.getKeyCode() == 37){
                  keys[37] = true;
                }
                if(e.getKeyCode() == 38){
                 keys[38] = true;
                }
            
        }
        
        public void keyTyped( KeyEvent e ){
            
        }
        
        public void keyReleased( KeyEvent e ){
            if(e.getKeyCode() == 39){
               keys[39] = false;
            }
                
             if(e.getKeyCode() == 37){
                  keys[37] = false;
                }
                if(e.getKeyCode() == 38){
                 keys[38] = false;
                }
        }
}