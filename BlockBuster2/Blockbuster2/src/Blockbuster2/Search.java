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
        for(int i = 0; i < search.Search.size(); i++)
            this.Search.add(i, search.Search.get(i));
    }

} // Search
