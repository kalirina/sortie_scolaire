package part_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Construction {
	private int n;
	private int score;
	private List<Character> studs;
	private List<List<Character>> relations;
	private Map<Character, List<Integer>> preferences;
	private Map<Character, Integer> affectations;
	
	public int get_n() {
		return n;
	}
	public int get_sc() {
		return score;
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
	public Map<Character, Integer> get_a() {
		return affectations;
	}
	
	public Construction(int n, List<Character> s, List<List<Character>> r, Map<Character, List<Integer>> p) {
		this.n = n;
		studs = s;
		relations = r;
		preferences = p;
		affectations = new HashMap<>();
	}
	
	public void solution_naive() {
		List<Integer> rooms = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++)
			rooms.add(i);
		
		for (int i = 0; i < n; i++) {
			List<Integer> p = preferences.get(studs.get(i));
			for (int j = 0; j < p.size(); j++) {
				if (rooms.contains(p.get(j))) {
					affectations.put(studs.get(i), p.get(j));
					rooms.remove(p.get(j));
					break;
				}
			}
		}
	}
	
	public void affichier_affectations() {
		System.out.println("\nLes affectations courantes:");
		for (int i = 0; i < n; i++) {
			System.out.println(studs.get(i) + " : " + affectations.get(studs.get(i)));
		}
	}
	
	public void afficher_menu() {
		System.out.println("\nVeuillez choisir une action:");
		System.out.println("1 - Echanger les chambres de deux personnes");
		System.out.println("2 - Afficher le nombre de personnes jalouses");
		System.out.println("3 - Fin\n");
	}
	
	public void changer_chambres(Scanner sc) {
		while (true) {
			affichier_affectations();
			System.out.println("Le score de jalousité: " + get_sc());
			System.out.println("Entrez deux personnes pour echanger les chambres (ex: A B) ou 0 pour finir:");
			String answer = sc.nextLine();
			if (answer.equals("0")) {
				return;
			}
			
			String[] pers = answer.trim().split("\\s+");
			if (pers.length != 2) {
				System.out.println("Invalid answer");
				continue;
			}
			
			char p1 = pers[0].charAt(0);
			char p2 = pers[1].charAt(0);
			
			String err = echanger(p1, p2);
	        if (err != null) {
	            System.out.println(err);
	        }
		}
	}
	
	public void calculer_score() {
		score = 0;
		for (int i = 0; i < n; i++) {
			char p = studs.get(i);
			int c = affectations.get(studs.get(i));
			List<Integer> pr = preferences.get(p);
			
			if (c == pr.get(0))
				continue;
			
			List<Character> jalouse_de = new ArrayList<>();
			
			for (int j = 0; j < relations.size(); j++) {
				List<Character> pair = relations.get(j);
				if (pair.contains(p)) {
					int index = pair.indexOf(p);
					if (index == 0)
						jalouse_de.add(pair.get(1));
					else
						jalouse_de.add(pair.get(0));
				}
			}
			
			boolean next_stud = false;
			for (int k = 0; k < pr.indexOf(c); k++) {
				int ch = pr.get(k);
				for (int l = 0; l < jalouse_de.size(); l++) {
					char fou = jalouse_de.get(l);
					int fou_ch = affectations.get(fou);
					if (fou_ch == ch) {
						score++;
						next_stud = true;
						break;
					}
				}
				if (next_stud)
					break;
			}
		}
	}
	
	public String echanger(char p1, char p2) {
	    if (!studs.contains(p1) || !studs.contains(p2))
	        return "Cette personne n'existe pas";
	    int c1 = affectations.get(p1);
	    int c2 = affectations.get(p2);
	    affectations.replace(p1, c2);
	    affectations.replace(p2, c1);
	    calculer_score();
	    return null;
	}
}
