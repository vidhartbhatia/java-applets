import java.awt.* ;

class TopWalls{
    int x,y; 
    int width = 50,height= 0; 
    public static int score = 0; 
    boolean flag = true ;
    Color col ;

    public TopWalls(int x, int y){
        this.x = x ;
        this.y = y ;
    }

    public void update(StartingPoint1 sp, Ball1 b){
        if(b.start){
            x-=2 ;
        }

        if(b.x>x+width){
            if(flag){
                score++ ;
                flag = false ;
            }
        }
        
        
        if(b.y+5< y+height && b.x+30> x && b.x < x+width){
            b.dead = true ;
        }
        
        if(x+width<0){
            x = 400 ;
            height = (int)(Math.random()*200) ;
            flag = true ;
        }
    }

    public void makeWalls(){
        col = new Color((int)(Math.random()*200), (int)(Math.random()*200), (int)(Math.random()*200)) ;
        height = (int)(Math.random()*200) ;
    }
    
    public void respawn(int i){
        col = new Color((int)(Math.random()*200), (int)(Math.random()*200), (int)(Math.random()*200)) ;
        height = (int)(Math.random()*200) ;
        x = i ;
    }

    public void paint(Graphics g){
       
        g.setColor(col ) ;
        g.fillRect(x,y,width,height) ;
   }
}
