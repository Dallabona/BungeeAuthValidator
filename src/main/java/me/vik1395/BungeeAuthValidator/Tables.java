package me.vik1395.BungeeAuthValidator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables 
{
	public boolean checkPlayerEntry(String playername)
	{
		boolean check = false;
		MySQL MySQL = new MySQL(Main.host, Main.port, Main.dbName, Main.username, Main.pass);
		Connection c = null;
		c = MySQL.openConnection();
		Statement statement;
		try 
		{
			statement = c.createStatement();
		
			ResultSet pcheck = statement.executeQuery("SELECT * FROM BungeeAuth WHERE playername = '" + playername.toLowerCase() + "';");
			if(!pcheck.next())
			{
				check=false;
			}
			else
			{
				check=true;
			}
			
			statement.close();
			c.close();
		
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
			System.err.println("[BungeeAuth] There is a problem with the connection to the MySQL Database!");
		}
		
		MySQL.closeConnection();
		
		return check;
	}
	
	public String getStatus(String playername)
	{
		boolean check = false;
		String status = "";
		MySQL MySQL = new MySQL(Main.host, Main.port, Main.dbName, Main.username, Main.pass);
		Connection c = null;
		c = MySQL.openConnection();
		try
		{
			Statement statement = c.createStatement();
			ResultSet pcheck = statement.executeQuery("SELECT * FROM BungeeAuth WHERE playername = '" + playername.toLowerCase() + "';");
			if(!pcheck.next())
			{
				check=false;
			}
			else
			{
				status = pcheck.getString("status");
				check=true;
			}
			if(!check)
			{
				status = "";
			}
			
			statement.close();
			c.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		MySQL.closeConnection();
		return status;
	}
}
