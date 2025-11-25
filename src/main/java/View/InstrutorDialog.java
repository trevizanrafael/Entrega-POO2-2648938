package View;

import Model.Instrutor;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InstrutorDialog extends JDialog {

    private final JTextField tfCpf     = new JTextField();
    private final JTextField tfNome    = new JTextField();
    private final JTextField tfUsuario = new JTextField();
    private final JPasswordField pfSenha = new JPasswordField();

    private final JButton btCancelar = new JButton("Cancelar");
    private final JButton btSalvar   = new JButton("Salvar");
    private final JButton btFechar   = new JButton("Fechar");

    private boolean confirmado = false;
    private boolean somenteLeitura = false;

    // ========= MODO "ADICIONAR" =========
    public InstrutorDialog(Frame parent) {
        super(parent, "Adicionar Instrutor", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tfCpf.setColumns(20);
        tfNome.setColumns(20);
        tfUsuario.setColumns(20);
        pfSenha.setColumns(20);

        JPanel form = new JPanel(new GridLayout(0, 2, 8, 8));
        form.setBorder(new EmptyBorder(15, 20, 15, 20));

        form.add(new JLabel("CPF:"));
        form.add(tfCpf);

        form.add(new JLabel("Nome:"));
        form.add(tfNome);

        form.add(new JLabel("Usuário:"));
        form.add(tfUsuario);

        form.add(new JLabel("Senha:"));
        form.add(pfSenha);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btCancelar);
        botoes.add(btSalvar);
        botoes.add(btFechar);

        btSalvar.addActionListener(e -> {
            if (tfCpf.getText().trim().isEmpty() ||
                tfNome.getText().trim().isEmpty() ||
                tfUsuario.getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "CPF, Nome, Usuário e Senha são obrigatórios.",
                        "Aviso",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            confirmado = true;
            dispose();
        });

        btCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });

        btFechar.addActionListener(e -> dispose());

        // no modo padrão (adicionar) não usa o "Fechar"
        btFechar.setVisible(false);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(botoes, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    // ========= MODO "EDITAR" (somenteLeitura = false) =========
    public InstrutorDialog(Frame parent, Instrutor existente) {
        this(parent, existente, false);
    }

    // ========= MODO "EDITAR" ou "CONSULTAR" =========
    public InstrutorDialog(Frame parent, Instrutor existente, boolean somenteLeitura) {
        this(parent); // monta tela base "Adicionar"

        this.somenteLeitura = somenteLeitura;

        if (existente != null) {
            tfCpf.setText(existente.getCpf());
            tfNome.setText(existente.getNome());
            tfUsuario.setText(existente.getUsuario());
            // NÃO mostrar hash:
            pfSenha.setText(""); // usuário só digita se quiser trocar
        }

        if (somenteLeitura) {
            // CONSULTAR
            setTitle("Consultar Instrutor");

            tfCpf.setEditable(false);
            tfNome.setEditable(false);
            tfUsuario.setEditable(false);
            pfSenha.setEditable(false);

            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btFechar.setVisible(true);
        } else {
            // EDITAR
            setTitle("Editar Instrutor");

            tfCpf.setEditable(false); // normalmente não deixa mudar CPF
            tfNome.setEditable(true);
            tfUsuario.setEditable(true);
            pfSenha.setEditable(true);

            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btFechar.setVisible(false);
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Instrutor getInstrutor() {
        Instrutor i = new Instrutor();
        i.setCpf(tfCpf.getText().trim());
        i.setNome(tfNome.getText().trim());
        i.setUsuario(tfUsuario.getText().trim());

        // senha: se o campo vier vazio, devolve null (não alterar)
        String senhaDigits = new String(pfSenha.getPassword()).trim();
        if (!senhaDigits.isEmpty()) {
            i.setSenha(senhaDigits);  // senha "pura", DAO faz o hash
        } else {
            i.setSenha(null);
        }

        return i;
    }
}
