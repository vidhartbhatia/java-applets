import java.awt.*;
 
class Platform{
    int x = 250,y = 560;
    int width = 50,height= 10;
   
    public void update(StartingPoint sp, Ball b){
        if(b.y + b.radius > y && b.x > x && b.x < (x+width)){
            b.dy = 0;
            b.grounded = true;
        }
    }
    
   
    
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(x, y, width, height);
    }
}
