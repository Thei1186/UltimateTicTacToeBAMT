package ultimatetictactoebamt.bll.bot;

import ultimatetictactoebamt.bll.game.IGameState;
import ultimatetictactoebamt.bll.move.IMove;

/**
 *
 * @author mjl
 */
public interface IBot
{

    /**
     * Makes a turn. Implement this method to make your bot do something.
     *
     * @param state the current game state
     * @return The column where the turn was made.
     */
    IMove doMove(IGameState state);

}
