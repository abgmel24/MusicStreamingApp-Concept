import java.util.ArrayList;
import java.util.Random;

public abstract class ContentCollections implements Comparable<ContentCollections>{
    private String title;
    private ArrayList<Content> list;

    public ContentCollections() {
        title = "default";
        list = new ArrayList<Content>();
    }

    public ContentCollections(String title, ArrayList<Content> l) {
        this.title = title;
        list = new ArrayList<Content>();
        for(int i = 0; i < l.size(); i++) {
            list.add(l.get(i));
        }
    }

    public int shuffle() {
        Random randy = new Random();
        return randy.nextInt(list.size());
    }

    public abstract void playCollection();

    @Override
    public abstract String toString();

    public int compareTo(ContentCollections c) {
        return Integer.valueOf(list.size()).compareTo(c.getList().size());
    }

    public ArrayList<Content> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }
    
}
