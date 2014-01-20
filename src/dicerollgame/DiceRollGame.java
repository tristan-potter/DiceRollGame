/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dicerollgame;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author trpot5670
 */
public class DiceRollGame{
    static DiceRollGame drg;
    static DRPlayer mainPlayer = new DRPlayer();  
    
    /**
     * Create the first panel that the player sees. This panel will allow them to make game creation decisions
     * such as number of dice to use and initial points
     * pre: none
     * post: Creates a panel that displays and receives game creation information from the user.
     * @return gameCreationPane - the panel that is added to the frame as the content frame. 
    */
    public JPanel createCreationPane(){
        gameCreationPane = new JPanel(new GridBagLayout());
        
        GridBagConstraints c = new GridBagConstraints();
        int padding = 5;
        c.insets = new Insets(padding, padding, padding, padding);
        
        //<editor-fold desc="Create lbl_creationTitle">
        lbl_creationTitle = new JLabel("Want to roll dice!?");
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        gameCreationPane.add(lbl_creationTitle, c);
        //</editor-fold>
        
        //<editor-fold desc="Create lbl_numberOfDicePrompt">
        lbl_numberOfDicePrompt = new JLabel("Enter the number of dice you want to use: ");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        gameCreationPane.add(lbl_numberOfDicePrompt, c);
        // </editor-fold>
        
        //<editor-fold desc="Create txt_numberOfDice">
        txt_numberOfDice = new JTextField(10);
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        gameCreationPane.add(txt_numberOfDice, c);
        //</editor-fold>
        
        //<editor-fold desc="Create lbl_initialPointsPrompt">
        lbl_initialPointsPrompt = new JLabel("Enter the number of points you want to start with: ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        c.anchor = GridBagConstraints.WEST;
        gameCreationPane.add(lbl_initialPointsPrompt, c);
        //</editor-fold>
        
        //<editor-fold desc="Create txt_initialPoints">
        txt_initialPoints = new JTextField(10);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        gameCreationPane.add(txt_initialPoints, c);
        //</editor-fold>
         
        //<editor-fold desc="Create btn_createGame">
        btn_createGame = new JButton("Gamble!");
        btn_createGame.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                btn_createGameActionPerformed(evt);
            }
        });
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        gameCreationPane.add(btn_createGame, c);
        //</editor-fold>
        
        
        return gameCreationPane;
    }
    
    /**
     * This function creates the components of the GUI and adds them to their proper panels. It 
     * then returns the contentPane they are all added to.
     * This function could simply use calls to mainPlayer instead of passing player to it. Wanted practice
     * tracking objects through methods.
     * @param player - the object containing the information about the player
     * @return JPanel contentPane 
     */
    public JPanel createContentPane(DRPlayer player){        
        // Base pane: gets returned
        contentPane = new JPanel(new GridBagLayout());
        
        // set layout
        GridBagConstraints c = new GridBagConstraints();
        int padding = 5;
        c.insets = new Insets(padding, padding, padding, padding);
        
        // <editor-fold desc="Create titlePane">
        // titlePane: creation
        titlePane = new JPanel();
        titlePane.setLayout(new GridBagLayout());
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        contentPane.add(titlePane, c);
        //</editor-fold>
        
        // <editor-fold desc="Create lbl_title">
        // titlePane: title
        lbl_contentTitle = new JLabel("ROLL DICE TO WIN!");
        lbl_contentTitle.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        titlePane.add(lbl_contentTitle, c);
        //</editor-fold>
        
        // <editor-fold desc="Create lbl_points">
        // titlePane: points display
        lbl_points = new JLabel("Current Points: " + player.getPoints());
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        titlePane.add(lbl_points, c);
        //</editor-fold>
        
        // <editor-fold desc="Create actionPane">
        // actionPane: creation
        actionPane = new JPanel();
        actionPane.setLayout(new GridBagLayout());
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        contentPane.add(actionPane, c);
        //</editor-fold>
        
        // <editor-fold desc="Create dicePane">
        // dicePane: creation
        dicePane = new JPanel();
        dicePane.setLayout(new GridBagLayout());
        dicePane.setBorder(BorderFactory.createEtchedBorder());
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        actionPane.add(dicePane, c);
        // </editor-fold>
        
        // <editor-fold desc="Create lbl_diceValues[]">
        // actionPane: dice values after roll
        int multiple = 1;
        lbl_diceValues = new JLabel[player.getNumberOfDice()];
        for(int i = 0; i < lbl_diceValues.length; i++){
            lbl_diceValues[i] = new JLabel("Dice " + (i+1) + ": " + "0");
            c.gridwidth = 1;
            c.gridx = (int)i%4;
            c.gridy = (int)(i/4);
            dicePane.add(lbl_diceValues[i], c);
        }
        //</editor-fold>
        
        // <editor-fold desc="Create lbl_total">
        // actionPane: total dice value
        lbl_total = new JLabel("Total Value: " + "0");
        lbl_total.setHorizontalAlignment(SwingConstants.CENTER);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        actionPane.add(lbl_total, c);
        //</editor-fold>
        
        // <editor-fold desc="Create lbl_promptRisk">
        // actionPane : add label for points risked
        lbl_promptRisk = new JLabel("How many points do you want to risk? ");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        actionPane.add(lbl_promptRisk, c);
        //</editor-fold>
        
        //<editor-fold desc="Create txt_call">
        // actionPane: add place to enter points risked
        txt_call = new JTextField(6);
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        actionPane.add(txt_call, c);
        //</editor-fold>
        
        // <editor-fold desc="Create cbx_call">
        // actionPane: place to put call
        cbx_call = new JComboBox(Call.values());
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 1;
        actionPane.add(cbx_call, c);
        // </editor-fold>
        
        // <editor-fold desc="Create btn_roll">
        // actionPane
        btn_roll = new JButton("Roll!");
        btn_roll.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt){
                btn_rollActionPerformed(evt);
            }
        });
        c.gridx = 0;
        c.gridy = 4;
        actionPane.add(btn_roll, c);
        // </editor-fold>
        
        // <editor-fold desc="Create lbl_output">
        lbl_output = new JLabel("Press button to roll!!!");
        c.gridx = 0;
        c.gridy = 5;
        actionPane.add(lbl_output, c);
        // </editor-fold>        
        
        contentPane.setOpaque(true);
        return contentPane;
    }
    
    /**
     * Creates the GUI
     * @param p - the first pane the user sees
     */
    private static void runGUI(JPanel p){
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame = new JFrame("Dice Roll Game");
        

        //Create and set up the content pane.
        frame.setContentPane(p);

        // finish up the frame and show it to the user.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
    
    /**
     * btn_roll actionPerformed function, gets called when button is pressed. 
     * pre: none
     * post: The dice are virtually rolled and output is given to user while points values are
     *      updated.
     * @param e - ActionEvent from the button
     */
    public void btn_rollActionPerformed(ActionEvent e){
        Call c;
        int pointsRisked;
        lbl_output.setText("Press button to roll!!!");
        if(txt_call.getText().isEmpty()) {
            lbl_output.setText("Please risk some points");
            pointsRisked = 0;
        } else {
            pointsRisked = Integer.parseInt(txt_call.getText());
        }
        
        switch(cbx_call.getSelectedIndex()){
            case 0:
                c = Call.NONE;
                lbl_output.setText("Please make a call");
                break;
            case 1:
                c = Call.LOW;
                break;
            case 2:
                c = Call.HIGH;
                break;
            default:
                c = Call.NONE;
                lbl_output.setText("Please make a call");
                break;
        }
        
        // roll the dice and provide output
        lbl_output.setText(mainPlayer.roll(c, pointsRisked));
        
        //set the current points text off of the points counter from the mainPlayer object
        lbl_points.setText("Current Points: " + mainPlayer.getPoints());
        
        //show the user the values of all the dice.
        for(int i = 0; i < mainPlayer.getNumberOfDice(); i++){
            lbl_diceValues[i].setText("Dice " + (i+1) + ": "  + mainPlayer.getValueOfDice(i));
        }
        //show the user the total value of all the dice
        lbl_total.setText("Total Value: " + mainPlayer.getTotalValue());
        
    }
    
    /**
     * btn_createGame actionPerformed function, gets called when button is pressed.
     * pre: none
     * post: game is created with specified number of dice.
     * @param e 
     */
    public void btn_createGameActionPerformed(ActionEvent e){
        int numberOfDice = Integer.parseInt(txt_numberOfDice.getText());
        int initialPoints = Integer.parseInt(txt_initialPoints.getText());
        mainPlayer = new DRPlayer(initialPoints, numberOfDice);
        
        //Create and set up the content pane.
        frame.remove(gameCreationPane);
        frame.setContentPane(createContentPane(mainPlayer));
        
        // finish up the frame and show it to the user.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
    }
    
    /**
     * creates the GUI and starts the game
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        drg = new DiceRollGame();
        runGUI(drg.createCreationPane());
        /* UNNECASSARY FOR NOW
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                runGUI(mainPlayer.getNumberOfDice()); 
            } 
        });*/
    }
    
    //used for debugging
    private void log(String c){
        System.out.println(c);
    }
    
    static JFrame frame;
    JPanel gameCreationPane;
            JLabel lbl_creationTitle;
            JLabel lbl_numberOfDicePrompt;
            JTextField txt_numberOfDice;
            JLabel lbl_initialPointsPrompt;
            JTextField txt_initialPoints;
            JButton btn_createGame;
    JPanel contentPane;
        JPanel titlePane;
            JLabel lbl_contentTitle;
            JLabel lbl_points;
        JPanel actionPane;
            JPanel dicePane;
                JLabel[] lbl_diceValues;
            JLabel lbl_total;
            JLabel lbl_promptRisk;
            JTextField txt_call;
            JComboBox cbx_call;
            JButton btn_roll;
            JLabel lbl_output;
}
