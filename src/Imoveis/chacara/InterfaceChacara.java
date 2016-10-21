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

    String logradouro, bairro, cidade, descricao;
    int numero, numeroDeQuartos, anoDeConstrucao;
    double areaTotal, valor, areaConstruida, distanciaCidade;

    @Override
    public void principal() {
        listaChacaras.setTipoImovel(Tipo.CHACARA);
        if (!listaChacaras.lerArquivo()) {
            this.exibeMensagem("Arquivo não encontrado");
        }
        listaChacaras.setLastCod();

        int opcao = -1;
        do {
            System.out.println("Chacara!");
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
    protected void criarImovel() {
        logradouro = inString("\nDigite o endereço do imovel: ");
        numero = inInt("\nInforme o Número do Imovel: ");
        bairro = inString("\nInforme o Bairro: ");
        cidade = inString("\nInforme a Cidade: ");
        descricao = inString("\nInforme a Descrição: ");
        areaTotal = inDouble("\nInforme a Area Total do Imovel: ");
        valor = inDouble("\nInforme o valor do Imovel:");
        areaConstruida = inDouble("\nInforme a Area Construida do Imovel: ");
        anoDeConstrucao = inInt("\nInforme o Ano da Construção: ");
        numeroDeQuartos = inInt("\nInforme o Número de Quartos: ");
        distanciaCidade = inDouble("\nInforme a Distancia da Cidade: ");
        chac = new Chacara(distanciaCidade, anoDeConstrucao, numeroDeQuartos, areaConstruida, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaChacaras.incluir(chac)) {
            this.exibeMensagem("\nChacara Incluida com Sucesso!");
            if (!listaChacaras.escreverArquivo()) {
                this.exibeMensagem("Erro ao escrever o arquivo");
            }
        } else {
            this.exibeMensagem("\nOcorreu Algum Erro");
        }
    }

    public Chacara consultar() {
        System.out.println("\n1. Pesquisar");
        System.out.println("2. Listar Todos");
        int o = inInt("Opção:");
        switch (o) {
            case 1:
                chac = (Chacara) listaChacaras.consultar(inInt("\nDigite o Codigo do Imovel: "));
                if (chac != null) {
                    return chac;
                } else {
                    this.exibeMensagem("Chacara Não Encontrada!");
                }
                break;
            case 2:
                int imovelCod = this.listaImoveis(listaChacaras);
                chac = (Chacara) listaChacaras.consultar(imovelCod);
                if (chac != null) {
                    return chac;
                } else {
                    this.exibeMensagem("Chacara Não Encontrada!");
                }
                break;
            default:
                this.exibeMensagem("Opção Invalida!");
        }
        return null;
    }

    public void excluir() {
        int codigo = inInt("Informe o Codigo da Chacarra:");
        if (listaChacaras.excluir(codigo)) {
            this.exibeMensagem("Chacara Excluida com Sucesso!");
        } else {
            this.exibeMensagem("Nenhuma Chacarra Encontrada! ");
        }

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
                this.listaOrdenada = this.listaChacaras.ordenarValor();
                int codImovel = this.listaImoveis2(listaOrdenada);
                System.out.print("=============================================");
                System.out.println(listaChacaras.consultar(codImovel).toString());
                System.out.println("===========================================");
                break;
            case 2:
                this.listaOrdenada = this.listaChacaras.ordenarCodigo();
                int codCodigo = this.listaImoveis2(listaOrdenada);
                System.out.print("=============================================");
                System.out.println(listaChacaras.consultar(codCodigo).toString());
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
        InterfaceChacara i = new InterfaceChacara();
        i.principal();
    }

}
