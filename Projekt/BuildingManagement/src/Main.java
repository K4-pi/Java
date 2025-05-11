import GUI.Entry;

import javax.swing.*;

public class Main extends Entry{
    public static void main(String[] args) {
        /*System zarządzania budynkiem. Kontrola wejścia do budynku, zgłaszanie usterek, prób włamania,
        monitorowanie temperatury wody i powietrza, wyłączanie/włączanie światła, sieci elektrycznej,
        zamykanie/otwieranie budynku, itp.*/

        /* TASKS:
                DONE:
                    - Kontrola wejścia
                TODO:
                    - Włączanie/wyłączanie światła
                    - Kontrolowanie sieci elektrycznej
                    - Monitorowanie temperatury wody i powietrza
                    - Zgłaszanie usterek
                    - Zgłaszanie prób włamania
                    - Hasherowanie haseł
        */

        SwingUtilities.invokeLater(() -> new Main().run());
    }
}