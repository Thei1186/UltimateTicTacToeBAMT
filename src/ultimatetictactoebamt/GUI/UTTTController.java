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
import javafx.scene.layout.GridPane;

/**
 *
 * @author Theis
 */
public class UTTTController implements Initializable
{

    @FXML
    private GridPane macroGridPane;
    @FXML
    private GridPane microGridPane1, microGridPane2, microGridPane3, microGridPane4, microGridPane5;
    @FXML
    private GridPane microGridPane6, microGridPane7, microGridPane8, microGridPane9;
    
    private GameModel gModel;

    public UTTTController()
    {
        gModel = new GameModel();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        Integer row = GridPane.getRowIndex((Node) event.getSource());
        Integer col = GridPane.getColumnIndex((Node) event.getSource());
        int r = (row == null) ? 0 : row;
        int c = (col == null) ? 0 : col;
        
        int player = gModel.getCurrentPlayer();
        Button btn = (Button) event.getSource();
        String xOrO = player == 0 ? "X" : "O";
        
        btn.setText(xOrO);
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }

    @FXML
    private void clearGame(ActionEvent event)
    {
        
    }

}
