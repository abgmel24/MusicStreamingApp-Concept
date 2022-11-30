public class Song extends Content{

    public Song() {
        super();
    }

    public Song(String title, Artist artist) {
        super(title, artist);
    }

    @Override
    public int compareTo(Content c) {
        //TODO Deal with exceptions
        if(c instanceof Song) {
            Song s = (Song) c;
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
    
}
