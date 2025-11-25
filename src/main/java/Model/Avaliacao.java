package Model;

public class Avaliacao {
    private Aluno aluno;
    private float coefForca;
    private float peso;
    private float bf; 

    public Avaliacao() {}

    public Avaliacao(Aluno aluno, float coefForca, float peso, float bf) {
        this.aluno = aluno;
        this.coefForca = coefForca;
        this.peso = peso;
        this.bf = bf;
    }

    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }

    public float getCoefForca() { return coefForca; }
    public void setCoefForca(float coefForca) { this.coefForca = coefForca; }

    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }

    public float getBf() { return bf; }
    public void setBf(float bf) { this.bf = bf; }
}
