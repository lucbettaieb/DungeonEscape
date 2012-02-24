/**
 * @author Luc Bettaieb
 */
package bettaiebdungeonrpg;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

public class RPGDriver extends Applet implements MouseListener {
  
    // FIELDS
    private Image blankPanel, boss, enemy, player, chest, minimap, exitDoor, 
            endScreen, logo, playButton, instructionsButton, instructionsScreen,
            gameOverScreen, prologue, keyFaded, key;
    private Image[] arrows              = new Image[4];
    private Image[] doors               = new Image[4];
    private Image[] dice                = new Image[6];
    
    private URL base;
    
    
    private Grid grid;
    private DoorHandler dh;
    private BattleHandler bh;
    
    private Player hero;
    private Enemy[] enemies = new Enemy[8];
    private Boss king;
    
    
    
    private boolean titleCard, endCard, loseCard, instructionsCard;
    
    
    
    public void init() {
        
        // Applet Specific Stuff
        this.setSize(550, 350);
        
        try {
           base = getDocumentBase(); }
        catch (Exception e) {}
 
        // Initializing images
        minimap = getImage(base,"images/minimap.png");
        
        blankPanel = getImage(base,"images/backgroundWithBar2.png");
        player = getImage(base,"images/mainChar.png");
        enemy = getImage(base,"images/meanRobot.png");
        boss = getImage(base,"images/badChar.png");
        chest = getImage(base,"images/chest.png");
        exitDoor = getImage(base,"images/exitDoor.png");
        
        keyFaded = getImage(base,"images/keyFaded.png");
        key = getImage(base,"images/key.png");
        
        arrows[0] = getImage(base,"images/arrowUp.png");
        arrows[1] = getImage(base,"images/arrowRight.png");
        arrows[2] = getImage(base,"images/arrowDown.png");
        arrows[3] = getImage(base,"images/arrowLeft.png");
        
        doors[0] = getImage(base,"images/doorTop.png");
        doors[1] = getImage(base,"images/doorLeft.png");
        doors[2] = getImage(base,"images/doorRight.png");
        doors[3] = getImage(base,"images/doorBottom.png");
        
        dice[0] = getImage(base,"images/dice/1.png");
        dice[1] = getImage(base,"images/dice/2.png");
        dice[2] = getImage(base,"images/dice/3.png");
        dice[3] = getImage(base,"images/dice/4.png");
        dice[4] = getImage(base,"images/dice/5.png");
        dice[5] = getImage(base,"images/dice/6.png");
        
        playButton = getImage(base,"images/play.png");
        instructionsButton = getImage(base,"images/instructions.png");
        
        logo = getImage(base,"images/title.png");
        prologue = getImage(base,"images/prologue.png");
        endScreen = getImage(base,"images/endScreen.png");
        instructionsScreen = getImage(base,"images/instructionsSlide.png");
        gameOverScreen = getImage(base,"images/gameOverScreen.png");
        
        
        //setting up the characters
        enemies[0] = new Enemy(1, 0);
        enemies[1] = new Enemy(0, 1);
        enemies[2] = new Enemy(2, 1);
        enemies[3] = new Enemy(3, 1);
        enemies[4] = new Enemy(3, 2);
        enemies[5] = new Enemy(4, 2);
        enemies[6] = new Enemy(1, 4);
        enemies[7] = new Enemy(3, 4);
   
        hero = new Player();
        king = new Boss();
        
        
        
        //display cards
        titleCard = true;
        instructionsCard = false;
        endCard = false;
        loseCard = false;
        
        
        // Initializing the grid
        grid = new Grid(5, 5);
        grid.gridSetup();       //GRID IS SETUP!
        addMouseListener(this);
        
        // Initializing the handlers
        dh = new DoorHandler();
        bh = new BattleHandler(hero, dice);
        
        
        //bunch of repaints to try to fix weird things happening
        for(int i = 0; i < 5; i++)
            repaint();
     }

     public void paint(Graphics g) {
         
         if(titleCard)
             paintTitleCard(g);
         else if(instructionsCard)
             paintInstructionsCard(g);
         else if(endCard)
             paintEndCard(g);
         else if(loseCard)
             paintLoseCard(g);
         
         else
             paintGame(g);
         
         
         
          
     }
     
