package part_1;

import java.util.Scanner;

import sun.jvm.hotspot.runtime.ConstructionException;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Configuration start = new Configuration();
		start.demarrer(sc);
		
		while (true) {
			start.afficher_menu();
			
			int choix = sc.nextInt();
			sc.nextLine();
			
			switch (choix) {
				case 1 ->
					start.new_relation(sc);
				case 2 ->
					start.new_preference(sc);
				case 3 -> {
					break;
				}
				default ->
					System.out.println("Invalid answer");
			}
		}
		
		// Construction start = new 
	}
}
