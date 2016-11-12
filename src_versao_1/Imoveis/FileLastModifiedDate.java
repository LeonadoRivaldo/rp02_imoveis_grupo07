/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Imoveis;

/**
 *
 * @author leona_000
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class FileLastModifiedDate {

    public static boolean lastModifiedIsEqual(String dir) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        File file = new File(dir + "\\listaImoveis.csv");
        File file2 = new File(dir + "\\listaImoveis");
        long data = 0;
        if (file.exists()) {
            FileInputStream inFile = new FileInputStream(new File(dir + "\\datacriacao.csv"));
            BufferedReader buff = new BufferedReader(new InputStreamReader(inFile, "UTF-8"));
            data = Long.parseLong(buff.readLine());
            buff.close();
        }
        boolean r = data == file.lastModified();
        return r;
    }
}
