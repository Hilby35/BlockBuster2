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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Date: Mar 25, 2019
 *
 * @author dsd5227
 */
public class OmdbConnection {

    private static final String APIKEY = "3679f682";

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

    public static void getMoviesByTitle(String title) {
        try {
            String json = readUrl("http://www.omdbapi.com/?apikey=" + APIKEY + "&s=" + title + "&plot=full");
            Search search = GSON.fromJson(json, Search.class);

            for(int i = 0; i < search.Search.size(); i++)
                search.Search.set(i, getMovieByImdbID(search.Search.get(i).imdbID));

            for (Item i : search.Search) {
                System.out.println(i);
            }

        } catch (JsonSyntaxException | IOException ex) {
            Logger.getLogger(OmdbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    public static Item getMovieByImdbID(String imdbID) throws IOException {
        String json = readUrl("http://www.omdbapi.com/?apikey=" + APIKEY + "&i=" + imdbID + "&plot=full");
        return GSON.fromJson(json, Item.class);
    }

} // OmdbConnection
