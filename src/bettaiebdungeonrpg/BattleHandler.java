/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bettaiebdungeonrpg;

import java.awt.*;
import java.awt.Image;
import java.util.Random;

/**
 *
 * @author lucbettaieb
 */
public class BattleHandler {
    private Random rando = new Random();
    
    private Player player;
    private Character enemy;
    
    private int amountOfEnemiesDefeated;
    private boolean hasKey;
    private int life;
    
    private int currentPlayerScore;  //for dice
    private int currentEnemyScore;   //for dice
    private int currentTheNumber;    //for dice / display
    
    private Image[] dice;
    
    public BattleHandler(Player player, Image[] dice){
        this.player = player;
        amountOfEnemiesDefeated = 0;
        hasKey = false;
        life = 5;                           //PLAYER LIFE (amount of times he/she can lose!
        this.dice = dice;
    }
    
    public void battle(Panel panelToCheck, Character enemy, Graphics g){
        this.enemy = enemy;
        
        if(panelToCheck.getChar().getDefeated() == false){
            round(player, this.enemy, g);
        }
        
            
    }
    
    public void round(Character player, Character foe, Graphics g){
        
        int theNumber;
        
        int playerRoll;
        int foeRoll;
        int bossRoll;
        
        double playerScore;
        double foeScore;
        
        int playerWins = 0;
        int foeWins = 0;
        
         
        theNumber = rando.nextInt(6)+1;
        currentTheNumber = theNumber;
            
    //player rolling and scoring
        playerRoll = rando.nextInt(6)+1;
        playerScore = Math.abs((playerRoll - theNumber));

            
        //enemy rolls
        foeRoll = rando.nextInt(6)+1;
        bossRoll = rando.nextInt(2)+1;
            
        //enemy scoring
        if(foe.getCharType().equals("enemy"))
            foeScore = Math.abs(foeRoll - theNumber);
        else
            foeScore = Math.abs(bossRoll - theNumber);   //MAJOR BOSS BUG HERE LOL!!!
        
            
            
            
            //player score-skill modifier
        if(player.getAmountOfSkill() == 1){
            playerScore = playerScore - 1;
        }    
        if(player.getAmountOfSkill() == 2){
            playerScore = playerScore - 1.5;
        }
        if(player.getAmountOfSkill() == 3){
            playerScore = playerScore - 2;
        }


        if(playerScore < foeScore)
            playerWins++;
        else if(foeScore < playerScore)
            foeWins++;

        
        g.setColor(Color.gray);
        g.fillRect(100, 100, 100, 75);
        g.setColor(Color.black);
        
        g.drawString("Player Roll: "+playerRoll, 102, 115);
        g.drawString("Foe roll: "+ foeRoll, 102, 130);
        g.drawString("Target roll: "+theNumber, 102, 145);


        if(playerWins == foeWins)
            round(player, foe, g);   //RECURSE!  (tie handling)
        else if(playerWins > foeWins){
            g.drawString("You won!", 102, 160);
            endRound(foe);
        }
        else{
            g.drawString("You lost...", 102, 160);
            endRound(player);
        }
        
    }
    
    public void endRound(Character loser) {  //TODO: implement this stuff!!
        loser.setDefeated(true);
        
        if(loser.getCharType().equals("player")) {
            // LOSER LOL YOU LOSE
            life--;
        }
         
        else if(loser.getCharType().equals("boss")){
            // GET KEY
            hasKey = true;
        }
        
        else {// Enemy has been defeated!
            amountOfEnemiesDefeated++;
        }
    }
    
    public int getAmountOfEnemiesDefeated(){
        return amountOfEnemiesDefeated;
    }
    public boolean getHasKey(){
        return hasKey;
    }
    
    public int getLife(){
        return life;
    }
    
    public int getCurrentPlayerScore(){
        return currentPlayerScore;
    }
    public int getCurrentEnemyScore(){
        return currentEnemyScore;
    }
    public int getCurrentTheNumber(){
        return currentTheNumber;
    }
}
