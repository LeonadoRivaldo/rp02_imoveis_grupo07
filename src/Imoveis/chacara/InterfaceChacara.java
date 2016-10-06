/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.chacara;

import static Imoveis.EntradasTeclado.inDouble;
import static Imoveis.EntradasTeclado.inInt;
import static Imoveis.EntradasTeclado.inString;
import Imoveis.ImobiliariaCrud;
import Imoveis.InterfaceSistema;

/**
 *
 * @author Juliana Mareco
 */
public class InterfaceChacara extends InterfaceSistema {

    private ImobiliariaCrud listaChacaras = new ImobiliariaCrud();
    private Chacara chac = null;

    String logradouro, bairro, cidade, descricao;
    int numero, nroQuartos, anoConstrucao;
    double areaTotal, valor, areaConstruida, distanciaCidade;

    @Override
    protected void criarImovel() {
        logradouro = inString("\nDigite o endereço do imovel:");
        numero = inInt("\nInforme o Número do Imovel: ");
        bairro = inString("\nInforme o Bairro: ");
        cidade = inString("\nInforme a Cidade: ");
        descricao = inString("\nInforme a Descrição: ");
        areaTotal = inDouble("\nInforme a Area Total do Imovel: ");
        valor = inDouble("\nInforme o valor do Imovel:");
        areaConstruida = inDouble("\nInforme a Area Construida do Imovel: ");
        anoConstrucao = inInt("\nInforme o Ano da Construção: ");
        nroQuartos = inInt("\nInforme o Número de Quartos: ");
        distanciaCidade = inDouble("\nInforme a Distancia da Cidade: ");
        chac = new Chacara(distanciaCidade, anoConstrucao, nroQuartos, areaConstruida, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaChacaras.incluir(chac)) {
            this.exibeMensagem("Chacara Incluida com Sucesso!");
        } else {
            this.exibeMensagem("Ocorreu Algum Erro");
        }
    }

    @Override
    public void principal() {
        int opcao;
        do {
            System.out.println("Chacara");
            System.out.println("1. Incluir");
            System.out.println("2. Consultar");
            System.out.println("0. Sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 1:
                    criarImovel();
                    break;
                case 2:
                    chac = (Chacara) listaChacaras.consultar(inInt("\nDigite o Codigo do Imovel: "));
                    if (chac != null) {
                        System.out.println(chac.toString());
                    } else {
                        this.exibeMensagem("Chacara Não Encontrada! ");
                    }
                    break;
                default:
                    throw new AssertionError();
            }

        } while (opcao != 0);

    }

    public static void main(String[] args) {
        InterfaceChacara i = new InterfaceChacara();
        i.principal();
    }

}
