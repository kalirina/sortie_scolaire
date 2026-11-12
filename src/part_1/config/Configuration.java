package part_1.config;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Character;

public class Configuration {
	private int n;
	private char[] studs;
	List<List<Character>> relations;
	Map<Character, List<Integer>> preferences;
	
	public void demarrer(Scanner sc) {
		System.out.println("Bienvenue au système de repartition de vos debils dans les chambres séparés");
		System.out.println("Veuillez saisir le nombre de cachalotes:");
		
		boolean invalid_flag = true;
		while (invalid_flag) {
			int n = sc.nextInt();
			
			if (n > 0 && n < 27) {
				this.n = n;
				invalid_flag = false;
			}
			else {
				System.out.println("Invalid answer");
			}
		}
		
		this.studs = new char[n];
		char ascii = 65;
		System.out.println("Voila les noms de vos etudiants:");
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			studs[i] = ascii;
			if (i != n - 1) 
				System.out.print(studs[i] + ", ");
			ascii++;
		}
		System.out.println(studs[n - 1] + " ]");
	}
	
	public void afficher_menu() {
		System.out.println("\nVeuillez choisir une action:");
		System.out.println("1 - Ajouter une relation entre deux personnes");
		System.out.println("2 - Ajouter les préférences d’une personne");
		System.out.println("3 - Fin de configuration de groupe\n");
	}
	
	public void new_relation(Scanner sc) {
		while (true) {
			System.out.println("Entrez une relation de deux personnes (ex: A B) ou 0 pour finir:");
			String answer = sc.nextLine();
			if (answer.equals("0"))
				return;
			else {
				String[] pers = answer.trim().split("\\s+");
				if (pers.length != 2) {
					System.out.println("Invalid answer");
					continue;
				}
				List<Character> relation = new ArrayList<>();
				relation.add(pers[0].charAt(0));
				relation.add(pers[1].charAt(0));
				this.relations.add(relation);
				System.out.println("Relation ajoute : " + relation);
			}
		}
//		if (!this.studs.contain(pers[0]) || !this.studs.contain(pers[1]))
//			System.out.println("Cette personne n'existe pas");
//		if (!this.relations.contains(pers))
//			this.relations.add(pers);
//		else:
//			System.out.println("Cette relation existe deja");
	}
	
	public void new_preference() {
		System.out.println("Entrez les preference d'une personne (ex: A 1 2 3):");
//		String answer = sc.next();
//		// ...
	}
	public void fin_config() {
		// if preferences and relation are complete 
	}
}
