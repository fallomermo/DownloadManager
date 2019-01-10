/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager.controle;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author Fabio
 */
public class Download {

    public Download() {
    }

    public void doDownload(String baseUrl, String filename) throws IOException {

        System.out.println("------------- Start Download  -------------");

        System.out.println("Consistindo diretórios...");
        String directory = System.getenv("SystemDrive")
                .concat(File.separator)
                .concat("Marcacao")
                .concat(File.separator)
                .concat("Accept");

        File dir = new File(directory);

        if (!dir.exists()) {
            System.err.println("Diretótio principal não existe[!]");
            if (dir.mkdirs()) {
                System.out.println("Criado diretótio " + dir.getAbsolutePath());
            } else {
                System.err.println("Erro Criando diretótio " + dir.getAbsolutePath());
                System.out.println("------------- Processo Concluído -------------\n");
                return;
            }
        } else {
            System.out.println("Limpando arquivos anteriores...");
            File[] files = dir.listFiles();
            for (File file : files) {
                file.delete();
            }
        }

        download(baseUrl, filename);
    }

    private void download(String fileUrl, String filename) throws MalformedURLException, IOException {

        System.out.println("Processando URL " + fileUrl);
        InputStream in = new URL(fileUrl).openStream();
        Files.copy(in, Paths.get(filename), StandardCopyOption.REPLACE_EXISTING);

        System.out.println("Processando " + filename);
        CreateAFD createAFD = new CreateAFD(filename);
        createAFD.createFile();

        System.out.println("Excluindo " + filename);
        Files.delete(Paths.get(filename));

        System.out.println("------------- Processo Concluído -------------\n");

    }
}
