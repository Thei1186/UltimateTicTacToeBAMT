/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt.GUI;

import ultimatetictactoebamt.bll.game.GameManager;
import ultimatetictactoebamt.bll.game.GameState;
import ultimatetictactoebamt.bll.game.IGameState;
import ultimatetictactoebamt.bll.move.IMove;

/**
 *
 * @author Asvør
 */
public class GameModel
{
    private GameManager gManager;
    
    public GameModel()
    {
        gManager = new GameManager(new GameState());
    }
    
    public String[][] getMacroBoard()
    {
        return gManager.getCurrentState().getField().getMacroboard();
    }
    
    public String[][] getBoard()
    {
        return gManager.getCurrentState().getField().getBoard();
    }
    
    public int getCurrentPlayer()
    {
        return gManager.getCurrentPlayer();
    }
    
    public IGameState getGameState()
    {
        return gManager.getCurrentState();
    }

    public boolean doMove(IMove move)
    {
        return gManager.updateGame(move);
    }
    
}
