import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

    public static final String[] menuString = {
        "1 - Create a listener account",
        "2 - List all Playlists and Albums and their content",
        "3 - Add songs to an existing playlist",
        "4 - Shuffle an existing Playlist or listen to an Album",
        "5 - Add a song or podcast to favorites",
        "6 - Export all of a listener’s favorites out to a file in ascending order by times streamed",
        "7 - Exit",
    };
    public static ArrayList<ContentCollections> musicBase = new ArrayList<ContentCollections>();

    public static void main(String[] args) {
        createMusicBase();

        Scanner scnr = new Scanner(System.in);
        String input = "";
        Listener listener = null;
        boolean condition = true;

        System.out.println("Welcome!");
        while(condition) {
            printMenu();
            input = scnr.nextLine();
            switch(input) {
                case "1":
                    System.out.print("Enter the name of your listener account: ");   
                    input = scnr.nextLine();
                    listener = new Listener(input);
                    break;

                case "2":
                    printAlbums();
                    printPlaylists();
                    break;

                case "3":
                    System.out.println("Enter the name of the playlist you want to add to: ");
                    input = scnr.nextLine();

                    Playlist p =  findPlaylist(input);
                    if(p == null) {
                        System.out.println("That playlist does not exist.");
                        System.out.println(" Would you like to create one with that name? (Yes/No)\n");
                        String answer = scnr.nextLine();
                        switch(answer.toLowerCase()) {
                            case "yes":
                                musicBase.add(new Playlist(input, new ArrayList<Content>()));
                                System.out.println("Playlist created!");
                                break;
                            default:
                                System.out.println("Invalid Input");
                        }
                        System.out.println("Returning to main menu\n.\n.\n.");
                    }

                    System.out.println("Enter the name of the song you want to add: ");
                    String t = scnr.nextLine();
                    System.out.println("Enter the artist of this song: ");
                    String a = scnr.nextLine();
                    p.addContent(new Song(t, new Artist(a)));
                    break;

                case "4":
                    System.out.println("Do you want to listen to a playlist or an album? ");
                    input = scnr.nextLine();
                    switch(input.toLowerCase()) {
                        case "playlist":
                            System.out.println("Enter the name of the playlist you want to listen to on shuffle: ");
                            input = scnr.nextLine();
                            findPlaylist(input).playCollection();
                            break;
                        case "album":
                            System.out.println("Enter the name of the album you want to listen to: ");
                            input = scnr.nextLine();
                            findAlbum(input).playCollection();
                            break;
                        default:
                            System.out.println("Invalid Input");
                            System.out.println("Returning to main menu\n.\n.\n.");
                    }
                    break;

                case "5":
                    if(listener == null) {
                        System.out.println("Please make a listener account first.");
                        break;
                    }
                    System.out.println("Enter the name of the song you want to favorite: ");
                    String n = scnr.nextLine();
                    System.out.println("Enter the artist of the song you want to favorite: ");
                    input = scnr.nextLine();
                    listener.getFavorites().addContent(new Song(n, new Artist(input)));
                    break;

                case "6":
                    
                    break;

                case "7":
                    condition = false;
                    System.out.println("Thanks for using the app!");
                    break;

                default:
                    System.out.println("Invaid Input");

            }
        }
        scnr.close();
        /*
         * Create a listener account
         * List all Playlists and Albums and their content
         * Add songs to an existing playlist
         * Shuffle an existing Playlist or listen to an Album
         * Add a song or podcast to favorites
         * Export all of a listener’s favorites out to a file in ascending order by times streamed
         * Exit
         */
    }

    public static Playlist findPlaylist(String name) {
        for(int i = 0; i < musicBase.size(); i++) {
            if(musicBase.get(i) instanceof Playlist && musicBase.get(i).getTitle().equals(name)) {
                return (Playlist) musicBase.get(i);
            }
        }
        return null;
    }

    public static Album findAlbum(String name) {
        for(int i = 0; i < musicBase.size(); i++) {
            if(musicBase.get(i) instanceof Album && musicBase.get(i).getTitle().equals(name)) {
                return (Album) musicBase.get(i);
            }
        }
        return null;
    }

    public static void printAlbums() {
        for(int i = 0; i < musicBase.size(); i++) {
            if(musicBase.get(i) instanceof Album) {
                System.out.println(musicBase.get(i));
            }
        }
    }

    public static void printPlaylists() {
        for(int i = 0; i < musicBase.size(); i++) {
            if(musicBase.get(i) instanceof Playlist) {
                System.out.println(musicBase.get(i));
            }
        }
    }

    public static void createMusicBase() {
        ArrayList<Content> list = new ArrayList<Content>();

        Artist aINK = new Artist("Ice Nine Kills");
        Song sIITE = new Song("IT is the end", aINK);
        Song sSITD = new Song("Stabbing in the Dark", aINK);
        Song sTAN = new Song("The American Nightmare", aINK);
        list.add(sIITE);
        list.add(sSITD);
        list.add(sTAN);
        Album alSilverScream = new Album("Silver Scream", list);
        musicBase.add(alSilverScream);

        list = new ArrayList<Content>();
        Artist aMIW = new Artist("Motionless in White");
        Song sM = new Song("Masterpiece", aMIW);
        Song sEY = new Song("Eternally Yours", aMIW);
        Song sSTM = new Song("Somebody Told Me", aMIW);
        list.add(sM);
        list.add(sEY);
        list.add(sSTM);
        Album alGraveyardShift = new Album("Graveyard Shift", list);
        musicBase.add(alGraveyardShift);

        list = new ArrayList<Content>();
        Artist aATREYU = new Artist("Atreyu");
        Song sSU = new Song("Save Us", aATREYU);
        Song sTTIN = new Song("The Time is Now", aATREYU);
        Song sW = new Song("Warrior", aATREYU);
        list.add(sSU);
        list.add(sTTIN);
        list.add(sW);
        Album alInOurWake = new Album("In Our Wake", list);
        musicBase.add(alInOurWake);

        list = new ArrayList<Content>();
        Artist wt = new Artist("Wrestletalk");
        Playlist playlist = new Playlist("x", list);
        Podcast wtPod;
        for(int i = 1; i <= 4; i++) {
            wtPod = new Podcast("Wrestetalk Podcast", wt, i);
            playlist.addContent(wtPod);
        }
        playlist.addContent(sIITE);
        musicBase.add(playlist);
    }

    public static void printMenu() {
        System.out.println("Type a number associated with what you would like to do!");
        for(int i = 0; i < menuString.length; i++) {
            System.out.println(menuString[i]);
        }
        System.out.println();
    }
}
