/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockbuster2;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Date: Mar 25, 2019
 *
 * @author dsd5227
 */
public class OmdbConnection {

    private static final String APIKEY = "3679f682";
    
    private static final String[] TYPE_VALUES = {"movie", "series", "episode"};
    private static final String[] PLOT_VALUES = {"short", "full"};
    
    private static final Gson GSON = new Gson();

    private static String readUrl(String urlString) throws IOException {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return null;
    }

    public static Search getMoviesByTitle(String title) {
        return getMoviesByTitle(title, "", 0);
    }
    
    public static Search getMoviesByTitle(String title, String type, int year) {
        Search search = null;
        
        try {
            String url = "http://www.omdbapi.com/?apikey=" + APIKEY + "&s=" + title;
            
            if(!type.isEmpty() && Arrays.asList(TYPE_VALUES).contains(type))
                url += "&type=" + type;
            
            if(year != 0)
                url += "&y=" + year;
            
            String json = readUrl(url);
            search = GSON.fromJson(json, Search.class);
            
            int totalResponses = Integer.parseInt(search.totalResults);
            if(totalResponses > 10) {
                for(int i = 0; i < totalResponses + 10; i+=10){
                    search = new Search(search);
                }
            } else {   
                for(int i = 0; i < search.Search.size(); i++)
                    search.Search.set(i, getMovieByImdbID(search.Search.get(i).imdbID, "", "", 0, ""));
            }
            
            for (Item i : search.Search) {
                System.out.println(i);
            }

        } catch (JsonSyntaxException | IOException ex) {
            Logger.getLogger(OmdbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            System.out.println("No Movies Found.");
            return null;
        }
        
        return search;
    }

    public static Item getMovieByImdbID(String imdbID, String title, String type, int year, String plot) throws IOException {
        String url = "http://www.omdbapi.com/?apikey=" + APIKEY + "&s=" + title;
            
            if(!type.isEmpty() && Arrays.asList(TYPE_VALUES).contains(type))
                url += "&type=" + type;
            
            if(year != 0)
                url += "&y=" + year;
            
            if(!plot.isEmpty() && Arrays.asList(PLOT_VALUES).contains(plot))
                url += "&plot=" + plot;
            
        String json = readUrl(url);
        return GSON.fromJson(json, Item.class);
    }
    
    public static String getPosterUrlByImdbID(String imdbID, int height) {
        String url = "http://img.omdbapi.com/?apikey=" + APIKEY + "&i=" + imdbID + "&h=" + height;
        return url;
    }
    
    public static String getPosterUrlByTitle(String title, int height) {
        String imdbID = getMoviesByTitle(title, "", 0, 0).Search.get(0).imdbID;
        return getPosterUrlByImdbID(imdbID, height);
        
    }

} // OmdbConnection
