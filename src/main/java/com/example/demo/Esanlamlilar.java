/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Esanlamlilar {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static String esanlamli(String aranan) throws IOException{
        String dosyaYolu = "C:\\Users\\ENAYMAN\\Desktop\\LocalSearchEngine\\esAnlamli.txt";
        File dosya = new File(dosyaYolu);
        String sonuc = "";
        
        try{
            try (BufferedReader oku = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(dosya), "UTF8"))) {
                String str;
                
                while ((str = oku.readLine()) != null) {
                    String[] kelime = str.split("-");
                    int i = 0;
                    if(kelime[i].equalsIgnoreCase(aranan)){
                        sonuc+=kelime[i+1];
                        i++;
                    }
                    if(kelime[i].equalsIgnoreCase(aranan)){
                        sonuc+=kelime[i-1];
                        i++;
                    }
                }
            }
        }
        catch(FileNotFoundException | UnsupportedEncodingException e){
        }
        
        return sonuc;
    }
    
    public static String kelimeCevir(String kelime){
    	String yeniKelime;
    	yeniKelime = kelime.replaceAll("ı", "\u0131");
        yeniKelime = yeniKelime.replaceAll("İ", "\u0130");
    	yeniKelime = yeniKelime.replaceAll("ğ", "\u011f");
        yeniKelime = yeniKelime.replaceAll("Ğ", "\u011e");
        yeniKelime = yeniKelime.replaceAll("ü", "\u00fc");
        yeniKelime = yeniKelime.replaceAll("Ü", "\u00dc");
        yeniKelime = yeniKelime.replaceAll("ö", "\u00f6");
        yeniKelime = yeniKelime.replaceAll("Ö", "\u00d6");
        yeniKelime = yeniKelime.replaceAll("ç", "\u00e7");
        yeniKelime = yeniKelime.replaceAll("Ç", "\u00c7");
        yeniKelime = yeniKelime.replaceAll("ş", "\u015f");
        yeniKelime = yeniKelime.replaceAll("Ş", "\u015e");
        
        return yeniKelime;
    }
    public static void main(String[] args) {
    }
}
