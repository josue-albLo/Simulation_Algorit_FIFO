/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fifo;
import java.util.*;

/**
 *
 * @author Josue_Guevara
 */
public class Main {
    
    //Variables globales
    private static int n;
    private static int id[];
    private static int llegada[];
    private static int rafaga[];
    private static int completado[];
    private static int retorno[];
    private static int espera[];
    private static float promedioEs = 0, promedioRe = 0;
    private static Scanner sc = new Scanner(System.in);

    
    public static void main(String args[]) {
        
        ingresaProcesos();
        ingresaDatosProcesos();
        ordenar();
        tiempoFinalizacion();
        
        System.out.println("\nid  llegada  rafaga  completo retorno espera");
        for (int i = 0; i < n; i++) {
            System.out.println(id[i] + "  \t " + llegada[i] + "\t" + rafaga[i] + "\t" + completado[i] + "\t" + retorno[i] + "\t" + espera[i]);
        }
        sc.close();
        System.out.println("\nTiempo promedio de espera: " + (promedioEs / n)); 
        System.out.println("Tiempo promedio de retorno:" + (promedioRe/ n)); 
    }
    
    
    private static void ingresaProcesos(){
        /*
        Pide el numero de procesos, con ese numero se inicializan los arrays que 
        contendran los datos
        */
        System.out.println("Ingrese el numero de procesos: ");
        n = sc.nextInt();
        id = new int[n];   // id de los procesos 
        llegada = new int[n];     // tiempo de llegada
        rafaga = new int[n];     //rafaga  
        completado= new int[n];     // tiempo de finalizacion 
        retorno = new int[n];     // tiempo de retorno
        espera = new int[n];     // tiempo de espera
        
    }
    
    
    private static void ingresaDatosProcesos(){
        /*
        Se rellenan los datos de nuestros arrays - llegada y rafaga -
        */
        for (int i = 0; i < n; i++) {
            //Pide el tiempo de llegada
            System.out.println("Proceso de entrada" + (i + 1) + " Tiempo de llegada: ");
            llegada[i] = sc.nextInt();
            
            //Pide la rafaga
            System.out.println("Proceso de entrada" + (i + 1) + " Tiempo de rafaga: ");
            rafaga[i] = sc.nextInt();
            id[i] = i + 1;
        }
    }
    
    
    private static void ordenar(){
        //Ordenamiento acorde al tiempo de llegada 
        int temp;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - (i + 1); j++) {
                if (llegada[j] > llegada[j + 1]) {
                    //mueve las pociciones de llegada
                    temp = llegada[j];
                    llegada[j] = llegada[j + 1];
                    llegada[j + 1] = temp;
                    
                    //mueve las pociciones de rafaga
                    temp = rafaga[j];
                    rafaga[j] = rafaga[j + 1];
                    rafaga[j + 1] = temp;
                    
                    //mueve los id de los procesos
                    temp = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = temp;
                }
            }
        }
    }
    
    
    private static void tiempoFinalizacion(){
        /*
        Encuentra los tiempos de retorno, espera y finalizacion.
        Tambien va sumando los datos para sacar el promedio de:
        Tiempo de espera
        Tiempo de retorno
        */
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                completado[i] = llegada[i] + rafaga[i];
            } else {
                if (llegada[i] > completado[i - 1]) {
                    completado[i] = llegada[i] + rafaga[i];
                } else {
                    completado[i] = completado[i - 1] + rafaga[i];
                }
            }
            retorno[i] = completado[i] - llegada[i];          // retorno = completado- llegada
            espera[i] = retorno[i] - rafaga[i];          // espera= retorno- rafaga
            promedioEs += espera[i];               // total del tiempo esperado
            promedioRe += retorno[i];               // total del tiempo de retorno
        }
    }
    
    
    
    
    

}
