package com.excilys.formation.java.computerdatabase.dto;

import java.util.ArrayList;

public class Pages {

  /** The ens. */
  private ArrayList<ArrayList<ComputerDTO>> ens = new ArrayList<>();
  /** The current page. */
  private int currentPage;

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    System.out.println("MAXSIZE" + pageMaxSize);
    this.currentPage = currentPage;
  }

  /** The page max. */
  private static int pageMaxSize = 10;

  public static void setPageMaxSize(int max) {
    pageMaxSize = max;
  }

  public int getPageMaxSize() {
    return pageMaxSize;
  }

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
   * @param id
   * @return
   */
  public ArrayList<ComputerDTO> currentPage(int id) {
    currentPage = id % (ens.size());
    return ens.get(currentPage);
  }

  /**
   * Current Page without param.
   * @param id
   * @return
   */
  public ArrayList<ComputerDTO> currentPage() {
    return ens.get(currentPage);
  }

  /**
   * Next page.
   * @return
   */
  public ArrayList<ComputerDTO> nextPage() {
    currentPage++;
    return currentPage();
  }

  /**
   * Previous page.
   * @return
   */
  public ArrayList<ComputerDTO> previousPage() {
    currentPage--;
    return currentPage();
  }
}
