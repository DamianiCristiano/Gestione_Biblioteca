package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import databaseManager.DataBase;


public class Libro {
	
	private int id;
	private String titolo;
	private String autore;
	private LocalDate annoPubblicazione;
	private boolean disponibile = true;
	DataBase db = new DataBase();
	
	
	public Libro() {
	}
	
	public Libro(int id, String titolo, String autore, LocalDate annoPubblicazione) {
		this.id = id;
		this.titolo = titolo;
		this.autore = autore;
		this.annoPubblicazione = annoPubblicazione;
		this.disponibile = true;
	}
	
	public Libro(int id, String titolo, String autore, LocalDate annoPubblicazione, boolean disponibile) {
		this.id = id;
		this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.disponibile = true;;
	}
	
	public Libro(String titolo, String autore, LocalDate annoPubblicazione) {
		this.titolo = titolo;
        this.autore = autore;
        this.annoPubblicazione = annoPubblicazione;
        this.disponibile = true;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getAutore() {
		return autore;
	}
	public void setAutore(String autore) {
		this.autore = autore;
	}
	public LocalDate getAnnoPubblicazione() {
		return annoPubblicazione;
	}
	public void setAnnoPubblicazione(LocalDate annoPubblicazione) {
		this.annoPubblicazione = annoPubblicazione;
	}
	public boolean isDisponibile() {
		return disponibile;
	}
	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}
	
	public void inserisciLibro(String titolo, String autore, LocalDate anno) {
		Connection conn = db.getConnection();
		String sql = "INSERT INTO libri (titolo, autore, anno_pubblicazione, disponibile) VALUES (?,?,?,?)";
		if (conn != null) {	
			try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, titolo);
                pstmt.setString(2, autore);
                pstmt.setDate(3, Date.valueOf(anno));
                pstmt.setBoolean(4, disponibile);
                pstmt.executeUpdate();
                System.out.println("Libro inserito con successo!");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
		}
	}
	
	public void mostraLibri() {
        Connection conn = db.getConnection();
        String sql = "SELECT * FROM libri";
        if (conn!= null) {    
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") 
                    + ", Titolo: " + rs.getString("titolo") 
                    + ", autore: " + rs.getString("autore") 
                    + ", anno di pubblicazione: " + rs.getDate("anno_pubblicazione") 
                    + ", disponibilita': " + rs.getBoolean("disponibile"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }
        }
	}
	
	public void cercaPerTitolo (String titolo) {
		Connection conn = db.getConnection();
        String sql = "SELECT * FROM libri WHERE titolo LIKE?";
        if (conn!= null) {    
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,  titolo);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id") 
                    + ", Titolo: " + rs.getString("titolo") 
                    + ", autore: " + rs.getString("autore") 
                    + ", anno di pubblicazione: " + rs.getDate("anno_pubblicazione") 
                    + ", disponibilita': " + rs.getBoolean("disponibile"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
            }}
	}
	
	public void prestaLibro(String titolo) {
		Connection conn = db.getConnection();
        if (conn!= null) {
        		try {
                PreparedStatement pstmt = conn.prepareStatement("Update libri SET disponibile = ? WHERE titolo = ?");
                pstmt.setString(2, titolo);
                pstmt.setBoolean(1, false);
                pstmt.executeUpdate();
                System.out.println("Libro prestato con successo!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
	}
	
	public void restituisciLibro(String titolo) {
		Connection conn = db.getConnection();
        if (conn!= null) {
        		try {
                PreparedStatement pstmt = conn.prepareStatement("Update libri SET disponibile = ? WHERE titolo = ?");
                pstmt.setString(2, titolo);
                pstmt.setBoolean(1, true);
                pstmt.executeUpdate();
                System.out.println("Libro prestato con successo!");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
	}
	
	public void eliminaLibro(String titolo) {
			Connection conn = db.getConnection();
        if (conn!= null) {
                try {
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM libri WHERE titolo =?");
                pstmt.setString(1, titolo);
                pstmt.executeUpdate();
                System.out.println("Libro eliminato con successo!");
        } catch(SQLException e) {
                e.printStackTrace();
          };
        }
    }


}
