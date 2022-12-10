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
        return getTitle() + " by " + getArtist() + " - played " + getStreams() + " times";
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Song) {
            Song s = (Song) o;
            return s.getArtist().equals(getArtist()) && s.getTitle().equals(getTitle());
        }
        return false;
    }

    public boolean isSong(String s, String a) {
        return getTitle().equals(s) && getArtist().getName().equals(a);
    }
    
}
