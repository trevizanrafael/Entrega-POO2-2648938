package Control.CRUDAluno;

import Control.BD.ConexaoBD;
import Model.Aluno;
import java.sql.*;

public class ConsultarAluno {

    public Aluno buscarPorCpf(String cpf) throws Exception {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = ConexaoBD.conectar();
            st = conn.createStatement();
            rs = st.executeQuery(
                "SELECT id_aluno, cpf, nome, usuario, senha, treino_id, instrutor_id " +
                "FROM aluno WHERE cpf = '" + cpf + "'"
            );

            if (rs.next()) {
                Aluno a = new Aluno();
                a.setIdAluno(rs.getInt("id_aluno"));
                a.setCpf(rs.getString("cpf"));
                a.setNome(rs.getString("nome"));
                a.setUsuario(rs.getString("usuario"));
                a.setSenha(rs.getString("senha"));
                Object t = rs.getObject("treino_id");
                Object i = rs.getObject("instrutor_id");
                if (t != null) a.setTreinoId((Integer) t);
                if (i != null) a.setInstrutorId((Integer) i);
                return a;
            }

            return null;

        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
