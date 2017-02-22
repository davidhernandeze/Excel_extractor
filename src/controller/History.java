/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Tony
 */
public class History {
   
    private ArrayList<Query> querys = new ArrayList<Query>();
    
    public void addQuery(Query query){
        this.querys.add(query);
    }
    
    public Query getQueryRepeated(Query query){
        
        Iterator it = querys.iterator();
        
        while(it.hasNext()){
            Query q = (Query) it.next();
            if(query.getTrade().equals(q.getTrade()) && 
                    query.getArea().equals(q.getArea())){
                return q;
            }
        }
      
        return null;
    }
}
