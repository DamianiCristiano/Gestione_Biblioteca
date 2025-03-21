package model;

import java.util.Scanner;

public class Menu {

	int scelta;
	Scanner sc = new Scanner(System.in);

	public void menu() {
		do {
			GestoreBiblioteca gb = new GestoreBiblioteca();
			System.out.println("1: Visualizza libri disponibili");
			System.out.println("2: Visualizza libro selezionato");
			System.out.println("3: Aggiungi libro");
			System.out.println("4: Presta libro:");
			System.out.println("5: Restituisci libro:");
			System.out.println("6: Elimina libro");
			System.out.println("7: Esci");
			scelta = sc.nextInt();
			sc.nextLine();

			if (scelta < 1 || scelta > 5) {
				System.out.println("Scelta non valida. Riprova.");
			}
			
			if (scelta == 1) {
				gb.visualizzaLibri();
			} 
			
            if (scelta == 2) {
            	gb.cercaLibroPerTitolo();
            }
            
            if (scelta == 3) {
                gb.aggiungiLibro();
            }
            
            if (scelta == 4) {
                gb.prestaLibro();
            }
            
            if (scelta == 5) {
                gb.restituisciLibro();
            }
            
            if (scelta == 6) {
                gb.eliminaLibro();
            }
			
		} while (scelta != 7);
		System.out.println("Buonagiornata!");
	}

}
