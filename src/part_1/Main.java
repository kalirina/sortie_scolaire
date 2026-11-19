package part_1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Configuration start = new Configuration();
		start.demarrer(sc);
		
		boolean config_flag = true;
		while (config_flag) {
			start.afficher_menu();
			
			int choix = sc.nextInt();
			sc.nextLine();
			
			switch (choix) {
				case 1 ->
					start.new_relation(sc);
				case 2 ->
					start.new_preference(sc);
				case 3 -> {
					if (start.ready_to_construction())
						config_flag = false;
				}
				default ->
					System.out.println("Invalid answer");
			}
		}
		
//		 System.out.println("ciao");
		 Construction next = new Construction(start.get_n(), start.get_s(), start.get_r(), start.get_p());

		 next.solution_naive();
		 next.affichier_affectations();
		 next.calculer_score();
		 System.out.println("Le score de jalousité: " + next.get_sc());
		 
		 while (true) {
			 next.afficher_menu();
			 
			 int choix = sc.nextInt();
			 sc.nextLine();
			 
			 switch (choix) {
			 	case 1 ->
					next.changer_chambres(sc);
				case 2 ->
					System.out.println("Le score de jalousité: " + next.get_sc());
				case 3 -> {
					System.out.println("Construction finished");
					sc.close();
					return;
				}
				default ->
					System.out.println("Invalid answer");
			}
		 }
	}
}

/*
 * A B
 * C B
 * D B
 * 
 * A 1 2 3 4
 * B 1 3 2 4
 * C 3 2 1 4
 * D 1 4 2 3
 * 
 */

