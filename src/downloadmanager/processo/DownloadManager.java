/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package downloadmanager.processo;

import downloadmanager.controle.Servico;

/**
 *
 * @author Fabio
 */
public class DownloadManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NullPointerException {

        Servico service = new Servico();
        service.processar();

    }
}
