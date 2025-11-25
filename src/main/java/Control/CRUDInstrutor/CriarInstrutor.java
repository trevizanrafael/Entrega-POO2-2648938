package Control.CRUDInstrutor;

import Control.BD.ConexaoBD;
import Control.Hash.HashSenha;
import Model.Instrutor;
import java.sql.Connection;
import java.sql.Statement;

public class CriarInstrutor {

    public void executar(Instrutor instr) throws Exception {
        Connection conn = null;
        Statement st = null;

        String senhaPura = instr.getSenha();
        String senhaHash = HashSenha.sha256(senhaPura);

        String sql = "INSERT INTO instrutor (cpf, nome, usuario, senha) VALUES (" +
                "'" + instr.getCpf() + "', " +
                "'" + instr.getNome().replace("'", "''") + "', " +
                "'" + instr.getUsuario().replace("'", "''") + "', " +
                "'" + senhaHash.replace("'", "''") + "'" +
                ")";

        try {
            conn = ConexaoBD.conectar();
            st = conn.createStatement();
            st.executeUpdate(sql);
            System.out.println("Instrutor criado com sucesso!");
        } finally {
            if (st != null) st.close();
            if (conn != null) conn.close();
        }
    }
}
