/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager.controle;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabio
 */
public class Servico {

    public void Servico() {
    }

    public void processar() throws NullPointerException {

        String baseURL = "https://www.ahgora.com.br/arquivos/afd?";

        String[] empresas = {"5e747fefe3c32f9c95bdd912756de753", "856e4aa6f67bd05e917bc4e3d92155cb"};
        for (String empresa : empresas) {

            System.out.println("Consistindo unidade " + System.getenv("SystemDrive"));
            DateFormat format = new SimpleDateFormat("ddMMyyyy");
            Date date_fim = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, -1);
            Date date_ini = calendar.getTime();

            String chave_empresa = "&chave_empresa=" + empresa;
            String data_inicial = "&data_inicial=" + format.format(date_ini);
            String data_final = "&data_final=" + format.format(date_fim);
            String cabecalho = "&cabecalho=1";

            System.err.println("Periodo " + data_inicial + " a " + data_final);
            String path = System.getProperty("user.dir").concat(File.separator);
            String filename = new File(path + empresa + ".txt").getAbsolutePath();
            System.out.println("File: " + filename);

            String[] params = {chave_empresa, data_inicial, data_final, cabecalho};

            for (String argumento : params) {
                baseURL = baseURL.concat(argumento);
            }

            try {
                Download download = new Download();
                download.doDownload(baseURL, filename);
            } catch (IOException ex) {
                Logger.getLogger(Servico.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
