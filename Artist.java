public class Artist extends Account {
    public Artist() {
        super();
    }

    public Artist(String name) {
        super(name);
    }

    public boolean equals(Artist other) {
        if(other instanceof Artist) {
            return getName().equals(other.getName());
        }
        return false;
    }

    public String toString() {
        return super.toString();
    }
}
