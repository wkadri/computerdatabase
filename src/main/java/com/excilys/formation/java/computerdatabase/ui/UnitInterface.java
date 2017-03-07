package com.excilys.formation.java.computerdatabase.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.excilys.formation.java.computerdatabase.dao.mysql.DAOException;
import com.excilys.formation.java.computerdatabase.service.CompanyService;
import com.excilys.formation.java.computerdatabase.service.ComputerService;

/**
 * The Class UnitInterface.
 */
public class UnitInterface {

  /**
   * The main method.
   * @param args the arguments
   * @throws InterruptedException the interrupted exception
   * @throws DAOException exception
   */
  public static void main(final String[] args) throws InterruptedException, DAOException {
    final CompanyService companyService = new CompanyService();
    final ComputerService computerService = new ComputerService();
    final Scanner sc = new Scanner(System.in);
    do {
      try {
        System.out.println(" ");
        System.out.println("Bienvenue sur Computer DataBase");
        System.out.println("Tapez la commande de votre choix :");
        System.out.println("1 . Recuperer l'ensemble des ordinateurs présent dans la base de donnée");
        System.out.println("2 . Recuperer l'ensemble des compagnies de la base de donnée");
        System.out.println("3 . Recuperer les informations d'un ordinateur en fonction de son id");
        System.out.println("4 . Ajouter un ordinateur à la base de donnée (avec seulement le nom)");
        System.out.println("5 . Modifier le nom d'un ordinateur");
        System.out.println("6 . Supprimer un ordinateur");
        final int i = sc.nextInt();
        int id = 0;
        String name = "";
        switch (i) {
          case 1:
            //final ComputerPages pages = new ComputerPages(computerService.getComputers(), sc);
            break;
          case 2:
            companyService.getCompanies();
            break;
          case 3:
            System.out.println("Saisissez l'id de l'ordinateur");
            id = sc.nextInt();
            computerService.describeComputerByID(id);
            break;
          case 4:
            System.out.println("Saisissez le nom du nouvel ordinateur");
            sc.nextLine();
            name = sc.nextLine();
            System.out.println("Saisissez la date d'introduction(facultatif format yyyy-mm-dd)");
            final String date = sc.nextLine();
            computerService.createComputer(name, date);
            break;
          case 5:
            String newIntroduced = "";
            System.out.println("Saisissez l'id de l'ordinateur à modifier");
            id = sc.nextInt();
            System.out.println("Saisissez le nouveau nom de l'ordinateur");
            while (name.isEmpty()) {
              name = sc.nextLine();
            }
            System.out.println("Saisissez la nouvelle date d'introduction");
            while (newIntroduced.isEmpty()) {
              newIntroduced = sc.nextLine();
            }
            computerService.updateComputer(id, name, newIntroduced, null);
            break;
          case 6:
            System.out.println("Saisissez l'id de l'ordinateur à supprimer");
            id = sc.nextInt();
            computerService.deleteComputer(id);
            break;
        }
      } catch (final InputMismatchException e) {
        System.out.println("Mauvaise commande");
        System.out.println("Recommencez");
        sc.nextLine();
      }
      Thread.sleep(100);
    } while (true);
  }
}
