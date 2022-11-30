public abstract class Content implements Comparable<Content> {

    private String title;
    private Artist artist;
    private int numStreams = 0;

    public Content() {
        title = "default";
        artist = new Artist();
    }

    public Content(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public void incrementStreams() {
        numStreams++;
        System.out.println("Now playing: " + title);
    }

    @Override
    public abstract int compareTo(Content c);
    
    public String getTitle() { 
        return title; 
    }
    public Artist getArtist() { 
        return artist; 
    }
    public int getStreams() { 
        return numStreams; 
    }
}
