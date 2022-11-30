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
        //TODO Deal with exceptions
        if(c instanceof Podcast) {
            Podcast s = (Podcast) c;
            if(s.getStreams() > super.getStreams()) {
                return 1;
            }
            else if(s.getStreams() < super.getStreams()) {
                return -1;
            }
            else {
                return 0;
            }
        }
        return 0;
    }

    public int getEpisodeNum() {
        return episodeNum;
    }
    
}
