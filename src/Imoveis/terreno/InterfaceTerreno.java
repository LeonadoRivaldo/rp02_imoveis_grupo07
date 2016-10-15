package Imoveis.terreno;

import static Imoveis.EntradasTeclado.*;
import Imoveis.InterfaceSistema;
import Imoveis.ImobiliariaCrud;
import Imoveis.Imovel;
import Imoveis.Tipo;
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
        listaTerrenos.setTipoImovel(Tipo.TERRENO);
        listaTerrenos.lerArquivo();
        int opcao = -1;
        do {
            System.out.println("#############################################");
            System.out.println("1 - incluir");
            System.out.println("2 - Consultar");
            System.out.println("3 - editar");
            System.out.println("0 - sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    criarImovel();
                    break;
                case 2:
                    tr = this.consultar();
                    if (tr != null) {
                        System.out.println("\n#############################################");
                        System.out.println(tr.toString());
                    }
                    break;
                case 3:
                    tr = consultar();
                    if (tr != null) {
                        this.editarImovel(tr, listaTerrenos);
                    }
                    break;
               case 4:
                    if (listaTerrenos.excluir(inInt("------------------------\nDigite o codigo do imovel:"))) {
                        this.exibeMensagem("Imovel exlcuido com sucesso");
                    }else{
                        this.exibeMensagem("erro");
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
            if (!listaTerrenos.escreverArquivo()) {
                this.exibeMensagem("Erro ao escrever o arquivo");
            }
        } else {
            this.exibeMensagem("Ocorreu algum erro");
        }
    }

    public Terreno consultar() {
        System.out.println("=======================================");
        System.out.println("1 - Pesquisar");
        System.out.println("2 - Listar todos");
        System.out.println("----------------------------------------");
        int o = inInt("Opção:");
        switch (o) {
            case 1:
                tr = (Terreno) listaTerrenos.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                if (tr != null) {
                    return tr;
                } else {
                    this.exibeMensagem("terreno não encontrado");
                }
                break;
            case 2:
                int imovelCod = this.listaImoveis(listaTerrenos);
                tr = (Terreno) listaTerrenos.consultar(imovelCod);
                if (tr != null) {
                    return tr;
                } else {
                    this.exibeMensagem("terreno não encontrado");
                }
                break;
            default:
                this.exibeMensagem("Opção invalida!");
        }
        return null;
    }

    public static void main(String[] args) {
        InterfaceTerreno i = new InterfaceTerreno();
        i.principal();
    }
}
