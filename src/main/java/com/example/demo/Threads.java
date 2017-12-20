package com.example.demo;

public class Threads implements Runnable {
	
	
	public static int[] diziSayi = new int[50];
	public static String[] diziKelime = new String[50];
	public static String[] diziUrl = new String[50];
	public TekSayfa[] tekDizi = new TekSayfa[50];
	public String url,aranan;
	public int a;
	public Threads(String url,String aranan,int a)
	{
		this.url = url;
		this.aranan = aranan;
		this.a = a;
	}
	@Override
	public void run()
	{		
			
			tekDizi[a] = new TekSayfa(url, aranan);
			int kelimeSayisi = tekDizi[a].main();
			System.out.println(url + "   [" +  aranan + "]" + "  son deger = " + kelimeSayisi);
			diziSayi[a] = kelimeSayisi;
			diziKelime[a] = aranan;
			diziUrl[a] = url;
			
			
			
			System.out.println(url + "   [" +  aranan + "]" + "  son deger = " + kelimeSayisi);
			
	}
}
