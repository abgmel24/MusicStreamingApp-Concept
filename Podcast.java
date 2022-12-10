public class Podcast extends Content {

    private int episodeNum;

    public Podcast() {
        super();
        episodeNum = 0;
    }

    public Podcast(String title, Artist artist, int episodeNum) {
        super(title, artist);
        this.episodeNum = episodeNum;
    }

    @Override
    public int compareTo(Content c) {
        if(c instanceof Podcast) {
            return super.compareTo(c);
        }
        return -1;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }

    public String toString() {
        return getTitle() + " Episode: " + episodeNum;
    }
    
}
