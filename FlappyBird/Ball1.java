import java.awt.*;

public class Ball1{
    int x = 0, y = 0   ;
    int radius = 10   ;
    double dx = 0   ;
    double dy = 0   ;
    double gravity = 0.45   ;
    boolean start = false   ;
    boolean dead = false   ;
    
    

    public Ball1(int i, int j){
        x = i   ;
        y = j   ;
    }

    public void update(StartingPoint1 sp){
        if(start == true){
       dy+=gravity   ;
       y+=dy   ;
    }
    
    if(y>500 || y < 0){
        dead = true   ;
    }
    
    if(dead){
        start = false   ;
        if(y<480){
        dy+=gravity   ;
        y+=dy   ;
    }
    }
    }
    
    public void start(){
        start = true   ;
    }
    
    public void jump(){
        if(!dead){
       dy = -8.5   ;
    }
    }
    
   public void respawn(){
       y = 100   ;
       dy = 0   ;
       TopWalls.score=0   ;
       start = true   ;
       dead = false   ;
    }
    

   
    public void paint(Graphics g){

    }
}