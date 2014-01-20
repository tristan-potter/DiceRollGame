/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dicerollgame;

/**
 * Class used to keep track of a players points. DRPlayer extends this class, rather than creating
 * and instance of it. Would work either way, but this was shows a usage of extension. And one player
 * can not have more than one set of points so having it as an object does not make much sense anyways. 
 * @author trpot5670
 */
public class Points {
    private int amount;
    
    Points(){
        amount = 0;
    }
    Points(int a){
        amount = a;
    }
    public void addPoints(int n){
        amount += n;
    }
    public void removePoints(int n){
        amount -= n;
    }
    public int getPoints(){
        return amount;
    }
}
