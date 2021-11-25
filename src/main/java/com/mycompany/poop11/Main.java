/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poop11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author lenovo
 */
public class Main {
    public static void main(String[] args) {
        
        System.out.println("##############File###############");
        File archivo = new File("archivo.txt");
        System.out.println(archivo.exists());
        if(!archivo.exists()){
            try {
                boolean seCreo = archivo.createNewFile();
                System.out.println(seCreo);
                System.out.println(archivo.exists());
            } catch (IOException ex) {
                ex.getMessage();
                ex.getStackTrace();
            }
        }
        
        System.out.println("##############FileOutputStream################");
        //Necesitamos un canal
        FileOutputStream fos = null;
        byte[] buffer = new byte[81];
        int nBytes;
        
        try {
            System.out.println("Escribir el texto a guardar en el archivo:");
            nBytes = System.in.read(buffer);
            fos = new FileOutputStream("fos.txt");
            fos.write(buffer,0,nBytes);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally{
            try {
                if(fos != null)
                    fos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        System.out.println("##############FileInputStream################");
        FileInputStream fis = null;                         //Declarar una variable donde se va a almacenar ese canal
        try {
            fis = new FileInputStream("fos.txt");
            nBytes = fis.read(buffer,0,81);
            String texto = new String(buffer,0,nBytes);
            System.out.println(texto);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } finally{
            try {
                if (fis != null)                
                    fis.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
        System.out.println("##############FileWriter################");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escribe texto para el archivo: ");      
            String texto2 = br.readLine();
            
            FileWriter fw = new FileWriter("fw.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter salida = new PrintWriter(bw);
            
            salida.println(texto2);
            
            for (int i = 0; i < 10; i++) {
                salida.println(i + " Linea del for");
            }
            for (int i = 0; i < 5; i++) {
                salida.println("Juan,José,Goméz,Pérez,31852687 ,19,15");
            }
            
            salida.close();
            
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        
        
        System.out.println("##############FileReader################");
        try {
            BufferedReader br;
            FileReader fr = new FileReader("fw.csv");
            br = new BufferedReader(fr);
            System.out.println("El texto del archivo es: ");
            String linea = br.readLine();
            while(linea != null){
                System.out.println(linea);
                linea = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        
        System.out.println("##############StringTokenizer################");
        String linea = "Ramiro,Juarez,Perez,319354263,21,22";
        StringTokenizer tokenizer = new StringTokenizer(linea,",");
        while(tokenizer.hasMoreTokens()){
            System.out.println(tokenizer.nextToken());
        }
        
        System.out.println("##############Serialización################");
        
        Date date = new Date();
        System.out.println(date);
        
        try {
            FileOutputStream f = new FileOutputStream("fecha.ser");
            ObjectOutputStream oos = new ObjectOutputStream(f);
            oos.writeObject(date);
            oos.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }
        
        System.out.println("##############Deserialización################");
        
        Date date2 = new Date();
        System.out.println("La fecha actualizada es: " + date2);
        
        try {
            FileInputStream f = new FileInputStream("fecha.ser");
            ObjectInputStream ois = new ObjectInputStream(f);
            Date date3 = (Date) ois.readObject();
            ois.close();
            System.out.println("Objeto deserializado " + date2);
            
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getCause());
        }
        
        System.out.println("##############Tokenizer en FileReader################");
        try {
            BufferedReader br;
            FileReader fr = new FileReader("a.txt");
            br = new BufferedReader(fr);
            String linea2 = br.readLine();
            

            StringTokenizer tokenizer2 = new StringTokenizer(linea2,",");
                while(tokenizer2.hasMoreTokens()){
                    System.out.println(tokenizer2.nextToken());
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getCause());
        } catch (IOException ex) {
            System.out.println(ex.getCause());
        }

    }
}

        