package com.utils;

import java.sql.Connection;
import java.sql.Statement;

import com.conn.DBConn;

public class PopulateDB {
	private static DBConn DB = new DBConn();
	private static Connection conn = DB.getConnection();
	
	public static String createTables() {
		
		// client
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS client ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(50))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // publisher
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS publisher ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(50),"
                    + "address VARCHAR(50))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // author
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS author ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "name VARCHAR(50))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // books
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS books ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "title VARCHAR(50),"
                    + "author VARCHAR(50),"
                    + "price DOUBLE,"
                    + "fkpuid INT,"
                    + "fkauid INT,"
                    + "CONSTRAINT FK_puid FOREIGN KEY (fkpuid)"
                    + " REFERENCES publisher(id),"
                    + "CONSTRAINT FK_auid FOREIGN KEY (fkauid)"
                    + " REFERENCES author(id))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // account
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS account ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "email VARCHAR(50),"
                    + "password VARCHAR(50))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // shipping_info
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS shipping_info ("
                    + "shipper VARCHAR(50),"
                    + "address VARCHAR(50))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // billinginfo
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS billing_info ("
                    + "email VARCHAR(50),"
                    + "payment VARCHAR(50))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // order
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
            		"CREATE TABLE IF NOT EXISTS orders ("
                            + "id INT PRIMARY KEY AUTO_INCREMENT,"
                            + "date DATETIME DEFAULT CURRENT_TIMESTAMP,"
                            + "fkclid INT,"
                            + "address VARCHAR(50),"
                            + "payment VARCHAR(50),"
                            + "CONSTRAINT FK_clid FOREIGN KEY (fkclid)"
                            + " REFERENCES client(id))"
                            + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // order itens
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "CREATE TABLE IF NOT EXISTS order_items ("
                    + "fkorid INT," //id da venda
                    + "fkboid INT," //id do livro
                    + "quantity INT,"
                    + "CONSTRAINT FK_orid FOREIGN KEY (fkorid)"
                    + " REFERENCES orders(id),"
                    + "CONSTRAINT FK_boid FOREIGN KEY (fkboid)"
                    + " REFERENCES books(id))"
                    + "ENGINE = InnoDB;");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return "Tabelas criadas!";
	}
	
