package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TekSayfa{
	
	
	public List<String> linklist = new ArrayList<>();
	public List<String> linklistT = new ArrayList<>();
	public List<String> linklistCopy = new ArrayList<>();
	public int sayac = 0;
	public int say = 0;
	public int toplam = 0;
	public String aranacakKelime = "yorumlar";
	public String url = "http://teknoban.blogspot.com.tr/";
	
public TekSayfa(String url,String aranacakKelime) {
		this.url = url;
		this.aranacakKelime = aranacakKelime;
	}

	public String anaSayfaBul() {
		String aranan = "/";
		String url2 = "";
		int i;

		for (i = 0; i < url.length(); i++) {
			if (url.substring(i, i + 1).equals(aranan)) {
				sayac++;
				if (sayac == 3) {
					url2 = url.substring(0, i + 1);
				}
			}
		}

		// System.out.println(url);
		// System.out.println(url2);
		return url2;
	}

	public static int kelimeAra(String text, String aranan) {
		int textKelimeSayisi = 0;

		for (int i = 0; i < text.length() - aranan.length() + 1; i++) {
			if (text.substring(i, i + aranan.length()).equalsIgnoreCase(aranan)) {
				textKelimeSayisi++;
				// System.out.println("text " + textKelimeSayisi);
			}
		}
		return textKelimeSayisi;

	}

	public void linkAl() {

		try {
			long ilk = System.currentTimeMillis();
			// anaSayfaBul();
			Document doc = Jsoup.connect(url).get();
			Elements links = doc.select("a[href]");
			for (Element link : links) {
				String strLink = link.attr("href");
				if (strLink.contains(url) && !linklist.contains(strLink)) {
					// System.out.println("\nlink : " + linkS);
					// System.out.println("text : " + link.text());
					linklist.add(strLink);
					linklistT.add(link.text());
				}
			}

			for (String listele : linklist) {
				System.out.println("link: " + listele);

				if (!listele.equals(url)) {
					System.out.println("document");
					Document document = Jsoup.connect(listele).get();
					String text = document.text();

					toplam += kelimeAra(text, aranacakKelime);
				} else {
					System.out.println(">>>>>>>>>DOC<<<<<<<<<");
					toplam += kelimeAra(doc.text(), aranacakKelime);
				}

				long son = System.currentTimeMillis();
				System.out.println("sure = " + (son - ilk));
				System.out.println("sayi = " + toplam);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int main() {
		long bas = System.currentTimeMillis();

		int derinlik, i;
		for (derinlik = 1; derinlik <= 2;) {
			if (derinlik == 1) {
				System.out.println("---------derinlik1---------");
				
				linkAl();
				
				derinlik++;

			}

			linklistCopy.addAll(linklist);
			final int boyut = linklistCopy.size();
			System.out.println("en bastaki " + boyut);

			if (derinlik == 2) {
				System.out.println("-------------derinlik2------------");
				for (i = 0; i < boyut; i++) {
					url = linklistCopy.get(i);
					System.out.println("\n------şuan " + linklistT.get(i) + " deyim------");
					System.out.println("--------url :" + url + "-----");
					linklist.removeAll(linklist);
					
					linkAl();
					
					derinlik++;
				}
			}

		}
		long ayak = System.currentTimeMillis();
		System.out.println(ayak - bas);
		return toplam;
	}

}
