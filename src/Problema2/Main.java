package Problema2;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;
import java.util.Date;
import java.time.Period;
import java.util.Locale;
import javax.swing.*;

import Problema2.Produs;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Produs> lista = new ArrayList<Produs>();
        Scanner readFile = new Scanner(new File("produse.csv"));
        readFile.useDelimiter(", |\\r\\n");
        String denumire;
        float pret;
        int cantitate;
        LocalDate data_expirarii;
        
        while(readFile.hasNext())
        {
            denumire=readFile.next();
            pret=readFile.nextFloat();
            cantitate=readFile.nextInt();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            data_expirarii=LocalDate.parse(readFile.next(),format);
            Produs p = new Produs(denumire,pret,cantitate,data_expirarii);
            lista.add(p);
        }
        readFile.close();

        Scanner read = new Scanner(System.in);
        int optiune;
        do {
            System.out.println("0. Exit.");
            System.out.println("1. Afisare produse.");
            System.out.println("2. Afisare produselor expirate.");
            System.out.println("3. Vanzare produs.");
            System.out.println("4. Afisare produse cu pret minim.");
            System.out.println("5. Salvare produse cu cantitate minima intr-un fisier de iesire.");
            System.out.println("Introduceti optiunea: ");
            optiune = read.nextInt();
            switch(optiune)
            {
                case 0: break;

                case 1:
                    System.out.println("Afisare produse: ");
                    for(Produs p: lista)
                    {
                        System.out.println(p);
                    }
                    break;

                case 2:
                    System.out.println("Produse expirate: ");
                    for (Produs p:lista)
                    {
                        if(p.getData_expirarii().compareTo(LocalDate.now()) < 0)
                            System.out.println(p);
                    }
                    break;

                case 3:
                    System.out.println("Introduceti produsul pe care doriti sa-l cumparati: ");
                    read.nextLine();
                    String nume_produs = read.nextLine();

                    System.out.println("Introduceti cantitatea: ");
                    int cant = read.nextInt();

                    for(Produs p:lista)
                    {
                        if(p.getDenumire().compareTo(nume_produs) == 0)
                        {
                            if(cant > p.getCantitate())
                                System.out.println(p.getCantitate() +" produse in stoc!");
                            else {
                                p.vanzare(cant);
                                if(p.getCantitate()==0)
                                    lista.remove(p);
                            }
                        }

                    }
                    break;
                case 4:
                    System.out.println("Afisare produse cu pretul minim: ");
                    float minim = lista.get(0).getPret();
                    for(Produs p:lista)
                    {
                        if(p.getPret()<minim)
                            minim=p.getPret();
                    }

                    for(Produs p:lista)
                    {
                        if(p.getPret()==minim)
                            System.out.println(p);
                    }
                    break;

                case 5:
                    FileWriter fisier_out = new FileWriter("produse_out.txt");
                    PrintWriter writeFile = new PrintWriter(fisier_out);

                    System.out.println("Introduceti cantitatea: ");
                    cant = read.nextInt();

                    int ok=0;

                    writeFile.printf("Produse cu cantitate mai mica decat %d:\n",cant);
                    for(Produs p:lista)
                    {
                        if(p.getCantitate()<cant) {
                            ok = 1;
                            writeFile.printf("%s, %.1f, %d, %s\n", p.getDenumire(), p.getPret(), p.getCantitate(), p.getData_expirarii());
                        }
                    }
                    if(ok == 0)
                    {
                        writeFile.printf("Nu s-au gasit produse!\n");
                    }
                    fisier_out.close();
            }

        }while(optiune!=0);






    }
}