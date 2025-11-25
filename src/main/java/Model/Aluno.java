package Model;

public class Aluno extends Usuario {
    private int idAluno;           // gerado automaticamente no banco
    private Integer instrutorId;   // FK do instrutor responsável
    private Integer treinoId;      // FK do treino atual

    public Aluno() {}

    public Aluno(String CPF, String usuario, String senha, String nome,
                 Integer instrutorId, Integer treinoId) {
        super(CPF, usuario, senha, nome);
        this.instrutorId = instrutorId;
        this.treinoId = treinoId;
    }

    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }

    public Integer getInstrutorId() { return instrutorId; }
    public void setInstrutorId(Integer instrutorId) { this.instrutorId = instrutorId; }

    public Integer getTreinoId() { return treinoId; }
    public void setTreinoId(Integer treinoId) { this.treinoId = treinoId; }
}
