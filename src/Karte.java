import java.util.EnumSet;

public enum Karte implements Comparable <Karte>{

    //Hier sind die Statistiken der Karten festgelegt
    AudiA4(190,1500,4.70,2020),
    BMW3er(250,1600,4.63,2019),
    MercedesCKlasse(205,1650,4.80,2021),
    TeslaModel3(346,1844,4.69,2020),
    VolkswagenGolf(130,1345,4.30,2018),
    FordFocus(125,1344,4.40,2019),
    HondaCivic(158,1270,4.50,2018),
    ToyotaCorolla(132,1275,4.60,2020),
    NissanAltima(188,1432,4.90,2019),
    ChevroletCruze(153,1335,4.60,2018),
    HyundaiElantra(147,1262,4.60,2020),
    KiaOptima(185,1470,4.80,2019),
    SubaruImpreza(152,1395,4.60,2020),
    Mazda3(186,1340,4.50,2021),
    DodgeCharger(292,1950,5.10,2019),
    JeepCherokee(180,1605,4.60,2020),
    LandRoverDiscovery(340,2112,4.97,2019),
    VolvoXC60(250,1815,4.70,2021),
    Porsche911(379,1515,4.50,2020),
    FerrariF8(710,1435,4.61,2020),
    LamborghiniHuracan(630,1422,4.52,2019),
    McLaren720S(710,1419,4.54,2019),
    BugattiChiron(1500,1996,4.54,2021),
    AstonMartinDB11(503,1770,4.75,2019),
    LexusIS(241,1680,4.70,2020),
    JaguarXE(247,1595,4.67,2021),
    MaseratiGhibli(345,1875,4.97,2020),
    AlfaRomeoGiulia(280,1530,4.64,2021),
    CadillacCT5(237,1636,4.93,2020),
    InfinitiQ50(300,1695,4.79,2019),
    AudiQ7(248,2100,5.06,2021),
    BMWX5(335,2137,4.92,2020),
    MercedesGLC(255,1854,4.66,2021),
    TeslaModelX(670,2489,5.04,2021),
    VolkswagenTouareg(280,2075,4.88,2020),
    FordMustang(310,1655,4.78,2019);

    //Ausruf der Variablen
int PS;
int Gewicht;
double Laenge;
int Alter;
//Konstruktor
 Karte (int PS,int Gewicht,double Laenge, int Alter){
     this.PS = PS;
     this.Laenge = Laenge;
     this.Gewicht = Gewicht;
     this.Alter = Alter;
 }

    public int getPS() {
        return PS;
    }

    public int getGewicht() {
        return Gewicht;
    }

    public double getLaenge() {
        return Laenge;
    }

    public int getAlter() {
        return Alter;
    }
    @Override
    public String toString(){
     String exit;
     exit = "PS: "+PS + " " + "Gewicht: "+Gewicht +"kg "+ " Baujahr: " + Alter + " Länge: "+ Laenge + "m";
     return exit;
 }
    public double getKartenWert(String attribut) {
        switch (attribut) {
            case "p":
                return getPS();
            case "g":
                return getGewicht();
            case "b":
                return getAlter();
            case "l":
                return getLaenge();
            default:
                throw new IllegalArgumentException("Ungültiges Attribut: " + attribut);
        }
    }



}

