/*Problema 1: Aplicație. Elaborați un program Java care va colecta date despre un set de aplicații.
Despre fiecare aplicație este necesar de colectat următoarea informație: denumire, versiune, limba, companieProducătoare, tip, cuSunet, cuInstalare, marime, licenta.
Operați cu colecții de obiecte.
Prevedeți și tratați situațiile excepționale ce pot apărea la operarea cu datele de prelucrat. Aplicația să permită efectuarea următoarelor operații:
    a) Introducerea unei noi aplicații;
    b) Afișarea datelor;
    c) Afișarea listei aplicațiilor în limba română și limba engleză;
    d) Afișarea listei aplicațiilor unei companii producătoare;
    e) Afișarea aplicațiilor care nu necesită să fie instalate în calculator cu licență freeware de tip joc;
    f) Afișarea aplicațiilor educative cu sunet și licență shareware;
    g) Afișarea aplicațiilor ce au mărimea cuprinsă într-un diapazon introdus de la tastatură.*/
package StudiuIndividual1.Aplicatii;
import StudiuIndividual1.Caracteristici;

import javax.swing.*;
import java.util.*;
import java.io.*;
public class App extends Caracteristici {
    // Change file pathname if you change the placement of the folder
    File file = new File("C:\\Users\\Lilian\\Downloads\\POO Individuala\\StudiuIndividual1\\src\\StudiuIndividual1\\Aplicatii\\Apps.txt");
    private JList list;
    private String[] version = new String[1000], lang = new String[1000],
            creatorCompany = new String[1000], type = new String[1000], license = new String[1000];
    private boolean[] withSound = new boolean[1000], withInstaller = new boolean[1000];
    private int entries=0;

