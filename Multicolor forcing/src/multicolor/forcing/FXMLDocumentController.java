/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multicolor.forcing;

import java.io.File;
import static java.lang.Math.pow;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 *
 * @author nick
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TextArea txtResults;
    @FXML
    private TextField txtColors;
    @FXML
    private TextField txtNodes;
    @FXML
    private Button btnGenerateList;
    @FXML
    private TextArea txtEndStates;
    @FXML
    private Button btnGenEndStates;
    
    public int colors;
    public int nodes;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> endStates = new ArrayList<>();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//      FileChooser fileChooser = new FileChooser();
//      fileChooser.setTitle("Navigate to file");
//      File dataFile = fileChooser.showOpenDialog(MulticolorForcing.getPrimaryStage());
    }    
    
    public static String baseConversion(String number, int sBase, int fBase)
    {
        return Integer.toString(Integer.parseInt(number, sBase), fBase);
    }
    
    public String genDecimalFormat() {
        String zeroes = "";
        for(int i = 0; i < nodes; i++) {
            zeroes += "0";
        }
        return zeroes;
    }
    
    private String printArray(ArrayList<String> array) {
        String result = "";
        for(String s : array) {
            result += s + "\n";
        }
        return result;
        
    }
    
    private boolean checkString(String s) {
        boolean hasZero = false;
        boolean hasOne = false;
        boolean hasTwo = false;
        char prev = 'x';
        char curr;
        
        for(int i = 0; i < s.length(); i++) {
            curr = s.charAt(i);
            if(curr == '0') {
                hasZero = true;
            }
            if(curr == '1') {
                hasOne = true;
            }
            if(curr == '2') {
                hasTwo = true;
            }
            if(curr == prev) {
                return false;
            } 
            prev = curr;
        }
        if(hasZero && hasOne && hasTwo) {
            return true;
        }
        else {
            return false;
        }
    }
    @FXML
    private void setNumColors(ActionEvent event) {
        colors = Integer.parseInt(txtColors.getText());
        txtColors.clear();
    }

    @FXML
    private void setNumNodes(ActionEvent event) {
        nodes = Integer.parseInt(txtNodes.getText());
        txtNodes.clear();
    }

    @FXML
    private void genList(ActionEvent event) {
        int num = 0;
        int index;
        ArrayList<Integer> toRemove = new ArrayList<Integer>();
        DecimalFormat fmt = new DecimalFormat(genDecimalFormat());
        list.clear();
        while(num < pow(colors, nodes)) {
            list.add(fmt.format(Integer.parseInt(baseConversion(Integer.toString(num), 10, colors))));
            num++;
        }
        for(String s : list) {
            if(!checkString(s)) {
                toRemove.add(list.indexOf(s));
            }
        }
        for (int i = toRemove.size() - 1; i >= 0 ; i--) {
            index = toRemove.get(i);
            list.remove(index);
        }
        
        txtResults.setText(printArray(list));
    }

    @FXML
    private void genEndStates(ActionEvent event) {
        endStates.clear();
        for(String s : list) {
            Graph g = new Graph(s);
            endStates.add(g.force());
        }
        txtEndStates.setText(printArray(endStates));
    }
}
