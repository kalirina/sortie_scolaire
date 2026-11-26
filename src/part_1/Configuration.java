package part_1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.Character;

public class Configuration {
	private int n;
	private List<Character> studs;
	private List<List<Character>> relations;
	private Map<Character, List<Integer>> preferences;
	
	public int get_n() {
		return n;
	}
	public List<Character> get_s() {
		return studs;
	}
	public List<List<Character>> get_r() {
		return relations;
	}
	public Map<Character, List<Integer>> get_p() {
		return preferences;
	}
	
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
		
		relations = new ArrayList<>();
		preferences = new HashMap<>();
		studs = new ArrayList<>();
		
		char ascii = 65;
		System.out.println("Voila les noms de vos etudiants:");
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			studs.add(ascii);
			if (i != n - 1) 
				System.out.print(ascii + ", ");
			ascii++;
		}
		System.out.println(studs.get(n - 1) + " ]");
	}
	
	public void afficher_menu() {
		System.out.println("\nVeuillez choisir une action:");
		System.out.println("1 - Ajouter une relation entre deux personnes");
		System.out.println("2 - Ajouter les préférences d’une personne");
		System.out.println("3 - Fin de configuration de groupe\n");
	}
	
	public void new_relation(Scanner sc) {
		while (true) {
			System.out.println("\nListe de relations : " + relations);
			System.out.println("Entrez une relation de deux personnes (ex: A B) ou 0 pour finir:");
			String answer = sc.nextLine();
			if (answer.equals("0"))
				return;
			
			String[] pers = answer.trim().split("\\s+");
			if (pers.length != 2) {
				System.out.println("Invalid answer");
				continue;
			}
			
			char p1 = pers[0].charAt(0);
			char p2 = pers[1].charAt(0);
			
			String err = ajouterRelation(p1, p2);
			if (err != null) {
				System.out.println(err);
			} else {
				System.out.println("Relation ajouté");
			}
		}
	}
	
	public String ajouterRelation(char p1, char p2) {
		if (!studs.contains(p1) || !studs.contains(p2)) {
			return "Cette personne n'existe pas";
		}
		if (relations.contains(List.of(p1,p2)) || relations.contains(List.of(p2,p1))) {
			return "Cette relation existe deja";
		}
		if (p1==p2) {
			return "Impossible pour la meme personne";
		}
		relations.add(List.of(p1, p2));
		return null;
	}
	
	public void new_preference(Scanner sc) {
		while (true) {
			System.out.println("\nListe de préférences : " + preferences);
			System.out.println("Entrez les preference d'une personne (ex: A 1 2 3) ou 0 pour finir:");
			String answer = sc.nextLine();
			if (answer.equals("0"))
				return;
			
			String[] all = answer.trim().split("\\s+");
			if (all.length != n + 1) {
				System.out.println("La reponse n'est pas complete");
				continue;
			}
			
			char person = all[0].charAt(0);
			List<Integer> prefs = new ArrayList<>();
			for (int i = 1; i < all.length; i++) {
				prefs.add(Integer.parseInt(all[i]));
			}
			
			String err = ajouterPreference(person, prefs);
	        if (err != null) {
	        	System.out.println(err);
	        } else {
	        	System.out.println("Préférence ajouté");
	        }
		}
	}
	
	public String ajouterPreference(char p, List<Integer> prefs) {
		if (!studs.contains(p)) {
			return "Cette personne n'existe pas";
		}
		if (prefs.size() !=n) {
			return "La reponse n'est pas complete";
		}
		
		List<Integer> vus = new ArrayList<>();
		for (int num : prefs) {
			if (num<1 || num>n) {
				return "Mauvais numero de chambre";
			}
			if (vus.contains(num)) {
				return "Les chambres doivent etre distinctes";
			}
			vus.add(num);
		}
		
		preferences.put(p, new ArrayList<>(prefs));
		return null;
	}
	
	public List<Character> personnesSansPreferences() {
		List<Character> missing = new ArrayList<>();
		for (char p : studs) {
			if (!preferences.containsKey(p)) {
				missing.add(p);
			}
		}
		return missing;
	}
	
	public boolean ready_to_construction() {
		List<Character> missing = personnesSansPreferences();
		if (!missing.isEmpty()) {
			System.out.println("Les préférnces ne sont pas définies pour toutes les personnes");
			return false;
		}
		return true;
	}
	
	public void initialiser(int n) {
	    this.n = n;
	    relations = new ArrayList<>();
	    preferences = new HashMap<>();
	    studs = new ArrayList<>();
	    char ascii = 'A';
	    for (int i = 0; i < n; i++) {
	        studs.add(ascii);
	        ascii++;
	    }
	}
}
