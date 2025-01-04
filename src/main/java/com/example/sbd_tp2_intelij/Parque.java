package com.example.sbd_tp2_intelij;

public class Parque {
    String localidade;
    String morada;
    String coordenadas;

    public Parque(String coordenadas, String morada, String localidade) {
        this.coordenadas = coordenadas;
        this.morada = morada;
        this.localidade = localidade;
    }

    public String getLocalidade() {
        return localidade;
    }
    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }
    public String getMorada() {
        return morada;
    }
    public void setMorada(String morada) {
        this.morada = morada;
    }
    public String getCoordenadas() {
        return coordenadas;
    }
    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
