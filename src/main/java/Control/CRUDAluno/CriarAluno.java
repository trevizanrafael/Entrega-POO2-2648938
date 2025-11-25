package Control.CRUDAluno;

import Control.BD.ConexaoBD;
import Control.Hash.HashSenha;
import Model.Aluno;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class CriarAluno {

    // "só cria" = apenas insere um aluno novo no banco
    public void executar(Aluno aluno) throws Exception {
        Connection conn = null;
        Statement st   = null;
        ResultSet rs   = null;

        try {
            conn = ConexaoBD.conectar();
            st   = conn.createStatement();

            Integer instrutorId = aluno.getInstrutorId();

            // 1) Se tiver instrutor vinculado, checa se já tem 4 alunos
            if (instrutorId != null) {
                String sqlCount = "SELECT COUNT(*) AS total " +
                                  "FROM aluno " +
                                  "WHERE instrutor_id = " + instrutorId;

                rs = st.executeQuery(sqlCount);

                int total = 0;
                if (rs.next()) {
                    total = rs.getInt("total");
                }

                if (total >= 4) {
                    // estoura erro para a tela tratar e mostrar pro admin
                    throw new IllegalStateException(
                        "Esse instrutor já possui o limite de 4 alunos."
                    );
                }

                // fecha o ResultSet antes de seguir
                rs.close();
                rs = null;
            }

            // 2) Monta a senha hasheada
            String senhaPura  = aluno.getSenha();
            String senhaHash  = HashSenha.sha256(senhaPura);

            // 3) Monta o INSERT no estilo da sua professora
            String sql = "INSERT INTO aluno (cpf, nome, usuario, senha, treino_id, instrutor_id) VALUES (" +
                    "'" + aluno.getCpf() + "', " +
                    "'" + aluno.getNome().replace("'", "''") + "', " +
                    "'" + aluno.getUsuario().replace("'", "''") + "', " +
                    "'" + senhaHash.replace("'", "''") + "', " +
                    // treino_id e instrutor_id podem ser null
                    (aluno.getTreinoId() == null ? "NULL" : aluno.getTreinoId()) + ", " +
                    (instrutorId == null ? "NULL" : instrutorId) +
                    ")";

            st.executeUpdate(sql);
            System.out.println("Aluno criado com sucesso!");

        } finally {
            // fecha recursos manualmente, igual prof faz
            if (rs   != null) rs.close();
            if (st   != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
