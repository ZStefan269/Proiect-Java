/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proiect;

/**
 *
 * @author stefa
 */
public class Flowers {

    private Integer id;
    private String denumire;
    private Integer varsta;
    private Integer cantitate;
    private String tipvanzare;
    private String actiune;

    public Flowers(Integer id, String denumire, Integer varsta, Integer cantitate, String tipvanzare, String actiune) {
        this.id = id;
        this.denumire = denumire;
        this.varsta = varsta;
        this.cantitate = cantitate;
        this.tipvanzare = tipvanzare;
        this.actiune = actiune;
    }

    public Integer getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public Integer getVarsta() {
        return varsta;
    }

    public Integer getCantitate() {
        return cantitate;
    }

    public String getTipvanzare() {
        return tipvanzare;
    }

    public String getActiune() {
        return actiune;
    }
}
