import java.util.ArrayList;

public class Playlist extends ContentCollections {

    public Playlist() {
        super();
    }

    public Playlist(String name, ArrayList<Content> list) {
        super(name, list);
    }

    public void addContent(Content c) {
        super.getList().add(c);
    }

    public void removeContent(Content c) {
        super.getList().remove(c);
    }

    @Override
    public void playCollection() {
        boolean[] arr = new boolean[getList().size()];
        int index;
        int count = getList().size();
        while(count > 0) {
            index = this.shuffle();
            if(!arr[index]) {
                getList().get(index).playContent();
                arr[index] = true;
                count--;
            }
        }
    }

    public String toString() {
        if(getList().size() == 0) {
            return getTitle() + " is empty.\n";
        }
        String s = getTitle() + " contains:\n";
        for(int i = 0; i < getList().size(); i++) {
            s += "  " + (i+1) + ". " + getList().get(i) + " \n";
        }
        return s;
    }
}
