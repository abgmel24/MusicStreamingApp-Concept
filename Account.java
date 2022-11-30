public class Account {
    private String name;

    public Account() {
        name = "default";
    }

    public Account(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
