package StudiuIndividual1.VehiculeMaritime;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        byte switchIndex = Byte.parseByte(JOptionPane.showInputDialog("1.Nava\n2.Barca cu panza\n3.Corabie \n4.Submarin"));

        switch (switchIndex) {
            case 1 -> {
                Nava nava = new Nava();
                nava.menu();
            }
            case 2 -> {
                BarcaCuPanze barca = new BarcaCuPanze();
                barca.menu();
            }
            case 3 -> {
                Corabie corabie = new Corabie();
                corabie.menu();
            }
            case 4 -> {
                Submarin submarine = new Submarin();
                submarine.menu();
            }
            default -> System.exit(0);
        }
    }
}