/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dicerollgame;

/**
 *
 * @author trpot5670
 */
public class DRPlayer extends Points{
    private Dice[] dice; // using arrays for expandability
    private int totalValue;
    
    /**
     * Creates an instance of class DRPlayer with default values. 
     * pre: none
     * post: a DRPlayer object is created with 2 dice and no points.
    */
    DRPlayer(){
        super();
        dice = new Dice[2];
        dice[0] = new Dice();
        dice[1] = new Dice();
        BOUNDS.LOW.LOWER = 2;
        BOUNDS.LOW.UPPER = 6;
        BOUNDS.HIGH.LOWER = 8;
        BOUNDS.HIGH.UPPER = 12;
    }
    /**
     * Creates an instance of class DRPlayer with default dice, but specified points. 
     * pre: none
     * post: a DRPlayer object is created with 2 dice and p points.
     * @param p - starting points
    */
    DRPlayer(int p){ //handicap/cheating
        super(p);
        dice = new Dice[2];
        dice[0] = new Dice();
        dice[1] = new Dice();
        BOUNDS.LOW.LOWER = 2;
        BOUNDS.LOW.UPPER = 6;
        BOUNDS.HIGH.LOWER = 8;
        BOUNDS.HIGH.UPPER = 12;
    }
    /**
     * Creates an instance of class DRPlayer with specified dice, and specified points. 
     * pre: none
     * post: a DRPlayer object is created with d dice and p points.
     * @param p - starting points
     * @param d - number of dice
    */
    DRPlayer(int p, int d){ // expandability
        super(p);
        dice = new Dice[d];
        for(int i = 0; i < d; i++){
            dice[i] = new Dice();
        }
        BOUNDS.LOW.LOWER = d;
        BOUNDS.LOW.UPPER = (int)((((d * 6) - (d)) / 2) + d - 1);
        BOUNDS.HIGH.LOWER = (int)((((d * 6) - (d)) / 2) + d + 1);
        BOUNDS.HIGH.UPPER = d * 6;
        
        log("" + BOUNDS.LOW.LOWER + ":" + BOUNDS.LOW.UPPER + ":" + BOUNDS.HIGH.LOWER +":" + BOUNDS.HIGH.UPPER);
    }
    /**
     * Re-initializes the dice variable with d more dice. 
     * pre: none
     * post: 2 + d dice are now created\
     * @param d - the number of dice to add
    */
    public void createDice(int d){ //expandability
        dice = new Dice[dice.length - 1 + d];
    }
    
    public int addDice(){
        totalValue = Dice.addDice(dice);
        return(totalValue);
    }
    
    public int addDice(int a, int b){
        int sum = 0;
        for(int i = a; i < b ; i += 2 ){
            sum += Dice.addDice(dice[i], dice[i+1]);
        }
        totalValue = sum;
        return sum;
    }
    
    public int getNumberOfDice(){
        return(dice.length);
    }
    
    /**
     * Gets the value of the die in the specified position 
     * pre: none
     * post: returns the value of the die in position i
     * @param i - the position of a dice in the array
    */
    public int getValueOfDice(int i){
        return dice[i].getValue();
    }
    /**
     * The currently created dice (2) are rolled, the param c is the call made, p is the points on the line.
     * pre: none
     * post: Points are added or subtracted depending on the roll, and output is provided to the user.
     * @param c
     * @param p 
     */
    public String roll(Call call, int p){
        String response = "";
        for(Dice die:dice){
            die.rollDice();
        }
        switch(call){
            case NONE: // no call
                response = "No call made";
                break;
            case LOW: // low
                if(addDice() >= BOUNDS.LOW.LOWER && addDice() <= BOUNDS.LOW.UPPER){ // call was right
                    response = ("The call LOW was correct.");
                    super.addPoints(p);
                } else { // call was wrong
                    response = ("The call LOW was incorrect.");
                    super.removePoints(p);
                }
                break;
            case HIGH: //high
                if(addDice() >= BOUNDS.HIGH.LOWER && addDice() <= BOUNDS.HIGH.UPPER){ // call was right
                    response = ("The call HIGH was correct.");
                    super.addPoints(p);
                } else { //call was wrong
                    response = ("The call HIGH was incorrect.");
                    super.removePoints(p);
                }
                break;
            default: // none
                
        }
        return response;
    }
    
    private void log(String s){
        System.out.println(s);
    }
    
    public int getTotalValue(){
        return totalValue;
    }
    
}

class BOUNDS{
    static class LOW{
        static int LOWER = 2;
        static int UPPER = 6;
    }
    static class HIGH{
        static int LOWER = 8;
        static int UPPER = 12;
    }
}