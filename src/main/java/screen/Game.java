package model;

public class Game {
    private String title;
    private String genre;
    private double price;

    public Game(String title, String genre, double price) {
        this.title = title;
        this.genre=genre;
        this.price=price;
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
