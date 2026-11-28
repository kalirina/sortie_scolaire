package part_1;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Configuration start = new Configuration();

		System.out.println("Bienvenue au système de repartition de vos debils dans les chambres séparés");
		int n;
		while (true) {
			System.out.println("Veuillez saisir le nombre de cachalotes:");
			String line = sc.nextLine().trim();
			n = Integer.parseInt(line);
			if (start.demarrer(n))
				break;
		}

		System.out.println("Voila les noms de vos etudiants:");
		System.out.print("[ ");
		for (int i = 0; i < n; i++) {
			char id = start.get_student_ids().get(i);
			System.out.print(i == n - 1 ? id + " ]" : id + ", ");
		}

		boolean configFlag = true;
		while (configFlag) {
			System.out.println("\nVeuillez choisir une action:");
			System.out.println("1 - Ajouter une relation entre deux personnes");
			System.out.println("2 - Ajouter les préférences d’une personne");
			System.out.println("3 - Fin de configuration de groupe\n");

			String line = sc.nextLine().trim();
			switch (line) {
				case "1" -> {
					while (true) {
						System.out.println("\nListe de relations : " + start.get_r());
						System.out.println("Entrez une relation de deux personnes (ex: A B) ou 0 pour finir:");
						String relation = sc.nextLine();
						if (relation.trim().equals("0")) {
							break;
						}
						if (start.new_relation(relation)) {
							System.out.println("Relation ajouté");
						} else {
							System.out.println("Invalid answer");
						}
					}
				}
				case "2" -> {
					while (true) {
						System.out.println("\nListe de préférences : " + start.get_p());
						System.out.println("Entrez les preference d'une personne (ex: A 1 2 3) ou 0 pour finir:");
						String preference = sc.nextLine();
						if (preference.trim().equals("0")) {
							break;
						}
						if (start.new_preference(preference)) {
							System.out.println("Préférence ajouté");
						} else {
							System.out.println("La reponse n'est pas valide");
						}
					}
				}
				case "3" -> {
					if (start.ready_to_construction()) {
						configFlag = false;
					} else {
						System.out.println("Les préférnces ne sont pas définies pour toutes les personnes");
					}
				}
				default -> System.out.println("Invalid answer");
			}
		}

		Construction next = new Construction(n, start.get_students(), start.get_r(), start.get_p());
		next.solution_naive();
		next.calculer_score();

		System.out.println("\nLes affectations courantes:");
		for (Student student : next.get_students()) {
			System.out.println(student.get_id() + " : "
					+ next.get_room_assignment().roomOf(student));
		}
		System.out.println("Le score de jalousité: " + next.get_sc());

		while (true) {
			System.out.println("\nVeuillez choisir une action:");
			System.out.println("1 - Echanger les chambres de deux personnes");
			System.out.println("2 - Afficher le nombre de personnes jalouses");
			System.out.println("3 - Fin\n");
			String line = sc.nextLine().trim();
			switch (line) {
				case "1" -> {
					while (true) {
						System.out.println("\nLes affectations courantes:");
						for (Student student : next.get_students()) {
							System.out.println(student.get_id() + " : "
									+ next.get_room_assignment().roomOf(student));
						}
						System.out.println("Le score de jalousité: " + next.get_sc());
						System.out.println("Entrez deux personnes pour echanger les chambres (ex: A B) ou 0 pour finir:");
						String exchange = sc.nextLine();
						if (exchange.trim().equals("0")) {
							break;
						}
						if (next.changer_chambres(exchange)) {
							System.out.println("Echange effectué");
						} else {
							System.out.println("Invalid answer");
						}
					}
				}
				case "2" -> System.out.println("Le score de jalousité: " + next.get_sc());
				case "3" -> {
					System.out.println("Construction finished");
					sc.close();
					return;
				}
				default -> System.out.println("Invalid answer");
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