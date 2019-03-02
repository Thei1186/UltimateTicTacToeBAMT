/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt.GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import ultimatetictactoebamt.UltimateButton;
import ultimatetictactoebamt.bll.game.GameState;
import ultimatetictactoebamt.bll.move.Move;

/**
 *
 * @author Theis
 */
public class UTTTController implements Initializable
{

    @FXML
    private GridPane macroGridPane;
    @FXML
    private Label lblMoves;

    private GameModel gModel;
    @FXML
    private AnchorPane mainPane;

    public UTTTController()
    {

    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gModel = new GameModel();
        createAllCells();

    }

    @FXML
    private void clearGame(ActionEvent event)
    {
        gModel.getGameState().getField().clearBoard();
    }

    private void createAllCells()
    {
        int btnWidth = 75;
        int btnHeight = 60;
        for (int x = 0; x < 9; x++)
        {
            for (int y = 0; y < 9; y++)
            {
                UltimateButton btn = new UltimateButton();
                btn.setPrefSize(btnWidth, btnHeight);
                btn.setMove(new Move(x, y));
                btn.setLayoutX(10 + (btnWidth + 2) * x);
                btn.setLayoutY(128 + (btnHeight + 2) * y);
                btn.setOnMouseClicked(event ->
                {
                    UltimateButton b = (UltimateButton) event.getSource();
                    boolean succes = gModel.doMove(b.getMove());
                    if (succes)
                    {
                        if (gModel.getGameState().getMoveNumber() % 2 == 0)
                        {
                            b.setText("X");
                        } else
                        {
                            b.setText("O");
                        }
                    }
                });
                mainPane.getChildren().add(btn);
            }
        }

    }

    @FXML
    private void handleMacroBoard(MouseEvent event)
    {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        Integer col = GridPane.getColumnIndex((Node) event.getSource());
        int r = (row == null) ? 0 : row;
        int c = (col == null) ? 0 : col;
    }

    private void clickCell(MouseEvent event)
    {

    }
}
