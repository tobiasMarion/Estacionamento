package model.bean;

public class Vaga {

    private int idVaga;
    private int numero;
    private String rua;
    private boolean obliqua;

    public int getIdVaga() {
        return idVaga;
    }

    public void setIdVaga(int idVaga) {
        this.idVaga = idVaga;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public boolean isObliqua() {
        return obliqua;
    }

    public void setObliqua(boolean obliqua) {
        this.obliqua = obliqua;
    }

}
