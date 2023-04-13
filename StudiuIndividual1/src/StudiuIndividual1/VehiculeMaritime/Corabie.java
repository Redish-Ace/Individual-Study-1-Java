package StudiuIndividual1.VehiculeMaritime;

import javax.swing.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Corabie extends Nava{
    File file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual1\\src\\StudiuIndividual1\\VehiculeMaritime\\Corabie.txt");
    protected int[] numberCannons = new int[1000];

    // Constructor ce actualizeaza vectorii
    Corabie(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    super.size[index] = scan.nextDouble();
                    super.speed[index] = scan.nextDouble();
                    numberCannons[index] = scan.nextInt();
                    index++;
                    entries++;
                }
            }
            else entries = 0;
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Permite introducerea informatiei a mai multor corabi
    @Override protected void setList(){
        int add = Integer.parseInt(JOptionPane.showInputDialog("Câte corabii vrei să adaugi?"));
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index < add; index++) {
                super.name[index] = JOptionPane.showInputDialog("Numele barcii "+index);
                super.size[index] = Double.parseDouble(JOptionPane.showInputDialog("Capacitatea barcii "+super.name[index]));
                super.speed[index] = Double.parseDouble(JOptionPane.showInputDialog("Viteza barcii "+super.name[index]));
                numberCannons[index] = Integer.parseInt(JOptionPane.showInputDialog("Numărul de canoane "+super.name[index]));
                bufferedWriter.write(super.name[index]+" "+super.size[index] + " "+super.speed[index] + " "+numberCannons[index] + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Found");
        }
        menu();
    }

    // Citeste informatia din fisier pentru a actualiza vectorii
    @Override protected void updateArrays(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    super.size[index] = scan.nextDouble();
                    super.speed[index] = scan.nextDouble();
                    numberCannons[index] = scan.nextInt();
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
    @Override protected void updateFile(){
        try{
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index<entries; index++){
                if(super.name[index] == null) break;
                bufferedWriter.write( super.name[index]+ " " + super.speed[index] + " " + super.size[index] + " " + numberCannons[index]+"\n");
            }
            bufferedWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Found");
        }
    }

    // Returneaza lista intreaga a corabilor in forma de cadru
    @Override  protected void getList(){
        updateArrays();
        String[] stringList = new String[1000];
        for(int index = 0; index<entries; index++){
            if(name[index] == null) break;
            stringList[index] = super.name[index]+ " " + super.speed[index] + " " + super.size[index] + " " + numberCannons[index];
        }
        JList jList = new JList(stringList);
        JPanel jPanel = new JPanel();
        jPanel.add(jList);
        JFrame jFrame = new JFrame("Lista corabiilor");
        jFrame.add(jPanel);
        jFrame.setSize(240,1080);
        jFrame.show();
        super.setTimer();
    }

    // Returneaza informatia a corabii dupa indexul introdus
    @Override protected void getVehicle(int index) {
        updateArrays();
        JOptionPane.showMessageDialog(null,  super.name[index]+ " " + super.speed[index] + " " + super.size[index] + " " + numberCannons[index]);
        super.menu();
    }

    // Stergerea informatiei unei corabi dupa indexul introdus
    @Override protected void deleteVehicle(int index){
        updateArrays();
        for(int index1 = index; index1<entries; index1++){
            super.name[index1] = super.name[index1 + 1];
            super.speed[index1] = super.speed[index1 + 1];
            super.size[index1] = super.size[index1 + 1];
            numberCannons[index] = numberCannons[index + 1];
        }
        entries--;
        updateFile();
        super.menu();
    }
}
