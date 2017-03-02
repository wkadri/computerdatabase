package com.excilys.formation.java.computerdatabase.dto;

import java.util.ArrayList;

/**
 * The Class Pages.
 */
public class Pages {

  /** The ens. */
  private ArrayList<ArrayList<ComputerDTO>> ens = new ArrayList<>();

  public ArrayList<ArrayList<ComputerDTO>> getEns() {
    return ens;
  }

  public void setEns(ArrayList<ArrayList<ComputerDTO>> ens) {
    this.ens = ens;
  }

  /** The current page. */
  private int currentPage;

  /**
   * Gets the current page.
   * @return the current page
   */
  public int getCurrentPage() {
    return currentPage;
  }

  /**
   * Sets the current page.
   * @param currentPage the new current page
   */
  public void setCurrentPage(int currentPage) {
    System.out.println("MAXSIZE" + pageMaxSize);
    this.currentPage = currentPage;
  }

  /** The page max. */
  private static int pageMaxSize = 10;

  /**
   * Sets the page max size.
   * @param max the new page max size
   */
  public static void setPageMaxSize(int max) {
    pageMaxSize = max;
  }

  /**
   * Gets the page max size.
   * @return the page max size
   */
  public int getPageMaxSize() {
    return pageMaxSize;
  }

  /**
   * Instantiates a new pages.
   * @param entree the entree
   * @param max the max
   */
  public Pages(final ArrayList<ComputerDTO> entree, int max) {
    int i = 0;
    currentPage = 0;
    pageMaxSize = max;
    while (i < entree.size() - 3) {
      final ArrayList<ComputerDTO> list = new ArrayList<>();
      while (list.size() < pageMaxSize && i < entree.size()) {
        list.add(entree.get(i));
        i++;
      }
      ens.add(list);
    }
  }

  /**
   * Current Page with param.
   * @param id the id
   * @return the array list
   */
  public ArrayList<ComputerDTO> currentPage(int id) {
    currentPage = id % (ens.size());
    return ens.get(currentPage);
  }

  /**
   * Current Page without param.
   * @return the array list
   */
  public ArrayList<ComputerDTO> currentPage() {
    return ens.get(currentPage);
  }

  /**
   * Next page.
   * @return the array list
   */
  public ArrayList<ComputerDTO> nextPage() {
    currentPage++;
    return currentPage();
  }

  /**
   * Previous page.
   * @return the array list
   */
  public ArrayList<ComputerDTO> previousPage() {
    currentPage--;
    return currentPage();
  }
}
