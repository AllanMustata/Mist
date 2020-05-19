package screen;

public class Game {
    private String title;
    private String genre;
    private double price;
    private String owner;

    public Game(String title, String genre, double price,String owner) {
        this.title = title;
        this.genre=genre;
        this.price=price;
        this.owner=owner;
    }

    public Game() {
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
