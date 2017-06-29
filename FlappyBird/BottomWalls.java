import java.awt.*;

class BottomWalls{
    int x,y;
    int width = 50,height= 400;
    Color col2;
   
    public BottomWalls(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void update(StartingPoint1 sp, Ball1 b, TopWalls tw){
        if(b.start){
        x-=2;
    }
    
    if(x+width<0){
        x = 400;
        y = tw.height+150;
    }
    
    if(b.y+30 > y && b.x+30> x && b.x< x+width){
            b.dead = true;
        }
}
    
    public void makeWalls(TopWalls tw){
        y = tw.height+150;
        col2 = new Color((int)(Math.random()*200), (int)(Math.random()*200), (int)(Math.random()*200));
    }
    
     public void respawn(int i, TopWalls tw){
         col2 = new Color((int)(Math.random()*200), (int)(Math.random()*200), (int)(Math.random()*200));
       y = tw.height+150;
        x = i;
    }

    
    public void paint(Graphics g){
        
        g.setColor(col2);
        g.fillRect(x,y,width,height);
    }
}
