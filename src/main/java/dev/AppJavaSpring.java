package dev;

import java.util.Scanner;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.config.AppConfig;
import dev.ihm.Menu;

public class AppJavaSpring {

	public static void main(String[] args) {
		// Création du contexte Spring
		// ClassPathXmlApplicationContext context = new
		// ClassPathXmlApplicationContext("application-config-memoire.xml");

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// récupération du bean Menu
		
		Menu menu = context.getBean(Menu.class);

		menu.afficher();

		// fermeture du Scanner
		context.getBean(Scanner.class).close();
		// fermeture du contexte Spring
		context.close();

	}

}
