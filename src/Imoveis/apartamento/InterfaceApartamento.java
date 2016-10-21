/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis.apartamento;

import static Imoveis.EntradasTeclado.*;
import Imoveis.InterfaceSistema;
import Imoveis.ImobiliariaCrud;
import Imoveis.Imovel;
import Imoveis.Tipo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leona_000
 */
public class InterfaceApartamento extends InterfaceSistema {

    private ImobiliariaCrud listaApartamentos = new ImobiliariaCrud();
    private Apartamento ap = null;
    private List<Imovel> listaOrdenada;
    private int codImovel;

    @Override
    public void principal() {
        listaApartamentos.setTipoImovel(Tipo.APARTAMENTO);
        if (!listaApartamentos.lerArquivo()) {
            this.exibeMensagem("Arquivo não encontrado");
        }
        listaApartamentos.setLastCod();
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
                    ap = this.consultar();
                    if (ap != null) {
                        System.out.print("#############################################");
                        System.out.println(ap.toString());
                    }
                    break;
                case 3:
                    ap = consultar();
                    if (ap != null) {
                        if (this.editarImovel(ap, listaApartamentos)) {
                            this.exibeMensagem("Imovel editado com sucesso");
                        }
                    }
                    break;
                case 4:
                    if (listaApartamentos.excluir(inInt("------------------------\nDigite o codigo do imovel:"))) {
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
        String logradouro = inString("----------------------------------------\nDigite o endereço do imovel:");
        int numero = inInt("------------------------------------------------\nDigite o numero do endereço:");
        String bairro = inString("----------------------------------------\nDigite o bairro de localizacao do imovel:");
        String cidade = inString("----------------------------------------\nDigite a cidade de localizacao do imovel:");
        String descricao = inString("----------------------------------------\nEscreva uma discrição para o imovel:");
        double areaTotal = inDouble("----------------------------------------\nDigite o tamanho do imovel:");
        double valor = inDouble("----------------------------------------\nDigite o valor do imovel:");
        String nomeEdificio = inString("----------------------------------------\nDigite o nome do edificio do imovel:");
        int andar = inInt("----------------------------------------\nAndar do apartamento:");
        double valorCondominio = inDouble("----------------------------------------\nDigite o valor do condominio:");
        int numeroDeQuartos = inInt("----------------------------------------\nQuantidade de quartos do imovel:");
        int anoDeConstrucao = inInt("----------------------------------------\nAno de construcao:");
        int numeroDeVagasNaGaragem = inInt("----------------------------------------\nNumero de vagas de garagem:");
        int numeroDoApartamento = inInt("----------------------------------------\nNumero do apartamento:");
        ap = new Apartamento(numeroDeQuartos, anoDeConstrucao, numeroDeVagasNaGaragem, numeroDoApartamento, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaApartamentos.incluir(ap)) {
            this.exibeMensagem("Apartamento incluido com sucesso!");
            if (!listaApartamentos.escreverArquivo()) {
                this.exibeMensagem("Erro ao escrever o arquivo");
            }
        } else {
            this.exibeMensagem("Ocorreu algum erro");
        }
    }

    private Apartamento consultar() {
        int op = 0;
        System.out.println("=======================================");
        System.out.println("1 - Pesquisar");
        System.out.println("2 - Pesquisa por bairro");
        System.out.println("3 - Pesquisa por valor");
        System.out.println("6 - Listar todos");
        System.out.println("----------------------------------------");
        int o = inInt("Opção:");
        switch (o) {
            case 1:
                ap = (Apartamento) listaApartamentos.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                if (ap != null) {
                    return ap;
                } else {
                    this.exibeMensagem("apartamento não encontrado");
                }
                break;
            case 2:
                do {
                    List<Imovel> aux = listaApartamentos.pesquisaBairro(inString("Digite o bairro que você quer pesquisar: "));
                    if (aux.size() > 0) {
                        int codImovel = this.listarImoveis2(aux);
                        ap = (Apartamento) listaApartamentos.consultar(codImovel);
                        if (ap != null) {
                            return ap;
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
                    List<Imovel> aux = listaApartamentos.pesquisaValor(inDouble("Digite o valor do imovel que você quer pesquisar: "));
                    if (aux.size() > 0) {
                        int codImovel = this.listarImoveis2(aux);
                        ap = (Apartamento) listaApartamentos.consultar(codImovel);
                        if (ap != null) {
                            return ap;
                        } else {
                            this.exibeMensagem("apartamento não encontrado");
                        }
                    } else {
                        this.exibeMensagem("nemhum apartamento encontrado nesse bairro");
                        op = inInt("consultar novamente? 1- sim | 2-nao");
                    }
                } while (op != 2);
                break;

            case 6:
                int imovelCod = this.listarImoveis(listaApartamentos);
                ap = (Apartamento) listaApartamentos.consultar(imovelCod);
                if (ap != null) {
                    return ap;
                } else {
                    this.exibeMensagem("apartamento não encontrado");
                }
                break;
            default:
                this.exibeMensagem("Opção invalida!");
        }
        return null;
    }

    private void Ordenar() {
        System.out.println("=======================================");
        System.out.println("1 - Ordernar por valor");
        System.out.println("2 - Ordernar por codigo");
        System.out.println("3 - Ordernar por Area total");
        System.out.println("----------------------------------------");
        int opcao = inInt("Digite a opção desejada: ");

        switch (opcao) {
            case 1:
                this.listaOrdenada = this.listaApartamentos.ordenarValor();
                codImovel = this.listarImoveis2(listaOrdenada);
                System.out.print("=============================================");
                System.out.println(listaApartamentos.consultar(codImovel).toString());
                System.out.println("===========================================");
                break;
            case 2:
                this.listaOrdenada = this.listaApartamentos.ordenarCodigo();
                codImovel = this.listarImoveis2(listaOrdenada);
                System.out.print("=============================================");
                System.out.println(listaApartamentos.consultar(codImovel).toString());
                System.out.println("===========================================");
                break;
            case 3:
                System.out.println("TODO");
                break;
            default:
                this.exibeMensagem("Opção invalida");
        }
    }

    public static void main(String[] args) {
        InterfaceApartamento i = new InterfaceApartamento();
        i.principal();
    }
}
