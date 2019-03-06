package ultimatetictactoebamt.bll.bot;

import dk.easv.bll.field.IField;
import dk.easv.bll.game.IGameState;
import dk.easv.bll.move.IMove;
import dk.easv.bll.move.Move;
import java.util.Random;


public class BotStuff implements IBot {

    private static final String BOTNAME = "kinky bot stuff";
    // Moves {row, col} in order of preferences. {0, 0} at top-left corner
    protected int[][] preferredMoves = {
            {1, 1}, //Center
            {0, 0}, {2, 2}, {0, 2}, {2, 0},  //Corners ordered across
            {0, 1}, {2, 1}, {1, 0}, {1, 2}}; //Outer Middles ordered across

    /**
     * Makes a turn. if there is already something in the spot we go totally bonkers and add to the current x value
     * and/or substract from the current y value, because y not? 
     * Furthermore, it attempts to substract from the x value, I know... what's up what that? 
     *
     * @return The selected move we want to make.
     */
    @Override
    public IMove doMove(IGameState state) {

        //Find macroboard to play in
        for (int[] move : preferredMoves)
        {
            if(state.getField().getMacroboard()[move[0]][move[1]].equals(IField.AVAILABLE_FIELD))
            {
         
                Random rnd = new Random();
                //find move to play
                for (int[] selectedMove : preferredMoves)
                {
                    
                    int x = move[0]*3 + selectedMove[0];
                    int y = move[1]*3 + selectedMove[1];
                     if(!state.getField().getBoard()[x][y].contains(IField.EMPTY_FIELD)
                           && !state.getField().getBoard()[x][y].contains(IField.AVAILABLE_FIELD))
                     {
                         try
                         {
                              x = x + (rnd.nextInt(selectedMove[0] % 2 + 1));
                  
                           
                         } catch (Exception e)
                         {
                            
                         }
                            try
                         {
                             y = y - (rnd.nextInt(selectedMove[0] % 2 - 1));
                             
                         } catch (Exception e)
                         {
                            
                         }
                         try
                         {
                             x = x - (rnd.nextInt(selectedMove[0] % 2 -1));
                  
                        
                         } catch (Exception e)
                         {
                            
                         }
            
                         
                      
                        
                     }
                    if(state.getField().getBoard()[x][y].equals(IField.EMPTY_FIELD))
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