	public static String insertData() {
		
		// client
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
            		"INSERT INTO client (id, name) VALUES"
            		+ "(1, 'Sigfried Munroe'),"
            		+ "(2, 'Penrod Braunston'),"
            		+ "(3, 'Gwenni Kighly'),"
            		+ "(4, 'Penelopa Laurenson'),"
            		+ "(5, 'Latisha Sharram');");
        } catch (Exception e) {
        	System.out.println("Insert client - Failed");
          e.printStackTrace();
        }
        
        // publisher
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO publisher (id, name, address) VALUES "
                    + "(1, 'Herzog, Halvorson and Hoppe', '094 Hooker Way'),"
                    + "(2, 'Botsford, Kihn and Hauck', '28635 Reinke Drive'),"
                    + "(3, 'Gottlieb-Abernathy, O''Reilly and Von', '50 Corscot Circle'),"
                    + "(4, 'Bergstrom', '3 Duke Place'),"
                    + "(5, 'Frami, Ryan and Koch', '386 Waywood Junction');");
        } catch (Exception e) {
        	System.out.println("Insert publisher - Failed");
          e.printStackTrace();
        }
        
        // author
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO author (id, name) VALUES "
                    + "(1, 'Lancelot Posnette'),"
                    + "(2, 'Aymer Bence'),"
                    + "(3, 'Massimo Sergant'),"
                    + "(4, 'Arabel Gilbard'),"
                    + "(5, 'Olivie Barfford');");
        } catch (Exception e) {
        	System.out.println("Insert author - Failed");
            e.printStackTrace();
        }
        
        // books
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO books (id, title, author, price, fkpuid, fkauid) VALUES "
                    + "(1, 'Small Town Girl', 'Terencio Mathias', 67.22, 4, 2),"
                    + "(2, 'Dementia (a.k.a. Daughter of Horror)', 'Curtis Nansom', 173.53, 5, 4),"
                    + "(3, 'Candyman: Farewell to the Flesh', 'Bab Brewerton', 252.24, 3, 2),"
                    + "(4, 'How to Murder Your Wife', 'Adriane Hentzeler', 161.3, 3, 4),"
                    + "(5, 'Brooklyn Dodgers: The Ghosts of Flatbush', 'Lars Fisher', 39.9, 5, 5);");
        } catch (Exception e) {
        	System.out.println("Insert books - Failed");
            e.printStackTrace();
        }
        
        // account
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO account (id, email, password) VALUES "
                    + "(1, 'aneil0@alexa.com', '7hr5knP3PI0'),"
                    + "(2, 'npipes2@sogou.com', 'cy8aqY121Q'),"
                    + "(3, 'fmill3@columbia.edu', 'SpGbQcwDKg'),"
                    + "(4, 'tgumey4@msu.edu', '7SQe5evU'),"
                    + "(5, 'jguppie5@tripod.com', '	VEC50hJN');");
        } catch (Exception e) {
        	System.out.println("Insert account - Failed");
            e.printStackTrace();
        }
        
        // shipping_info
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO shipping_info (shipper, address) VALUES "
                    + "('CHS Inc', '28 Gateway Plaza'),"
                    + "('Veritiv Corporation', '26220 Haas Court'),"
                    + "('Franklin Universal Trust', '4998 Chinook Way'),"
                    + "('Dividend and Income Fund', '871 Summer Ridge Park'),"
                    + "('Edwards Lifesciences Corporation', '1 Mifflin Circle');");
        } catch (Exception e) {
        	System.out.println("Insert shipping_info - Failed");
            e.printStackTrace();
        }
        
        // billing_info
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(
                    "INSERT INTO billing_info (email, payment) VALUES "
                    + "('soutibridge0@foxnews.com', 'pix'),"
                    + "('tbeneze3@cpanel.net', 'credit card'),"
                    + "('lmackiewicz4@bing.com', 'pix'),"
                    + "('gstrivens5@ft.com', 'credit card'),"
                    + "('sgrazier7@bloglines.com', 'money');");
        } catch (Exception e) {
        	System.out.println("Insert billing_info - Failed");
            e.printStackTrace();
        }
     
        // orders
        try (Statement stmt = conn.createStatement()){
        	stmt.execute(
        			"INSERT INTO orders (date, fkclid, address, payment) VALUES"
        			+ "('2022-02-11 10:58:30', 4, '4426 Weeping Birch Pass', 'credit card'),"
        			+ "('2022-11-20 12:12:12', 1, '126 Clarendon Parkway', 'money'),"
        			+ "('2022-03-07 17:29:01', 2, '588 Westerfield Center', 'ticket'),"
        			+ "('2022-11-29 13:57:03', 2, '40482 Briar Crest Crossing', 'credit card'),"
        			+ "('2022-09-24 09:07:11', 4, '51 Twin Pines Hill', 'pix'),"
        			+ "('2022-02-12 13:06:31', 3, '05581 Dovetail Avenue', 'money'),"
        			+ "('2022-03-07 13:26:28', 5, '1 Cordelia Drive', 'pix'),"
        			+ "('2022-06-14 13:16:04', 5, '9 Mayer Avenue', 'money');");
        	
        }catch(Exception e) {
        	System.out.println("Insert orders - Failed");
        	e.printStackTrace();
        }
        
        // order_items
        try(Statement stmt = conn.createStatement()){
        	stmt.execute(
        			"INSERT INTO order_items (fkorid, fkboid, quantity) VALUES "
        			+ "(2, 5, 7),"
        			+ "(4, 1, 3),"
        			+ "(6, 4, 6),"
        			+ "(7, 5, 3),"
        			+ "(5, 2, 4),"
        			+ "(3, 2, 2),"
        			+ "(1, 1, 8),"
        			+ "(8, 4, 5);");
        	
        }catch(Exception e) {
        	System.out.println("Insert order_items - Failed");
        	e.printStackTrace();
        }
        
        return "Data Insert - Sucessful";
	}
	
	public static String insertBook() {
		 
		
		return "Ajust Books - Sucessful";
	}
}
