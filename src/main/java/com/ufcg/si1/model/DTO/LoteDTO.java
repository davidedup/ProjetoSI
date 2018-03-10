package com.ufcg.si1.model.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoteDTO {

    private int numeroDeItens;
    private Date dataDeValidade;

    public LoteDTO() {
    }

    public LoteDTO(int numeroDeItens, String dataDeValidade) {
        this.numeroDeItens = numeroDeItens;
        try {
			this.dataDeValidade = new SimpleDateFormat("dd/MM/yyyy").parse(dataDeValidade);
		} catch (ParseException e) {
			
		}
    }

    public int getNumeroDeItens() {
        return this.numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public Date getDataDeValidade() {
        return this.dataDeValidade;
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

}
