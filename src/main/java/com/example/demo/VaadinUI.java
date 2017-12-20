package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.github.appreciated.material.MaterialTheme;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
@UIScope
public class VaadinUI extends UI {
	static List<String> url = new ArrayList<>();
	static List<String> cumle = new ArrayList<>();
	static List<String> cumleEs = new ArrayList<>();
	static String girdiyeYaz="";
	static String cıktıyaYaz="";
	static String puanYaz="";
	static int urlSayisi=0;
	static int kelimeSayisi=0;
	private static final long serialVersionUID = 1L;
	
	TextField urlgir = new TextField("URL Gir");
	TextField aranan = new TextField("Cümle yada kelime gir");
	TextArea girdi =new TextArea("Girilen URL");
	TextArea cıktı =new TextArea("SONUC");
	TextArea puantaj =new TextArea("PUANLAR");
	Button buttonE = new Button("EKLE");
	Button buttonA = new Button("ARA");

	@Override
	
	protected void init(VaadinRequest request) {
		AbsoluteLayout layout = new AbsoluteLayout();
		//layout.setSizeFull();
		layout.setResponsive(true);
		setContent(layout);
		layout.addComponent(urlgir,"left: 100px; top: 100px;");
		layout.addComponent(buttonE,"left: 100px; top: 150px;");
		layout.addComponent(aranan,"left: 1000px; top: 100px;");
		layout.addComponent(buttonA,"left: 1000px; top: 150px;");
		layout.addComponent(girdi,"left: 325px; top: 150px;");
		layout.addComponent(cıktı,"left: 100px; top: 350px;");
		layout.addComponent(puantaj,"left: 1000px; top: 350px;");
		girdi.setHeight("150px");
		girdi.setWidth("600px");
		cıktı.setHeight("200px");
		cıktı.setWidth("825px");
		puantaj.setHeight("200px");
		buttonE.addStyleName(MaterialTheme.BUTTON_FRIENDLY);
	    buttonE.setIcon(VaadinIcons.PLUS_CIRCLE);
	    buttonA.addStyleName(MaterialTheme.BUTTON_FRIENDLY);
	    buttonA.setIcon(VaadinIcons.SEARCH);
	    
	    Label header = new Label("TÜRK ARAMA MOTORU");
	       header.addStyleName(ValoTheme.LABEL_H1);
	      layout.addComponent(header,"left: 325px; top: 25px;");
	    
		buttonE.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					url.add(urlgir.getValue());
					girdiyeYaz+=urlgir.getValue()+"\n";
					girdi.setValue(girdiyeYaz);
					urlSayisi++;
					/*System.out.println("sayı  "+urlSayisi);
					System.out.println("girdi: "+girdiyeYaz);
					for(int i=0;i<urlSayisi;i++)
						System.out.println(url.get(i));*/
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		});
		buttonA.addClickListener(new Button.ClickListener() {
			
			private static final long serialVersionUID = 1L;

			public void buttonClick(ClickEvent event) {
				try {
					String ayrac = " "; 
					StringTokenizer s = new StringTokenizer(aranan.getValue(), ayrac);
					kelimeSayisi=s.countTokens();
					System.out.println(kelimeSayisi);
					while (s.hasMoreTokens()) {
						cumle.add(s.nextToken());
					}

					for(int i = 0; i <kelimeSayisi ; i++) {
						cumle.add(kelimeSayisi,Esanlamlilar.esanlamli(cumle.get(i)));
					}
					String a="";
					for(String esli :cumle) {
						a+=esli+" ";
					}
					s = new StringTokenizer(a, ayrac);
					kelimeSayisi=s.countTokens();
					System.out.println(kelimeSayisi);
					cumle.removeAll(cumle);
					while (s.hasMoreTokens()) {
						cumle.add(s.nextToken());
					}
					
					for(int i = 0; i < kelimeSayisi; i++) {
						String yeniKelime = Esanlamlilar.kelimeCevir(cumle.get(i));
						if(!cumle.get(i).equals(yeniKelime)) {
							cumle.add(i,yeniKelime);
						}					
					}
					
					ThreadTest.main();
					for (int i=0;i<ThreadTest.linkSayisi;i++) {
						cıktıyaYaz+=ThreadTest.linkDizi[i]+"\n";
					}	
					cıktı.setValue(cıktıyaYaz);
					for (int i=0;i<ThreadTest.linkSayisi;i++) {
						puanYaz+=ThreadTest.ortalamaCopy[i]+"\n";
					}	
					puantaj.setValue(puanYaz);	
				}
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}
	
}
