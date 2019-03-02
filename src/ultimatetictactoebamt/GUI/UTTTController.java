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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import ultimatetictactoebamt.UltimateButton;
import ultimatetictactoebamt.bll.move.IMove;
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

    private GridPane[][] microGrid;
    private UltimateButton[][] ultmBtn;

    public UTTTController()
    {
         microGrid = new GridPane[3][3];
         ultmBtn = new UltimateButton[9][9];
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        gModel = new GameModel();
        createMicroGridPanes();

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

    private void createMicroGridPanes()
    {
        for (int i = 0; i < 3; i++)
        {
            macroGridPane.addRow(i);
            for (int j = 0; j < 3; j++)
            {
                GridPane gp = new GridPane();
                for (int k = 0; k < 3; k++)
                {
                    gp.addColumn(k);
                    gp.addRow(k);
                }
                microGrid[i][j] = gp;
                for (int m = 0; m < 3; m++)
                {
                    ColumnConstraints cc = new ColumnConstraints();
                    cc.setPercentWidth(33);
                    cc.setHgrow(Priority.ALWAYS);
                    cc.setFillWidth(true);
                    gp.getColumnConstraints().add(cc);

                    RowConstraints rc = new RowConstraints();
                    rc.setVgrow(Priority.ALWAYS);
                    rc.setFillHeight(true);
                    rc.setPercentHeight(33);
                    gp.getRowConstraints().add(rc);
                }
                gp.setGridLinesVisible(true);
                macroGridPane.addColumn(j);
                macroGridPane.add(gp, i, j);
            }

        }
        insertButtonsIntoGridPanes();
    }

    private void insertButtonsIntoGridPanes()
    {
        int btnWidth = 75;
        int btnHeight = 60;
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                GridPane gp = microGrid[i][j];
                for (int x = 0; x < 3; x++)
                {
                    for (int y = 0; y < 3; y++)
                    {
                        UltimateButton btn = new UltimateButton();
                        btn.setPrefSize(btnWidth, btnHeight);             
                        btn.setUserData(new Move(x+i*3, y+j*3));
                        btn.setOnMouseClicked(event ->
                        {
                            UltimateButton b = (UltimateButton) event.getSource();
                            boolean succes = gModel.doMove((IMove) btn.getUserData());
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
                        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                        gp.add(btn,x,y);
                        ultmBtn[x+i*3][y+j*3] = btn;
                    }
                }
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

}
