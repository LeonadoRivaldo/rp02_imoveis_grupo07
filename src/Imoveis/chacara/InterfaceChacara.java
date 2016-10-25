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
import Imoveis.Imovel;
import Imoveis.InterfaceSistema;
import Imoveis.Tipo;
import java.util.List;

/**
 *
 * @author Juliana Mareco
 */
public class InterfaceChacara extends InterfaceSistema {

    private ImobiliariaCrud listaChacaras = new ImobiliariaCrud();
    private Chacara chac = null;
    private List<Imovel> listaOrdenada;
    private int codImovel;
    


    String logradouro, bairro, cidade, descricao;
    int numero, numeroDeQuartos, anoDeConstrucao;
    double areaTotal, valor, areaConstruida, distanciaCidade;

    @Override
    /**
     * Metodo principal, menu da interface chacara
     */
    public void principal() {
        listaChacaras.setTipoImovel(Tipo.CHACARA);
        if (!listaChacaras.lerArquivo()) {
            this.exibeMensagem("Arquivo Não Encontrado!");
        }
        listaChacaras.setLastCod();
        
         
        

        int opcao = -1;
        do {
            System.out.println("\n#############################################");
            System.out.println("1. Incluir");
            System.out.println("2. Consultar");
            System.out.println("3. Editar");
            System.out.println("4. Excluir");
            System.out.println("5. Ordenar");
            System.out.println("0. Sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    criarImovel();
                    break;
                case 2:
                    chac = this.consultar();
                    if (chac != null) {
                        System.out.println(chac.toString());
                    } 
                    
                    break;
                case 3:
                    chac = consultar();
                    if (chac != null) {
                        this.editarImovel(chac, listaChacaras);
                    } 
                    
                    
                    
                    break;
                case 4:
                    this.excluir();
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
    /**
     * Metodo que recebe os dados para criar um imovel
     */
    protected void criarImovel() {
        logradouro = inString("\nDigite o Endereço do Imovel: ");
        numero = inInt("\nInforme o Número do Imovel: ");
        bairro = inString("\nInforme o Bairro: ");
        cidade = inString("\nInforme a Cidade: ");
        descricao = inString("\nInforme a Descrição: ");
        areaTotal = inDouble("\nInforme a Área Total do Imovel: ");
        valor = inDouble("\nInforme o Valor do Imovel:");
        areaConstruida = inDouble("\nInforme a Área Construida do Imovel: ");
        anoDeConstrucao = inInt("\nInforme o Ano da Construção: ");
        numeroDeQuartos = inInt("\nInforme o Número de Quartos: ");
        distanciaCidade = inDouble("\nInforme a Distancia da Cidade: ");
        chac = new Chacara(distanciaCidade, anoDeConstrucao, numeroDeQuartos, areaConstruida, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaChacaras.incluir(chac)) {
            this.exibeMensagem("\nChacara Incluida com Sucesso!");
            if (!listaChacaras.escreverArquivo()) {
                this.exibeMensagem("Erro ao Escrever o Arquivo!");
            }
        } else {
            this.exibeMensagem("\nOcorreu Algum Erro!");
        }
    }

    /**
     * Metodo que escolhe o tipo de consulta
     * @return dados da pesquisa
     */
    public Chacara consultar() {
        int op = -1;
        do {
            System.out.println("=======================================");
            System.out.println("1. Pesquisar");
            System.out.println("2. Pesquisa por Bairro");
            System.out.println("3. Pesquisa por Valor");
            System.out.println("4. Listar Todos");
            System.out.println("0. Sair");
            System.out.println("----------------------------------------");
            op = inInt("Opção: ");
            switch (op) {
                case 0:
                    principal();

                    break;
                case 1:
                    chac = (Chacara) listaChacaras.consultar(inInt("\nDigite o codigo do Imovel: "));
                    if (chac != null) {
                        return chac;
                    } else {
                        this.exibeMensagem("Chacara Não Encontrada!");
                    }
                    break;
                case 2:
                    do {
                        List<Imovel> aux = listaChacaras.pesquisaBairro(inString("\nDigite o Bairro que você quer pesquisar: "));
                        if (aux.size() > 0) {
                            codImovel = this.listarImoveis2(aux);
                            chac = (Chacara) listaChacaras.consultar(codImovel);
                            if (chac != null) {
                                return chac;
                            } else {
                                this.exibeMensagem("Chacara Não Encontrada!");
                            }
                        } else {
                            this.exibeMensagem("Nemhuma Chacara Encontrada nesse Bairro!");
                            op = inInt("Consultar Novamente? 1. Sim | 2. Não");
                        }
                    } while (op != 2);
                    break;
                case 3:
                    do {
                        List<Imovel> aux = listaChacaras.pesquisaValor(inDouble("Digite o valor do Imovel que você quer pesquisar: "));
                        if (aux.size() > 0) {
                            codImovel = this.listarImoveis2(aux);
                            chac = (Chacara) listaChacaras.consultar(codImovel);
                            if (chac != null) {
                                return chac;
                            } else {
                                this.exibeMensagem("Chacara Não Encontrada!");
                            }
                        } else {
                            this.exibeMensagem("Nemhuma Chacara Encontrada nesse Bairro!");
                            op = inInt("Consultar Novamente? 1. Sim | 2. Não");
                        }
                    } while (op != 2);
                    break;

                case 4:
                    codImovel = this.listarImoveis(listaChacaras);
                    chac = (Chacara) listaChacaras.consultar(codImovel);
                    if (chac != null) {
                        return chac;
                    } else {
                        this.exibeMensagem("Chacara Não Encontrada!");
                    }
                    break;
                default:
                    this.exibeMensagem("Opção Invalida!");
            }
        } while (op != 0);
        return null;
    }

    /**
     * Metodo que chama o metodo excluir e exibe mensagens 
     */
    public void excluir() {
        int codigo = inInt("Informe o Codigo da Chacarra:");
        if (listaChacaras.excluir(codigo)) {
            this.exibeMensagem("Chacara Excluida com Sucesso!");
        } else {
            this.exibeMensagem("Nenhuma Chacarra Encontrada! ");
        }

    }

    /**
     * Metodo que chama os metodos ordenar dependendo da entrada
     */
    private void Ordenar() {
        int op = -1;

        do {
            System.out.println("=======================================");
            System.out.println("1. Ordernar por Valor");
            System.out.println("2. Ordernar por Codigo");
            System.out.println("3. Ordernar por Area total");
            System.out.println("0. Voltar");
            System.out.println("----------------------------------------");
            int opcao = inInt("Digite a Opção Desejada: ");
            Imovel imo;
            switch (opcao) {
                case 0:
                    principal();

                    break;
                case 1:
                    this.listaOrdenada = this.listaChacaras.ordenarValor();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaChacaras.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaChacaras.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("Imovel Inexistente!");
                    }
                    break;
                case 2:
                    this.listaOrdenada = this.listaChacaras.ordenarCodigo();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaChacaras.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaChacaras.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("Imovel Inexistente!");
                    }
                    break;
                case 3:
                    this.listaOrdenada = this.listaChacaras.ordenarArea();
                    codImovel = this.listarImoveis2(listaOrdenada);
                    imo = listaChacaras.consultar(codImovel);
                    if (imo != null) {
                        System.out.print("=============================================");
                        System.out.println(listaChacaras.consultar(codImovel).toString());
                        System.out.println("===========================================");
                    } else {
                        this.exibeMensagem("Imovel Inexistente!");
                       
                    }
                    break;
                default:
                    this.exibeMensagem("Opção Invalida!");
            }
        } while (op != 0);
    }

    public static void main(String[] args) {
        InterfaceChacara i = new InterfaceChacara();
        i.principal();
    }

}
