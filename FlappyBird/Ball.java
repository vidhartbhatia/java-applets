import java.awt.*;

public class Ball{
    int x = 0, y = 0;
    int radius = 5;
    double dx = 0;
    double dy = 0;
    double gravity = 15, energyloss = 1, dt = 0.2, xfriction = 0.9;
    boolean grounded = false;
    boolean stopping = false;
    
    public Ball(int i, int j){
        x = i;
        y = j;
    }
    
    public void update(StartingPoint sp){
        if(x + dx> sp.getWidth() - radius - 1){
            x = sp.getWidth() - radius - 1;
            dx = -dx;
        }
        else if(x < 0 + radius){
            x = 0+radius;
            dx = -dx;
        }
        else{
            x+=dx;
        }
        
        if(y == sp.getHeight()- radius - 1){
            
            grounded = true;
            
        }
        
        if(grounded){
            dx*=xfriction;
            if(Math.abs(dx) < 0.1){
                stopping = true;
                dx = 0;
            }
            else{
                stopping = false;
            }
        }
        
        if(y >= sp.getHeight() - radius-1 ){
            y = sp.getHeight() - radius-1 ;
            dy*=energyloss;
           
        }
        else{
           dy+= dt*gravity;
            y+= dy*dt + 0.5*dt*dt*gravity;
            grounded = false;
        }
    }
    
    public void moveRight(){
        if(dx < 10){
            dx+= 3;
        }
    }
    
    public void moveLeft(){
        if(dx > -10){
            dx-= 3;
        }
    }
    
    public void jump(){
        if(true){
        dy = -5;
        y += dy;
    }
    }
    
    public void paint(Graphics g){
        
        g.setColor( Color.red );
    
        g.fillOval(x-radius, y-radius, radius*2, radius*2);

    }
}