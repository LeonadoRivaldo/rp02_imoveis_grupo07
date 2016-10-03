/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistenciaDados;

import imovel.Imovel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class ObjectCrud {

    //<editor-fold defaultstate="collapsed" desc="Salvar objeto no hd">
    public static void salvarObjeto(Imovel imovel, String tipo) {
        try {
            ObjectOutputStream oos = null;

            String filePath = System.getenv("APPDATA") + "\\.Imobiliária " + "\\" + tipo + "\\";

            FileOutputStream fOutS = new FileOutputStream(filePath + imovel.getCodigo() + ".ser");
            oos = new ObjectOutputStream(fOutS);

            oos.writeObject(imovel);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(ObjectCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Salvar lista de objetos no hd">
    public static void saveObjectFromList(List<Imovel> list, String tipo) {

        ObjectOutputStream oos = null;
        String filePath = System.getenv("APPDATA") + "\\.Imobiliária" + "\\" + tipo + "\\";
        int i = 0;
        for (Imovel imovel : list) {
            try {

                FileOutputStream fOutS = new FileOutputStream(filePath + i + ".ser");
                oos = new ObjectOutputStream(fOutS);

                oos.writeObject(imovel);

                i++;

            } catch (IOException ex) {
                Logger.getLogger(ObjectCrud.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(ObjectCrud.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Criar uma lista com os objetos previamente salvos">
    public static List<Imovel> readObject(String tipo) {

        List<Imovel> list = new ArrayList();

        File folder = new File(System.getenv("APPDATA") + "\\.Imobiliária\\" + tipo);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                try {
                    String filePath = System.getenv("APPDATA") + "\\.Imobiliária\\" + tipo + "\\" + file.getName();

                    FileInputStream fin = new FileInputStream(filePath);
                    ObjectInputStream ois = new ObjectInputStream(fin);
                    Imovel imovel = (Imovel) ois.readObject();

                    list.add(imovel);
                    ois.close();
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(ObjectCrud.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return list;

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Deletar um objeto através do id no hd">
    public static void deleteObject(Imovel imovel, String tipo) {
        File file = new File(System.getenv("APPDATA") + "\\.Imobiliária\\" + tipo + "\\" + imovel.getCodigo() + ".ser");
        file.delete();
    }
    //</editor-fold>

}
