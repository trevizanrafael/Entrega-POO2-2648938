package Model;

public abstract class Usuario{
    private String CPF;          
    private String usuario; 
    private String senha;
    private String nome;

    public Usuario() {}

    public Usuario(String CPF, String usuario, String senha, String nome) {
        this.CPF = CPF;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
    }

    public String getCpf() { return CPF; }
    public void setCpf(String CPF) { this.CPF = CPF; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}
