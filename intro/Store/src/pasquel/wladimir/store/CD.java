package pasquel.wladimir.store;
import java.util.Date;

public class CD extends Item{
    private Artist artist;
    private Date releasDate;

    public CD(String title, double price, int quantity, Artist artist, Date releasDate) {
        super(title, price, quantity);
        this.getArtist();
        this.getReleasDate();
    }
    public Artist getArtist() {
        return artist;
    }
    public void setArtist(Artist artist) {
        this.artist = artist;
    }
    public Date getReleasDate() {
        return releasDate;
    }
    public void setReleasDate(Date releasDate) {
        this.releasDate = releasDate;
    }
    
    
}
