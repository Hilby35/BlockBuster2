/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Blockbuster2;

import java.util.List;

/**
 * Date: Mar 25, 2019
 * @author dsd5227
 */
public class Search {
    List<Item> Search;
    String totalResults;
    String Response;
    
    public void addLastPage(Search search){
        for(Item i : search.Search)
            this.Search.add(i);
    }
    
    public String toString() {
        String output = "Search Results:";
        
        for(int i = 0; i < Search.size(); i++)
            output += "Result " + (i+1) + ":\n" + Search.get(i);
        
        return output;
    }

} // Search
