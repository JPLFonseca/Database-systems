package com.example.sbd_tp2_intelij;

public class tipoVeiculo {
    String Tipo;
    String Descricao;
    int potencia;
    int numeroPortas;
    int numeroLugares;
    String tipoMotor;
    int capacidadeCarga;


    public tipoVeiculo(String Tipo, String Descricao, int potencia, int numeroPortas, int numeroLugares, String tipoMotor, int capacidadeCarga) {
        this.Tipo = Tipo;
        this.Descricao = Descricao;
        this.potencia = potencia;
        this.numeroPortas = numeroPortas;
        this.numeroLugares = numeroLugares;
        this.tipoMotor = tipoMotor;
        this.capacidadeCarga = capacidadeCarga;
    }

    public String getTipo() {
        return Tipo;
    }
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
    public String getDescricao() {
        return Descricao;
    }
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
    public int getPotencia() {
        return potencia;
    }
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }
    public int getNumeroPortas() {
        return numeroPortas;
    }
    public void setNumeroPortas(int numeroPortas) {
        this.numeroPortas = numeroPortas;
    }
    public int getNumeroLugares() {
        return numeroLugares;
    }
    public void setNumeroLugares(int numeroLugares) {
        this.numeroLugares = numeroLugares;
    }
    public String getTipoMotor() {
        return tipoMotor;
    }
    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }
    public int getCapacidadeCarga() {
        return capacidadeCarga;
    }
    public void setCapacidadeCarga(int capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }
}
