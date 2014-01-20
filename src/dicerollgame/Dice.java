/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dicerollgame;

/**
 *
 * @author trpot5670
 */
public class Dice {
    private int value;
    private final int MIN = 1;
    private final int MAX = 6;
    Dice(){
        value = 0;
    }
    
    public void rollDice(){
        value = MIN + (int)(Math.random() * ((MAX-MIN) + 1));
    }
    
    public void rollDice(int n){
        value = n;
    }
       
    public int getValue(){
        return(value);
    }
    
    static int addDice(Dice dice1, Dice dice2){
        return(dice1.getValue() + dice2.getValue());
    }
    
    static int addDice(Dice[] dice){
        int addition = 0;
        for(Dice die:dice){
            addition += die.getValue();
        }
        return addition;
    }
    
}
