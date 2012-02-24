package bettaiebdungeonrpg;
import java.util.Random;
/**
 * @author lucbettaieb
 */
public class Enemy extends Character {
    private Random rando = new Random();
    private int posX;
    private int posY;
    
    public Enemy(int posX, int posY){
        super("enemy");
        this.posX = posX;
        this.posY = posY;
        
    }

    public int sendTurn(int theNumber) {
        return ((rando.nextInt(11)-1));
    }
    
    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    public boolean getOpened() {
        return false;
    }
 
    
}