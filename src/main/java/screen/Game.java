package screen;

import java.util.Random;

public class Game {
    private String title;
    private String genre;
    private String devname;
    private double price;
    private int numRatings;
    private double averageRating;
    private String owner;
    private int copies_sold;
    private double rating;
    private Random copies_dice=new Random();
    private Random rating_dice=new Random();

    public Game()
    {
        this.copies_sold=copies_dice.nextInt(20)+1;
        this.rating=rating_dice.nextInt(6);
    }

    public Game(String title, String genre, String devname, double price, int numRatings, double averageRating, String owner) {
        this.title = title;
        this.genre=genre;
        this.devname = devname;
        this.price=price;
        this.numRatings = numRatings;
        this.averageRating = averageRating;
        this.owner = owner;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getDevname() {
        return devname;
    }

    public double getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDevname(String devname) {
        this.devname = devname;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCopies_sold(int copies_sold) {
        this.copies_sold = copies_sold;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCopies_sold() {
        return copies_sold;
    }

    public double getRating() {
        return rating;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }
}
