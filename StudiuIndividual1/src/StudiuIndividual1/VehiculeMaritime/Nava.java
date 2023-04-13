package StudiuIndividual1.VehiculeMaritime;
/*Problema 2:
    • Determinați ierarhia clasei. Pentru a determina ierarhia claselor, legați clasele date în aplicație printr-o
relație de moștenire. Din clasele enumerate, alegeți una care va fi în fruntea ierarhiei. Aceasta este și clasa
abstractă.
    • Implementați clasele. Definiți toate metodele get și set necesare în clase. Specificați datele componente ale clasei ca private,
    implementați metode ce ar putea descrie clasele.
    • Scrieți un program demonstrativ în care obiectele din diferite clase sunt create și plasate într-o listă.
    • Programul conține un meniu care, la cererea utilizatorului, vă permite să adăugați un element în listă,
    să eliminați un element din listă și să afișați lista pe ecran (să implementați metodele adăugate de voi)
    • Definiția claselor, implementarea lor și programul demo ar trebui să fie plasate în fișiere separate.
Clasele: Navă, corabie, barca cu pânze, submarina.*/
import StudiuIndividual1.Caracteristici;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
public class Nava extends Caracteristici {
    File file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual1\\src\\StudiuIndividual1\\VehiculeMaritime\\Nava.txt");
    protected double[] speed = new double[1000];
    protected int entries=0;

    // Constructor ce actualizeaza vectorii
    Nava(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    super.size[index] = scan.nextDouble();
                    speed[index] = scan.nextDouble();
                    index++;
                    entries++;
                }
            }
            else entries = 0;
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Permite introducerea informatiei a mai multor nave
    protected void setList(){
        int add = Integer.parseInt(JOptionPane.showInputDialog("Cate nave doriti sa adaugati?"));
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index < add; index++) {
                super.name[index] = JOptionPane.showInputDialog("Numele navei "+index);
                super.size[index] = Double.parseDouble(JOptionPane.showInputDialog("Capacitatea navei "+super.name[index]));
                speed[index] = Double.parseDouble(JOptionPane.showInputDialog("Viteza navei "+super.name[index]));
                bufferedWriter.write(super.name[index]+" "+super.size[index] + " "+speed[index] + " ");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Found");
        }
        menu();
    }

    // Citeste informatia din fisier pentru a actualiza vectorii
    protected void updateArrays(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    super.size[index] = scan.nextDouble();
                    speed[index] = scan.nextDouble();
                    index++;
                    entries++;
                }
            }
            else entries = 0;
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Actualizeaza informatia din fisier
    protected void updateFile(){
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index<entries; index++){
                if(super.name[index] == null) break;
                bufferedWriter.write(super.name[index]+" "+super.size[index]+" "+speed[index]+"\n");
            }
            bufferedWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Found");
        }
    }

    // Returneaza lista intreaga a navelor in forma de cadru
    protected void getList(){
        updateArrays();
        String[] stringList = new String[1000];
        for(int index = 0; index<entries; index++){
            if(super.name[index] == null) break;
            stringList[index]=super.name[index] + " " + super.size[index] + " " + speed[index];
        }
        JList jList = new JList(stringList);
        JPanel jPanel = new JPanel();
        jPanel.add(jList);
        JFrame jFrame = new JFrame("Lista Navelor");
        jFrame.add(jPanel);
        jFrame.setSize(240,1080);
        jFrame.show();
        super.setTimer();
    }

    // Returneaza informatia a navei dupa indexul introdus
    protected void getVehicle(int index) {
        updateArrays();
        JOptionPane.showMessageDialog(null, super.name[index] + " " + super.size[index] + " " + speed[index]);
        menu();
    }

    // Stergerea informatiei unei nave dupa indexul introdus
    protected void deleteVehicle(int index1){
        updateArrays();
        for(int index = index1; index<entries; index++){
            if(super.name[index] == null) break;
            super.name[index] = super.name[index + 1];
            super.size[index] = super.size[index + 1];
            speed[index] = speed[index + 1];
        }
        entries--;
        updateFile();
        menu();
    }

    // Meniul de accesare a metodelor
    public void menu(){
        byte switchIndex = Byte.parseByte(JOptionPane.showInputDialog("1.Adaugati nave\n2.Stergeti navele\n3.Lista navelor\n4.Nava dupa index"));

        switch (switchIndex) {
            case 1 -> setList();
            case 2 -> {
                int vIndex = Integer.parseInt(JOptionPane.showInputDialog("Introduceti index"));
                deleteVehicle(vIndex);
            }
            case 3 -> getList();
            case 4 -> {
                int vIndex = Integer.parseInt(JOptionPane.showInputDialog("Introduceti index"));
                getVehicle(vIndex);
            }
            default -> System.exit(0);
        }
    }
}
