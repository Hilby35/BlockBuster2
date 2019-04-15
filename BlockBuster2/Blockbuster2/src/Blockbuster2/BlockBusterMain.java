/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Blockbuster2;

/**
 *
 * @author mch5367
 */
public class BlockBusterMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //System.out.println(OmdbConnection.getPosterUrlByTitle("Dumbo", 600));
        
        System.out.println(OmdbConnection.getMoviesByTitle("Bee", 2007));
        System.out.println("\n\n\n");
        System.out.println(OmdbConnection.getMoviesByTitle("Bee", 2008));
        System.out.println("\n\n\n");
        System.out.println(OmdbConnection.getMoviesByTitle("Bee"));
    }
    
}
