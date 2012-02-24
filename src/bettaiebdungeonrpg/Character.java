package bettaiebdungeonrpg;

import java.util.Random;

/**
 * @author lucbettaieb
 */
public abstract class Character {
//    private Random rando = new Random();
    
    private String charType;
    
    private int amountOfSkill;
    
    private int life;
    private int originalLife;
    
    private boolean isDefeated;
    
    public Character(String charType){
        isDefeated = false;
        
        life = 3;
        originalLife = 3;
        
        this.charType = charType;
    }
   
    
    
    //------------------------------------------------------
    
    
    //SETTAZ AN' GETTAZ
    public int getLife(){
        return life;
    }
    public int getOriginalLife(){
        return originalLife;
    }
    
    public void lifemm(){
        life--;
    }
    
    public void setLife(int life){
        this.life = life;
    }
    
    public void addSkill(){
        amountOfSkill++;
    }
    
    public int getAmountOfSkill(){
        return amountOfSkill;
    }
    
    public String getCharType(){
        return charType;
    }
    
    public void setDefeated(boolean defeat){
        isDefeated = defeat;
    }
    
    public boolean getDefeated(){
        return isDefeated;
    }
}