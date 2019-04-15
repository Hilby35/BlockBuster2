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
public class Item {

    String Title, Year, Rated, Released, Runtime, Genre, Director, Writer, Actors, Plot, Language, Country, Awards, Poster, Metascore, imdbRating, imdbVotes, imdbID, Type, DVD, BoxOffice, Production, Website, Response;
    List<Ratings> Ratings;

    @Override
    public String toString() {
        String output = "Title: " + Title;
        output += "\nImdbID: " + imdbID;
        output += "\nReleased: " + Released;
        output += "\nRuntime: " + Runtime;
        output += "\nPlot: " + Plot;
        output += "\nPoster: " + Poster;
        
        return output;
    }
    
    
    
} // item
