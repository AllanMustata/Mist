package screen;

import java.util.Random;

public class Game {
    private String title;
    private String genre;
    private double price;
    private String owner;
    private int copies_sold;
    private double rating;
    private Random copies_dice=new Random();
    private Random rating_dice=new Random();

    public Game(String title, String genre, double price,String owner) {
        this.title = title;
        this.genre=genre;
        this.price=price;
        this.owner=owner;

    }

    public Game() {
        this.copies_sold=copies_dice.nextInt(20)+1;
        this.rating=rating_dice.nextInt(6);
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

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
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

    public void setPrice(double price) {
        this.price = price;
    }
}
