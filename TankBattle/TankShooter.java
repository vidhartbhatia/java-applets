 

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;
import java.awt.Graphics; 
import java.awt.Image; 
import java.io.File; 
import java.io.IOException; 
import javax.imageio.ImageIO; 
class Soldier{
    int x = 500;
    int y = 550;
    Bullet[] bullet = new Bullet[20];
    int n = 0;
    int xVel = 0;
    int bulSpeed = 8;
    float friction = 0.9f;
    boolean down;
    boolean dead;
    double gravity = 0;
    boolean inBounds = true;
    int jex = 100;
    int jey = 100;

    public Soldier(int x, int y, boolean down){
        this.x = x;
        this.y = y;
        this.down = down;
    }

    public void update(Soldier OppSol){
        if(x < 10 || x > 960){
            inBounds = false;
        }
        else{
            inBounds = true;
        }
        if(!down){
            for(int i = 0; i <= bullet.length-2; i++){
                if(bullet[i] != null){
                    bullet[i].y-=bulSpeed;

                    if(bullet[i].x >= OppSol.x+10 && bullet[i].x <= OppSol.x + 40 && bullet[i].y <= 70 && bullet[i].y >=50){
                        dead = true;
                    }

                }
            }
        }
        else{
            for(int i = 0; i <= bullet.length-2; i++){
                if(bullet[i] != null){
                    bullet[i].y+=bulSpeed;

                    if(bullet[i].x >= OppSol.x+10 && bullet[i].x <= OppSol.x + 40 && bullet[i].y >=530 && bullet[i].y <= 550){
                        dead = true;
                    }

                }
            }
        }

        if(x < 5){
            xVel = Math.abs(xVel);
            x+=xVel;
        }
        else if(x > 960){
            xVel = -xVel;
            x+=xVel;
        }
        else{
            x+=xVel;
        }
    }

    public void shoot(){
        bullet[n] = new Bullet(x+23, y-10);
        n++;
        if(n > bullet.length-2){
            n =0;
        }

    }

    public void reset(int x, int y){
        this.x = x;
        this.y = y;
        for(int i = 0; i <=bullet.length-2; i++){
            bullet[i]=null;
        }
    }


    public void moveLeft(){
        if(xVel>=-15){
            xVel--;
        }
    }

    public void moveRight(){
        if(xVel<=15){
            xVel++;
        }
    }

    public void paint(Graphics bg){
        //bg.setColor(Color.black);
        // bg.fillRect(x,y,50,20);
        if(!down)
            for(int i = 0; i <=bullet.length-2; i++){
                bg.setColor(Color.red);
                if(bullet[i] != null)
                    bg.fillRect(bullet[i].x+7, bullet[i].y+13, 5, 10);
        }
        else{
            for(int i = 0; i <=bullet.length-2; i++){
                bg.setColor(Color.black);
                if(bullet[i] != null)
                    bg.fillRect(bullet[i].x+12, bullet[i].y+58, 5, 10);
            }
        }

    }
}



class Bullet{
    int x, y;

    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
    }

}

public class TankShooter extends Applet implements Runnable, KeyListener,MouseMotionListener
{
    boolean[] keyDown;
    int mx,my;
    Soldier sol;
    Soldier sol1;
    int ct = 0;
    int ct1 = 0;
    Dimension dim;
    Graphics bg;
    Image offscreen;
    Image pic; 
    Image pic2;
    Image back ;
    Image back2 ;
    boolean upShoot = false;
    boolean downShoot = false;
    double oldtime = 0;
    double oldtime2 = 0;
    double time1989 = System.currentTimeMillis();

    public void init(){
        setSize(1000, 680);

        dim = getSize();

        addKeyListener(this);
        keyDown = new boolean[100];
        addMouseMotionListener(this);
        sol = new Soldier(500, 550, false);
        sol1 = new Soldier(500, 50, true);
        pic = getImage(getDocumentBase(), "a.png");
        pic2 = getImage(getDocumentBase(), "b.png");
        back = getImage(getDocumentBase(), "track.png");
        back2 = getImage(getDocumentBase(), "track.png");
        offscreen = createImage(dim.width, dim.height);
        bg = offscreen.getGraphics();

    }
    //Extend the start,stop,run methods to implement double buffering.
    Thread thread;
    public void start() {thread = new Thread(this); thread.start();} 

