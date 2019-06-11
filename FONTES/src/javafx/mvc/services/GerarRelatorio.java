/*

package javafx.mvc.services;

import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Pedro Enju
 
public class GerarRelatorio {

    public static void main(String[] args) {
        
        
            Relatório de Pedido
            param: DATAI = Data Inicial, DATAF = Data Final
        
            Relatório de Caixa
            param: ID_USUARIO = ID do Usuário, DATA_ABERTURA = Data de Abertura, DATA_FECHAMENTO = Data de Fechamento
        
        
        try {
            HashMap<String, Object> param = new HashMap<>();
            GerarRelatorio.create("Cliente", param, Conexao.getInstance().getConn());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void create(String archiveName, HashMap<String, Object> param, Connection conn) throws Exception {

        try {
            /* Caminho do Relatório 
            InputStream arquivo = GerarRelatorio.class.getClass().getResourceAsStream("/javafx/mvc/report/" + archiveName + "Report.jrxml");

            /* Compila o Relatório 
            JasperReport rep = JasperCompileManager.compileReport(arquivo);

            /* Indica os parâmetros do Relatório 
            JasperPrint fillRep = JasperFillManager.fillReport(rep, param, conn);

            /* Cria o Relatório   false = Para evitar que ele feche a aplicação inteira 
            JasperViewer jrv = new JasperViewer(fillRep, false);

            /* Abre o Relatório 
            jrv.setVisible(true);
        } catch (JRException e) {
            throw new Exception(e.getMessage());
        }

    }

}
*/