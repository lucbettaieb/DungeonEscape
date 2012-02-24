package bettaiebdungeonrpg;

/**
 * @author lucbettaieb
 */
public class Grid {
    
    private Panel[][] grid = new Panel[5][5];
    private int posX;
    private int posY;
    private int posXMax;
    private int posYMax;
    
    public Grid(int ROWS, int COLUMNS){
        grid = new Panel[ROWS][COLUMNS];
        posX = 2; //ROWS
        posY = 2; //COLS
        
        posXMax = ROWS-1;
        posYMax = COLUMNS-1; 
    }
    
    public Panel getPanel(int posX, int posY){
        return grid[posX][posY];
    }
    
    public void gridSetup(){
        //   X  Y        PANEL TYPE      X Y
        grid[0][0] = new TreasurePanel  (0,0);
        grid[1][0] = new EnemyPanel     (1,0);
        grid[2][0] = new TreasurePanel  (2,0);
        grid[3][0] = new BlankPanel     (3,0);
        grid[4][0] = new BlankPanel     (4,0);
        
        grid[0][1] = new EnemyPanel     (0,1);
        grid[1][1] = new BlankPanel     (1,1);
        grid[2][1] = new EnemyPanel     (2,1);
        grid[3][1] = new EnemyPanel     (3,1);
        grid[4][1] = new BlankPanel     (4,1);
        
        grid[0][2] = new ExitPanel      (0,2); //QUESTION
        grid[1][2] = new BlankPanel     (1,2);
        grid[2][2] = new BlankPanel     (2,2); //START
        grid[3][2] = new EnemyPanel     (3,2);
        grid[4][2] = new EnemyPanel     (4,2);
        
        grid[0][3] = new BlankPanel     (0,3);
        grid[1][3] = new BlankPanel     (1,3);
        grid[2][3] = new TreasurePanel  (2,3);
        grid[3][3] = new BlankPanel     (3,3);
        grid[4][3] = new BlankPanel     (4,3);
        
        grid[0][4] = new TreasurePanel  (0,4);
        grid[1][4] = new EnemyPanel     (1,4);
        grid[2][4] = new BlankPanel     (2,4);
        grid[3][4] = new EnemyPanel     (3,4);
        grid[4][4] = new BossPanel      (4,4);
        
    }
    
   
    
    public void moveNorth(){
        if(posY > 0)
            posY--; //-- because north is less (top left is 0,0)
        else
            System.err.println("Can't do that.");
        
    }
    
    public void moveSouth(){
        if(posY < posYMax)
            posY++; //++ because south is more (bottom right is MAX)
        else   
            System.err.println("Can't do that.");
        
    }
    
    public void moveWest(){
        if(posX > 0)
            posX--;
        else   
            System.err.println("Can't do that.");
        
    }
    
    public void moveEast(){
        if(posX < posXMax)
            posX++;
        else
            System.err.println("Can't do that.");
        
    }
    
    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public int getPosYMax(){
        return posYMax;
    }
    
    public int getPosXMax(){
        return posXMax;
    }

    
}
