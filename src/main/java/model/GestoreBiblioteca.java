package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import databaseManager.DataBase;


public class GestoreBiblioteca {
	

	DataBase db;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private Connection dbConn;
    Scanner sc = new Scanner(System.in);
	
	
	
	public void mostraLibri() {
		 db.getConnection();
		 
		try {
			this.stmt = this.dbConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			this.rs = this.stmt.executeQuery("SELECT * FROM libri");
            while (this.rs.next()) {
            	Libro libro = new Libro(
            			this.rs.getInt("id"), 
            			this.rs.getString("titolo"), 
            			this.rs.getString("autore"), 
            			this.rs.getDate("anno_pubblicazione").toLocalDate(),
            			this.rs.getBoolean("disponibille")
            			);
                System.out.println("ID: " + libro.getId() +
                		", Titolo: " + libro.getTitolo() + 
                		", Autore: " + libro.getAutore() + 
                		", Anno: " + libro.getAnnoPubblicazione());
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public void aggiungiLibro () {
		Libro l = new Libro();
		
    	System.out.println("Inserisci nome:");
        String nome = sc.next();
        sc.nextLine();
        
        System.out.println("Inserisci autore:");
        String autore = sc.nextLine();
        
        System.out.println("Inserisci data (yyyy-mm-gg):");
        String data = sc.nextLine();
        
        LocalDate anno = LocalDate.parse(data);
        
        l.setTitolo(nome);
        l.setAutore(autore);
        l.setAnnoPubblicazione(anno);
        l.inserisciLibro(nome, autore, anno);
	}
	
	public void visualizzaLibri() {
		Libro l = new Libro();
		l.mostraLibri();
	}
	
	public void cercaLibroPerTitolo() {
		System.out.println("Inserisci il titolo del libro:");
        String titolo = sc.nextLine();
        
        Libro l = new Libro();
        l.cercaPerTitolo(titolo);
	}
	
	public void prestaLibro() {
		System.out.println("Inserisci il titolo del libro:");
		Libro l = new Libro();
		String titolo = sc.nextLine();
        l.prestaLibro(titolo);;
	}
	
	public void restituisciLibro() {
		System.out.println("Inserisci il titolo del libro:");
		Libro l = new Libro();
		String titolo = sc.nextLine();
        l.restituisciLibro(titolo);
	}
	
	public void eliminaLibro () {
		System.out.println("Inserisci il titolo del libro:");
        String titolo = sc.nextLine();
        
        Libro l = new Libro();
        l.eliminaLibro(titolo);;
	}
	
	
}
