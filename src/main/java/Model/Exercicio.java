package Model;

public class Exercicio {
    private String nome;
    private String peso;
    private String repeticoes;

    public Exercicio() {}

    public Exercicio(String nome, String peso, String repeticoes) {
        this.nome = nome;
        this.peso = peso;
        this.repeticoes = repeticoes;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }

    public String getRepeticoes() { return repeticoes; }
    public void setRepeticoes(String repeticoes) { this.repeticoes = repeticoes; }
}
