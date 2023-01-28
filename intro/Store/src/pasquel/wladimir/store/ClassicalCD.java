package pasquel.wladimir.store;
import java.util.Date;

public class ClassicalCD extends Item{
    private String composer;
    private String[] performers = new String[5];
    private String recordingLocation;
    private Date releaseDate;
    private int performer_count = 0;

    

    public ClassicalCD(String title, double price, int quantity, String composer, String[] performers,
            String recordingLocation, Date releaseDate) {
        super(title, price, quantity);
        this.setComposer(composer);
        this.setRecordingLocation(recordingLocation);
        this.setReleaseDate(releaseDate);

        for (int i = 0; i < performers.length; i++) {
            if(performers[i]!=null){addPerformer(performers[i]);}
        }
        this.performers=performers;
    }
    public String getComposer() {
        return composer;
    }
    public void setComposer(String composer) {
        this.composer = composer;
    }
    public String getRecordingLocation() {
        return recordingLocation;
    }
    public void setRecordingLocation(String recordingLocation) {
        this.recordingLocation = recordingLocation;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void addPerformer (String performer){
        if (performer_count >=performers.length)
            System.out.println("The performers array is full! Cannot add "+ performer);
        else {
            performers[performer_count]=performer;
            performer_count++;
        }
    }

    public void showPerformers(){
        if(performer_count==0)
            System.out.println("No performers have been added for this CD");
        for (int i = 0; i < performer_count; i++) {
            System.out.println("Performer: " + performers[i]);
        }
    }
}
