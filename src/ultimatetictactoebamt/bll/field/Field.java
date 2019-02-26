/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt.bll.field;

import java.util.List;
import ultimatetictactoebamt.bll.move.IMove;

/**
 *
 * @author Theis
 */
public class Field implements IField
{
    private String[][] macroBoard;
    private String[][] microBoard;
    private List<IMove> listMoves;
    
    public Field()
    {
        macroBoard = new String[3][3];
        microBoard = new String[9][9];
    }
    
    
    @Override
    public void clearBoard()
    {
       macroBoard = new String[3][3];
       microBoard = new String[9][9];
    }

    @Override
    public List<IMove> getAvailableMoves()
    {
        return listMoves;
    }

    @Override
    public String getPlayerId(int column, int row)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEmpty()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isFull()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean isInActiveMicroboard(int x, int y)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[][] getBoard()
    {
        return microBoard;
    }

    @Override
    public String[][] getMacroboard()
    {
        return macroBoard;
    }

    @Override
    public void setBoard(String[][] board)
    {
       this.microBoard = board;
    }

    @Override
    public void setMacroboard(String[][] macroboard)
    {
       this.macroBoard = macroboard;
    }

}
