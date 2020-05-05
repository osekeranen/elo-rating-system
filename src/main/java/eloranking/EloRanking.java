package eloranking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a simple class for implementing the Elo rating system. 
 */
public class EloRanking<T> {
    
    private Map<T, Integer> ranking;

    /**
     * Constructs an empty ranking.
     */
    public EloRanking() {
        ranking = new HashMap<>();
    }
    
    /**
     * Adds the specified player with a rating of 1000 to the ranking.
     * 
     * @param player the player to be added
     */
    public void add(T player) {
        ranking.put(player, 1000);
    }
    
    /**
     * Adds the specified player with the specified rating to the ranking.
     * 
     * @param player the player to be added
     * @param rating the rating with which the player is to be inserted
     */
    public void add(T player, int rating) {
        ranking.put(player, rating);
    }
    
    /**
     * Updates the ratings of the two specified players in the ranking after
     * a played game.
     * 
     * @param A the first player to be updated
     * @param B the second player to be updated
     * @param score the score of the game
     */
    public void play(T A, T B, double score) {
        int delta = (int) Math.round(32 * (score - this.getProbability(A, B)));
        ranking.put(A, this.getRating(A) + delta);
        ranking.put(B, this.getRating(B) - delta);
    }

    /**
     * Returns the rankings sorted by ratings.
     * 
     * @return the rankings sorted by ratings
     */
    public LinkedHashMap<T, Integer> getRanking() {
        List<T> players = new ArrayList<>(ranking.keySet());
        List<Integer> ratings = new ArrayList<>(ranking.values());
        Collections.sort(ratings, Collections.reverseOrder());
        LinkedHashMap<T, Integer> sortedRanking = new LinkedHashMap<>();
        
        Iterator<Integer> ratingIterator = ratings.iterator();
        while (ratingIterator.hasNext()) {
            int rating = ratingIterator.next();
            Iterator<T> playerIterator = players.iterator();
            
            while (playerIterator.hasNext()) {
                T player = playerIterator.next();
                
                if (ranking.get(player) == rating) {
                    sortedRanking.put(player, rating);
                    playerIterator.remove();
                    break;
                }
            }
        }
        
        return sortedRanking;
    }
    
    /**
     * Returns the rating of the specified player.
     * 
     * @param player the player whose rating is to be returned
     * @return the rating of the specified player
     */
    public int getRating(T player) {
        int rating = ranking.get(player);
        return rating;
    }
    
    /**
     * Returns the probability of A beating B.
     * 
     * @param A the first player
     * @param B the second player
     * @return the probability of A beating B
     */
    public double getProbability(T A, T B) {
        double probability = 1 / (1 + Math.pow(10, ((double) this.getRating(B) - (double) this.getRating(A)) / 400));
        return probability;
    }
}
