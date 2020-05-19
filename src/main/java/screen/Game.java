package screen;

public class Game {
    private String title;
    private String genre;
    private String devname;
    private double price;
    private int numRatings;
    private double averageRating;

    public Game()
    {

    }

    public Game(String title, String genre, String devname, double price, int numRatings, double averageRating) {
        this.title = title;
        this.genre=genre;
        this.devname = devname;
        this.price=price;
        this.numRatings = numRatings;
        this.averageRating = averageRating;
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
}
