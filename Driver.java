import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    private static final String[] menuString = {
        "1 - Create a Listener account",
        "2 - List all Playlists and Albums and their content",
        "3 - Add content to an existing playlist",
        "4 - Shuffle an existing Playlist or listen to an Album",
        "5 - Add a song or podcast to favorites",
        "6 - Export all of a listener’s favorites out to a file in ASCENDING ORDER by times streamed",
        "7 - Exit",
    };

    public static ArrayList<Content> contentBase = new ArrayList<Content>();
    private static ArrayList<ContentCollections> collectionsBase = new ArrayList<ContentCollections>();
    private static Listener listener = null;
    public static void main(String[] args) {
        createCollectionsBase();

        Scanner scnr = new Scanner(System.in);
        String input = "";
        boolean condition = true;

        System.out.println("\n\n\nWelcome!");
        while(condition) {
            printMenu();
            input = scnr.nextLine();
            switch(input) {
                case "1":
                    caseOne(input, scnr);
                    break;
                case "2":
                    caseTwo(input, scnr);
                    break;

                case "3":
                    caseThree(input, scnr);
                    break;

                case "4":
                    caseFour(input, scnr);
                    break;

                case "5":
                    caseFive(input, scnr);
                    break;

                case "6":
                    caseSix(input, scnr);
                    break;

                case "7":
                    condition = false;
                    System.out.println("Thanks for using the app!");
                    break;

                default:
                    System.out.println("Invaid Input");
            }
            System.out.println();
        }
        scnr.close();
    }

    public static void caseOne(String input, Scanner scnr) {
        System.out.print("Enter the name of your listener account (Case Sensitive): ");   
        input = scnr.nextLine();
        listener = new Listener(input);
    }

    public static void caseTwo(String input, Scanner scnr) {
        printAlbums();
        printPlaylists();
        System.out.println("Press any key to return to the main menu");
        scnr.nextLine();
        System.out.println("Returning to main menu\n.\n.\n.");
    }

    public static void caseThree(String input, Scanner scnr) {
        System.out.println("Enter the name (Case Sensitive) of the playlist you want to add to: ");
        input = scnr.nextLine();

        Playlist p =  findPlaylist(input);
        if(p == null) {
            System.out.println("That playlist does not exist.");
            System.out.println("Would you like to create one with that name? (Yes/No)");
            String answer = scnr.nextLine();
            switch(answer.toLowerCase()) {
                case "yes":
                    collectionsBase.add(new Playlist(input, new ArrayList<Content>()));
                    System.out.println("Playlist created!");
                    break;
                case "no":
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            System.out.println("Returning to main menu\n.\n.\n.");
        } else {
            System.out.println("Enter if you want to add a song or podcast. You can type s for song and p for podcast");
            String answer = scnr.nextLine();
            if(answer.equals("p")) {
                answer = "podcast";
            } else if(answer.equals("s")) {
                answer = "song";
            }
            String n;
            String a;
            switch(answer) {
                case "podcast":
                    System.out.println("Enter the name of the podcast (Case Sensitive) you want to add: ");
                    n = scnr.nextLine();
                    System.out.println("Enter the artist (Case Sensitive) of this podcast: ");
                    a = scnr.nextLine();
                    System.out.println("Enter the episode number of the podcast: ");
                    int num;
                    try {
                        num = Integer.parseInt(scnr.nextLine());
                    } catch(IllegalArgumentException e) {
                        System.out.println("Invalid Input");
                        System.out.println("Returning to main menu\n.\n.\n.");
                        break;
                    }
                    Podcast pod = getPodcastFromBase(n, a, num);
                    if(pod == null) {
                        p.addContent(new Podcast(n, new Artist(a), num));
                    } else {
                        p.addContent(pod);
                    }
                    break;
                case "song":
                    System.out.println("Enter the name of the song (Case Sensitive) you want to add: ");
                    n = scnr.nextLine();
                    System.out.println("Enter the artist of this song (Case Sensitive): ");
                    a = scnr.nextLine();

                    Song s = getSongFromBase(n, a);
                    if(s == null) {
                        p.addContent(new Song(n, new Artist(a)));
                    } else {
                        p.addContent(s);
                    }
                    break;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("Returning to main menu\n.\n.\n.");
            }
        }
    }

    public static void caseFour(String input, Scanner scnr) {
        System.out.println("Do you want to listen to a playlist or an album?");
        System.out.println("You can enter 'p' for playlist and 'a' for album.");
        input = scnr.nextLine();
        if(input.equals("p")) {
            input = "playlist";
        } else if(input.equals("a")) {
            input = "album";
        }
        switch(input.toLowerCase()) {
            case "playlist":
                System.out.println("Enter the name of the playlist (Case Sensitive) you want to listen to on shuffle: ");
                input = scnr.nextLine();
                findPlaylist(input).playCollection();
                break;
            case "album":
                System.out.println("Enter the name of the album (Case Sensitive) you want to listen to: ");
                input = scnr.nextLine();
                findAlbum(input).playCollection();
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println("Returning to main menu\n.\n.\n.");
        }
    }

    public static void caseFive(String input, Scanner scnr) {
        if(listener == null) {
            System.out.println("Please make a listener account first.");
            return;
        }

        System.out.println("Enter if you want to add a song or podcast. You can type s for song and p for podcast");
        String answer = scnr.nextLine();
        if(answer.equals("p")) {
            answer = "podcast";
        } else if(answer.equals("s")) {
            answer = "song";
        }
        String n;
        String a;
        switch(answer) {
            case "podcast":
                System.out.println("Enter the name of the podcast (Case Sensitive) you want to add: ");
                n = scnr.nextLine();
                System.out.println("Enter the artist of this podcast (Case Sensitive): ");
                a = scnr.nextLine();
                System.out.println("Enter the episode number of the podcast: ");
                int num;
                try {
                    num = Integer.parseInt(scnr.nextLine());
                } catch(IllegalArgumentException e) {
                    System.out.println("Invalid Input");
                    System.out.println("Returning to main menu\n.\n.\n.");
                    break;
                }
                Podcast pod = getPodcastFromBase(n, a, num);
                if(pod == null) {
                    listener.getFavorites().addContent(new Podcast(n, new Artist(a), num));
                } else {
                    listener.getFavorites().addContent(pod);
                }
                break;
            case "song":
                System.out.println("Enter the name of the song (Case Sensitive) you want to add: ");
                n = scnr.nextLine();
                System.out.println("Enter the artist of this song (Case Sensitive): ");
                a = scnr.nextLine();
                
                Song s = getSongFromBase(n, a);
                if(s == null) {
                    listener.getFavorites().addContent(new Song(n, new Artist(a)));
                } else {
                    listener.getFavorites().addContent(s);
                }
                break;
            default:
                System.out.println("Invalid Input");
                System.out.println("Returning to main menu\n.\n.\n.");
        }
    }

    public static void caseSix(String input, Scanner scnr) {
        System.out.println("Enter the name of the file (Case Sensitive) you want for your favorites: ");
        System.out.println("DISCLAIMER: If you enter the name of a file that already exists, it will be rewritten.");
        input = scnr.nextLine();

        try {
            PrintWriter writer = new PrintWriter(new FileWriter(input + ".txt"));
            writer.println(listener.getFavorites().toString());
            System.out.println("File Created!");
            writer.close();
        } catch(IOException e) {
            System.out.println("Something went wrong with creating your file.");
            System.out.println("Returning to main menu\n.\n.\n.");
        } catch(NullPointerException e) {
            System.out.println("You need to create a listener account before writing favorites.");
            System.out.println("Returning to main menu\n.\n.\n.");
        }
    }

    public static Playlist findPlaylist(String name) {
        for(int i = 0; i < collectionsBase.size(); i++) {
            if(collectionsBase.get(i) instanceof Playlist && collectionsBase.get(i).getTitle().equals(name)) {
                return (Playlist) collectionsBase.get(i);
            }
        }
        return null;
    }

    public static Album findAlbum(String name) {
        for(int i = 0; i < collectionsBase.size(); i++) {
            if(collectionsBase.get(i) instanceof Album && collectionsBase.get(i).getTitle().equals(name)) {
                return (Album) collectionsBase.get(i);
            }
        }
        return null;
    }

    public static void printAlbums() {
        for(int i = 0; i < collectionsBase.size(); i++) {
            if(collectionsBase.get(i) instanceof Album) {
                System.out.println(collectionsBase.get(i));
            }
        }
    }

    public static void printPlaylists() {
        for(int i = 0; i < collectionsBase.size(); i++) {
            if(collectionsBase.get(i) instanceof Playlist) {
                System.out.println(collectionsBase.get(i));
            }
        }
    }

    public static void createCollectionsBase() {
        ArrayList<Content> list = new ArrayList<Content>();

        Artist aINK = new Artist("Ice Nine Kills");
        list.add(new Song("IT is the end", aINK));
        list.add(new Song("Stabbing in the Dark", aINK));
        list.add(new Song("The American Nightmare", aINK));
        collectionsBase.add(new Album("Silver Scream", list));

        list = new ArrayList<Content>();
        Artist aMIW = new Artist("Motionless in White");
        list.add(new Song("Masterpiece", aMIW));
        list.add(new Song("Eternally Yours", aMIW));
        list.add(new Song("Somebody Told Me", aMIW));
        collectionsBase.add(new Album("Graveyard Shift", list));

        list = new ArrayList<Content>();
        Artist aATREYU = new Artist("Atreyu");
        list.add(new Song("Save Us", aATREYU));
        list.add(new Song("The Time is Now", aATREYU));
        list.add(new Song("Warrior", aATREYU));
        collectionsBase.add(new Album("In Our Wake", list));

        list = new ArrayList<Content>();
        Artist wt = new Artist("Wrestletalk");
        Playlist playlist = new Playlist("Generic Playlist", list);
        Podcast wtPod;
        for(int i = 1; i <= 4; i++) {
            wtPod = new Podcast("Wrestetalk Podcast", wt, i);
            playlist.addContent(wtPod);
        }
        collectionsBase.add(playlist);
    }

    public static void printMenu() {
        System.out.println("Type a number associated with what you would like to do!");
        if(listener != null) {
            System.out.println("You are currently logged into account " + listener + ".");
        }
        for(int i = 0; i < menuString.length; i++) {
            System.out.println(menuString[i]);
        }
        System.out.println();
    }

    public static Song getSongFromBase(String n, String a) {
        Song s;
        for(int i = 0; i < contentBase.size(); i++) {
            if(contentBase.get(i) instanceof Song) {
                s = (Song) contentBase.get(i);
                if(s.isSong(n, a)) {
                    return s;
                }
            }
        }
        return null;
    }

    public static Podcast getPodcastFromBase(String n, String a, int num) {
        Podcast p;
        for(int i = 0; i < contentBase.size(); i++) {
            if(contentBase.get(i) instanceof Podcast) {
                p = (Podcast) contentBase.get(i);
                if(p.isPodcast(n, a, num)) {
                    return p;
                }
            }
        }
        return null;
    }
}
