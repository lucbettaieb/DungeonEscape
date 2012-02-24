/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bettaiebdungeonrpg;

/**
 *
 * @author lucbettaieb
 */
public abstract class Panel {
    private String panelType;
    private int posX;
    private int posY;
    
    private boolean chestOpen;
    
    
    public Panel(String panelType, int posX, int posY){
        this.panelType = panelType;
        this.posX = posX;
        this.posY = posY;
        
        chestOpen = false;
    }
    
    public abstract Character getChar();
    
    public abstract void show();
    public abstract void hide();
    public abstract String getPanelType();
    
    
    
    public void openChest(){
        chestOpen = true;
    }
    
    public boolean getChestOpen(){
        return chestOpen;
    }
    
   
    
}
