/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimatetictactoebamt;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Theis
 */
public class FXMLDocumentController implements Initializable
{

    @FXML
    private GridPane macroGridPane;
    @FXML
    private GridPane microGridPane1, microGridPane2, microGridPane3, microGridPane4, microGridPane5;
    @FXML
    private GridPane microGridPane6, microGridPane7, microGridPane8, microGridPane9;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
