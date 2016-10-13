/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.salaComercial;

import Imoveis.Predio;
import Imoveis.Predio.*;
import Imoveis.Tipo;

/**
 *
 * @author Junior
 */
public class SalaComercial extends Predio {

    protected int nroBanheiros;
    protected int nroSala;
    
    public SalaComercial() {
        super();
    }

    public SalaComercial(int nroBanheiros, int nroSala, String nomeEdificio,
            int andar, double valorCondominio, String logradouro, int numero,
            String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(nomeEdificio, andar, valorCondominio, logradouro, numero, bairro,
                cidade, descricao, areaTotal, valor);
        this.nroBanheiros = nroBanheiros;
        this.nroSala = nroSala;
    }

    /**
     * @return the nroBanheiros
     */
    public int getNroBanheiros() {
        return nroBanheiros;
    }

    /**
     * @param nroBanheiros the nroBanheiros to set
     */
    public void setNroBanheiros(int nroBanheiros) {
        this.nroBanheiros = nroBanheiros;
    }

    /**
     * @return the nroSala
     */
    public int getNroSala() {
        return nroSala;
    }

    /**
     * @param nroSala the nroSala to set
     */
    public void setNroSala(int nroSala) {
        this.nroSala = nroSala;
    }

    @Override
    public String toString() {
        return super.toString() + "\nNumero de Banheiros: " + nroBanheiros + "\nNumero da Sala: " + nroSala;
    }

}
