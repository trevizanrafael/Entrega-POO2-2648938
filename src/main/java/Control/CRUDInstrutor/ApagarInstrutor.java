package Control.CRUDInstrutor;

import Control.BD.ConexaoBD;
import java.sql.Connection;
import java.sql.Statement;

public class ApagarInstrutor {

    public int porCpf(String cpf) throws Exception {
        Connection conn = null;
        Statement st = null;

        try {
            conn = ConexaoBD.conectar();
            st = conn.createStatement();
            String sql = "DELETE FROM instrutor WHERE cpf = '" + cpf + "'";
            int linhas = st.executeUpdate(sql);
            System.out.println("Linhas apagadas: " + linhas);
            return linhas;
        } finally {
            if (st != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
