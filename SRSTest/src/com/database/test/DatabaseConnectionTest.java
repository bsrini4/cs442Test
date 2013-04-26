package com.database.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import com.database.DatabaseConnection;

import org.junit.Test;

public class DatabaseConnectionTest {
         
	
	public Connection init() throws Exception
	{
		return DatabaseConnection.getConnection();
	}
	
	@Test
	public void TestConnectionIsNotNull() throws Exception {
		
		Connection conn =init();		
		//Checking if connection object is not null
		assertNotNull("check connection object is not null",conn);
	}
	
	@Test
	public void TestConnectionIsClosed() throws Exception {
		
		Connection conn =init();		
		//checking if connection is closed properly inside the close connection method
       DatabaseConnection.closeConnection(conn);        
       assertEquals("check DB connection is closed",true,conn.isClosed());
             
	}
   
	@Test
	public void TestConnectionIsOpen() throws Exception {
		
		Connection conn =init();		
		//checking if connection is opened properly
       assertEquals("check DB connection is open",false,conn.isClosed());       
	}
	
	@Test
	public void TextClosingAlreadyClosedConnection() throws Exception {
		
		Connection conn =init();		
		//checking if connection is closed properly inside the close connection method
       DatabaseConnection.closeConnection(conn);        
       //making conn.isclosed() return true -> false in condition
       DatabaseConnection.closeConnection(conn);        
       assertEquals("check DB connection is closed",true,(conn.isClosed()));       
	}
	
	@Test
	public void TestClosingNullConnection() throws Exception {
		Connection conn = null;
		DatabaseConnection.closeConnection(conn);
		assertNull("Try closing a null connection", conn);
		
	}


	
	
    
}