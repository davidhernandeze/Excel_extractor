/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;

public class Query{
    
    ArrayList<ArrayList> dataSplited = new ArrayList<ArrayList>();
    private String trade;
    private String area;
    private ArrayList<Result> result;
    private int iteration;
    
    public Query(String trade, String area){
        
        this.iteration = 0;
        this.trade = trade;
        this.area = area;
        
    }

    public String getTrade() {
        return trade;
    }

    public String getArea() {
        return area;
    }
    
    public void setResult(ArrayList<Result> result){
        this.result = result;
        splitResultByName(result);
    }
    
    
    public ArrayList<Result> getNextGroup(){
        
        if(this.dataSplited.size() > 0){
        if(this.iteration == this.dataSplited.size()){
            
            this.iteration = 0;
        }
        
        
        ArrayList<Result> group = this.dataSplited.get(iteration);
        iteration++;
        
        return group;
        }
        
        return null;
    }

    private void splitResultByName(ArrayList<Result> result) {
        
        ArrayList<String> names = new ArrayList<String>();
       
        for(Result res: result){
                       
            if(!names.contains(res.getName())){
                names.add(res.getName());
            }
            
        }
        
         for(String name: names){
            
            ArrayList<Result> frag = new ArrayList<Result>();
            
            for(Result res: result){
                
                if(res.getName().equals(name)){
                    frag.add(res);
                }
                
            }
            
            this.dataSplited.add(frag);
            
        }
       
        
       
    }
    
   
}
