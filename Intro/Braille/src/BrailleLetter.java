import java.util.Arrays;
import java.util.Objects;

public class BrailleLetter {
    char [][] letter;

    public BrailleLetter() {
        letter = new char[3][2];
    }
    public BrailleLetter(char [][] letter) {
        this.letter = letter;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i<3; i++) {
            for (int j =0; j<2; j ++) {
                str.append(letter[i][j] + " ");
            }
            str.append("\n");
        }
        return str.toString();
    }

    @Override
    public boolean equals(Object ob)
    {
        if (ob == this) {
            return true;
        }

        if (ob == null || ob.getClass() != getClass()) {
            return false;
        }

        BrailleLetter p = (BrailleLetter) ob;
        return Objects.equals(letter, p.letter) ;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(letter);
    }


}
