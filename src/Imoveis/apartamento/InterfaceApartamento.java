/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.apartamento;

import static Imoveis.EntradasTeclado.*;
import Imoveis.InterfaceSistema;
import Imoveis.ImobiliariaCrud;

/**
 *
 * @author leona_000
 */
public class InterfaceApartamento extends InterfaceSistema {

    private ImobiliariaCrud listaApartamentos = new ImobiliariaCrud();
    private Apartamento ap = null;

    @Override
    public void principal() {
        int opcao = -1;
        do {
            System.out.println("##########################");
            System.out.println("1 - incluir");
            System.out.println("2 - exibir");
            System.out.println("0 - sair");
            opcao = inInt("Opcao: ");

        } while (opcao != 0);

    }

    @Override
    protected void criarImovel() {
        String logradouro  = inString("----------------------------------------\nDigite o endereço do imovel:");
        int numero = inInt("------------------------------------------------\nDigite o numero do endereço:");
        String bairro = inString("----------------------------------------\nDigite o bairro de localizacao do imovel:");
        String cidade = inString("----------------------------------------\nDigite a cidade de localizacao do imovel:");
        String descricao = inString("----------------------------------------\nDigite a cidade de localizacao do imovel:");
        double areaTotal;
        double valor;
        String nomeEdificio = inString("----------------------------------------\nDigite o nome do edificio do imovel:");
        int andar = inInt("----------------------------------------\nAndar do apartamento:");
        double valorCondominio;
        int numeroDeQuartos = inInt("----------------------------------------\nQuantidade de quartos do imovel:");
        int anoDeConstrucao = inInt("----------------------------------------\nAno de construcao:");
        int numeroDeVagasNaGaragem = inInt("----------------------------------------\nNumero de vagas de garagem:");
        int numeroDoApartamento = inInt("----------------------------------------\nNumero do apartamento:");
        ap = new Apartamento(numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        listaApartamentos.incluir(ap);
    }

    public static void main(String[] args) {
        InterfaceApartamento i = new InterfaceApartamento();
        i.principal();
    }

}
