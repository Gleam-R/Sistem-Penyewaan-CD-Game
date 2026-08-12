package SisforCDGame;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int kodeGame;
    private String namaGame;
    private String kategori;
    private int stok;

    public Order(int kodeGame, String namaGame, String kategori, int stok) {
        this.kodeGame = kodeGame;
        this.namaGame = namaGame;
        this.kategori = kategori;
        this.stok = stok;
    }

    // Getters
    public int getKodeGame() {
        return kodeGame;
    }

    public String getNamaGame() {
        return namaGame;
    }

    public String getKategori() {
        return kategori;
    }

    public int getStok() {
        return stok;
    }

    // Setters
    public void setKodeGame(int kodeGame) {
        this.kodeGame = kodeGame;
    }

    public void setNamaGame(String namaGame) {
        this.namaGame = namaGame;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }
}
