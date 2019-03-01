package ultimatetictactoebamt.bll.game;

import ultimatetictactoebamt.bll.bot.IBot;
import ultimatetictactoebamt.bll.field.IField;
import ultimatetictactoebamt.bll.move.IMove;

/**
 * This is a proposed GameManager for Ultimate Tic-Tac-Toe, the implementation
 * of which is up to whoever uses this interface. Note that initializing a game
 * through the constructors means that you have to create a new instance of the
 * game manager for every new game of a different type (e.g. Human vs Human,
 * Human vs Bot or Bot vs Bot), which may not be ideal for your solution, so you
 * could consider refactoring that into an (re-)initialize method instead.
 *
 * @author mjl
 */
public class GameManager
{

    /**
     * Three different game modes.
     */
    public enum GameMode
    {
        HumanVsHuman,
        HumanVsBot,
        BotVsBot
    }

    private final IGameState currentState;
    private int currentPlayer = 0; //player0 == 0 && player1 == 1
    private GameMode mode = GameMode.HumanVsHuman;
    private IBot bot = null;
    private IBot bot2 = null;

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Human
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     */
    public GameManager(IGameState currentState)
    {
        this.currentState = currentState;
        mode = GameMode.HumanVsHuman;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Human vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The bot to play against in vsBot mode.
     */
    public GameManager(IGameState currentState, IBot bot)
    {
        this.currentState = currentState;
        mode = GameMode.HumanVsBot;
        this.bot = bot;
    }

    /**
     * Set's the currentState so the game can begin. Game expected to be played
     * Bot vs Bot
     *
     * @param currentState Current game state, usually an empty board, but could
     * load a saved game.
     * @param bot The first bot to play.
     * @param bot2 The second bot to play.
     */
    public GameManager(IGameState currentState, IBot bot, IBot bot2)
    {
        this.currentState = currentState;
        mode = GameMode.BotVsBot;
        this.bot = bot;
        this.bot2 = bot2;
    }

    /**
     * User input driven Update
     *
     * @param move The next user move
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame(IMove move)
    {
        //Verify the new move
        if (!verifyMoveLegality(move))
        {
            System.out.println(verifyMoveLegality(move));
            return false;
        }

        //Update the currentState
        updateBoard(move);
        updateMacroboard(move);

        //Update currentPlayer
        currentPlayer = (currentPlayer + 1) % 2;
        
        System.out.println(move);
        
        System.out.println(currentPlayer);
        
        
        return true;
    }

    /**
     * Non-User driven input, e.g. an update for playing a bot move.
     *
     * @return Returns true if the update was successful, false otherwise.
     */
    public Boolean updateGame()
    {
        //Check game mode is set to one of the bot modes.
        assert (mode != GameMode.HumanVsHuman);

        //Check if player is bot, if so, get bot input and update the state based on that.
        if (mode == GameMode.HumanVsBot && currentPlayer == 1)
        {
            //Check bot is not equal to null, and throw an exception if it is.
            assert (bot != null);

            IMove botMove = bot.doMove(currentState);

            //Be aware that your bots might perform illegal moves.
            return updateGame(botMove);
        }

        //Check bot is not equal to null, and throw an exception if it is.
        assert (bot != null);
        assert (bot2 != null);

        //TODO: Implement a bot vs bot Update.
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Boolean verifyMoveLegality(IMove move)
    {
        //Tests if the move is legal and checks whether the move is placed on an occupied spot.
        
        IField field = currentState.getField();
        boolean valid = field.isInActiveMicroboard(move.getX(), move.getY());
        
        if (valid && (move.getX() < 0 || 9 <= move.getX()))
        {
            valid = false;
        }
        
        if (valid && (move.getY() < 0 || 9 <= move.getY()))
        {
            valid = false;
        }
                
        if (valid && !field.getBoard()[move.getX()][move.getY()].equals(IField.EMPTY_FIELD))
        {
            valid = false;
        }
        return valid;
    }

    private void updateBoard(IMove move)
    {
        //Updates the board to the new state 
        String[][] microBoard = currentState.getField().getBoard();
        microBoard[move.getX()][move.getY()] = currentPlayer + "";
        currentState.setMoveNumber(currentState.getMoveNumber() + 1);
        
        if (currentState.getMoveNumber() % 2 == 0)
        {
            currentState.setRoundNumber(currentState.getRoundNumber() + 1);
            
            updateMacroboard(move);
        }
    }

    private void updateMacroboard(IMove move)
    {
        //Updates the macroboard to the new state 
        String[][] macroBoard = currentState.getField().getMacroboard();
        for (int i = 0; i < macroBoard.length; i++)
        {
            for (int j = 0; j < macroBoard[i].length; j++)
            {
                if (macroBoard[i][j].equals(IField.AVAILABLE_FIELD))
                {
                    macroBoard[i][j] = IField.EMPTY_FIELD;
                }
            }
            
        }
        
        int microX = move.getX() % 3;
        int microY = move.getY() % 3;
        
        if (macroBoard[microX][microY].equals(IField.EMPTY_FIELD))
        {
            macroBoard[microX][microY] = IField.AVAILABLE_FIELD;
        }
        else
        {
            for (int i = 0; i < macroBoard.length; i++)
            {
                for (int j = 0; j < macroBoard[i].length; j++)
                {
                    if(macroBoard[i][j].equals(IField.EMPTY_FIELD))
                    {
                        macroBoard[i][j] = IField.AVAILABLE_FIELD;
                    }
                }
            }
        }
    }
    
   
    public int getCurrentPlayer()
    {
        return currentPlayer;
    }
    
    public void setCurrentPlayer(int player)
    {
        currentPlayer = player;
    }
    
    public IGameState getCurrentState()
    {
        return currentState;
    }
            
}
