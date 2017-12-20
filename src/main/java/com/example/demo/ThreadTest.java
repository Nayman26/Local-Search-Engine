package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
	public static Double[] ortalama = new Double[15];
	public static Double[] ortalamaCopy = new Double[15];
	public static int threadSayisi = 3;
	public static Threads[] threadDizi = new Threads[15];
	public static String[] linkDizi = new String[15];
	public static String[] kelimeDizi = new String[15];
	public static int linkSayisi;
	public static int kelimeSayisi;
	
	
	public static void puanlama(String[] linkDizi,String[] kelimeDizi)
	{
		
		int[] gecici = new int[15];
		for(int i = 0;i<linkSayisi;i++)
		{
			int a = 0;
			int toplam = 0;
			for(int j = 0;j<linkSayisi*kelimeSayisi;j++)
			{
				if(linkDizi[i].equals(Threads.diziUrl[j]))
				{
					gecici[a] = Threads.diziSayi[j];
					toplam +=gecici[a];
					a++;
				}
				
			}
			
			ortalama[i] = (double)toplam/kelimeSayisi;
		
			ortalamaCopy[i] = (double)toplam/kelimeSayisi;
			for(int k = 0;k<a;k++)
			{
				if(gecici[k] == 0)
				{
					gecici[k]++;
				}
				if(ortalama[i] == 0)
				{
					//ortalama.add(i,ortalama.get(i) + 1);
					ortalama[i]++;
					ortalamaCopy[i]++;
				}
				
				if(ortalama[i]<gecici[k])
				{
					ortalamaCopy[i] += (gecici[k]/ortalama[i]);
				}
				else if(ortalama[i]>gecici[k])
				{
					ortalamaCopy[i] -= (ortalama[i]/gecici[k]); 
				}
				
			}
			
		}		
	}
	
	
	public static void siralama(Double[] arr,String[] arr2) 
	{
		int n = linkSayisi;
		Double temp = 0.0;
		String temp2 = "";
		for (int i = 0; i < n; i++) 
		{
			for (int j = 1; j < (n - i); j++) 
			{
				if(arr[j - 1] < 0 && arr[j] < 0)
				{
					if (arr[j - 1] > arr[j]) 
					{
						// swap elements
						temp = arr[j - 1];
						arr[j - 1] = arr[j];
						arr[j] = temp;
						System.out.println("DENEME4");
						temp2 = arr2[j - 1];
						arr2[j - 1] = arr2[j];
						arr2[j] = temp2;
						System.out.println("DENEME5");
					}
				}
				else
				{
					if (arr[j - 1] < arr[j]) 
					{
						// swap elements
						temp = arr[j - 1];
						arr[j - 1] = arr[j];
						arr[j] = temp;
						System.out.println("DENEME4");
						temp2 = arr2[j - 1];
						arr2[j - 1] = arr2[j];
						arr2[j] = temp2;
						System.out.println("DENEME5");
					}
				}
			}
		}
	}
	
	public static void main()
	{
		
		linkSayisi=VaadinUI.urlSayisi;
		for(int i = 0;i < linkSayisi; i++)
		{
			linkDizi[i] = VaadinUI.url.get(i);
		}
		
		kelimeSayisi = VaadinUI.kelimeSayisi;
		for(int i = 0;i < kelimeSayisi;i++)
		{
			kelimeDizi[i] =VaadinUI.cumle.get(i);
		}
		
		ExecutorService executorService = Executors.newFixedThreadPool(kelimeSayisi*linkSayisi);
		int sayac = 0;
		for(int i = 0;i<linkSayisi;i++)
		{
			for(int j = 0;j<kelimeSayisi;j++)
			{
				executorService.submit(new Threads(linkDizi[i], kelimeDizi[j], sayac));
				sayac++;
			}
			
		}
				
		executorService.shutdown();
		
		try {
            executorService.awaitTermination(30, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
        }
		
		puanlama(linkDizi, kelimeDizi);
		System.out.println("SIRALAMADAN ONCE");
		/*System.out.println("Ortalama = " + ortalamaCopy[0]);
		System.out.println("Ortalama = " + ortalamaCopy[1]);
		System.out.println("Ortalama = " + ortalamaCopy[2]);*/
		siralama(ortalamaCopy,linkDizi);
		/*System.out.println("SIRALAMADAN SONRA");
		System.out.println(linkDizi[0] + "Ortalama = " + ortalamaCopy[0]);
		System.out.println(linkDizi[1] + "Ortalama = " + ortalamaCopy[1]);
		System.out.println(linkDizi[2] + "Ortalama = " + ortalamaCopy[2]);*/
		
	}
}
