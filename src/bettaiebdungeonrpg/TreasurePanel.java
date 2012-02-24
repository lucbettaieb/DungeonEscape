/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bettaiebdungeonrpg;

/**
 *
 * @author lucbettaieb
 */
public class TreasurePanel extends Panel {
    private boolean isVisible;
    private String panelType;
    
    public TreasurePanel(int posX, int posY){
        super("treasure", posX, posY);
        panelType = "treasure";
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

    @Override
    public Character getChar() {
        return new Enemy(1, 1); //TODO: LOL
    }
    
    
}
