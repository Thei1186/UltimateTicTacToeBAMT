/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.easv.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import dk.easv.bll.move.Move;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Kokushi
 */
public class NewClassBot implements IBot
{
     private static final String BOTNAME = "Not so Classy";
    // Moves {row, col} in order of preferences. {0, 0} at top-left corner
    protected int[][] preferredMoves = {
        {0, 0}, {2, 2}, {0, 2}, {2, 0},  //Corners ordered across
        {1, 1}, //Center      
            {0, 1}, {2, 1}, {1, 0}, {1, 2}}; //Outer Middles ordered across
    
    protected int[][] preferredMoves2 = {
        {1, 1}, {2, 2}, {1, 0}, {0, 0}, //Outer Middles ordered across   
        {0, 1}, //Center
            {1, 2}, {2, 1}, {0, 2}, {2, 0}};  //Corners ordered across
            
    

    /**
     * Makes a turn. Edit this method to make your bot smarter.
     * A bot that uses a local prioritised list algorithm, in order to win any local board,
     * and if all boards are available for play, it'll run a on the macroboard,
     * to select which board to play in.
     *
     * @return The selected move we want to make.
     */
    @Override
    public IMove doMove(IGameState state) {
        Random rd = new Random();
        //Find macroboard to play in
        for (int[] move : preferredMoves2)
        {
            if(state.getField().getMacroboard()[move[0]][move[1]].equals(IField.AVAILABLE_FIELD))
            {
                //find move to play
                for (int[] selectedMove : preferredMoves)
                {
                    List<IMove> validMoves = state.getField().getAvailableMoves();
                    int x = move[0]*3 + selectedMove[0];
                    int y = move[1]*3 + selectedMove[1];
                    if(!state.getField().getBoard()[x][y].equals(IField.EMPTY_FIELD)) //if spot is already taken it returns a random move
                    {
                        return validMoves.get(rd.nextInt(validMoves.size()));
                    }
                    if(state.getField().getBoard()[x][y].equals(IField.EMPTY_FIELD)) //if spot isn't taken it returns the next valid move on the preferred list
                    {
                        return new Move(x,y);
                    }
                }
            }
        }

        //NOTE: Something failed, just take the first available move I guess!
        return state.getField().getAvailableMoves().get(0);
    }

    @Override
    public String getBotName() {
        return BOTNAME;
    }
}
