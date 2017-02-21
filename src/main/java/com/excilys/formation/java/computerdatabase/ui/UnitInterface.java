package com.excilys.formation.java.computerdatabase.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * Simple UI build from a Scanner
 */
public class UnitInterface {

	public static void main(String[] args) throws InterruptedException {
		CompanyService companyService = new CompanyService();
		ComputerService computerService = new ComputerService();
		Scanner sc = new Scanner(System.in);
		do {
			try {
				System.out.println(" ");
				System.out.println("Bienvenue sur Computer DataBase");
				System.out.println("Tapez la commande de votre choix :");
				System.out.println("1 . Recuperer l'ensemble des noms et id des ordinateurs dans la base de donnée");
				System.out.println("2 . Recuperer l'ensemble des noms et id  compagnies ans la base de donnée");
				System.out.println("3 . Recuperer les informations d'un ordinateur en fonction de son id");
				System.out.println("4 . Ajouter un ordinateur à la base de donnée (avec seulement le nom)");
				System.out.println("5 . Modifier le nom d'un ordinateur");
				System.out.println("6 . Supprimer un ordinateur");
				int i = sc.nextInt();
				int id = 0;
				String name = "";
				switch (i) {
				case 1:
					computerService.getComputers();
					break;
				case 2:
					companyService.getCompanies();
					break;
				case 3:
					System.out.println("Saisissez l'id de l'ordinateur");
					id = sc.nextInt();
					computerService.describeComputerByID(id);
					break;
				case 4: {
					System.out.println("Saisissez le nom du nouvel ordinateur");
					sc.nextLine();
					name = sc.nextLine();
					computerService.createComputer(name);
					break;
				}
				case 5: {
					System.out.println("Saisissez l'id de l'ordinateur à modifier");
					id = sc.nextInt();
					System.out.println("Saisissez le nouveau nom de l'ordinateur");
					while (name.isEmpty()) {
						name = sc.nextLine();
					}
					computerService.updateComputer(id, name);
					break;
				}
				case 6:
					System.out.println("Saisissez l'id de l'ordinateur à supprimer");
					id = sc.nextInt();
					computerService.deleteComputer(id);
					break;
				}
			} catch (InputMismatchException e) {
				System.out.println("Mauvaise commande");
				System.out.println("Recommencez");
				sc.nextLine();
			}
			Thread.sleep(100);
		} while (true);
	}
}