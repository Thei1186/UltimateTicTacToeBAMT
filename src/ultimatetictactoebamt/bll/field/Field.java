/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt.bll.field;

import java.util.ArrayList;
import java.util.List;
import ultimatetictactoebamt.bll.move.IMove;
import ultimatetictactoebamt.bll.move.Move;

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
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                microBoard[i][j] = AVAILABLE_FIELD;
            }
        }
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                macroBoard[i][j] = EMPTY_FIELD; 
            }
        }
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
                if (isInActiveMicroboard(i, j) && microBoard[i][j].equals(EMPTY_FIELD))
                {
                    listMoves.add(new Move(i, j));
                }
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
        int microX = x > 0 ? x/3 : 0;
        int microY = y > 0 ? y/3 : 0;
        
        String newValue = macroBoard[microX][microY];
        return newValue.equals(AVAILABLE_FIELD);
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
