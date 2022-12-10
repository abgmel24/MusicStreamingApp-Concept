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
        return getTitle() + " Episode: " + episodeNum  + " - played " + getStreams() + " times";
    }

    public boolean isPodcast(String s, String a, int num) {
        return getTitle().equals(s) && getArtist().getName().equals(a) && episodeNum == num;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Podcast) {
            Podcast p = (Podcast) o;
            return p.getArtist().equals(getArtist()) && p.getTitle().equals(getTitle()) && p.getEpisodeNum()==episodeNum;
        }
        return false;
    }
    
}
