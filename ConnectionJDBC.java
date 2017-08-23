package fr.codevallee.formation.tp1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionJDBC {
	
	public static Map <String, Object> seconnecter () throws SQLException {
		
		// Step 1: Allocate a database 'Connection' object
       try( Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monresto?useSSL=false", "root", "root");
         // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
      
        // Step 2: Allocate a 'Statement' object in the Connection
        Statement stmt = conn.createStatement();
) {
        // Step 3: Execute a SQL SELECT query, the query result
        //  is returned in a 'ResultSet' object.
        String strSelect = "select nom, total from commande";
        System.out.println("The SQL query is: " + strSelect); // Echo For debugging
        System.out.println();

        ResultSet rset = stmt.executeQuery(strSelect);

        // Step 4: Process the ResultSet by scrolling the cursor forward via next().
        //  For each row, retrieve the contents of the cells with getXxx(columnName).
        
        
        
        System.out.println("The records selected are:");
        int rowCount = 0;
        Map <String, Object> mymap = new HashMap <>();
       
        List<String> listNom = new ArrayList<String>();
        
        while(rset.next()) {   // Move the cursor to the next row, return false if no more row
           String nom = rset.getString("nom");
           double total = rset.getDouble("total");
          // int    qty   = rset.getInt("qty");
           System.out.println(nom + ", " + total);
           ++rowCount;
           
           listNom.add(nom);
           
           
        }

        mymap.put("noms", listNom);

        System.out.println("Total number of records = " + rowCount);
		return mymap;
	}
       catch(SQLException es) {
    	   
    	   es.printStackTrace();
    	   
       }
	return null;
	}
	
}
