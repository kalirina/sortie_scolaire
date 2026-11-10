package part_1.config;

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.lang.Character;

public class Configuration {
	private int n;
	private char[] studs;
	List<List<Character>> relations;
	Map<Character, List<Integer>> preferences;
	
	public void demarrer() {
		System.out.println("Bienvenue au système de repartition de vos debils dans les chambres séparés");
		System.out.println("Veuillez saisir le nombre de cachalotes:");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n > 0 && n < 27)
			this.n = n;
		else {
			System.out.println("Invalid answer");
			sc.close();
			return ;
		}
		char ascii = 71;
		for (int i = 0; i < n; i++) {
			studs[i] = ascii;
			ascii++;
		}
		
		sc.close();
	}
	
	public void afficher_menu() {
		System.out.println("Veuillez choisir une action:");
		System.out.println("1 - Ajouter une relation entre deux personnes");
		System.out.println("2 - Ajouter les préférences d’une personne");
		System.out.println("3 - Fin de configuration de groupe");
		Scanner sc = new Scanner(System.in);
		int choix = sc.nextInt();
		switch (choix) {
			case 1 ->
				new_relation();
			case 2 ->
				new_preference();
			case 3 ->
				fin_config();
			default ->
				System.out.println("Invalid answer");
		}
	}
	
	private void new_relation() {
		
	}
	
	private void new_preference() {
		
	}
	private void fin_config() {
		
	}
}
