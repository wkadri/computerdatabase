package com.excilys.formation.java.computerdatabase.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.formation.java.computerdatabase.dto.ComputerDTO;

/**
 * Computer Page
 * 
 * @author Walid KADRI
 */
public class ComputerPages {
	
	private ArrayList<ArrayList<ComputerDTO>> ens = new ArrayList<>();
	private int current_page;
	private final int pageMax = 10;
	
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
		current_page = 0;
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
			}
			
		}
	}
	
	public ArrayList<ArrayList<ComputerDTO>> getEns() {
		return ens;
	}
	
	public void setEns(final ArrayList<ArrayList<ComputerDTO>> ens) {
		this.ens = ens;
	}
	
	void display() {
		ens.get(current_page).forEach(t -> System.out.println(t.toString()));
	}
	
	public void displayPage(final int num) {
		current_page = num;
		display();
	}
	
	public void nextPage() {
		current_page++;
		display();
	}
	
	public void previousPage() {
		current_page--;
		display();
	}
}
