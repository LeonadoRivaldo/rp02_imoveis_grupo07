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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leona_000
 */
public class InterfaceApartamento extends InterfaceSistema {

    private ImobiliariaCrud listaApartamentos = new ImobiliariaCrud();
    private Apartamento ap = null;

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
            System.out.println("0 - sair");
            opcao = inInt("Opcao: ");

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    criarImovel();
                    break;
                case 2:
                    ap = this.consultar();
                    if (ap != null) {
                        System.out.println("\n#############################################");
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

    public Apartamento consultar() {
        System.out.println("=======================================");
        System.out.println("1 - Pesquisar");
        System.out.println("2 - Listar todos");
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
                int imovelCod = this.listaImoveis(listaApartamentos);
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

    public static void main(String[] args) {
        InterfaceApartamento i = new InterfaceApartamento();
        i.principal();
    }
}
