package bettaiebdungeonrpg;
import java.util.Random;
/**
 * @author lucbettaieb
 */
public class Boss extends Character {
    private Random rando = new Random();
    
    public Boss() {
        super("boss");
    }
    
    public int sendTurn(int theNumber) {
        return ((rando.nextInt(11)-1));
    }

    
    
    
    
    
    public boolean getOpened() {
        return false;
    }

    
}