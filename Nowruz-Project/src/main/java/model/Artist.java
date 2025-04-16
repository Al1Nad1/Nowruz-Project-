package model;

public class Artist extends User {
    private String genre;

    public Artist(String username, String password, String genre) {
        super(username, password);
        this.genre = genre;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Artist{" + "username='" + username + "', genre='" + genre + "'}";
    }
}
