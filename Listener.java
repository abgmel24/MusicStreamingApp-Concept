import java.util.ArrayList;

public class Listener extends Account {
    private ArrayList<Content> favorites;

    public Listener() {
        super();
        favorites = new ArrayList<Content>();
    }

    public Listener(String name) {
        super(name);
        favorites = new ArrayList<Content>();
    }

    public void favorite(Content c) {
        favorites.add(c);
    }

    public ArrayList<Content> getFavorites() {
        return favorites;
    }
}
