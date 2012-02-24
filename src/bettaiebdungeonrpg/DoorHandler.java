/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bettaiebdungeonrpg;

/**
 *
 * @author lucbettaieb
 */
public class DoorHandler {
    public boolean northDoorHandler(int posX, int posY){
        if(posY > 0)
            return true;
        else
            return false;
    }
    public boolean eastDoorHandler(int posX, int posY){
        if(posX < 4)
            return true;
        else
            return false;
    }
    public boolean southDoorHandler(int posX, int posY){
        if(posY < 4)
            return true;
        else
            return false;
    }
    public boolean westDoorHandler(int posX, int posY){
        if(posX > 0)
            return true;
        else
            return false;
    }  
}
