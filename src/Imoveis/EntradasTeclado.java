package Imoveis;

import java.util.Scanner;

/**
 * Classe que auxilia na captura de informações inseridas pelo usuário
 *
 * @author Giliardi Schmidt
 */
public abstract class EntradasTeclado {

    private static Scanner input = new Scanner(System.in);

    /**
     * Metodo que exibe uma mensagem recebida por parâmetro para o usuario e
     * captura o proximo int que será digitado
     *
     * @param mensagem Recebe a mensagem que será exibida ao usuário
     * @return Retorna o int inserido pelo usuário
     */
    public static int inInt(String mensagem) {
        System.out.println(mensagem);
        int in = 1;
        try {
            in = input.nextInt();
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Ocorreu algum erro, tente novamente");
            System.out.println("");
            input.nextLine();
            return inInt(mensagem);
        }
        return in;
    }
    
        /**
     * Metodo que exibe uma mensagem recebida por parâmetro para o usuario e
     * captura o proximo double que será digitado
     *
     * @param mensagem Recebe a mensagem que será exibida ao usuário
     * @return Retorna o double inserido pelo usuário
     */
    public static double inDouble(String mensagem) {
        System.out.println(mensagem);
        double in;
        try {
            in = input.nextDouble();
            input.nextLine();
        } catch (Exception e) {
            System.out.println("Ocorreu algum erro, tente novamente");
            System.out.println("");
            input.nextLine();
            return inDouble(mensagem);
        }
        return in;
    }

    /**
     * Metodo que exibe uma mensagem recebida por parâmetro para o usuario e
     * captura a proxima String que será digitado
     *
     * @param mensagem Recebe a mensagem que será exibida ao usuário
     * @return Retorna a String inserido pelo usuário
     */
    public static String inString(String mensagem) {
        String in;
        System.out.println(mensagem);
        in = input.nextLine();
        return in;
    }

    /**
     * Metodo que exibe um separador (####) para o usuario
     */
    public static void div() {
        System.out.println("----------------------------------------------------");
    }
}
