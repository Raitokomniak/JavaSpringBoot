package com.example.demo;

/*Pieni Spring Boot -sovellus, joka hyödyntää edellä toteutettua rakennetta. Tähän osioon kuuluu lähinnä RESTrajapinta, joka tarjoaa käyttäjälle HTTP:n kautta käyttöliittymätoiminnot, kuten kurssien hakemisen
selaimeen ja uuden tiedon lisääminen. Tiedon lisäämisen voi toteuttaa toimimaan esim. Postmanilla JSONkutsuina tai tehdä jopa html-lomakkeilla kevyen käyttöliittymän (staattiset sivut Spring Bootissa). Ohjelmaa
voi laajentaa halutessaan tukemaan myös monipuolisempia toimintoja kuten opiskelijan lisääminen tietylle
kurssille jne.
Jos REST ei ole hallussa, voi pienen käyttöliittymän tehdä myös konsolisovelluksena. REST-rajapinnan arvo on
kuitenkin suurempi arvioinnissa. */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MyRestController {

	public static void main(String[] args) {
		SpringApplication.run(StudentService.class, args);
	}

}
