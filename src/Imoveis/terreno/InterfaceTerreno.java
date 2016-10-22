package Imoveis.terreno;

import static Imoveis.EntradasTeclado.*;
import Imoveis.InterfaceSistema;
import Imoveis.ImobiliariaCrud;
import Imoveis.Imovel;
import Imoveis.Tipo;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Acer
 */
public class InterfaceTerreno extends InterfaceSistema {

    private ImobiliariaCrud listaTerrenos = new ImobiliariaCrud();
    private Terreno tr = null;
    private List<Imovel> listaOrdenada;
    private int codImovel;

    @Override
    public void principal() {
        listaTerrenos.setTipoImovel(Tipo.TERRENO);
        
        if( !listaTerrenos.lerArquivo() ){
            this.exibeMensagem("Arquivo não encontrado");
        }
        listaTerrenos.setLastCod();
        int opcao = -1;
        do {
           System.out.println("#############################################");
            System.out.println("1 - incluir");
            System.out.println("2 - Consultar");
            System.out.println("3 - editar");
            System.out.println("4 - excluir");
            System.out.println("5 - Ordernar");
            System.out.println("0 - sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    this.criarImovel();
                    break;
                case 2:
                    tr = this.consultar();
                    if (tr != null) {
                        System.out.print("#############################################");
                        System.out.println(tr.toString());
                    }
                    break;
                case 3:
                    tr = consultar();
                    if (tr != null) {
                        if (this.editarImovel(tr, listaTerrenos)) {
                            this.exibeMensagem("Imovel editado com sucesso");
                        }
                    }
                    break;
                case 4:
                    if (listaTerrenos.excluir(inInt("------------------------\nDigite o codigo do imovel:"))) {
                        this.exibeMensagem("Imovel exlcuido com sucesso");
                    } else {
                        this.exibeMensagem("erro");
                    }
                    break;
                case 5:
                    this.Ordenar();
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

      private Terreno consultar() {
        int op = -1;
        do {
            System.out.println("=======================================");
            System.out.println("1 - Pesquisar");
            System.out.println("2 - Pesquisa por bairro");
            System.out.println("3 - Pesquisa por valor");
            System.out.println("4 - Listar todos");
            System.out.println("0 - sair");
            System.out.println("----------------------------------------");
            op = inInt("Opção:");
            switch (op) {
                case 0:
                    break;
                case 1:
                    tr = (Terreno) listaTerrenos.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                    if (tr != null) {
                        return tr;
                    } else {
                        this.exibeMensagem("apartamento não encontrado");
                    }
                    break;
                case 2:
                    do {
                        List<Imovel> aux = listaTerrenos.pesquisaBairro(inString("Digite o bairro que você quer pesquisar: "));
                        if (aux.size() > 0) {
                            int codImovel = this.listarImoveis2(aux);
                            tr = (Terreno) listaTerrenos.consultar(codImovel);
                            if (tr != null) {
                                return tr;
                            } else {
                                this.exibeMensagem("apartamento não encontrado");
                            }
                        } else {
                            this.exibeMensagem("nemhum apartamento encontrado nesse bairro");
                            op = inInt("consultar novamente? 1- sim | 2-nao");
                        }
                    } while (op != 2);
                    break;
                case 3:
                    do {
                        List<Imovel> aux = listaTerrenos.pesquisaValor(inDouble("Digite o valor do imovel que você quer pesquisar: "));
                        if (aux.size() > 0) {
                            int codImovel = this.listarImoveis2(aux);
                            tr = (Terreno) listaTerrenos.consultar(codImovel);
                            if (tr != null) {
                                return tr;
                            } else {
                                this.exibeMensagem("apartamento não encontrado");
                            }
                        } else {
                            this.exibeMensagem("nemhum apartamento encontrado nesse bairro");
                            op = inInt("consultar novamente? 1- sim | 2-nao");
                        }
                    } while (op != 2);
                    break;

                case 4:
                    int imovelCod = this.listarImoveis(listaTerrenos);
                    tr = (Terreno) listaTerrenos.consultar(imovelCod);
                    if (tr != null) {
                        return tr;
                    } else {
                        this.exibeMensagem("apartamento não encontrado");
                    }
                    break;
                default:
                    this.exibeMensagem("Opção invalida!");
            }
        } while (op != 0);
        return null;
    }
  
    private void Ordenar() {
        int op = -1;
        do {
            System.out.println("=======================================");
            System.out.println("1 - Ordernar por valor");
            System.out.println("2 - Ordernar por codigo");
            System.out.println("3 - Ordernar por Area total");
            System.out.println("0 - voltar");
            System.out.println("----------------------------------------");
            int opcao = inInt("Digite a opção desejada: ");
            Imovel imo;
            switch (opcao) {
                case 0:
                    break;
                case 1:
                    this.listaOrdenada = this.listaTerrenos.ordenarValor();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaTerrenos.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaTerrenos.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("imovel inexistente");
                    }
                    break;
                case 2:
                    this.listaOrdenada = this.listaTerrenos.ordenarCodigo();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaTerrenos.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaTerrenos.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("imovel inexistente");
                    }
                    break;
                case 3:
                    this.listaOrdenada = this.listaTerrenos.ordenarArea();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaTerrenos.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaTerrenos.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("imovel inexistente");
                    }
                    break;
                default:
                    this.exibeMensagem("Opção invalida");
            }
        } while (op != 0);
    }
    public static void main(String[] args) {
        InterfaceTerreno i = new InterfaceTerreno();
        i.principal();
    }
}
