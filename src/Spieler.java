import java.util.ArrayList;
import java.util.Collections;

public class Spieler {
    private ArrayList<Karte> k1 = new ArrayList<>();

    private String name;

    public Spieler(String name) {
        this.name = name;
    }

    public void setK1(Karte pk1) {
        this.k1.add(pk1);
    }

    public Karte getKarte(int index) {
        return this.k1.get(index);
    }

    public ArrayList<Karte> getList(){
        return k1;

    }
    public String getName() {
        return name;
    }
    public boolean isEdona(){
        return this.name.equalsIgnoreCase("Edona");
}
}
