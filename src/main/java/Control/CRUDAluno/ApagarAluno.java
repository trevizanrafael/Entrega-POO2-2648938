package Control.CRUDAluno;

import Control.BD.ConexaoBD;
import java.sql.Connection;
import java.sql.Statement;

public class ApagarAluno {

    public int porCpf(String cpf) throws Exception {
        Connection conn = null;
        Statement st = null;

        try {
            conn = ConexaoBD.conectar();
            st = conn.createStatement();

            String sql = "DELETE FROM aluno WHERE cpf = '" + cpf + "'";
            int linhas = st.executeUpdate(sql); // retorna quantas linhas foram apagadas
            System.out.println("Linhas apagadas: " + linhas);
            return linhas;

        } finally {
            if (st != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
