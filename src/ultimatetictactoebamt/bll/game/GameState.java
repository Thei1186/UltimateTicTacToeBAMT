/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt.bll.game;

import ultimatetictactoebamt.bll.field.IField;

/**
 *
 * @author Theis
 */
public class GameState implements IGameState
{
    private int moveNumber;
    private int roundNumber;
    
    public GameState()
    {
        moveNumber = 1;
        roundNumber = 1;
    }
    
    
    @Override
    public IField getField()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getMoveNumber()
    {
        return moveNumber;
    }

    @Override
    public void setMoveNumber(int moveNumber)
    {
        this.moveNumber = moveNumber;
    }

    @Override
    public int getRoundNumber()
    {
        return roundNumber;
    }

    @Override
    public void setRoundNumber(int roundNumber)
    {
        this.roundNumber = roundNumber;
    }
    
}
