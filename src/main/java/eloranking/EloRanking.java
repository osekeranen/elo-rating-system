package eloranking;

import java.util.HashMap;

public class EloRanking<T> {
    
    private HashMap<T, Integer> ranking;

    public EloRanking() {
        ranking = new HashMap<>();
    }
    
    public void add(T t) {
        ranking.put(t, 1000);
    }
    
    public void add(T t, int rating) {
        ranking.put(t, rating);
    }
    
    public void play(T A, T B, double score) {
        int delta = (int) Math.round(32 * (score - this.getProbability(A, B)));
        ranking.put(A, this.getRating(A) + delta);
        ranking.put(B, this.getRating(B) - delta);
    }
    
    public int getRating(T t) {
        int rating = ranking.get(t);
        return rating;
    }
    
    public double getProbability(T A, T B) {
        double probability = 1 / (1 + Math.pow(10, ((double) this.getRating(B) - (double) this.getRating(A)) / 400));
        return probability;
    }
}
