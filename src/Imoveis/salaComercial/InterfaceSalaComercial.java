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
        if (!listaSalaComercial.lerArquivo()) {
            this.exibeMensagem("Arquivo não encontrado");
        }
        listaSalaComercial.setLastCod();
        int opcao = -1;
        do {
            System.out.println("#############################################");
            System.out.println("1 - Incluir");
            System.out.println("2 - Consultar");
            System.out.println("3 - Editar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    criarImovel();
                    break;
                case 2:
                    sc = this.consultar();
                    if (sc != null) {
                        System.out.println("\n#############################################");
                        System.out.println(sc.toString());
                    }
                    break;
                case 3:
                    sc = consultar();
                    if (sc != null) {
                        this.editarImovel(sc, listaSalaComercial);
                    }
                    break;
                case 4:
                    int codigo = inInt("Informe o código do Imovel que deseja Excluir:");
                    if (listaSalaComercial.excluir(codigo)) {
                        this.exibeMensagem("Sala Comercial excluir com Sucesso");
                        break;
                    } else {
                        this.exibeMensagem("Não foi possivel Excluir");
                        break;
                    }

                default:
                    this.exibeMensagem("Opção invalida!");
            }
        } while (opcao != 0);

    }

    public SalaComercial consultar() {
        System.out.println("=======================================");
        System.out.println("1 - Pesquisar");
        System.out.println("2 - Listar todos");
        System.out.println("----------------------------------------");
        int o = inInt("Opção:");
        switch (o) {
            case 1:
                sc = (SalaComercial) listaSalaComercial.consultar(inInt("------------------------\nDigite o codigo do imovel:"));
                if (sc != null) {
                    return sc;
                } else {
                    this.exibeMensagem("Sala Comercial não encontrado");
                }
                break;
            case 2:
                int imovelCod = this.listarImoveis(listaSalaComercial);
                sc = (SalaComercial) listaSalaComercial.consultar(imovelCod);
                if (sc != null) {
                    return sc;
                } else {
                    this.exibeMensagem("Sala Comercial não encontrado");
                }
                break;
            default:
                this.exibeMensagem("Opção invalida!");
        }
        return null;
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
        sc = new SalaComercial(nroBanheiros, nroSala, nomeEdificio, andar, valorCondominio, logradouro, numero, bairro, cidade, descricao, areaTotal, valor);
        if (listaSalaComercial.incluir(sc)) {
            this.exibeMensagem("Sala Comercial incluida com sucesso!");
            if (!listaSalaComercial.escreverArquivo()) {
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
