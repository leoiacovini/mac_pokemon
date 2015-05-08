package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBReader {

	static Pokemon[] poks = new Pokemon[151];
	
	public static void queryPokemons() {
		
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:pokedex.sqlite");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    try {
	    	
	    	System.out.println("Opened database successfully");
	    	int i = 0;
	    	Statement stmt = c.createStatement();
	        ResultSet rs = stmt.executeQuery( "SELECT * FROM pokemon;" );
	        while ( rs.next() && i <= 150 ) {
	           int id = rs.getInt("id");
	           String  name = rs.getString("identifier");
	           
	           // Query for the pokemon type
	           System.out.println("Pokemon Name: " + name);
	           Statement q = c.createStatement();
	           ResultSet hb = q.executeQuery("SELECT type_id FROM pokemon_types WHERE pokemon_id = " + id + " ORDER BY ROWID ASC LIMIT 1");
	           int typeID = hb.getInt("type_id");
	           
	           Statement t = c.createStatement();
	           ResultSet tp = t.executeQuery("SELECT name FROM type_names WHERE type_id = " + typeID + " AND local_language_id = 9");
	           String typeName = tp.getString("name");
	           
	           Tipos pkmType = Helpers.typeFromString(typeName);
	           
	           
	           // Query for the moves
	           hb = q.executeQuery("SELECT move_id FROM pokemon_moves WHERE pokemon_id = " + id + " AND version_group_id = 1");
	           int j = 0;
	           Ataque atks[] = new Ataque[4];
	           int lastMoveID = 0; 
	           while(hb.next() && j <= 3) {
	        	   int moveID = hb.getInt("move_id");
	        	   tp = t.executeQuery("SELECT * FROM moves WHERE id = " + moveID);
	        	   String atkName = tp.getString("identifier");
	        	   int atkPower = tp.getInt("power");
	        	   int atkPriority = tp.getInt("priority");
	        	   
	        	   // Query For Atk Type
	        	   System.out.println("Move name: " + atkName);
	        	   int atkTypeID = tp.getInt("type_id");
	        	   Statement statement = c.createStatement();
		           ResultSet queryAtkResult = statement.executeQuery("SELECT * FROM types WHERE id = " + atkTypeID + " ORDER BY ROWID ASC LIMIT 1");
	        	   
		           Tipos atkType = Helpers.typeFromString(queryAtkResult.getString("identifier"));
		           System.out.println("Move type: " + atkType.toString());
		           atks[j] = new Ataque(atkName, atkType ,atkPower, atkPriority);
	        	   if (moveID != lastMoveID) {
	        		   j++;
	        	   }
	        	   lastMoveID = moveID;
	           }
	           
	           
	           // Query For Pokemon HP
	           
	           Statement hpQuery = c.createStatement();
	           ResultSet hpStat = hpQuery.executeQuery("SELECT * FROM pokemon_stats WHERE pokemon_id = " + id +" AND stat_id = 1 LIMIT 1");  
	           int pokHPMAX = hpStat.getInt("base_stat") * 3;
	           
	           poks[i] = new Pokemon(id, name, pkmType, atks, pokHPMAX);
	           
	           i++;
	           
	           t.close();
	           tp.close();
	           q.close();
	           hb.close();
	           hpQuery.close();
	           hpStat.close();
	        } 
	        
	        rs.close();
	        stmt.close();
	        c.close();
	      } catch ( Exception e ) {
	        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	        System.exit(0);
	      }
	}

}
