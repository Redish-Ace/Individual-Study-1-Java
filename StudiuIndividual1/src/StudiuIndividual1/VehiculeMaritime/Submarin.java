package StudiuIndividual1.VehiculeMaritime;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class Submarin extends Nava {
    File file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual1\\src\\StudiuIndividual1\\VehiculeMaritime\\Submarin.txt");
    protected int[] numberArmament = new int[1000], timeWorkingBatteries = new int[1000];
    private double[] depthDive = new double[1000];
    private String[] typeSonar = new String[1000];

    // Constructor ce actualizeaza vectorii
    Submarin(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    super.size[index] = scan.nextDouble();
                    super.speed[index] = scan.nextDouble();
                    numberArmament[index] = scan.nextInt();
                    timeWorkingBatteries[index] = scan.nextInt();
                    depthDive[index] = scan.nextDouble();
                    typeSonar[index] = scan.next();
                    index++;
                    entries++;
                }
            }
            else entries = 0;
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Permite introducerea informatiei a mai multor submarine
    @Override protected void setList(){
        int add = Integer.parseInt(JOptionPane.showInputDialog("Câte submarine vrei să adaugi?"));
        try{
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(int index = 0; index < add; index++) {
                super.name[index] = JOptionPane.showInputDialog("Numele submarinului "+index);
                super.speed[index] = Double.parseDouble(JOptionPane.showInputDialog("Viteza submarinului "+super.name[index]));
                super.size[index] = Double.parseDouble(JOptionPane.showInputDialog("Capacitatea submarinului "+super.name[index]));
                numberArmament[index] = Integer.parseInt(JOptionPane.showInputDialog("Numărul de armament "+super.name[index]));
                timeWorkingBatteries[index] = Integer.parseInt(JOptionPane.showInputDialog("Timpul bateriilor last "+super.name[index]));
                depthDive[index] = Double.parseDouble(JOptionPane.showInputDialog("Adâncimea la care ajunge submarinul " + super.name[index]));
                typeSonar[index] = JOptionPane.showInputDialog("Type of sonar " + super.name[index]);
                bufferedWriter.write(super.name[index]+" "+super.speed[index] + " "+super.size[index] + " "+numberArmament[index] + " "+timeWorkingBatteries[index] + " " +
                        depthDive[index] + " "+typeSonar[index] + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Found");
        }
        super.menu();
    }

    // Citeste informatia din fisier pentru a actualiza vectorii
    @Override protected void updateArrays(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    super.speed[index] = scan.nextDouble();
                    super.size[index] = scan.nextDouble();
                    numberArmament[index] = scan.nextInt();
                    timeWorkingBatteries[index] = scan.nextInt();
                    depthDive[index] = scan.nextDouble();
                    typeSonar[index] = scan.next();
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
                bufferedWriter.write( super.name[index] + " " + super.speed[index] + " " + super.size[index] + " " +
                        numberArmament[index] + " " + timeWorkingBatteries[index] + " " + depthDive[index] + " " + typeSonar[index]+"\n");
            }
            bufferedWriter.close();
        }catch(IOException ioe){
            System.out.println("File Not Found");
        }
    }

    // Returneaza lista intreaga a submarinelor in forma de cadru
    @Override  protected void getList(){
        updateArrays();
        String[] stringList = new String[1000];
        for(int index = 0; index<entries; index++){
            if(name[index] == null) break;
            stringList[index] = name[index] + " " + speed[index] + " " + size[index] + " " +
                    numberArmament[index] + " " + timeWorkingBatteries[index] + " " + depthDive[index] + " " + typeSonar[index];
        }
        JList jList = new JList(stringList);
        JPanel jPanel = new JPanel();
        jPanel.add(jList);
        JFrame jFrame = new JFrame("Lista submarinelor");
        jFrame.add(jPanel);
        jFrame.setSize(340,1080);
        jFrame.show();
        super.setTimer();
    }

    // Returneaza informatia a submarinei dupa indexul introdus
    @Override protected void getVehicle(int index) {
        updateArrays();
        JOptionPane.showMessageDialog(null,  name[index] + " " + speed[index] + " " + size[index] + " " +
                numberArmament[index] + " " + timeWorkingBatteries[index] + " " + depthDive[index] + " " + typeSonar[index]);
        super.menu();
    }

    // Stergerea informatiei unei submarine dupa indexul introdus
    @Override protected void deleteVehicle(int index){
        updateArrays();
        for(int index1 = index; index1<entries; index1++){
            name[index1] = name[index1 + 1];
            size[index1] = size[index1 + 1];
            speed[index1] = speed[index1 + 1];
            numberArmament[index] = numberArmament[index + 1];
            timeWorkingBatteries[index] = timeWorkingBatteries[index + 1];
            depthDive[index] = depthDive[index + 1];
            typeSonar[index] = typeSonar[index + 1];
        }
        entries--;
        updateFile();
        super.menu();
    }
}
