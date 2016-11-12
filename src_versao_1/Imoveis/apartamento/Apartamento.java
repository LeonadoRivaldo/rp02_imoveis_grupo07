/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.apartamento;

import Imoveis.Predio;
import Imoveis.Predio.*;

/**
 *
 * @author leona_000
 */
public class Apartamento extends Predio {

    protected int numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento;

    public Apartamento() {
        super();
    }

    public Apartamento(int numeroDeQuartos, int anoDeConstrucao, int numeroDeVagasNaGaragem, int numeroDoApartamento, String nomeEdificio, int andar, double valorCondominio, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.numeroDeQuartos = numeroDeQuartos;
        this.anoDeConstrucao = anoDeConstrucao;
        this.numeroDeVagasNaGaragem = numeroDeVagasNaGaragem;
        this.numeroDoApartamento = numeroDoApartamento;
    }

    public Apartamento(int codigo,int numeroDeQuartos, int anoDeConstrucao, int numeroDeVagasNaGaragem, int numeroDoApartamento, String nomeEdificio, int andar, double valorCondominio, String logradouro, int numero, String bairro, String cidade, String descricao, double areaTotal, double valor) {
        super(codigo, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        this.numeroDeQuartos = numeroDeQuartos;
        this.anoDeConstrucao = anoDeConstrucao;
        this.numeroDeVagasNaGaragem = numeroDeVagasNaGaragem;
        this.numeroDoApartamento = numeroDoApartamento;
    }

    public int getNumeroDeQuartos() {
        return numeroDeQuartos;
    }

    public void setNumeroDeQuartos(int numeroDeQuartos) {
        this.numeroDeQuartos = numeroDeQuartos;
    }

    public int getAnoDeConstrucao() {
        return anoDeConstrucao;
    }

    public void setAnoDeConstrucao(int anoDeConstrucao) {
        this.anoDeConstrucao = anoDeConstrucao;
    }

    public int getNumeroDeVagasNaGaragem() {
        return numeroDeVagasNaGaragem;
    }

    public void setNumeroDeVagasNaGaragem(int numeroDeVagasNaGaragem) {
        this.numeroDeVagasNaGaragem = numeroDeVagasNaGaragem;
    }

    public int getNumeroDoApartamento() {
        return numeroDoApartamento;
    }

    public void setNumeroDoApartamento(int numeroDoApartamento) {
        this.numeroDoApartamento = numeroDoApartamento;
    }

    @Override
    public String toString() {
        String str = "";
        str += super.toString();
        str += "\nNumero de quartos: " + numeroDeQuartos;
        str += "\nAno de construcao: " + anoDeConstrucao;
        str += "\nNumero de vagas na garagem: " + numeroDeVagasNaGaragem;
        str += "\nNumero do apartamento: " + numeroDoApartamento;
        return str;
    }

}
