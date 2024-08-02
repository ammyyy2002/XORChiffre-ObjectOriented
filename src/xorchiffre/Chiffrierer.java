package xorchiffre;

import xorchiffre.util.FileHandler;
import xorchiffre.util.enums.TransformMode;
import java.nio.charset.StandardCharsets;

/** Enthaelt Chiffrierungslogik.
 */
public class Chiffrierer {
    private byte[] eingabeArray;
    private byte[] verschluesselungsSequenz;
    private byte[] ausgabeArray;
    private final int key, a, b, m;

    FileHandler fr;

    /** Schluessel und Parameter der Verschluesselungssequenz werden initialisiert.
     */
    public Chiffrierer(int key, int a, int b, int m) {
        this.key = key;
        this.a = a;
        this.b = b;
        this.m = m;
        fr = new FileHandler();
    }

    /** Fuehrt je nach Eingabe Chiffrierung oder Dechiffrierung aus.
     *
     * @param klartextPfad String, enthaelt den Dateipfad der Klartextdatei
     * @param geheimtextPfad String, enthaelt den Dateipfad des Geheimtextes
     * @param auswahl Enum CHIFFRIEREN oder DECHIFFRIEREN
     */
    public void ausfuehren(String klartextPfad, String geheimtextPfad, TransformMode auswahl) {
        if (auswahl == TransformMode.CHIFFRIEREN) {
            abfolgeAusfuehren(klartextPfad, geheimtextPfad);
        } else {
            abfolgeAusfuehren(geheimtextPfad, klartextPfad);
        }
        fr.printAusgabeFile(auswahl);
    }

    /**Enthaelt Chiffrierungslogik.
     *
     * @param eingabePfad String, Dateipfad der eingelesen und verschluesselt wird.
     * @param ausgabePfad String, Dateipfad in den Ergebnis geschrieben wird.
     */
    private void abfolgeAusfuehren(String eingabePfad, String ausgabePfad) {
        this.eingabeArray = fr.einlesen(eingabePfad);
        this.verschluesselungErstellen();
        this.xorChiffrieren();
        fr.schreiben(this.ausgabeArray, ausgabePfad);
    }


    /**Verschluesselt mit XOR Bytesequenz der Eingabe mit Bytes der Verschluesselungssequenz.
     */
    private void xorChiffrieren() {
        ausgabeArray = new byte[eingabeArray.length];
        for (int i = 0; i < eingabeArray.length; i++) {
            ausgabeArray[i] = (byte) (this.eingabeArray[i] ^ verschluesselungsSequenz[i]);
        }
    }

    /**Erstellt basierend auf Byte-Laenge der Eingabedatei eine zufaellige Verschluesselungssequenz.
     */
    private void verschluesselungErstellen() {
        this.verschluesselungsSequenz = new byte[eingabeArray.length];
        int zahl = this.key;
        for (int i = 0; i < eingabeArray.length; i++) {
            zahl = (this.a * zahl + this.b) % this.m;
            verschluesselungsSequenz[i] = (byte) (zahl & 0xFF);
        }
    }
}
