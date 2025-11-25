package Control.Hash;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class HashSenha {

    public static String sha256(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(texto.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b)); // converte pra hex
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash", e);
        }
    }
}
