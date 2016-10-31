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
import Imoveis.Imovel;
import Imoveis.Tipo;
import java.util.List;

/**
 *
 * @author junior
 */
public class InterfaceSalaComercial extends InterfaceSistema {

    private ImobiliariaCrud listaSalaComercial = new ImobiliariaCrud();
    private SalaComercial sc = null;
    private List<Imovel> listaOrdenada;

    @Override
    public void principal() {
        listaSalaComercial.setTipoImovel(Tipo.SALACOMERCIAL);
        if (!listaSalaComercial.lerArquivo()) {
            this.exibeMensagem("Arquivo não encontrado");
        }
        listaSalaComercial.setLastCod();
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
                    if (!this.listaSalaComercial.isEmpty()) {
                        sc = this.consultar();
                        if (sc != null) {
                            System.out.print("#############################################");
                            System.out.println(sc.toString());
                        }
                    } else {
                        this.exibeMensagem("Lista de Sala Comercial  Vazia!");
                    }
                    break;
                case 3:
                    if (!this.listaSalaComercial.isEmpty()) {
                        sc = consultar();
                        if (sc != null) {
                            if (this.editarImovel(sc, listaSalaComercial)) {
                                this.exibeMensagem("Imovel editado com sucesso");
                            }
                        } else {
                            this.exibeMensagem("Imovel não encontrado");
                        }
                    } else {
                        this.exibeMensagem("Lista de Sala Comercial  Vazia!");
                    }
                    break;
                case 4:
                    if (!this.listaSalaComercial.isEmpty()) {
                        if (listaSalaComercial.excluir(inInt("------------------------\nDigite o codigo do imovel:"))) {
                            this.exibeMensagem("Imovel exlcuido com sucesso");
                        } else {
                            this.exibeMensagem("erro");
                        }
                    } else {
                        this.exibeMensagem("Lista de Sala Comercial  Vazia!");
                    }
                    break;
                case 5:
                    if (!this.listaSalaComercial.isEmpty()) {
                        this.Ordenar();
                    } else {
                        this.exibeMensagem("Lista de Sala Comercial  Vazia!");
                    }
                    break;
                default:
                    this.exibeMensagem("Opção invalida!");
            }
        } while (opcao != 0);

    }

    @Override
    protected void criarImovel() {
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
        sc = new SalaComercial(nroBanheiros, nroSala, nomeEdificio,
                andar, valorCondominio, logradouro, numero,
                bairro, cidade, descricao, areaTotal, valor);
        if (listaSalaComercial.incluir(sc)) {
            this.exibeMensagem("Sala Comercial incluido com sucesso!");
            if (!listaSalaComercial.escreverArquivo()) {
                this.exibeMensagem("Erro ao escrever o arquivo");
            }
        } else {
            this.exibeMensagem("Ocorreu algum erro");
        }
    }

    /**
     * metodo de interface pra consulta por diferentes parametros
     *
     * @return um objeto ou uma lista que foi pesquisada
     */
    private SalaComercial consultar() {
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
                    principal();

                    break;
                case 1:
                    sc = (SalaComercial) listaSalaComercial.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                    if (sc != null) {
                        return sc;
                    } else {
                        this.exibeMensagem("Sala Comercial não encontrada");
                    }
                    break;
                case 2:
                    do {
                        List<Imovel> aux = listaSalaComercial.pesquisaBairro(inString("Digite o bairro que você quer pesquisar: "));
                        if (aux.size() > 0) {
                            int codImovel = this.listarImoveis2(aux);
                            sc = (SalaComercial) listaSalaComercial.consultar(codImovel);
                            if (sc != null) {
                                return sc;
                            } else {
                                this.exibeMensagem("Sala Comercial não encontrada");
                            }
                        } else {
                            this.exibeMensagem("nemhuma Sala Comercial encontrada nesse bairro");
                            op = inInt("consultar novamente? 1- sim | 2-nao");
                        }
                    } while (op != 2);
                    break;
                case 3:
                    do {
                        List<Imovel> aux = listaSalaComercial.pesquisaValor(inDouble("Digite o valor do imovel que você quer pesquisar: "));
                        if (aux.size() > 0) {
                            int codImovel = this.listarImoveis2(aux);
                            sc = (SalaComercial) listaSalaComercial.consultar(codImovel);
                            if (sc != null) {
                                return sc;
                            } else {
                                this.exibeMensagem("Sala Comercial não encontrada");
                            }
                        } else {
                            this.exibeMensagem("nemhuma Sala Comercial encontrada nesse bairro");
                            op = inInt("consultar novamente? 1- sim | 2-nao");
                        }
                    } while (op != 2);
                    break;

                case 4:
                    int imovelCod = this.listarImoveis(listaSalaComercial);
                    sc = (SalaComercial) listaSalaComercial.consultar(imovelCod);
                    if (sc != null) {
                        return sc;
                    } else {
                        this.exibeMensagem("Sala Comercial não encontrada");
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
            op = inInt("Digite a opção desejada: ");
            Imovel imo;
            switch (op) {
                case 0:
                    principal();

                    break;
                case 1:
                    this.listaOrdenada = this.listaSalaComercial.ordenarValor();
                    int codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaSalaComercial.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaSalaComercial.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("imovel inexistente");
                    }
                    break;
                case 2:
                    this.listaOrdenada = this.listaSalaComercial.ordenarCodigo();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaSalaComercial.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaSalaComercial.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("imovel inexistente");
                    }
                    break;
                case 3:
                    this.listaOrdenada = this.listaSalaComercial.ordenarArea();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaSalaComercial.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaSalaComercial.consultar(codImovel).toString());
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
        InterfaceSalaComercial i = new InterfaceSalaComercial();
        i.principal();
    }

}
