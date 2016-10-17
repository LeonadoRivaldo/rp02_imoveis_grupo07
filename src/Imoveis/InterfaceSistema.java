/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

import static Imoveis.EntradasTeclado.div;
import static Imoveis.EntradasTeclado.inDouble;
import static Imoveis.EntradasTeclado.inInt;
import static Imoveis.EntradasTeclado.inString;
import Imoveis.apartamento.Apartamento;
import Imoveis.chacara.Chacara;
import Imoveis.salaComercial.SalaComercial;
import Imoveis.terreno.Terreno;
import java.util.List;

/**
 *
 * @author leona_000
 */
public abstract class InterfaceSistema {

    /**
     * metodo abstrato somente para definir um padrao nas interfaces.
     */
    //  protected abstract void editarImovel(String atributo, Imovel imovel);
    /**
     * metodo abstrato somente para definir um padrao nas interfaces.
     */
    protected abstract void criarImovel();

    /**
     * metodo abstrato somente para definir um padrao nas interfaces.
     */
    public abstract void principal();

    /**
     * função para exibir mensagens no sistema
     *
     * @param mensagem mensagem que vai ser exibida para o usuario
     */
    protected void exibeMensagem(String mensagem) {
        System.out.println("\n===========================================================");
        System.out.println(mensagem.toUpperCase());
        System.out.println("===========================================================\n");
    }

    /**
     * Função para exibir uma lista com todos os imoveis na lista de imoveis que
     * deve ser passada pro parametro e retorna um inteiro com o codigo do
     * imovel
     *
     * @param lista o objeto da lista de cada interface, para cada sessao do
     * sistema
     * @return retorno é o codigo do imovel para que o imovel seja exibido
     */
    public int listaImoveis(ImobiliariaCrud lista) {
        List<Imovel> list = lista.getListaImoveis();
        int imovelCodig;
        div();
        for (Imovel imovel : list) {
            System.out.println(imovel.getCodigoObj() + " - Logradouro: " + imovel.getLogradouro() + " Valor:" + imovel.getValor());
        }
        div();
        return inInt("Digite o codigo do imovel: ");
    }

    public int listaImoveis2(List<Imovel> lista) {
        int imovelCodig;
        div();
        for (Imovel imovel : lista) {
            System.out.println(imovel.getCodigoObj() + " - Logradouro: " + imovel.getLogradouro() + " Valor:" + imovel.getValor());
        }
        div();
        return inInt("Digite o codigo do imovel: ");
    }

