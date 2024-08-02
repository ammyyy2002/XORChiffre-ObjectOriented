package xorchiffre;

import xorchiffre.util.UserInterface;
import xorchiffre.util.enums.TransformMode;

/**
 * Beinhaltet komplette Logik.
 */

public class Starten {
    /**
     * Fuehrt Hauptprogramm aus und fragt User nach Eingabewerten.
     */
    public void start() {

        Chiffrierer xorChiffre = new Chiffrierer(3, 3877, 29573, 139968);
        //xorChiffre.ausfuehren(askKlartext(), askGeheimtext(), askTransformMode());
        xorChiffre.ausfuehren("res/Gedicht.txt", "res/Geheimtext.txt", TransformMode.CHIFFRIEREN);

    }

    /** Erfragt Dateipfad der Klartextdatei.
     *
     * @return Dateipfad
     */
    public String askKlartext() {
        return UserInterface.in.requestString("Gib den Pfad der Klartextdatei ein: ");
    }

    /** Erfragt Dateipfad der Klartextdatei.
     *
     * @return Dateipfad
     */
    public String askGeheimtext() {
        return UserInterface.in.requestString("Gib den Pfad der Geheimtextdatei ein: ");
    }

    /** Erfragt Dateipfad der Geheimtextdatei.
     *
     * @return <code>TransfromMode.CHIFFRIEREN</code> oder <code>TransfomrMode.DECHIFFRIEREN</code>
     */
    public TransformMode askTransformMode() {
        return 1 + UserInterface.in.requestChoice("Gib einen Modus ein (1: Chiffrieren, 2: Dechiffrieren): ",
                "1", "2") == 1 ? TransformMode.CHIFFRIEREN : TransformMode.DECHIFFRIEREN;
    }
}
