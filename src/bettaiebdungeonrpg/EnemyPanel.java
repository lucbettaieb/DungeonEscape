/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bettaiebdungeonrpg;

/**
 *
 * @author lucbettaieb
 */
public class EnemyPanel extends Panel {
    private boolean isVisible;
    private String panelType;
    private int posX;
    private int posY;
    
    private Enemy robot = new Enemy(posX, posY);
    
    public EnemyPanel(int posX, int posY){
        super("enemy", posX, posY);
        this.posX = posX;
        this.posY = posY;
        panelType = "enemy";
        
    }
    public void show(){
        isVisible = true;
    }
    public void hide(){
        isVisible = false;
    }
    
    public String getPanelType() {
        return panelType;
    }
    
    public Enemy getChar(){
        return robot;
    }
    
}
