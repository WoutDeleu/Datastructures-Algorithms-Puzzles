import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Speler {
    private int id;
    private boolean isVolledig;
    private int aantal;

    private Set<Character> mogelijkheid;
    private Set<Character> onmogelijkheid;
    private Set<Character> zekerheid;


    public Speler(int id, int aantal, int personen, int locaties, int wapens) {
        this.id = id;
        mogelijkheid = new HashSet<>();
        onmogelijkheid = new HashSet<>();
        zekerheid = new HashSet<>();
        this.aantal = aantal;

        char[] per = "123456789".toCharArray();
        char[] pl = "ABCDEFGHI".toCharArray();
        char[] wa = "abcdefghi".toCharArray();
        for (int i = 0; i<personen; i++) mogelijkheid.add(per[i]);
        for (char i = 0; i<locaties; i++) mogelijkheid.add(pl[i]);
        for (char i = 0; i<wapens; i++) mogelijkheid.add(wa[i]);
    }

    public int getId() { return id; }
    public boolean getIsvolledig() { return  isVolledig; }
    public void verwijderMogelijkheid(char ch) { mogelijkheid.remove(ch); }
    public boolean bevatZekerheid(char ch) { return zekerheid.contains(ch); }
    public boolean bevatOnmoglijkheid(char ch) { return onmogelijkheid.contains(ch); }
    public boolean bevatMoglijkheid(char ch) { return mogelijkheid.contains(ch); }

    public void voegOnmogelijkheidToe(char ch) {
        onmogelijkheid.add(ch);
        verwijderMogelijkheid(ch);
    }
    public void voegZekerheidToe(char ch) {
        zekerheid.add(ch);
        verwijderMogelijkheid(ch);
    }

    public void eindVergelijking() {
        for(Character c : mogelijkheid) {
            if (!onmogelijkheid.contains(c)) {
                zekerheid.add(c);
            }
        }
    }

    public void printKaarten() {
        ArrayList<Character> k = new ArrayList<>(zekerheid);
        Collections.sort(k);
        for(Character c: k) {
            System.out.print(c);
        }
    }


    public boolean controlleerVolledigheid() {
        int counter = 0;
        for (Character c: zekerheid) {
            counter++;
        }
        if (counter == zekerheid.size()) isVolledig = true;
        return isVolledig;
    }


    public void vergelijk(Speler sp1, Speler sp2, Speler sp3) {
        Set<Character> temp = new HashSet<>();
        for(char c : this.mogelijkheid) {
            if(sp1.onmogelijkheid.contains(c) && sp2.onmogelijkheid.contains(c) && sp3.onmogelijkheid.contains(c)) {
                temp.add(c);
            }
        }
        for(Character c: temp) {
            voegZekerheidToe(c);
        }
        temp.clear();

        //vergelijken van mogelijkheden
        //Bij toevoeging gevonden antwoorden (geen dubbele)
    }
}