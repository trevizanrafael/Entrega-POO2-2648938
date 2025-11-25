package Model;




public class Instrutor extends Usuario {
    private Integer idInstrutor;

    public Instrutor() {}

    public Instrutor(Integer idInstrutor, String CPF, String usuario, String senha, String nome) {
        super(CPF, usuario, senha, nome);
        this.idInstrutor = idInstrutor; 
    }

    public Integer getIdInstrutor() {
        return idInstrutor;
    }

    public void setIdInstrutor(Integer idInstrutor) {
        this.idInstrutor = idInstrutor;
    }
}
