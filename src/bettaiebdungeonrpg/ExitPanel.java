/*
 * Luc Bettaieb
 */
package bettaiebdungeonrpg;

/**
 *
 * @author lucbettaieb
 */
public class ExitPanel extends Panel {
    private boolean isVisible;
    private String panelType;
    
    public ExitPanel(int posX, int posY){
        super("exit", posX, posY);
        panelType = "exit";
        
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

    //this code does nothing.
    public Character getChar() {
        return new Enemy(1, 1); //TODO: 
    }
    
}
