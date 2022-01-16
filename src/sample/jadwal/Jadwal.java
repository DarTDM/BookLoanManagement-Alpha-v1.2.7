package sample.jadwal;

public class Jadwal {

    private int id;
    private String matkul;
    private String dosen;
    private int ruang;
    private int gkb;
    private String waktu;

    public Jadwal(int id, String matkul, int gkb, int ruang, String dosen, String waktu) {
        this.id = id;
        this.matkul = matkul;
        this.gkb = gkb;
        this.ruang = ruang;
        this.dosen = dosen;
        this.waktu = waktu;

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatkul() {
        return matkul;
    }

    public void setMatkul(String matkul) {
        this.matkul = matkul;
    }

    public String getDosen() {
        return dosen;
    }

    public void setDosen(String dosen) {
        this.dosen = dosen;
    }

    public int getRuang() {
        return ruang;
    }

    public void setRuang(int ruang) {
        this.ruang = ruang;
    }

    public int getGkb() {
        return gkb;
    }

    public void setGkb(int gkb) {
        this.gkb = gkb;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
}
