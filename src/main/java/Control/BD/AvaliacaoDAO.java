package Control.BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import Model.Avaliacao;

public class AvaliacaoDAO {

    public void criarTabela() throws Exception {
        try (Connection con = ConexaoBD.conectar();
             Statement st = con.createStatement()) {

            String sql = """
                CREATE TABLE IF NOT EXISTS avaliacao (
                    id_avaliacao SERIAL PRIMARY KEY,
                    aluno_id INT NOT NULL,
                    coef_forca REAL,
                    peso REAL,
                    bf REAL,
                    data_avaliacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
            """;

            st.execute(sql);
        }
    }

    public void salvar(Avaliacao a) throws Exception {
        String sql = "INSERT INTO avaliacao (aluno_id, coef_forca, peso, bf) VALUES (?, ?, ?, ?)";

        try (Connection con = ConexaoBD.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, a.getAluno().getIdAluno());
            ps.setFloat(2, a.getCoefForca());
            ps.setFloat(3, a.getPeso());
            ps.setFloat(4, a.getBf());
            ps.executeUpdate();
        }
    }
}
