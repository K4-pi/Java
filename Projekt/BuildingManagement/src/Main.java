import GUI.Entry;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        /*System zarządzania budynkiem. Kontrola wejścia do budynku, zgłaszanie usterek, prób włamania,
        monitorowanie temperatury wody i powietrza, wyłączanie/włączanie światła, sieci elektrycznej,
        zamykanie/otwieranie budynku, itp.*/

        /* TASKS:
                DONE:
                    - Kontrola wejścia
                    - Włączanie/wyłączanie światła
                    - Kontrolowanie sieci elektrycznej
                    - Monitorowanie temperatury wody i powietrza
                TODO:
                    - Zgłaszanie usterek
                    - Zgłaszanie prób włamania
                    - Hasherowanie haseł ?
        */

        SwingUtilities.invokeLater(() -> new Entry("Building entrance", 400, 500, false).run());
    }
}