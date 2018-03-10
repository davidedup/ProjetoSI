package com.ufcg.si1.model.DTO;

public class LoteDTO {

    private int numeroDeItens;
    private String dataDeValidade;

    public LoteDTO() {
    }

    public LoteDTO(int numeroDeItens, String dataDeValidade) {
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = dataDeValidade;
    }

    public int getNumeroDeItens() {
        return this.numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public String getDataDeValidade() {
        return this.dataDeValidade;
    }

    public void setDataDeValidade(String dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

}
