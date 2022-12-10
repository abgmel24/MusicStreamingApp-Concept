import java.util.ArrayList;
import java.util.Collections;

public class Listener extends Account {
    private Playlist favorites;

    public Listener() {
        super();
        favorites = new Playlist("Favorites", new ArrayList<Content>());
    }

    public Listener(String name) {
        super(name);
        favorites = new Playlist("Favorites", new ArrayList<Content>());
    }

    public void favorite(Content c) {
        favorites.addContent(c);
    }

    public Playlist getFavorites() {

        Collections.sort(favorites.getList());
        return favorites;
    }

    public String toString() {
        return super.toString();
    }
}