    // Constructor ce actualizeaza vectorii
    App(){
        try{
            Scanner scan = new Scanner(file);
            if(scan.hasNext()) {
                int index = 0;
                while (scan.hasNext()) {
                    super.name[index] = scan.next();
                    version[index] = scan.next();
                    lang[index] = scan.next();
                    creatorCompany[index] = scan.next();
                    type[index] = scan.next();
                    license[index] = scan.next();
                    withSound[index] = scan.nextBoolean();
                    withInstaller[index] = scan.nextBoolean();
                    super.size[index] = scan.nextDouble();
                    index++;
                    entries++;
                }
            }
            else entries = 0;
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Creaza cadrul su panoul in care se returneaza informatia
    private void panel(String title){
        JPanel jPanel = new JPanel();
        jPanel.add(list);

        JFrame frame = new JFrame(title);
        frame.add(jPanel);
        frame.setSize(700, 1000);
        frame.show();
        menu();
    }

    // Permite introducerea informatiei a mai multor aplicatii
    public void setList() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Câte aplicații doriți să adăugați?: ");
        int add = scan.nextInt();
        scan.nextLine();
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for (int index = entries; index < entries+add; index++) {
                System.out.printf("Numele aplicatiei %d: ",index);
                name[index] = scan.next();

                System.out.printf("Versiunea aplicației %s: ",name[index]);
                version[index] = scan.next();

                System.out.printf("Limba aplicației %s: ",name[index]);
                lang[index] = scan.next();

                System.out.printf("Compania care a creat aplicația %s: ",name[index]);
                creatorCompany[index] = scan.next();

                System.out.printf("Tip aplicație %s: ",name[index]);
                type[index] = scan.next();

                System.out.printf("Licența aplicației %s: ",name[index]);
                license[index] = scan.next();

                System.out.printf("Aplicația %s are sunet? (true/false): ",name[index]);
                withSound[index] = scan.nextBoolean();

                System.out.printf("Aplicația %s are un program de instalare? (true/false): ",name[index]);
                withInstaller[index] = scan.nextBoolean();

                System.out.printf("Dimensiunea aplicației %s (MB): ",name[index]);
                size[index] = scan.nextDouble();
                bufferedWriter.write( name[index] + " " + version[index] + " " + lang[index] + " " + creatorCompany[index] + " " + type[index] + " " + license[index] + " " + withSound[index] + " " + withInstaller[index] + " " + size[index] + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }
        finally {
            entries += add;
        }
        menu();
    }

    // Citeste informatia din fisier pentru a actualiza vectorii
    public void updateArrays(){
        try{
            Scanner scan = new Scanner(file);
            int index = 0;
            while (scan.hasNext()) {
                name[index] = scan.next();
                version[index] = scan.next();
                lang[index] = scan.next();
                creatorCompany[index] = scan.next();
                type[index] = scan.next();
                license[index] = scan.next();
                withSound[index] = scan.nextBoolean();
                withInstaller[index] = scan.nextBoolean();
                size[index] = scan.nextDouble();
                index++;
            }
        }catch(FileNotFoundException fnfe){
            System.out.println("File Not Found");
        }
    }

    // Returneaza lista intreaga a aplicatiilor
    public void getList() {
        updateArrays();
        String[] stringList = new String[1000];
        int stringIndex = 0;
        for(int index=0; index<entries; index++){
            stringList[stringIndex] = name[index]+" "+version[index]+" "+lang[index]+" "+creatorCompany[index]+" "+type[index]+" "+license[index]+" "+withSound[index]+" "+withInstaller[index]+" "+size[index];
            stringIndex++;
        }
        list = new JList(stringList);
        panel("Aplicatii");
        super.setTimer();
    }

    // Returneaza lista aplicatiilor dupa limba introdusa
    public void getAppByLang(String langFind) {
        updateArrays();
        String[] stringList = new String[1000];
        int stringIndex = 0;
        for(int index=0; index<entries; index++){
            if(lang[index].equals(langFind)) {
                stringList[stringIndex] = name[index] + " " + version[index] + " " + lang[index] + " " + creatorCompany[index] + " " + type[index] + " " + license[index] + " " + withSound[index] + " " + withInstaller[index] + " " + size[index];
                stringIndex++;
            }
        }
        list = new JList(stringList);
        panel("Aplicatii in " + langFind);
        super.setTimer();
    }

    // Returneaza lista aplicatiilor dupa compania introdusa
    public void getAppByCompany(String compFind) {
        updateArrays();
        String[] stringList = new String[1000];
        int stringIndex = 0;
        for(int index=0; index<entries; index++){
            if(creatorCompany[index].equals(compFind)) {
                stringList[stringIndex] = name[index] + " " + version[index] + " " + lang[index] + " " + creatorCompany[index] + " " + type[index] + " " + license[index] + " " + withSound[index] + " " + withInstaller[index] + " " + size[index];
                stringIndex++;
            }
        }
        list = new JList(stringList);
        panel("Aplicatii create de " + compFind);
        super.setTimer();
    }

    // Returneaza lista aplicatiilor cu installer, dupa licenta si tipul introdus
    public void getAppByInstallerLicenseType(boolean instFind, String licenseFind, String typeFind){
        updateArrays();
        String[] stringList = new String[1000];
        int stringIndex = 0;
        for(int index=0; index<entries; index++){
            if(withInstaller[index] == instFind) {
                if (license[index].equals(licenseFind)) {
                    if (type[index].equals(typeFind)) {
                        stringList[stringIndex] = name[index] + " " + version[index] + " " + lang[index] + " " + creatorCompany[index] + " " + type[index] + " " + license[index] + " " + withSound[index] + " " + withInstaller[index] + " " + size[index];
                        stringIndex++;
                    }
                }
            }
        }
        list = new JList(stringList);
        if(instFind) {
            panel("Aplicatii cu installer, licenta "+licenseFind+" tip "+typeFind);
        }else{
            panel("Aplicatii fara installer, licenta "+licenseFind+" tip "+typeFind);
        }
        super.setTimer();
    }

    // Returneaza lista aplicatiilor cu sunet, dupa licenta si tipul introdus
    public void getAppByTypeSoundLicense(String typeFind, boolean soundFind, String licenseFind){
        updateArrays();
        String[] stringList = new String[1000];
        int stringIndex = 0;
        for(int index=0; index<entries; index++){
            if(type[index].equals(typeFind)) {
                if (withSound[index] == soundFind) {
                    if (license[index].equals(licenseFind)) {
                        stringList[stringIndex] = name[index] + " " + version[index] + " " + lang[index] + " " + creatorCompany[index] + " " + type[index] + " " + license[index] + " " + withSound[index] + " " + withInstaller[index] + " " + size[index];
                        stringIndex++;
                    }
                }
            }
        }
        list = new JList(stringList);
        if(soundFind) {
            panel("Aplicatii tip "+typeFind +", cu sunet , licenta "+licenseFind);
        }else{
            panel("Aplicatii tip "+typeFind +", fara sunet , licenta "+licenseFind);
        }
        super.setTimer();
    }

    // Returneaza lista aplicatiilor cu dimensiune intre min si max introdus
    public void getAppBySize(double min, double max){
        updateArrays();
        String[] stringList = new String[1000];
        int stringIndex = 0;
        for(int index=0; index<entries; index++){
            if(size[index]>min && size[index]<max) {
                stringList[stringIndex] = name[index] + " " + version[index] + " " + lang[index] + " " + creatorCompany[index] + " " + type[index] + " " + license[index] + " " + withSound[index] + " " + withInstaller[index] + " " + size[index];
                stringIndex++;
            }
        }
        list = new JList(stringList);
        panel("Aplicatii cu marime intre "+min+" si "+max);
        super.setTimer();
    }

    // Meniul de accesare a metodelor
    public void menu(){
        Scanner scan = new Scanner(System.in);
        System.out.println("1.Adăugați aplicația\n" +
                "2.Lista de aplicații\n" +
                "3.Căutați aplicația după limbă\n" +
                "4.Căutați aplicația după companie\n" +
                "5.Căutați aplicația după program de instalare (true/false), licență, tip\n" +
                "6.Căutați aplicația după tip, sunet (true/false), licență\n" +
                "7.Căutați aplicația după dimensiune\n");

        System.out.print("Alegeti optiunea: ");byte switchIndex = scan.nextByte();
        scan.nextLine();
        switch (switchIndex){
            case 1 -> setList();
            case 2 -> getList();
            case 3 -> {
                System.out.print("Introduceti limba cautata: ");
                String lang = scan.nextLine();

                getAppByLang(lang);
            }
            case 4 -> {
                System.out.print("Introduceti compania cautata: ");
                String comp = scan.nextLine();

                getAppByCompany(comp);
            }
            case 5 -> {
                System.out.print("Are aplicatia instalare: ");
                boolean installer = scan.hasNextBoolean();
                scan.nextLine();

                System.out.print("Introcudeti licenta cautata: ");
                String license = scan.nextLine();

                System.out.print("Introcudeti tipul de aplicatie cautata: ");
                String type = scan.nextLine();

                getAppByInstallerLicenseType(installer, license,type);
            }
            case 6 -> {
                System.out.print("Introcudeti tipul de aplicatie cautata: ");
                String type = scan.nextLine();

                System.out.print("Are aplicatia sunet: ");
                boolean sound = scan.hasNextBoolean();
                scan.nextLine();

                System.out.print("Introcudeti licenta cautata: ");
                String license = scan.nextLine();

                getAppByTypeSoundLicense(type, sound, license);
            }
            case 7 -> {
                System.out.print("Introduceti dimensiunea minima cautata: ");
                double min = scan.nextDouble();

                System.out.print("Introduceti dimensiunea minima cautata: ");
                double max = scan.nextDouble();

                getAppBySize(min, max);
            }
            default -> System.exit(0);
        }
    }
}
