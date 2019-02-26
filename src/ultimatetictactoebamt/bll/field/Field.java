/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt.bll.field;

import java.util.ArrayList;
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
        List<IMove> listMoves = new ArrayList<>();
        for (int i = 0; i < microBoard.length; i++)
        {
            for (int j = 0; j < microBoard[i].length; j++)
            {
                //TODO isInActiveMicroboard needs to work before this part can
                //be written since we need to check whether a move is "legal"
            }
        }
        return listMoves;
    }

    @Override
    public String getPlayerId(int column, int row)
    {
        return microBoard[column][row];
    }

    @Override
    public boolean isEmpty()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (microBoard[i][j] != AVAILABLE_FIELD && microBoard[i][j] != EMPTY_FIELD)
                {
                    return false;
                }
            }
        }
            return true;
    }

    @Override
    public boolean isFull()
    {
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (microBoard[i][j] == AVAILABLE_FIELD || microBoard[i][j] == EMPTY_FIELD)
                {
                    return false;
                }
            }
        }
        return true;
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
