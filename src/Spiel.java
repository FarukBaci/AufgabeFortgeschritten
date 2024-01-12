
import javax.swing.*;
import java.util.*;
import java.util.stream.Stream;

public class Spiel {

    static final EnumSet Set2 = EnumSet.allOf(Karte.class);
    Scanner SC = new Scanner(System.in);
    public void Spielen(ArrayList<Karte>k1) {
        //boolean istDran = true;
        System.out.println("Hallo. \nSchön, dass ihr Quartett spielen wollt.");
        System.out.println("Wie viele Spieler wollen spielen?");
        int anzahlSpieler = -1;
        do{
            try {
                anzahlSpieler = new Scanner(System.in).nextInt();
                if(anzahlSpieler <= 1 ) System.out.println("Es müssen mindestens 2 Personen spielen!");
            } catch (InputMismatchException e) {
                System.out.println("Bitte gebe eine Zahl ein!");
            }

        } while(anzahlSpieler <= 1);

        Spieler[] alleSpieler = new Spieler[anzahlSpieler];
        int i = 0;
        for (Spieler spieler : alleSpieler) {
            System.out.println("Nächster Spieler bitte Name eingeben");
            String namee = new Scanner(System.in).next();
            if (namee.equalsIgnoreCase("Edona")) {              // Es wird sichergestellt, dass Edona nicht spielen darf
                System.out.println("Du darfst nicht spielen >:(     ");
                System.out.println("Spiel wird beendet!!!!");
                System.err.println("SCHADE EDDYG");
                System.exit(1);
            } else {
                spieler = new Spieler(namee);                                   //Alle anderen Namen werden in die Liste der Spieler eingefügt
                alleSpieler[i] = spieler;
                i++;
            }
        }
        int anzahlKarten = k1.size() - k1.size() % anzahlSpieler; //Deckgröße wird angepasst auf Anzahl der Spieler
        int kartePK = anzahlKarten / anzahlSpieler;             //Anzahl der Karten pro Person
        for (Spieler spielerVerteilen: alleSpieler) {           //Die Karten werden den Spielern verteilt
            for (int x = 0; x < kartePK; x++) {
                spielerVerteilen.getList().add(k1.get(0));
                k1.removeFirst();
            }
        }

        Spieler aktiverSpieler = alleSpieler[0];
        Spieler gewinner = aktiverSpieler;
        System.out.println("Herzlich Willkommen zum Auto-Quartett!!!");
        System.out.println("Ihre Regeln:" + "\n" + "Der Spieler, der an der Reihe ist, soll ein Attribut seiner aktuellen Karte wählen." + "\n" + "Danach werden die obersten Karte der anderen Spieler aufgedeckt\n" +
                "und die Verlierer geben ihre Karten ab." + "\n" + "Bitte wähle 'p' für PS, 'g' für Gewicht, 'b' für Baujahr und 'l' für Länge.");
        System.out.println("Für alle Werte gilt: Je höher desto besser!");
        System.out.println(aktiverSpieler.getName() + " darf nun beginnen...");
        System.out.println("--------------------------------------");

        int Spielzug = 0; //Speicher für Rundenanzahl

        System.out.println("");
        System.out.println("Runde " + (Spielzug + 1) + ": " + aktiverSpieler.getName() + " ist am Zug.");
        System.out.println("Dein Auto: " + aktiverSpieler.getKarte(0).name());
        System.out.print(aktiverSpieler.getKarte(0));
        System.out.println("");
        boolean spielLaeuft = true;
        while (spielLaeuft) {
            for (Spieler spielerHatKarten : alleSpieler) {
                if (spielerHatKarten.getList().size() == anzahlKarten) {
                    spielLaeuft = false;
                }
            }
            Spielzug++;

            boolean korrekt = true; //Flag für erneute Eingabe bei fehlerhafter Eingabe
            while (korrekt) {
                //String Attribut = "p";   //Hier können wir ein Spiel simulieren--------------
                String Attribut = SC.next();    //Das hier muss dafür zum Kommentar geändert werden
                if (Attribut.equals("p") || Attribut.equals("g") || Attribut.equals("b") || Attribut.equals("l")) {
                    for (Spieler spieler : alleSpieler) {
                        if (spieler == aktiverSpieler) continue;
                        if(!spieler.getList().isEmpty()) {
                            System.out.println("");
                            System.out.println("Der Gegner " + spieler.getName() + " hatte die Karte: " + spieler.getKarte(0).name() + ": " + spieler.getKarte(0));
                        }
                        else{
                            System.out.println("");
                            System.out.println("Der Gegner " + spieler.getName() + " hat keine Karte.");
                        }
                    }
                        gewinner = findGewinner(alleSpieler, Attribut);
                        aktiverSpieler = gewinner;
                    if(gewinner == null){   //Falls alle Spieler den gleichen Wert haben, legen alle Spieler ihre Karten hinten ans Deck und der aktuelle Spieler ist weiter am Zug
                        for(Spieler spieler : alleSpieler){
                            spieler.getList().add(spieler.getKarte(0));
                            spieler.getList().removeFirst();
                            System.out.println("Es gab keinen Gewinner!");
                            System.out.println("");
                        }
                    }
                    else {
                        for (Spieler spieler : alleSpieler) {
                            if (spieler == gewinner) continue;
                            if (!spieler.getList().isEmpty()) {
                                gewinner.getList().add(spieler.getList().get(0));
                                spieler.getList().removeFirst();
                                gewinner.getList().add(gewinner.getList().getFirst());
                                gewinner.getList().removeFirst();
                            }
                        }

                        System.out.println(gewinner.getName() + " hat die Runde gewonnen.");
                        System.out.println(gewinner.getName() + " ist nun dran");
                        System.out.println("--------------------------------------");
                        System.out.println("Runde " + (Spielzug + 1) + ": " + aktiverSpieler.getName() + " ist am Zug.");
                        System.out.println("Dein Auto: " + aktiverSpieler.getKarte(0).name());
                        System.out.print(aktiverSpieler.getKarte(0));      //Hier breakpoint um zu zeigen, dass es funktioniert!!!!!
                        aktiverSpieler = gewinner;
                        Spielzug++;

                    }

                    korrekt = false;
                } else {
                    System.out.println("Falsche Eingabe.");
                    System.out.println("Bitte wähle 'p' für PS, 'g' für Gewicht, 'b' für Baujahr und 'l' für Länge.");
                    System.out.println("--------------------------------------");
                }

            }
        }
            System.out.println();
            System.out.println("Herzlichen Glückwunsch "+gewinner.getName() + ", du hast gewonnen!!");
            System.out.println("Hier ist deine Trophäe.");
            String trophyEmoji = "\uD83C\uDFC6";
            System.out.println(trophyEmoji);

            //Unwichtig, nur für das ANzeigen des Pokals in einem Fenster
            JFrame frame = new JFrame("Trophy Emoji");

            // Setzen der Größe des Fensters
            frame.setSize(300, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Erstellen eines Labels mit dem Pokal-Emoji
            JLabel label = new JLabel("\uD83C\uDFC6", SwingConstants.CENTER);

            // Vergrößern der Schriftgröße für das Emoji
            label.setFont(label.getFont().deriveFont(100f));

             // Hinzufügen des Labels zum Fenster
            frame.add(label);

            // Anzeigen des Fensters
            frame.setVisible(true);

        }

    public static Spieler findGewinner(Spieler[] spielerArray, String attribut) {
        return Arrays.stream(spielerArray)
                .filter(s -> !s.getList().isEmpty())                    // Filtere Spieler ohne Karten   --> Methode nimmt ein Lambda (eine kurze Funktion) entgegen.
                // s vom Typ Spieler bleibt nur im Stream wenn dieser noch karten besitzt, damit nur mit denen verglichen wird, die auch Karten besitzen
                .max(Comparator.comparing(s -> s.getList().get(0).getKartenWert(attribut)))
                .orElse(null);                                  // Falls kein Gewinner gefunden wurde, weil alle Karten den selben wert haben
    }
    public static Spieler findGewinner2(Spieler[] spielerArray, String attribut) {  //funktioniert nur für eine Gerade anzahl an spielern und für 3 Spieler.
        // Hierbei wird der case behandelt, wobei nur bei gewicht der niedrigste Wert gewinnen würde, es wird also nur für Gewicht der MIN Wert gesucht
        Comparator<Spieler> comparator = Comparator.comparing(s -> s.getList().get(0).getKartenWert(attribut));

        Stream<Spieler> spielerStream = Arrays.stream(spielerArray)
                .filter(s -> !s.getList().isEmpty());

        if ("g".equals(attribut)) {
            // Verwende den minimalen Wert für das Attribut "g"
            return spielerStream
                    .min(comparator)
                    .orElse(null);
        } else {
            // Verwende den maximalen Wert für andere Attribute
            return spielerStream
                    .max(comparator)
                    .orElse(null);
        }
    }

    public static void main(String[] args) {
        Spiel spiel1 = new Spiel();
        ArrayList<Karte> k1 = new ArrayList<>(Set2);
        k1 = shuffle(k1);
        spiel1.Spielen(k1);
    }
    public static <Karte> ArrayList shuffle(ArrayList<Karte> list){
        Collections.shuffle(list);   //Karten werden hier gemischt
        return list;
    }
}
