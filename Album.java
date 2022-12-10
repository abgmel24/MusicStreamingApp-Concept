import java.util.ArrayList;

public class Album extends ContentCollections{
    public Album() {
        super();
    }

    public Album(String title, ArrayList<Content> list) {
        super(title, list);
    }

    @Override
    public void playCollection() {
        for(int i = 0; i < getList().size(); i++) {
            getList().get(i).playContent();
        }        
    }

    public String toString() {
        String s = getTitle() + " contains:\n";
        for(int i = 0; i < getList().size(); i++) {
            s += "  " + (i+1) + ". " + getList().get(i) + "\n";
        }
        return s;
    }
}
