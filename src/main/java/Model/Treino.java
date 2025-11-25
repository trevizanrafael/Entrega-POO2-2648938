package Model;

import java.util.ArrayList;
import java.util.List;


public class Treino {
    private List<Exercicio> exercicios = new ArrayList<>();
    private int duracao; // em minutos, se preferir

    public Treino() {}

    public Treino(List<Exercicio> exercicios, int duracao) {
        if (exercicios != null) this.exercicios = exercicios;
        this.duracao = duracao;
    }

    public List<Exercicio> getExercicios() { return exercicios; }
    public void setExercicios(List<Exercicio> exercicios) {
        this.exercicios = (exercicios != null) ? exercicios : new ArrayList<>();
    }

    public int getDuracao() { return duracao; }
    public void setDuracao(int duracao) { this.duracao = duracao; }
}
