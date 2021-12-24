import java.util.ArrayList;

public class Budgetten {
    private ArrayList<Budget> budgetten;

    public Budgetten(int b) {
        budgetten = new ArrayList<Budget>(b);
    }
    public void add(Budget b) {
        budgetten.add(b);
    }
    public void schrijfOplossing(int a) {
        int teller = 0;
        for(Budget b : budgetten) {
            if(b.isGeldig()) teller++;
        }
        if(teller == 0) System.out.print(a + " " + "GEEN");
        else {
            for(Budget b : budgetten) {
                System.out.print(a);
                if(b.isGeldig()) b.schrijf();
            }
        }
    }

    public void setGeldig(int i, boolean geldig) {
        budgetten.get(i).setGeldig(geldig);
    }

    public int get(int i) {
        return budgetten.get(i).getBudget();
    }
}
