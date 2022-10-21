package models.enums;

public enum Zona {

    CENTRO(0,0),
    MUNICIPIO(2,2),
    PARCO(6,-4),
    AEROPORTO(7,12),
    STAZIONE(5,4),
    CENTROSTORICO(4,1),
    PORTO(-9,-7),
    UFFICI(-2,-3),
    TEATRO(-5,2),
    ZONARESIDENZIALE(5,-8),
    ZONAINDUSTRIALE(-10,5),
    ZONACOMMERCIALE(-3,-4),
    UNIVERSITA(3,2),
    SCUOLA(4,-7);

    private int x;
    private int y;

    Zona(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}