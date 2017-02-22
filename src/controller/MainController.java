/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import view.MainMenu;

public class MainController implements ActionListener {

    private MainMenu menu;
    private History history;
    
    private File inputFile;
    private File outputFile;    

    public MainController(MainMenu menu) {
       
        this.menu = menu;
        history = new History();
        
        menu.setVisible(true);
        menu.setDefaultCloseOperation(EXIT_ON_CLOSE);

        menu.getButtonConsult().setEnabled(false);

        menu.getButtonFile().addActionListener(this);
        menu.getButtonConsult().addActionListener(this);
        menu.getButtonOutputFile().addActionListener(this);
        menu.getButtonFile().setEnabled(false);

        menu.getOutputText().setEditable(false);

    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == menu.getButtonFile()) {

            selectInputFile();

        }
        
        if (e.getSource() == menu.getButtonOutputFile()) {

            selectOutputFile();

        }

        if (e.getSource() == menu.getButtonConsult()) {

            String area = menu.getAreaDrop().getSelectedItem().toString();
            String trade = menu.getTradeDrop().getSelectedItem().toString();

            Query query = new Query(trade, area);
            
            if(history.getQueryRepeated(query) == null){
                
                ArrayList<Result> result = ExcelConsultant.makeConsult(inputFile, trade, area);
                query.setResult(result);
                history.addQuery(query);
                
                this.printData(query.getNextGroup());
                
            }else{
                
                this.printData(history.getQueryRepeated(query).getNextGroup());
                
            }

        }

    }

    private void selectInputFile() {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Excel data", "xlsx");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(menu);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            inputFile = fileChooser.getSelectedFile();
            menu.getFileText().setText(inputFile.getAbsolutePath());
            menu.getFileText().setForeground(Color.green);
            menu.getButtonConsult().setEnabled(true);
            fillComboBoxes();

        }

    }

    private void fillComboBoxes() {

        ArrayList<String> areas = ExcelConsultant.getUnrepeatableDataOfACollum(inputFile, 5);
        Iterator a = areas.iterator();
        while (a.hasNext()) {

            menu.getAreaDrop().addItem((String) a.next());

        }

        ArrayList<String> trades = ExcelConsultant.getUnrepeatableDataOfACollum(inputFile, 1);
        Iterator i = trades.iterator();
        while (i.hasNext()) {

            menu.getTradeDrop().addItem((String) i.next());

        }

    }

    private void printData(ArrayList<Result> result) {
        
        String output = "";
        
        if(result != null)
        for(Result r: result) {
           
                output = output.concat(r.getTradeName() + " ");
                output = output.concat(r.getPerson() + " ");
                output = output.concat(r.getNumber() + " ");
                output = output.concat(r.getNotes() + " ");
                output = output.concat("\n");     

        }

        if (output.equals("")) {
            
            JOptionPane.showMessageDialog(menu, "No results for this search");
            ExcelConsultant.logResult(result, this.outputFile, menu.getNameField().getText());

        } else {

            JOptionPane.showMessageDialog(menu, "Results saved in the log");
            
            ExcelConsultant.logResult(result, this.outputFile, menu.getNameField().getText());
            menu.getOutputText().setText(output);

        }

    }

    private void selectOutputFile() {
        
         JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Excel data", "xlsx");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(menu);

        if (returnVal == JFileChooser.APPROVE_OPTION) {

            outputFile = fileChooser.getSelectedFile();
            menu.getFileTextOutput().setText(outputFile.getAbsolutePath());
            menu.getFileTextOutput().setForeground(Color.green);
           
            menu.getButtonFile().setEnabled(true);
        }
    }

}
