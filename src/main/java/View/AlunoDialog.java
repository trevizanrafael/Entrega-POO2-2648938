package View;

import Model.Aluno;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AlunoDialog extends JDialog {

    private final JTextField tfCpf         = new JTextField();
    private final JTextField tfNome        = new JTextField();
    private final JTextField tfUsuario     = new JTextField();
    private final JPasswordField pfSenha   = new JPasswordField();
    private final JTextField tfInstrutorId = new JTextField();
    private final JTextField tfTreinoId    = new JTextField();

    private final JButton btCancelar = new JButton("Cancelar");
    private final JButton btSalvar   = new JButton("Salvar");
    private final JButton btFechar   = new JButton("Fechar");

    private boolean confirmado = false;
    private boolean somenteLeitura = false;

    // ========= MODO "ADICIONAR" =========
    public AlunoDialog(Frame parent) {
        super(parent, "Adicionar Aluno", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        tfCpf.setColumns(20);
        tfNome.setColumns(20);
        tfUsuario.setColumns(20);
        pfSenha.setColumns(20);
        tfInstrutorId.setColumns(20);
        tfTreinoId.setColumns(20);

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

        form.add(new JLabel("ID Instrutor:"));
        form.add(tfInstrutorId);

        form.add(new JLabel("ID Treino:"));
        form.add(tfTreinoId);

        // Botões
        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botoes.add(btCancelar);
        botoes.add(btSalvar);
        botoes.add(btFechar);

        // Ações dos botões
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

        // No modo padrão (adicionar), não mostra o "Fechar"
        btFechar.setVisible(false);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(botoes, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
    }

    // ========= MODO "EDITAR" (somenteLeitura = false) =========
    public AlunoDialog(Frame parent, Aluno existente) {
        this(parent, existente, false);
    }

    // ========= MODO "EDITAR" ou "CONSULTAR" =========
    public AlunoDialog(Frame parent, Aluno existente, boolean somenteLeitura) {
        this(parent);                 // monta a tela base
        this.somenteLeitura = somenteLeitura;

        if (existente != null) {
            tfCpf.setText(existente.getCpf());
            tfNome.setText(existente.getNome());
            tfUsuario.setText(existente.getUsuario());

            if (existente.getInstrutorId() != null) {
                tfInstrutorId.setText(String.valueOf(existente.getInstrutorId()));
            }
            if (existente.getTreinoId() != null) {
                tfTreinoId.setText(String.valueOf(existente.getTreinoId()));
            }
        }

        if (somenteLeitura) {
            // CONSULTA
            setTitle("Consultar Aluno");

            tfCpf.setEditable(false);
            tfNome.setEditable(false);
            tfUsuario.setEditable(false);
            pfSenha.setEditable(false);
            tfInstrutorId.setEditable(false);
            tfTreinoId.setEditable(false);

            btSalvar.setVisible(false);
            btCancelar.setVisible(false);
            btFechar.setVisible(true);
        } else {
            // EDIÇÃO
            setTitle("Editar Aluno");

            tfCpf.setEditable(false); // normalmente não deixa mudar CPF
            tfNome.setEditable(true);
            tfUsuario.setEditable(true);
            pfSenha.setEditable(true);
            tfInstrutorId.setEditable(true);
            tfTreinoId.setEditable(true);

            btSalvar.setVisible(true);
            btCancelar.setVisible(true);
            btFechar.setVisible(false);
        }
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public Aluno getAluno() {
        Aluno a = new Aluno();
        a.setCpf(tfCpf.getText().trim());
        a.setNome(tfNome.getText().trim());
        a.setUsuario(tfUsuario.getText().trim());

        // Nota: NÃO colocamos aqui a senha se o campo estiver vazio.
        String senhaDigits = new String(pfSenha.getPassword()).trim();
        if (!senhaDigits.isEmpty()) {
            // usuário digitou nova senha -> retornamos essa senha "pura"
            a.setSenha(senhaDigits);
        } else {
            // usuário NÃO digitou senha -> sinalizamos com null para não tocar na senha
            a.setSenha(null);
        }

        if (!tfInstrutorId.getText().trim().isEmpty()) {
            try {
                a.setInstrutorId(Integer.parseInt(tfInstrutorId.getText().trim()));
            } catch (NumberFormatException ex) {
                a.setInstrutorId(null);
            }
        } else {
            a.setInstrutorId(null);
        }

        if (!tfTreinoId.getText().trim().isEmpty()) {
            try {
                a.setTreinoId(Integer.parseInt(tfTreinoId.getText().trim()));
            } catch (NumberFormatException ex) {
                a.setTreinoId(null);
            }
        } else {
            a.setTreinoId(null);
        }

        return a;
    }
}
