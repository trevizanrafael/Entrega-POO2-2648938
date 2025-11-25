package Control;

import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import java.util.Objects;

public class TreinoParametrosDialog extends JDialog {
    private final JTextField tfPeso = new JTextField();
    private final JComboBox<String> cbGrupo = new JComboBox<>(new String[]{
        "PEITO","COSTAS","OMBROS","PERNAS","BÍCEPS","TRÍCEPS","ABDÔMEN","GLÚTEOS"
    });
    private final JComboBox<String> cbObjetivo = new JComboBox<>(new String[]{
        "Hipertrofia","Força","Resistência","Emagrecimento","Reabilitação"
    });
    private final JTextField tfIdade = new JTextField();

    private boolean confirmado = false;

    public static class Result {
    private final String peso;
    private final String grupo;
    private final String objetivo;
    private final String idade;

    public Result(String peso, String grupo, String objetivo, String idade) {
        this.peso = peso;
        this.grupo = grupo;
        this.objetivo = objetivo;
        this.idade = idade;
    }

    public String getPeso() { return peso; }
    public String getGrupo() { return grupo; }
    public String getObjetivo() { return objetivo; }
    public String getIdade() { return idade; }
}


    public TreinoParametrosDialog(Frame owner) {
        super(owner, "Parâmetros do Treino", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // === Filtros numéricos ===
        // Idade: apenas dígitos (inteiro)
        ((AbstractDocument) tfIdade.getDocument()).setDocumentFilter(new IntegerOnlyFilter());

        // Peso: dígitos + um separador decimal (ponto ou vírgula)
        ((AbstractDocument) tfPeso.getDocument()).setDocumentFilter(new DecimalFilter());

        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6,6,6,6);
        c.fill = GridBagConstraints.HORIZONTAL;
        int row = 0;

        c.gridx = 0; c.gridy = row; form.add(new JLabel("Peso (kg):"), c);
        c.gridx = 1; c.gridy = row++; form.add(tfPeso, c);

        c.gridx = 0; c.gridy = row; form.add(new JLabel("Grupo Muscular:"), c);
        c.gridx = 1; c.gridy = row++; form.add(cbGrupo, c);

        c.gridx = 0; c.gridy = row; form.add(new JLabel("Objetivo:"), c);
        c.gridx = 1; c.gridy = row++; form.add(cbObjetivo, c);

        c.gridx = 0; c.gridy = row; form.add(new JLabel("Idade:"), c);
        c.gridx = 1; c.gridy = row++; form.add(tfIdade, c);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btOk = new JButton("OK");
        JButton btCancelar = new JButton("Cancelar");
        botoes.add(btCancelar);
        botoes.add(btOk);

        btOk.addActionListener(e -> {
            try {
                // validações
                String idadeTxt = tfIdade.getText().trim();
                String pesoTxt  = tfPeso.getText().trim();

                if (isBlank(idadeTxt) || isBlank(pesoTxt)) {
                    throw new IllegalArgumentException("Informe peso e idade.");
                }

                // Normaliza peso: vírgula -> ponto
                pesoTxt = pesoTxt.replace(',', '.');

                int idade = Integer.parseInt(idadeTxt);
                if (idade < 10 || idade > 100) {
                    throw new IllegalArgumentException("Idade deve estar entre 10 e 100 anos.");
                }

                double peso = Double.parseDouble(pesoTxt);
                if (peso < 20.0 || peso > 300.0) {
                    throw new IllegalArgumentException("Peso deve estar entre 20 e 300 kg.");
                }

                confirmado = true;
                // Armazena normalizado de volta (opcional)
                tfPeso.setText(String.valueOf(peso));
                tfIdade.setText(String.valueOf(idade));

                dispose();
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(this, "Valores numéricos inválidos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } catch (IllegalArgumentException iae) {
                JOptionPane.showMessageDialog(this, iae.getMessage(), "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        btCancelar.addActionListener(e -> {
            confirmado = false;
            dispose();
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.CENTER);
        getContentPane().add(botoes, BorderLayout.SOUTH);

        setSize(400, 260);
        setLocationRelativeTo(owner);
    }

    public boolean isConfirmado() { return confirmado; }

    public Result getResult() {
        // devolve peso normalizado (ponto) e strings puras
        String pesoNorm = tfPeso.getText().trim().replace(',', '.');
        return new Result(
            pesoNorm,
            String.valueOf(cbGrupo.getSelectedItem()),
            String.valueOf(cbObjetivo.getSelectedItem()),
            tfIdade.getText().trim()
        );
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    // === Filtros ===
    /** Aceita só dígitos (0-9). */
    static class IntegerOnlyFilter extends DocumentFilter {
        @Override public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
            if (str != null && str.chars().allMatch(Character::isDigit)) {
                super.insertString(fb, offs, str, a);
            }
        }
        @Override public void replace(FilterBypass fb, int offs, int len, String str, AttributeSet a) throws BadLocationException {
            if (str == null || str.chars().allMatch(Character::isDigit)) {
                super.replace(fb, offs, len, str, a);
            }
        }
    }

    /** Aceita dígitos e no máximo um separador decimal ('.' ou ','). */
    static class DecimalFilter extends DocumentFilter {
        @Override public void insertString(FilterBypass fb, int offs, String str, AttributeSet a) throws BadLocationException {
            if (str != null && isAllowed(fb.getDocument(), str)) {
                super.insertString(fb, offs, str, a);
            }
        }
        @Override public void replace(FilterBypass fb, int offs, int len, String str, AttributeSet a) throws BadLocationException {
            if (str == null || isAllowed(fb.getDocument(), str)) {
                super.replace(fb, offs, len, str, a);
            }
        }
        private boolean isAllowed(Document doc, String str) {
            try {
                String current = doc.getText(0, doc.getLength());
                String next = current + str;
                // Permitir apenas dígitos e , .
                if (!next.matches("[0-9]*([\\.,]?[0-9]*)?")) return false;
                // No máximo um separador (vírgula ou ponto)
                long sepCount = next.chars().filter(ch -> ch == '.' || ch == ',').count();
                return sepCount <= 1;
            } catch (BadLocationException e) {
                return false;
            }
        }
    }
}
