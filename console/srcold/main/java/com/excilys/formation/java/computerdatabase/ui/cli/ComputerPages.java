package com.excilys.formation.java.computerdatabase.ui.cli;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.formation.java.computerdatabase.ui.dto.ComputerDTO;

/**
 * Computer Page.
 * @author Walid KADRI
 */
public class ComputerPages {
  /** The ens. */
  private ArrayList<ArrayList<ComputerDTO>> ens = new ArrayList<>();
  /** The current page. */
  private int currentPage;
  /** The page max. */
  private final int pageMax = 10;

  /**
   * Instantiates a new computer pages.
   * @param entree the entree
   * @param sc the sc
   */
  public ComputerPages(final ArrayList<ComputerDTO> entree, final Scanner sc) {
    int i = 0;
    while (i < entree.size() - 10) {
      final ArrayList<ComputerDTO> list = new ArrayList<>();
      while (list.size() < pageMax || i == entree.size() - 1) {
        list.add(entree.get(i));
        i++;
      }
      ens.add(list);
    }
    currentPage = 0;
    display();
    String c = "";
    while (c != "q") {
      System.out.println("Commande : n page suivante;p page précedente;q quitter");
      c = sc.nextLine();
      switch (c) {
        case "p":
          previousPage();
          break;
        case "n":
          nextPage();
          break;
        case "q":
          c = "q";
          break;
        default:
          break;
      }
    }
  }

  /**
   * Gets the ens.
   * @return the ens
   */
  public ArrayList<ArrayList<ComputerDTO>> getEns() {
    return ens;
  }

  /**
   * Sets the ens.
   * @param ens the new ens
   */
  public void setEns(final ArrayList<ArrayList<ComputerDTO>> ens) {
    this.ens = ens;
  }

  /**
   * Display.
   */
  void display() {
    ens.get(currentPage).forEach(t -> System.out.println(t.toString()));
  }

  /**
   * Display page.
   * @param num the num
   */
  public void displayPage(final int num) {
    currentPage = num;
    display();
  }

  /**
   * Next page.
   */
  public void nextPage() {
    currentPage++;
    display();
  }

  /**
   * Previous page.
   */
  public void previousPage() {
    currentPage--;
    display();
  }
}
