package Control.CRUDInstrutor;

import Control.BD.ConexaoBD;
import Model.Instrutor;
import java.sql.*;

public class ConsultarInstrutor {

    public Instrutor buscarPorCpf(String cpf) throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBD.conectar();
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM instrutor WHERE cpf = '" + cpf + "'");

            if (rs.next()) {
                Instrutor i = new Instrutor();
                i.setCpf(rs.getString("cpf"));
                i.setNome(rs.getString("nome"));
                i.setUsuario(rs.getString("usuario"));
                i.setSenha(rs.getString("senha"));
                return i;
            }

            return null;

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
