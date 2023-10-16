package Problema2;

import java.time.LocalDate;
public class Produs {
    private String denumire;
    private float pret;
    private int cantitate;
    private LocalDate data_expirarii;

    private static float incasari;
    public Produs(String denumire, float pret, int cantitate, LocalDate data_expirarii) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
        this.data_expirarii = data_expirarii;
    }

    public String toString() {
        return denumire + ", " + pret + ", " + cantitate + ", " + data_expirarii;
    }

    public LocalDate getData_expirarii()
    {
        return data_expirarii;
    }

    public String getDenumire()
    {
        return denumire;
    }

    public int getCantitate()
    {
        return cantitate;
    }

    public float getPret()
    {
        return pret;
    }
    public void vanzare(int cant)
    {
        cantitate-=cant;
        incasari = pret*cantitate;
    }

}