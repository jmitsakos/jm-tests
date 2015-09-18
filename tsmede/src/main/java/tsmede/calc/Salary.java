package tsmede.calc;

import javax.swing.*;
import java.text.DecimalFormat;

/**
 * Created by mitsakos
 * 14-02-2011 09:44
 */

public class Salary {

    public static void main (String args[]) {
        String miktaStr;
        int mikta;
        int ethsia;
        double ika;
        double tsmede;
        double forologhsima;
        double eforiaMexri30; double eforiaMeta30; double eforia;
        double kathara;
        String result;
        DecimalFormat df = new DecimalFormat("#.0#");
        try {
            String anw5etias = JOptionPane.showInputDialog("Exete perasei thn 5etia? (y/n)");
            while (true) {
                miktaStr = JOptionPane.showInputDialog("Eisagete ta mikta sas");
                mikta = Integer.parseInt(miktaStr);
                if (anw5etias.equals("y")) {
                    ethsia = 14*mikta;
                    ika = 0.0418*ethsia;
                    tsmede = 0.1582*ethsia;
                    forologhsima = ethsia - ika - tsmede;
                    if (forologhsima > 30000) {
                        eforiaMexri30 = 18000*0.24;
                        eforiaMeta30 = (forologhsima - 30000)*0.35;
                        eforia = eforiaMexri30 + eforiaMeta30;
                        kathara = (forologhsima - eforia)/14;
                    }
                    else {
                        eforiaMexri30 = (forologhsima - 12000)*0.24;
                        eforia = eforiaMexri30;
                        kathara = (forologhsima - eforia)/14;
                    }
                }

                else {
                    ethsia = 14*mikta;
                    ika = 0.0418*ethsia;
                    tsmede = 0.11*ethsia;
                    forologhsima = ethsia - ika - tsmede;
                    if (forologhsima > 30000) {
                        eforiaMexri30 = 18000*0.24;
                        eforiaMeta30 = (forologhsima - 30000)*0.35;
                        eforia = eforiaMexri30 + eforiaMeta30;
                        kathara = (forologhsima - eforia)/14;
                    }
                    else {
                        eforiaMexri30 = (forologhsima - 12000)*0.24;
                        eforia = eforiaMexri30;
                        kathara = (forologhsima - eforia)/14;
                    }
                }
                result = "Mikta: " + mikta + "\n" + "IKA: " + df.format(ika/14) + "\n" + "TSMEDE: " + df.format(tsmede/14) + "\n" +
                        "Eforia: " + df.format(eforia/14) + "\n" + "Kathara: " + df.format(kathara);
                JOptionPane.showMessageDialog(null, result);
            }
        }
        catch (Exception ex) {
            System.exit(0);
        }
    }
}