     public void paintTitleCard(Graphics g){
         g.setColor(Color.GRAY);
         g.fillRect(0, 0, 550, 350);
         g.setColor(Color.WHITE);
         g.drawString("Luc Bettaieb 2012", 25, 340);
         g.drawImage(logo, 35, 10, this);
         g.drawImage(prologue, 250, 100, this);         
         g.drawImage(playButton, 25, 130, this);
         g.drawImage(instructionsButton, 25, 180, this);
         
         
     }
     
     public void paintGame(Graphics g){
         
         paintPanel(g);
         drawDoors(g);
         drawGUI(g);
         mapDrawer(g);
         drawText(g);
         
         battleHandle(g);

         //info printer
         print(0, g, grid.getPosX() + " | "+ grid.getPosY() + "     Skill Level: " +hero.getAmountOfSkill() +
                 "     Life: "+bh.getLife()+"     Enemies Defeated: "+bh.getAmountOfEnemiesDefeated() );
         //System.out.println(grid.getPanel(grid.getPosX(),grid.getPosY()).getPanelType());
     }
     
     public void paintEndCard(Graphics g){
         g.drawImage(endScreen, 0, 0, this);
         g.setColor(Color.WHITE);
         g.drawString("You emerge from the dungeon into the bus station...", 100, 255);
         g.drawString("With your blurry vision you spot two young men", 100, 277);
         g.drawString("speaking with an elderly homeless man.", 100, 291);
         g.drawString("Hearing something about missiles and dirigibles, you quickly", 100, 307);
         g.drawString("leave the room and start heading for your bus.", 100, 321);
         g.drawString("Congratulations, you won!", 200, 337);
     }
     
     public void paintLoseCard(Graphics g){
         
         g.drawImage(gameOverScreen,0,0,this);
         g.setColor(Color.white);
         g.drawString("Refresh to try again!", 50, 340);
     }
     
     public void paintInstructionsCard(Graphics g){
         g.setColor(Color.gray);
         g.fillRect(0, 0, 550, 350);
         g.drawImage(instructionsScreen, 0, 0, this);
     }
     
