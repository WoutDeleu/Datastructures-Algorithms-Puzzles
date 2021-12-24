import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Speler {
    int id;

    public Set<Character> getOnmogelijkheid() {
        return onmogelijkheid;
    }

    Set<Character> mogelijkheid;
    Set<Character> onmogelijkheid;
    Set<Character> kaarten;


    public Speler(int id, int aantal, int personen, int locaties, int wapens) {
        this.id = id;
        mogelijkheid = new HashSet<>();
        onmogelijkheid = new HashSet<>();
        kaarten = new HashSet<>(aantal);

        for (int i = 1; i<=personen; i++) {
            mogelijkheid.add((char) i );
        }
        char ch = 'A';
        for (char i = 0; i<locaties; i++) {
           mogelijkheid.add(ch);
           ch++;
        }
        ch = 'a';
        for (char i = 0; i<wapens; i++) {
            mogelijkheid.add(ch);
            ch++;
        }
    }

    public int getId() {
        return id;
    }

    public void verwijderMogelijkheid(char ch) {
        mogelijkheid.remove(ch);
    }
    public void voegOnmogelijkheidToe(char ch) {
        onmogelijkheid.add(ch);
        verwijderMogelijkheid(ch);
    }
    public boolean bevatOnmoglijkheid(char ch) {
        return onmogelijkheid.contains(ch);
    }

    public void vergelijk() {
        for(Character c : mogelijkheid) {
            if (!onmogelijkheid.contains(c)) {
                kaarten.add(c);
            }
        }
    }

    public void printKaarten() {
        ArrayList<Character> k = new ArrayList<>(kaarten);
        Collections.sort(k);
        for(Character c: k) {
            System.out.print(c);
        }
        //System.out.println();
    }
    public void printonm() {
        ArrayList<Character> k = new ArrayList<>(kaarten);
        Collections.sort(k);
        for(Character c: k) {
            System.out.print(c);
        }
        System.out.println();
    }
    public void printmog() {
        ArrayList<Character> k = new ArrayList<>(kaarten);
        Collections.sort(k);
        for(Character c: k) {
            System.out.print(c);
        }
        System.out.println();
    }

    public void voegZekerheidToe(char ch) {
        kaarten.add(ch);
        verwijderMogelijkheid(ch);
    }
}