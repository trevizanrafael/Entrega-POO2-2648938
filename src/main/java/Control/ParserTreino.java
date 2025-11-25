package Control;

import java.util.regex.*;
import java.util.*;

public class ParserTreino {

    public static Map<String, String> extrairCampos(String texto) {
        Map<String, String> mapa = new HashMap<>();

        // Expressões regulares pra cada tipo de campo
        Pattern pEx = Pattern.compile("Exercício (\\d+):\\s*(.+)");
        Pattern pPeso = Pattern.compile("Peso (\\d+):\\s*(.+)");
        Pattern pRep = Pattern.compile("Repetições (\\d+):\\s*(.+)");

        Matcher mEx = pEx.matcher(texto);
        while (mEx.find()) {
            mapa.put("Exercicio" + mEx.group(1), mEx.group(2).trim());
        }

        Matcher mPeso = pPeso.matcher(texto);
        while (mPeso.find()) {
            mapa.put("Peso" + mPeso.group(1), mPeso.group(2).trim());
        }

        Matcher mRep = pRep.matcher(texto);
        while (mRep.find()) {
            mapa.put("Repeticoes" + mRep.group(1), mRep.group(2).trim());
        }

        return mapa;
    }
}
