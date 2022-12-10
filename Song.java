public class Song extends Content{

    public Song() {
        super();
    }

    public Song(String title, Artist artist) {
        super(title, artist);
    }

    @Override
    public int compareTo(Content c) {
        if(c instanceof Song) {
            return super.compareTo(c);
        }
        return -1;
    }

    @Override
    public String toString() {
        return getTitle() + " by " + getArtist();
    }
    
}
