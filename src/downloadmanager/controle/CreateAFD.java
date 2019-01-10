/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager.controle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Fabio
 */
public final class CreateAFD {

    String FILENAME;

    public CreateAFD() {
    }

    public CreateAFD(String filename) {
        this.FILENAME = filename;
    }

    public void createFile() {
        if (!FILENAME.isEmpty()) {
            openFile(FILENAME);
        }
    }

    @SuppressWarnings("empty-statement")
    public void openFile(String filename) {

        System.out.println("Abrindo aquivo " + filename);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String sCurrentLine, cabecalho = "1", tempLine = null;
            String directory = System.getenv("SystemDrive").concat(File.separator)
                    .concat("Marcacao").concat(File.separator)
                    .concat("Accept").concat(File.separator);
            String nfile = directory;

            ArrayList<String> mAFD = new ArrayList<>();

            int linha = 0;

            while ((sCurrentLine = br.readLine()) != null) {

                linha++;
                if (cabecalho.equals(sCurrentLine.substring(9, 10)) && sCurrentLine.length() < 232) {

                    System.err.println("Linha " + linha + " com problema[!] > Tipo: " + Integer.parseInt(sCurrentLine.substring(9, 10)));
                    tempLine = sCurrentLine;
                    System.err.println("Saltando linha " + linha);

                } else {

                    if (null != tempLine) {
                        String concat = tempLine.concat(sCurrentLine);
                        sCurrentLine = concat;
                        tempLine = null;
                    }

                    String index = sCurrentLine.substring(9, 10);
                    switch (Integer.parseInt(index)) {
                        case 1: {
                            System.out.println("Tipo do registro: " + index + "> Linha: " + linha + "> Tamanho: " + sCurrentLine.length());
                            mAFD.add(sCurrentLine);
                            nfile += sCurrentLine.substring(187, 204).concat("_")
                                    .concat(sCurrentLine.substring(204, 212)).concat("-")
                                    .concat(sCurrentLine.substring(212, 220)).concat(".txt");
                            System.out.println("Nome do AFD: " + nfile);
                        }
                        break;
                        case 2: {
                            mAFD.add(sCurrentLine);
                        }
                        break;
                        case 3: {
                            mAFD.add(sCurrentLine);
                        }
                        break;
                        case 4: {
                            mAFD.add(sCurrentLine);
                        }
                        break;
                        case 5: {
                            mAFD.add(sCurrentLine);
                        }
                        break;
                        case 0: {
                            System.out.println("Fechando arquivo " + nfile);
                            mAFD.add(sCurrentLine);
                            createFileAFD(nfile, mAFD);
                            nfile = directory;
                            mAFD.clear();
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void createFileAFD(String filename, ArrayList<String> data) {
        File arquivo = new File(filename);
        System.out.println("AFD " + filename);
        try (FileWriter fw = new FileWriter(arquivo)) {

            for (String linha : data) {
                if (linha != null) {
                    fw.append(linha.concat("\n"));
                }
            }
            fw.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
