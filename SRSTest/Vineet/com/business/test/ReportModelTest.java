package com.business.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bean.School;
import com.business.ReportModel;
import com.business.update.IllinoisUpdateStrategy;
import com.database.DatabaseConnection;

public class ReportModelTest {
	
	ReportModel testModel;
	@BeforeClass
	public void setup() {
		testModel = new ReportModel();
	}
	
	@Test
	public void testsetDBConn() {
		assertNotNull("check connection",testModel.dbconnect);
	}
	
	@Test
	public void testgetSchoolList() {
		testModel.getNewData();
		Map<String, String> testMap = testModel.getSchoolList();
		assertFalse("check if school list returns",testMap.isEmpty());
	}
	
	@Test
	public void testgetParameterList() {
		testModel.getNewData();
		Map<String, String> testMap = testModel.getParameterList();
		assertFalse("check if parameter list returns",testMap.isEmpty());
	}

	@Test
	public void testgetNewData() {
		testModel.getNewData();
		
		String QueryString = "SELECT count(id) from School";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);

			rs.next();
			boolean check = (rs.getInt(1) > 0);
			assertTrue("school count is greater than zero",check );
		} catch (Exception e) {
			fail("exception occured");
		}
	}
	
	@Test
	public void testclearData() {
		testModel.clearExistingData();
		
		String QueryString = "SELECT count(id) from School";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);

			rs.next();
			boolean check = (rs.getInt(1) == 0);
			assertTrue("school count is greater than zero",check );
		} catch (Exception e) {
			fail("exception occured");
		}
	}
	
	@AfterClass
	public void tearDown() {
		testModel.clearExistingData();
		testModel.getNewData();
	}
	
}