     public void battleHandle(Graphics g){
         
         //robot battle
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("enemy")){
             
            if((enemies[curEnemy(grid.getPosX(), grid.getPosY())].getDefeated() == false))
                bh.battle(grid.getPanel(grid.getPosX(), grid.getPosY()), enemies[curEnemy(grid.getPosX(), grid.getPosY())], g);
            else{
                g.setColor(Color.gray);
                g.fillRect(100, 100, 100, 75);
                g.setColor(Color.black);
            
                g.drawString("You beat me!", 102, 115);
            }
                //System.out.println(enemies[curEnemy(grid.getPosX(), grid.getPosY())].getDefeated());
             
         }
         
         
         //boss battle
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("boss")){
             
            if((king.getDefeated() == false) && bh.getAmountOfEnemiesDefeated() >= 4){
             
                bh.battle(grid.getPanel(grid.getPosX(), grid.getPosY()), king, g); 
            }
            else if(king.getDefeated() == true){
                g.setColor(Color.gray);
                g.fillRect(100, 100, 100, 75);
                g.setColor(Color.black);
            
                g.drawString("You've won.", 102, 115);
                g.drawString("Now, go.", 102, 130);
            }
            
         
         }
         //Get skill here.
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("treasure") && 
                 grid.getPanel(grid.getPosX(), grid.getPosY()).getChestOpen() == false){
             
             grid.getPanel(grid.getPosX(), grid.getPosY()).openChest();
             hero.addSkill();
             print(1, g, "You got some skill!"); //Fix the weird disappearance of this when its first shown TODO: 
         }
         
         //win the game
         if(bh.getHasKey() == true && grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("exit")){
             endCard = true;
             repaint();
         }
         
         if(bh.getLife() < 1){
             endCard = false;
             loseCard = true;
             repaint();
         }
             
         
         
             
     }
     
     public void drawDice(int theNum, int playerRoll, int foeRoll, Graphics g){    //MIGHT BE UNUSED
         print(1, g, "Target number was: "+theNum);
         g.drawImage(dice[playerRoll - 1], 50, 50, this);
         g.drawImage(dice[foeRoll - 1], 75, 50, this);
     }
     
     public int curEnemy(int x, int y){
         for(int i = 0; i < enemies.length; i++){
             if(enemies[i].getPosX() == x && enemies[i].getPosY() == y)
                 return i; 
         }
         System.err.println("SOMETHING WENT WRONG IN CUR ENEMY");
         return -1;
         
     }
     
     public void drawText(Graphics g){                                              //HINT TEXT INFO HANDLER
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("boss")){
             if(bh.getAmountOfEnemiesDefeated() < 4)
                 print(1, g, "(Hint) Boss: Come back when you've defeated 4 enemies!");
             else if(king.getDefeated() == true)
                 print(1, g, "(Hint) Boss: You've defeated me! Leave this place.");
             else 
                 print(1, g, "(Hint) Boss: You've proven your worth!  Let us battle!");
         }
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("exit")){
             print(1, g, "(Hint) I'll need to get the key from the boss to get through!");
         }
         
     }
     
     public void drawGUI(Graphics g){
         g.drawImage(arrows[0],495, 5, this);
         g.drawImage(arrows[1],515, 25, this);
         g.drawImage(arrows[2],495, 45, this);
         g.drawImage(arrows[3],475, 25, this);
         g.drawImage(minimap, 450, 250, this);
         
         g.drawImage(keyFaded, 400, 300, this);
         if(bh.getHasKey() == true){
             g.drawImage(key, 400, 300, this);
         }
     }
     
     public void drawDoors(Graphics g){
         if(dh.northDoorHandler(grid.getPosX(), grid.getPosY())){
             g.drawImage(doors[0], 250, 0, this);  
         }
         if(dh.westDoorHandler(grid.getPosX(), grid.getPosY())){
             g.drawImage(doors[1], 0, 110, this);
         }
         if(dh.eastDoorHandler(grid.getPosX(), grid.getPosY())){
             g.drawImage(doors[2], 490, 110, this);
         }
         if(dh.southDoorHandler(grid.getPosX(), grid.getPosY())){
             g.drawImage(doors[3], 250, 223, this);
         }
     }
    
     public void paintPanel(Graphics g){
       
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("blank")){
             g.drawImage(blankPanel,0,0,this);
             g.drawImage(player, 400, 100, this);
             
         }
         
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("exit")) {
             g.drawImage(blankPanel,0,0,this);
             g.drawImage(player, 400, 100, this);
             g.drawImage(exitDoor, 0, 110, this);
             //TODO: THIS ISH
         }
         
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("enemy")) {
             g.drawImage(blankPanel,0,0,this);
             g.drawImage(player, 400, 100, this);
             g.drawImage(enemy, 200, 100, this);
         }
         
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("treasure")) {
             g.drawImage(blankPanel,0,0,this);
             g.drawImage(player, 400, 100, this);
             g.drawImage(chest, 200, 100, this);
         }
         
         if(grid.getPanel(grid.getPosX(), grid.getPosY()).getPanelType().equals("boss")) {
             g.drawImage(blankPanel,0,0,this);
             g.drawImage(player, 400, 100, this);
             g.drawImage(boss, 200, 100, this);
             
             
         }
        
                
    }
    
     public void mapDrawer(Graphics g){
        if(grid.getPosX() == 0 && grid.getPosY() == 0)
            g.fillRect(455, 256, 10, 10);
        if(grid.getPosX() == 1 && grid.getPosY() == 0)
           g.fillRect(475, 256, 10, 10);
        if(grid.getPosX() == 2 && grid.getPosY() == 0)
            g.fillRect(495, 256, 10, 10);
        if(grid.getPosX() == 3 && grid.getPosY() == 0)
            g.fillRect(515, 256, 10, 10);
        if(grid.getPosX() == 4 && grid.getPosY() == 0)
            g.fillRect(535, 256, 10, 10);
        if(grid.getPosX() == 0 && grid.getPosY() == 1)
            g.fillRect(455, 276, 10, 10);
        if(grid.getPosX() == 1 && grid.getPosY() == 1)
            g.fillRect(475, 276, 10, 10);
        if(grid.getPosX() == 2 && grid.getPosY() == 1)
            g.fillRect(495, 276, 10, 10);
        if(grid.getPosX() == 3 && grid.getPosY() == 1)
            g.fillRect(515, 276, 10, 10);
        if(grid.getPosX() == 4 && grid.getPosY() == 1)
            g.fillRect(535, 276, 10, 10);
        if(grid.getPosX() == 0 && grid.getPosY() == 2)
            g.fillRect(455, 296, 10, 10);
        if(grid.getPosX() == 1 && grid.getPosY() == 2)
            g.fillRect(475, 296, 10, 10);
        if(grid.getPosX() == 2 && grid.getPosY() == 2)
            g.fillRect(495, 296, 10, 10);
        if(grid.getPosX() == 3 && grid.getPosY() == 2)
            g.fillRect(515, 296, 10, 10);
        if(grid.getPosX() == 4 && grid.getPosY() == 2)
            g.fillRect(535, 296, 10, 10);
        if(grid.getPosX() == 0 && grid.getPosY() == 3)
            g.fillRect(455, 316, 10, 10);
        if(grid.getPosX() == 1 && grid.getPosY() == 3)
            g.fillRect(475, 316, 10, 10);
        if(grid.getPosX() == 2 && grid.getPosY() == 3)
            g.fillRect(495, 316, 10, 10);
        if(grid.getPosX() == 3 && grid.getPosY() == 3)
            g.fillRect(515, 316, 10, 10);
        if(grid.getPosX() == 4 && grid.getPosY() == 3)
            g.fillRect(535, 316, 10, 10);
        if(grid.getPosX() == 0 && grid.getPosY() == 4)
            g.fillRect(455, 336, 10, 10);
        if(grid.getPosX() == 1 && grid.getPosY() == 4)
            g.fillRect(475, 336, 10, 10);
        if(grid.getPosX() == 2 && grid.getPosY() == 4)
            g.fillRect(495, 336, 10, 10);
        if(grid.getPosX() == 3 && grid.getPosY() == 4)
            g.fillRect(515, 336, 10, 10);
        if(grid.getPosX() == 4 && grid.getPosY() == 4)
            g.fillRect(535, 336, 10, 10);
    }
     
     public void print(int i, Graphics g, String str){
         g.setColor(Color.black);
         if(i == 0){
            g.drawString(str, 10, 320);
         }
         else {
            g.drawString(str, 10, 340);
         }
     }
     
     
     public void mousePressed(MouseEvent me) {
         if(!titleCard && !endCard && !instructionsCard){
            if((me.getX() > 495 && me.getX() < 525) &&(me.getY() >5 && me.getY() < 35))  //up button
                grid.moveNorth();

            if((me.getX() > 515 && me.getX() < 545) &&(me.getY() >25 && me.getY() < 55))  //right button
                grid.moveEast();

            if((me.getX() > 495 && me.getX() < 525) &&(me.getY() >45 && me.getY() < 75))  //down button
                grid.moveSouth();

            if((me.getX() > 475 && me.getX() < 505) &&(me.getY() >25 && me.getY() < 55))  //left button
                grid.moveWest();
            
            repaint();
         }
         
         else if(titleCard){
             //main menu mouse stuff
             if((me.getX() > 25 && me.getX() < 85) && (me.getY() > 130 && me.getY() < 176)){
                 titleCard = false;
             }
             if((me.getX() > 25 && me.getX() < 221) && (me.getY() > 180 && me.getY() < 226)){
                 instructionsCard = true;
                 titleCard = false;
             }
             repaint();
         }
         else if(instructionsCard){
             if((me.getX() > 451 && me.getX() < 530) && (me.getY() >18  && me.getY() < 43)){
                 titleCard = true;
                 instructionsCard = false;
             }
             repaint();
             
         }
         else if(endCard){
             //repaint();
         }
         
    }

     public void mouseReleased(MouseEvent me) {}
    
     public void mouseClicked(MouseEvent me) {}
 
     public void mouseEntered(MouseEvent me) {}

     public void mouseExited(MouseEvent me) {}
}