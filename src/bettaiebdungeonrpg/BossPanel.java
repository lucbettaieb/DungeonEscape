/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bettaiebdungeonrpg;

import java.awt.Graphics;

/**
 *
 * @author lucbettaieb
 */
public class BossPanel extends Panel {
    private boolean isVisible;
    private String panelType;
    
    private Boss king = new Boss();
    
    public BossPanel(int posX, int posY){
        super("boss", posX, posY);
        panelType = "boss";
        
    }
    public void show() {
        isVisible = true;
    }

    public void hide() {
        isVisible = false;
    }

    public String getPanelType() {
        return panelType;
    }
    
    public Boss getChar(){
        return king;
    }
    
    
    
    
    
}
