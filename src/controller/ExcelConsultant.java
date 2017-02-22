/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Tony
 */
public class ExcelConsultant {

    static ArrayList<String> getUnrepeatableDataOfACollum(File inputFile, int col) {

        ArrayList<String> collection = new ArrayList<>();
        try {

            FileInputStream fis = new FileInputStream(inputFile);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = myWorkBook.getSheetAt(0);

            int nRows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < nRows; i++) {

                String item = sheet.getRow(i).getCell(col).getStringCellValue();
                String delim= " ";
                String[] separatedWords = item.split(delim);
                
                for(String word: separatedWords){
                    
                    word = word.replaceAll(",",""); 
                    word = word.replaceAll(" ",""); 
                    
                    if (!collection.contains(word)) {
                   
                        collection.add(word);

                    }
                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        collection.sort(null);

        return collection;

    }

    public static ArrayList<Result> makeConsult(File inputFile, String trade, String area) {

        ArrayList<Result> result = new ArrayList<>();

        try {

            FileInputStream fis = new FileInputStream(inputFile);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = myWorkBook.getSheetAt(0);

            int nRows = sheet.getPhysicalNumberOfRows();

            for (int i = 1; i < nRows; i++) {

                Row actualRow = sheet.getRow(i);

                if (actualRow.getCell(1).getStringCellValue().contains(trade)
                        && actualRow.getCell(5).getStringCellValue().contains(area)) {

                    String name = actualRow.getCell(0).getStringCellValue();
                    String tradeName = actualRow.getCell(2).getStringCellValue();
                    String person = actualRow.getCell(3).getStringCellValue();
                    String number = actualRow.getCell(4).getStringCellValue();
                    String notes = actualRow.getCell(6).getStringCellValue();
                    
                    Result r = new Result(name, tradeName, person, number, notes);
                    
                    result.add(r);

                }
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }

    static void logResult(ArrayList<Result> result, File outputFile, String name) {
        
        try {
            
            FileInputStream fis;     
            fis = new FileInputStream(outputFile);  
            XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);
            XSSFSheet sheet = myWorkBook.getSheetAt(0);
            
            int nRow = sheet.getPhysicalNumberOfRows();
            
            if(nRow == 0){
                sheet.createRow(nRow);
                
                sheet.getRow(nRow).createCell(0).setCellValue("Timestamp");
                sheet.getRow(nRow).createCell(1).setCellValue("Name");
                sheet.getRow(nRow).createCell(2).setCellValue("Trade Name");
                sheet.getRow(nRow).createCell(3).setCellValue("Number");
                sheet.getRow(nRow).createCell(4).setCellValue("Person");
                sheet.getRow(nRow).createCell(5).setCellValue("Notes");
                
                nRow++;
            }
            
            for(Result res : result){
                
                sheet.createRow(nRow);
                
                Date date = new Date();
                sheet.getRow(nRow).createCell(0).setCellValue(date.toString());
                sheet.getRow(nRow).createCell(1).setCellValue(name);
                sheet.getRow(nRow).createCell(2).setCellValue(res.getTradeName());
                sheet.getRow(nRow).createCell(3).setCellValue(res.getNumber());
                sheet.getRow(nRow).createCell(4).setCellValue(res.getPerson());
                sheet.getRow(nRow).createCell(5).setCellValue(res.getNotes());
                
                nRow++;
           }
              //important to close InputStream
                fis.close();
                //Open FileOutputStream to write updates
                FileOutputStream output_file =new FileOutputStream(outputFile);
                //write changes
                myWorkBook.write(output_file);
                //close the stream
                output_file.close(); 
            
         } catch (Exception ex) {
             ex.printStackTrace();
        }
        
    }
   

}
