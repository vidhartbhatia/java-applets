public class board
{
    int size;
    int mines;

    boolean dead = false;
    int [][] board;
    int [][] reveal;
    public board(int size , int mines)
    {
        this.size = size;
        this.mines = mines;

        board = new int[size][size];
        reveal = new int[size][size];
    }

    public void initialize(int x1 , int y1)
    {
        for(int i = 0 ; i<mines;)
        {
            int x = (int)(Math.random()*size);
            int y = (int)(Math.random()*size);

            if((y==y1&&x==x1)||(y==y1+1&&x==x1)||(y==y1&&x==x1+1)||(y==y1-1&&x==x1)||(y==y1&&x==x1-1)||(y==y1+1&&x==x1+1)||(y==y1-1&&x==x1-1)||(y==y1+1&&x==x1-1)||(y==y1-1&&x==x1+1))
            {
                continue;
            }
            else if(board[y][x]==0)
            {
                board[y][x] =-1;
                i++;
            }

        }

        for(int i =0 ; i <size;i++)
        {
            for(int j =0;j<size;j++)
            {
                if(i==y1&&j==x1)
                {
                    board[i][j]=0;
                }
                else if(board[i][j]!=-1)
                {
                    int count = 0;
                    try
                    {
                        if(board[i+1][j]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    try
                    {
                        if(board[i][j+1]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    try
                    {
                        if(board[i-1][j]==-1)
                            count++;
                    }
                    catch(Exception e){}
                    try
                    {
                        if(board[i][j-1]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    try
                    {
                        if(board[i+1][j+1]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    try
                    {
                        if(board[i+1][j-1]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    try
                    {
                        if(board[i-1][j-1]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    try
                    {
                        if(board[i-1][j+1]==-1)
                            count++;
                    }
                    catch(Exception e){}

                    board[i][j] = count;
                }
            }
        }
    }

    public static void main()
    {
        /*
        for(int i =0 ; i <a.size;i++)
        {
        for(int j =0;j<a.size;j++)
        {
        System.out.print(a.board[i][j] + " ");
        }
        System.out.println();
        }
         */
    }

    public void flagged(int x , int y)
    {
        if(reveal[x][y]==-1)
            reveal[x][y]=2;
        else if(reveal[x][y]==0)
        {
            reveal[x][y]=-1;
        }
        else if(reveal[x][y]==2)
        {
            reveal[x][y]=0;
        }
    }

    public void clicked(int x , int y)
    {
        try{
            if(reveal[x][y]==0)
            {
                reveal[x][y]=1;
                if(board[x][y]==-1)
                {
                    dead = true;
                }

                if(board[x][y]==0)
                {
                    clicked(x+1,y);
                    clicked(x-1,y);
                    clicked(x,y+1);
                    clicked(x,y-1);
                    clicked(x+1,y+1);
                    clicked(x-1,y-1);
                    clicked(x+1,y-1);
                    clicked(x-1,y+1);
                }
            }

        }
        catch(Exception e){}
    }

    //public void rightClicked(int x , int y)
    //{
    //    reveal[x][y]=-1;
    //}

    public int minesLeft(boolean t)
    {
        try{
            int count=0;
            int truecount=0;
            for(int i  =0;i<size;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(board[i][j]==-1)
                    {
                        count++;
                        truecount++;
                    }
                }
            }
            for(int i  =0;i<size;i++)
            {
                for(int j=0;j<size;j++)
                {
                    if(reveal[i][j]==-1)
                    {
                        count--;
                        if(board[i][j]==-1)
                            truecount--;
                    }
                }
            }
            if(!t)
                return count;
            else
                return truecount;
        }
        catch(Exception e)
        {
            return -1;
        }
    }

    public void reset(){
        for(int i =0 ; i <size;i++)
        {
            for(int j =0;j<size;j++)
            {
                reveal[i][j] = 0;
            }
        }
    }
}