    public boolean editarImovel(Imovel imovel, ImobiliariaCrud lista) {
        Terreno t = null;
        Apartamento a = null;
        SalaComercial sc = null;
        Chacara ch = null;
        Residencia r = null;
        Predio p = null;

        String atributo;
        div();
        System.out.println(imovel.toString());
        div();
        atributo = inString("Digite o atributo que voce quer editar: ");
        /*
         */

        //Acrescentar os que faltam
        //ATRIBUTOS DE IMOVEL
        if (atributo.equalsIgnoreCase("logradouro")) {
            String logradouro = inString("----------------------------------------\nDigite o endereço do imovel:");
            imovel.setLogradouro(logradouro);
        } else if (atributo.equalsIgnoreCase("numero")) {
            int numero = inInt("------------------------------------------------\nDigite o numero do endereço:");
            imovel.setNumero(numero);
        } else if (atributo.equalsIgnoreCase("bairro")) {
            String bairro = inString("----------------------------------------\nDigite o bairro de localizacao do imovel:");
            imovel.setBairro(bairro);
        } else if (atributo.equalsIgnoreCase("cidade")) {
            String cidade = inString("----------------------------------------\nDigite a cidade de localizacao do imovel:");
            imovel.setCidade(cidade);
        } else if (atributo.equalsIgnoreCase("descricao")) {
            String descricao = inString("----------------------------------------\nEscreva uma discrição para o imovel:");
            imovel.setDescricao(descricao);
        } else if (atributo.equalsIgnoreCase("area total")) {
            double areaTotal = inDouble("----------------------------------------\nDigite o tamanho do imovel:");
            imovel.setAreaTotal(areaTotal);
        } else if (atributo.equalsIgnoreCase("valor")) {
            double valor = inDouble("----------------------------------------\nDigite o valor do imovel:");
            imovel.setValor(valor);
            //ATRIBUTOS DE PREDIO
        } else if (atributo.equalsIgnoreCase("Nome Edificio")) {
            String nomeEdificio = inString("----------------------------------------\nDigite o nome do edificio do imovel:");
            if (imovel instanceof Predio) {
                p = (Predio) imovel;
                p.setNomeEdificio(nomeEdificio);
            }
        } else if (atributo.equalsIgnoreCase("andar")) {
            int andar = inInt("----------------------------------------\nAndar do apartamento:");
            if (imovel instanceof Predio) {
                p = (Predio) imovel;
                p.setAndar(andar);
            }
        } else if (atributo.equalsIgnoreCase("Valor Condominio")) {
            double valorCondominio = inDouble("----------------------------------------\nDigite o valor do condominio:");
            if (imovel instanceof Predio) {
                p = (Predio) imovel;
                p.setValorCondominio(valorCondominio);
            }
            //ATRIBUTO DE APARTAMENTO & RESIDENCIA
        } else if (atributo.equalsIgnoreCase("numero de quartos")) {
            int numeroDeQuartos = inInt("----------------------------------------\nQuantidade de quartos do imovel:");
            if (imovel instanceof Apartamento) {
                a = (Apartamento) imovel;
                a.setNumeroDeQuartos(numeroDeQuartos);
            } else if (imovel instanceof Residencia) {
                r = (Residencia) imovel;
                r.setNroQuartos(numeroDeQuartos);
            }
        } else if (atributo.equalsIgnoreCase("ano de construcao")) {
            int anoDeConstrucao = inInt("----------------------------------------\nAno de construcao:");
            if (imovel instanceof Apartamento) {
                a = (Apartamento) imovel;
                a.setAnoDeConstrucao(anoDeConstrucao);
            } else if (imovel instanceof Residencia) {
                r = (Residencia) imovel;
                r.setAnoConstrucao(anoDeConstrucao);
            }
        } else if (atributo.equalsIgnoreCase("numero de vagas na garagem")) {
            int numeroDeVagasNaGaragem = inInt("----------------------------------------\nNumero de vagas de garagem:");
            if (imovel instanceof Apartamento) {
                a = (Apartamento) imovel;
                a.setNumeroDeVagasNaGaragem(numeroDeVagasNaGaragem);
            } else if (imovel instanceof Residencia) {
            }
        }//ATRIBUTO DE APARTAMENTO 
        else if (atributo.equalsIgnoreCase("numero do apartamento")) {
            int numeroDoApartamento = inInt("----------------------------------------\nNumero do apartamento:");
            if (imovel instanceof Apartamento) {
                a = (Apartamento) imovel;
                a.setNumeroDoApartamento(numeroDoApartamento);
            }
        }//ATRIBUTOS DE TERRENO
        else if (atributo.equalsIgnoreCase("dimensao frente")) {
            if (imovel instanceof Terreno) {
                t = (Terreno) imovel;
                t.setDimensaoFrente(inDouble("----------------------------------------\nDigite a dimensão da frente:"));
            }
        } else if (atributo.equalsIgnoreCase("dimensao lado")) {
            if (imovel instanceof Terreno) {
                t = (Terreno) imovel;
                t.setDimensaoLado(inDouble("----------------------------------------\nDigite a dimensão do lado:"));
            }
        }//ATRIBUTO DE RESIDENCIA
        else if (atributo.equalsIgnoreCase("area construida")) {
            double areaConstruida = inDouble("----------------------------------------\nInforme a Area Construida do Imovel:");
            if (imovel instanceof Residencia) {
                r = (Residencia) imovel;
                r.setAreaConstruida(areaConstruida);
            }
        }//ATRIBUTO DE CHACARA
        else if (atributo.equalsIgnoreCase("Distancia da Cidade")) {
            double distanciaCidade = inDouble("----------------------------------------\nInforme a Distancia da Cidade:");
            if (imovel instanceof Chacara) {
                ch = (Chacara) imovel;
            }
            ch.setDistanciaCidade(distanciaCidade);
        }//ATRIBUTOS DE SALA COMERCIAL
        else if (atributo.equalsIgnoreCase("numero banheiros")) {
            int nroBanheiros = inInt("----------------------------------------\nInforme o numero de banheiros:");
            if (imovel instanceof SalaComercial) {
                sc = (SalaComercial) imovel;
                sc.setNroBanheiros(nroBanheiros);
            }
        } else if (atributo.equalsIgnoreCase("numero sala")) {
            int nroSala = inInt("----------------------------------------\nInforme o nnumero da sala:");
            if (imovel instanceof SalaComercial) {
                sc = (SalaComercial) imovel;
                sc.setNroSala(nroSala);
            }
        } else {
            this.exibeMensagem("Opção invalida");
            this.editarImovel(imovel, lista);
        }

        div();
        if (inString("Deseja editar outro atributo??").equalsIgnoreCase("sim")) {
            this.editarImovel(imovel, lista);
        } else {
            return lista.editar(imovel.getCodigoObj(), imovel);
        }
        return false;
    }
}
