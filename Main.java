package farmacie.gestiune;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class Main {

	 public static void main(String[] args) {
	        /*EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                	Farmacie f = new Farmacie();
	                    f.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });*/
		String jdbcURL="jdbc:mysql://localhost:3306/gestiune_farmacii";
 		String username="root";
 		String password="";
 		System.out.println("Selectati o optiune:");
 		System.out.println("1. Afisarea tuturor farmaciilor dintr-un anumit oras");
 		System.out.println("2. Cate comenzi a primit farmacia Dona in August. suma totala, valoarea medie per comanda");
 		System.out.println("3. Cate comenzi de antibiotice a primit farmacia Vlad in 2020");
 		System.out.println("4. Care e farmacia care a comandat cel mai mult in 2020 , ca valoare absoluta");
 		System.out.println("5. Afisarea tuturor farmaciilor care au pe stoc un anumit medicament");
 		System.out.println("6. Afisarea cantitatilor totale pentru un medicament selectat dintr-un anumit oras");
 		System.out.println("0. Iesire");
 		Scanner scanner = new Scanner(System.in);
 		String choice;
 		 do {
             choice = scanner.nextLine();
             switch (choice) {
             case "1":
            	 System.out.println("Dati numele orasului");
            	 scanner = new Scanner(System.in);
            	 String nume_oras = scanner.nextLine();
            	 try {
                     Connection con = DriverManager.getConnection(jdbcURL, username, password);
                     if (con!=null) {
         				System.out.println("Conectat la baza de date");
         			}
                                        
                    PreparedStatement s=con.prepareStatement("SELECT nume FROM farmacie WHERE oras=?");
                    s.setString(1, nume_oras);   
                    ResultSet result = s.executeQuery();
                    //System.out.println(result.getString(2));
         			while (result.next()) {
         				String u=result.getString(1);
         				System.out.println(u);
         			}
                    con.close();
                 } catch (Exception exception) {
                     exception.printStackTrace();
                 }
            	 break;
              case "2":
            	  try {
                      Connection con = DriverManager.getConnection(jdbcURL, username, password);
                      if (con!=null) {
          				System.out.println("Conectat la baza de date");
          			}
                     
                     PreparedStatement s=con.prepareStatement("SELECT COUNT(*), SUM(valoare_comanda), AVG(valoare_comanda) FROM comanda"
                       + " INNER JOIN `farmacie` ON `comanda`.`id_farmacie` = `farmacie`.`id_farmacie`"                   
                	   + " WHERE  farmacie.nume='Dona' AND MONTH(data_livrare)='08'");
                     ResultSet result = s.executeQuery();
                     //System.out.println(result.getString(2));
          			while (result.next()) {
          				String nr_comenzi=result.getString(1);
          				System.out.println("Farmaciile Dona au primit " + nr_comenzi + " comenzi in luna August");
          				String valoare_totala=result.getString(2);
          				System.out.println("Suma totala a comenzilor este " + valoare_totala);
          				String valoare_medie=result.getString(3);
          				System.out.println("Valoarea medie per comanda este " + valoare_medie);
          			}
                     con.close();
                  } catch (Exception exception) {
                      exception.printStackTrace();
                  }
            	  break;
              case "3":
            	  try {
                      Connection con = DriverManager.getConnection(jdbcURL, username, password);
                      //if (con!=null) {
          				//System.out.println("Conectat la baza de date");
          			  //}
                     
                     PreparedStatement s=con.prepareStatement("SELECT COUNT(*) FROM comanda"
                       + " INNER JOIN `farmacie` ON `comanda`.`id_farmacie` = `farmacie`.`id_farmacie`"
                       + " INNER JOIN `comanda_produs` ON `comanda`.`id_comanda` = `comanda_produs`.`id_comanda`"
                       + " INNER JOIN `medicament` ON `comanda_produs`.`id_medicament` = `medicament`.`id_medicament`"
                       + " INNER JOIN `categorie_medicament` ON `medicament`.`id_medicament` = `categorie_medicament`.`id_medicament`"
                       + " INNER JOIN `categorie` ON `categorie`.`id_categorie` = `categorie_medicament`.`id_categorie`"
                	   + " WHERE  farmacie.nume='Vlad' AND YEAR(data_livrare)='2022' AND categorie.nume='antibiotic'");
                     ResultSet result = s.executeQuery();
                     //System.out.println(result.getString(2));
          			while (result.next()) {
          				String u=result.getString(1);
          				System.out.println(u);
          			}
                     con.close();
                  } catch (Exception exception) {
                      exception.printStackTrace();
                  }
            	  break;
              case "4":
            	  try {
                      Connection con = DriverManager.getConnection(jdbcURL, username, password);
                      if (con!=null) {
          				System.out.println("Conectat la baza de date");
          			}
                     
                     PreparedStatement s=con.prepareStatement("SELECT nume, max(valoare_totala)  FROM ("
                     		+ " SELECT farmacie.nume, SUM(comanda.valoare_comanda) AS valoare_totala FROM farmacie"
                     		+ " INNER JOIN `comanda` ON `comanda`.`id_farmacie` = `farmacie`.`id_farmacie`"
                     		+ " WHERE  YEAR(data_livrare)='2020'"
                     		+ " GROUP BY farmacie.nume)"
                     		+ "A;");
                     //SELECT SUM(valoare_comanda) FROM comanda INNER JOIN `farmacie` ON `comanda`.`id_farmacie` = `farmacie`.`id_farmacie` WHERE YEAR(data_livrare)='2020' GROUP BY farmacie.nume;
                     		
                     ResultSet result = s.executeQuery();
                     //System.out.println(result.getString(2));
          			while (result.next()) {
          				String u=result.getString(1);
          				System.out.println(u);
          			}
                     con.close();
                  } catch (Exception exception) {
                      exception.printStackTrace();
                  }
               	break;
              case "5":
            	 System.out.println("Dati numele medicamentului");
             	 scanner = new Scanner(System.in);
             	 String nume_medicament = scanner.nextLine();
             	try {
                    Connection con = DriverManager.getConnection(jdbcURL, username, password);
                    if (con!=null) {
        				System.out.println("Conectat la baza de date");
        			}
                   
                   PreparedStatement s=con.prepareStatement("SELECT farmacie.nume FROM farmacie"
                   + " INNER JOIN `stoc` ON `stoc`.`id_farmacie` = `farmacie`.`id_farmacie`"                   
           		   + " INNER JOIN `medicament` ON `medicament`.`id_medicament` = `stoc`.`id_medicament`"
              	   + " WHERE  stoc.medicamente_ramase>1 AND medicament.nume=?");
                   s.setString(1, nume_medicament);   
                   		
                   ResultSet result = s.executeQuery();
                   //System.out.println(result.getString(2));
        			while (result.next()) {
        				String u=result.getString(1);
        				System.out.println(u);
        			}
                   con.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
             	break;
              case "6":
            	 System.out.println("Dati numele medicamentului");
              	 scanner = new Scanner(System.in);
              	 nume_medicament = scanner.nextLine();
              	 System.out.println("Dati numele orasului");
            	 scanner = new Scanner(System.in);
            	 nume_oras = scanner.nextLine();
            	 try {
                     Connection con = DriverManager.getConnection(jdbcURL, username, password);
                     if (con!=null) {
         				System.out.println("Conectat la baza de date");
         			}
                    
                    PreparedStatement s=con.prepareStatement("SELECT SUM(stoc.medicamente_ramase) FROM stoc"
                    + " INNER JOIN `farmacie` ON `farmacie`.`id_farmacie` = `stoc`.`id_farmacie`"		
            		+ " INNER JOIN `medicament` ON `medicament`.`id_medicament` = `stoc`.`id_medicament`"
               	    + " WHERE  medicament.nume=? AND farmacie.oras=?");
                    s.setString(1, nume_medicament);   
                    s.setString(2, nume_oras);   
                    		
                    ResultSet result = s.executeQuery();
                    //System.out.println(result.getString(2));
         			while (result.next()) {
         				String u=result.getString(1);
         				System.out.println(u);
         			}
                    con.close();
                 } catch (Exception exception) {
                     exception.printStackTrace();
                 }
              	break;
             } // end of switch
         } while (!choice.equals("0")); // end of loop
		
	    }
}
