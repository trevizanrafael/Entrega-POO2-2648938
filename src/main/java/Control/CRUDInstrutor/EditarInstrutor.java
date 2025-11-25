package Control.CRUDInstrutor;

import Control.BD.ConexaoBD;
import Model.Instrutor;
import java.sql.Connection;
import java.sql.Statement;

public class EditarInstrutor {

    public void executar(Instrutor i) throws Exception {
        Connection conn = null;
        Statement st = null;

        String sql = "UPDATE instrutor SET " +
                "nome = '" + i.getNome().replace("'", "''") + "', " +
                "usuario = '" + i.getUsuario().replace("'", "''") + "', " +
                "senha = '" + i.getSenha().replace("'", "''") + "' " +
                "WHERE cpf = '" + i.getCpf() + "'";

        try {
            conn = ConexaoBD.conectar();
            st = conn.createStatement();
            st.executeUpdate(sql);
            System.out.println("Instrutor atualizado com sucesso!");
        } finally {
            if (st != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
