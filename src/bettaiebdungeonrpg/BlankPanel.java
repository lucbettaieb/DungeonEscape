/**
 * @author lucbettaieb
 */

package bettaiebdungeonrpg;

public class BlankPanel extends Panel {
    private boolean isVisible;
    private String panelType;
    
    public BlankPanel(int posX, int posY){
        super("blank", posX, posY);
        panelType = "blank";
        
    }
    
    public void show(){
        isVisible = true;
    }
    public void hide(){
        isVisible = false;
    }
    public String getPanelType(){
        return panelType;
    }

    //this code does nothing.
    public Character getChar() {
        return new Enemy(1, 1);   //TODO: LOL
    }

  
    
    
    
    
    
    
}
