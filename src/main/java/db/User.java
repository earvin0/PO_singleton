package db;

public class User {
    private int id;
    private String imie;
    private String nazwisko;
    private int rok;
    private float kara;

    public User(int ID, String Imie, String Nazwisko, int Rok, float Kara){
        setId(ID);
        setImie(Imie);
        setNazwisko(Nazwisko);
        setRok(Rok);
        setKara(Kara);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public float getKara() {
        return kara;
    }

    public void setKara(float kara) {
        this.kara = kara;
    }
}