    public void run(){

        while(true){
            double time = (System.currentTimeMillis()-time1989)/1000.0;
            double time2 = (System.currentTimeMillis()-time1989)/1000.0;
            showStatus(""+time);
            if(keyDown[32]){
                if(time > oldtime){
                    sol.shoot();
                    oldtime = time + 0.4;
                }

            }

            if(keyDown[37] ){
                sol.moveLeft();

            }

            if(keyDown[39]){
                sol.moveRight();

            }
            if(keyDown[37]!=true && keyDown[39]!=true && sol.x>10)
            {
                sol.xVel*=sol.friction;
            }

            if(keyDown[83]){
                if(time2>oldtime2){
                    sol1.shoot();
                    oldtime2 = time2 + 0.4;
                }

            }
            else{

            }
            if(keyDown[65]){
                sol1.moveLeft();

            }

            if(keyDown[68]){
                sol1.moveRight();

            }

            if(keyDown[68]!=true && keyDown[65]!=true)
            {
                sol1.xVel*=sol1.friction;
            }

            // showStatus(""+sol.dead+" " +sol.x + " " + sol1.x + " " + sol.inBounds + " " + sol1.inBounds );
            sol.update(sol1);
            sol1.update(sol);
            repaint();

            try{
                thread.sleep(18);
            }
            catch(InterruptedException e){
            }
        }
    }

    public void paint(Graphics g) {
        bg.clearRect(0,0,dim.width, dim.height);
        bg.setColor(Color.green);
        bg.drawImage(back, -100, sol.y-125,this);
        bg.drawImage(back, 558, sol.y-125,this);
        bg.drawImage(back2, -100, sol1.y-125,this);
        bg.drawImage(back, 558, sol1.y-125,this);
        bg.drawImage(pic, sol.x, sol.y,70, 70, this) ;
        sol.paint(bg);
        bg.setColor(Color.blue);
        bg.drawImage(pic2, sol1.x, sol1.y,70, 70, this) ;
        sol1.paint(bg);
        if(sol.dead == true){
            bg.setColor(Color.red);
            bg.fillRect(0, 0, 1000, 720);
            bg.setColor(Color.yellow);
            Font font = new Font("Arial", Font.BOLD, 20);
            bg.setFont(font);
            bg.drawString("the bottom guy won:)", 400, 300);
            if(keyDown[65+25]){
                sol.reset(500, 550);
                sol1.reset(500, 50);
                sol.dead = false;
                sol1.dead = false;
            }
        }
        else if(sol1.dead == true){
            bg.setColor(Color.red);
            bg.fillRect(0, 0, 1000, 720);
            bg.setColor(Color.yellow);
            Font font = new Font("Arial", Font.BOLD, 20);
            bg.setFont(font);
            bg.drawString("the top guy won:)", 400, 300);
            if(keyDown[65+25]){
                sol.reset(500, 550);
                sol1.reset(500, 50);
                sol.dead = false;
                sol1.dead = false;
            }
        }
        g.drawImage(offscreen, 0, 0, this);

    }

    public void update(Graphics g){
        paint(g);
    }

    public void keyPressed( KeyEvent e ){
        keyDown[e.getKeyCode()] = true;

    }

    public void keyTyped( KeyEvent e ){

    }

    public void keyReleased( KeyEvent e ){
        keyDown[e.getKeyCode()] = false;

    }

    public void mouseMoved( MouseEvent e ){
        mx = e.getX();
        my = e.getY();
        //showStatus(mx + "  " + my);
    }

    public void mouseDragged( MouseEvent e ){

    }

    public void mouseClicked( MouseEvent e ){
    }
}
