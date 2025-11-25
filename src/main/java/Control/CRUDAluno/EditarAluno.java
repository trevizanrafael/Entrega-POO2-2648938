package Control.CRUDAluno;

import Control.BD.ConexaoBD;
import Control.Hash.HashSenha;
import Model.Aluno;
import java.sql.Connection;
import java.sql.Statement;

public class EditarAluno {

    public void executar(Aluno aluno) throws Exception {
        Connection conn = null;
        Statement st = null;

        try {
            conn = ConexaoBD.conectar();
            st   = conn.createStatement();

            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE aluno SET ");
            sb.append("nome='").append(aluno.getNome().replace("'", "''")).append("', ");
            sb.append("usuario='").append(aluno.getUsuario().replace("'", "''")).append("', ");
            sb.append("treino_id=").append(
                    aluno.getTreinoId() == null ? "NULL" : aluno.getTreinoId()
            ).append(", ");
            sb.append("instrutor_id=").append(
                    aluno.getInstrutorId() == null ? "NULL" : aluno.getInstrutorId()
            );

            // senha nova só se o campo não estiver vazio
            String senhaPura = aluno.getSenha();
            if (senhaPura != null && !senhaPura.isBlank()) {
                String senhaHash = HashSenha.sha256(senhaPura);
                sb.append(", senha='").append(senhaHash.replace("'", "''")).append("'");
            }

            sb.append(" WHERE cpf='").append(aluno.getCpf()).append("'");

            String sql = sb.toString();
            st.executeUpdate(sql);

        } finally {
            if (st   != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
