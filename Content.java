import java.util.ArrayList;

public abstract class Content implements Comparable<Content> {

    private String title;
    private Artist artist;
    private int numStreams = 0;

    public Content() {
        title = "default";
        artist = new Artist();
        Driver.contentBase.add(this);
    }

    public Content(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
        Driver.contentBase.add(this);
    }

    public void playContent() {
        numStreams++;
        System.out.println("Now playing: " + this);
    }

    @Override
    public int compareTo(Content c) {
        return Integer.valueOf(numStreams).compareTo(c.getStreams());
    }
    
    public String getTitle() { 
        return title; 
    }
    public Artist getArtist() { 
        return artist; 
    }
    public int getStreams() { 
        return numStreams; 
    }

    @Override
    public abstract boolean equals(Object o);

    @Override
    public abstract String toString();
}
