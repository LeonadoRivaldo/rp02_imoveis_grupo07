/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.salaComercial;

import static Imoveis.EntradasTeclado.inDouble;
import static Imoveis.EntradasTeclado.inInt;
import Imoveis.InterfaceSistema;
import static Imoveis.EntradasTeclado.inString;
import Imoveis.ImobiliariaCrud;
import Imoveis.Tipo;

/**
 *
 * @author junio
 */
public class InterfaceSalaComercial extends InterfaceSistema {

    private ImobiliariaCrud listaSalaComercial = new ImobiliariaCrud();
    private SalaComercial sc = null;

    @Override
    public void principal() {
        listaSalaComercial.setTipoImovel(Tipo.SALACOMERCIAL);
        listaSalaComercial.lerArquivo();
        int opcao = -1;
        do {
            System.out.println("Sala Comercial");
            System.out.println("1 - incluir");
            System.out.println("2 - Consultar");
            System.out.println("0 - sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    criarImovel();
                    break;
                case 2:
                    sc = (SalaComercial) listaSalaComercial.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                    if (sc != null) {
                        System.out.println(sc.toString());
                    } else {
                        this.exibeMensagem("A Sala Comercial Não Foi Encontrada!");
                    }
                    break;
                default:
                    throw new AssertionError();
            }

        } while (opcao != 0);

    }

    @Override
    protected void criarImovel(){
        String nomeEdificio = inString("----------------------------------------\nDigite o nome do edificio do imovel:");
        String logradouro = inString("----------------------------------------\nDigite o endereço do imovel:");
        int numero = inInt("------------------------------------------------\nDigite o numero do endereço:");
        String bairro = inString("----------------------------------------\nDigite o bairro de localizacao do imovel:");
        String cidade = inString("----------------------------------------\nDigite a cidade de localizacao do imovel:");
        int nroSala = inInt("----------------------------------------\nNumero da sala:");
        int andar = inInt("----------------------------------------\nAndar do apartamento:");
        int nroBanheiros = inInt("----------------------------------------\nNumero de banheiros:");
        double areaTotal = inDouble("----------------------------------------\nDigite o tamanho do imovel:");
        String descricao = inString("----------------------------------------\nEscreva uma discrição para o imovel:");
        double valor = inDouble("----------------------------------------\nDigite o valor do imovel:");
        double valorCondominio = inDouble("----------------------------------------\nDigite o valor do condominio:");
        sc = new SalaComercial(nroBanheiros, nroSala, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaSalaComercial.incluir(sc)) {
            this.exibeMensagem("Sala Comercial incluida com sucesso!");
            if(!listaSalaComercial.escreverArquivo()){
                this.exibeMensagem("Erro ao escrever o arquivo");
            }
        } else {
            this.exibeMensagem("Ocorreu algum erro");
        }
    }

    public static void main(String[] args) {
        InterfaceSalaComercial i = new InterfaceSalaComercial();
        i.principal();
    }

}
