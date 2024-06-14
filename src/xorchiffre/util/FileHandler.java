package amyivan.xorchiffre.util;
import amyivan.xorchiffre.util.enums.TransformMode;

import java.io.*;
import java.nio.charset.StandardCharsets;

/** Sorgt fuer reibungsloses Einlesen und Schreiben von Datein.
 */
public class FileHandler {

    private String ausgabepfad;

    public FileHandler() {
    }

    /**
     * Liest Eingabedatei ein.
     *
     * @param eingabepfad Dateipfad der Datei, die eingelesen wird
     */
    public byte[] einlesen(String eingabepfad) {

        byte[] eingabe;

        try (FileInputStream eingabeStream = new FileInputStream(eingabepfad)) {
            eingabe = new byte[eingabeStream.available()];
            eingabeStream.read(eingabe);
        } catch (IOException e) {
            e.getMessage();
            return null;
        }
        return eingabe;
    }

    /**
     * Schreibt Byte-Array in Ausgabedatei.
     *
     * @param ausgabeArray Byte-Array, welches Bytes der (de)chiffrierten Datei enthaelt
     * @param ausgabepfad  String, der Dateipfad der zu beschreibenden Datei enthaelt
     */
    public void schreiben(byte[] ausgabeArray, String ausgabepfad) {
        this.ausgabepfad = ausgabepfad;
        try (FileOutputStream writer = new FileOutputStream(ausgabepfad)) {
            writer.write(ausgabeArray);
        } catch (IOException e) {
            System.out.println("Fehler beim Schreiben in die Datei: " + e.getMessage());
        }
    }

    public void printAusgabeFile(TransformMode tm) {

        if (tm == TransformMode.CHIFFRIEREN)
        {
            System.out.println("\nERGEBNIS DER CHIFFRIERUNG: ");
        }
        else
        {
            System.out.println("\nERGEBNIS DER DECHIFFRIERUNG: ");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(this.ausgabepfad))) {
        //try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(this.ausgabepfad), StandardCharsets.UTF_8))){
            String zeile;
            while ((zeile = br.readLine()) != null) {
                System.out.println(zeile);
            }
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}