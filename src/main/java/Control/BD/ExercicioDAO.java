package Control.BD;

import Model.Exercicio;
import java.sql.*;
import java.util.List;

public class ExercicioDAO {

    public void criarTabela() throws Exception {
        try (Connection con = ConexaoBD.conectar();
             Statement st = con.createStatement()) {

            st.executeUpdate(
                "CREATE TABLE IF NOT EXISTS exercicio (" +
                "  id_exercicio SERIAL PRIMARY KEY," +
                "  treino_id    INT," +
                "  nome         TEXT," +
                "  peso         TEXT," +
                "  repeticoes   TEXT" +
                ");"
            );
        }
    }

    // gera um novo treino_id sequencial (começando de 1000001)
    public int gerarNovoTreinoId() throws Exception {
        try (Connection con = ConexaoBD.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT MAX(treino_id) FROM exercicio")) {

            int base = 1000000;

            if (rs.next()) {
                int max = rs.getInt(1);
                if (rs.wasNull() || max < base) {
                    return base + 1;
                } else {
                    return max + 1;
                }
            }
            return base + 1;
        }
    }

    // salva a lista de exercícios amarrados a um treino_id
    public void salvar(List<Exercicio> lista, int treinoId) throws Exception {
        if (lista == null || lista.isEmpty()) return;

        try (Connection con = ConexaoBD.conectar();
             PreparedStatement ps = con.prepareStatement(
                 "INSERT INTO exercicio (treino_id, nome, peso, repeticoes) VALUES (?,?,?,?)"
             )) {

            con.setAutoCommit(false);

            for (Exercicio e : lista) {
                if (e == null || e.getNome() == null || e.getNome().trim().isEmpty()) {
                    continue;
                }

                ps.setInt(1, treinoId);
                ps.setString(2, e.getNome());
                ps.setString(3, e.getPeso());
                ps.setString(4, e.getRepeticoes());
                ps.addBatch();
            }

            ps.executeBatch();
            con.commit();
        }
    }
}
