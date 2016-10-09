package Imoveis.terreno;

import static Imoveis.EntradasTeclado.*;
import Imoveis.InterfaceSistema;
import Imoveis.ImobiliariaCrud;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class InterfaceTerreno extends InterfaceSistema {

    private ImobiliariaCrud listaTerrenos = new ImobiliariaCrud();
    private Terreno tr = null;

    @Override
    public void principal() {
        int opcao = -1;
        do {
            System.out.println("##########################");
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
                    tr = (Terreno) listaTerrenos.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                    if (tr != null) {
                        System.out.println("\n#############################################");
                        System.out.println(tr.toString());
                    } else {
                        this.exibeMensagem("terreno não encontrado");
                    }
                    break;
                default:
                    this.exibeMensagem("Opção invalida!");
            }

        } while (opcao != 0);

    }

    @Override
    protected void criarImovel() {
        String logradouro = inString("----------------------------------------\nDigite o endereço do terreno:");
        int numero = inInt("------------------------------------------------\nDigite o numero do endereço:");
        String bairro = inString("----------------------------------------\nDigite o bairro de localizacao do terreno:");
        String cidade = inString("----------------------------------------\nDigite a cidade de localizacao do terreno:");
        String descricao = inString("----------------------------------------\nEscreva uma discrição para o terreno:");
        double areaTotal = inDouble("----------------------------------------\nDigite o tamanho do terreno:");
        double valor = inDouble("----------------------------------------\nDigite o valor do terreno:");
        double dimensaoFrente = inDouble("----------------------------------------\nDigite a dimensão da frente:");
        double dimensaoLado = inDouble("----------------------------------------\nDigite a dimensão do lado:");

        tr = new Terreno(dimensaoFrente, dimensaoLado, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaTerrenos.incluir(tr)) {
            this.exibeMensagem("Terreno incluido com sucesso!");
        } else {
            this.exibeMensagem("Ocorreu algum erro");
        }
    }

    public static void main(String[] args) {
        InterfaceTerreno i = new InterfaceTerreno();
        i.principal();
    }

}
