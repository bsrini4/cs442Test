package com.business.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bean.School;
import com.business.ReportModel;
import com.business.update.IllinoisUpdateStrategy;
import com.database.DatabaseConnection;

public class ReportModelTest {
	@After
	public void clearDatabase() throws Exception {
		String QueryString = "truncate table CS442.dbo.School";
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			statement.execute(QueryString);
		}  finally {
		}
	}
	@Before
	public void populateDB() {
		testGetNewDataWhenValid();
	}

	@Test
	public void testSetDBConn() {
		// setup
		ReportModel reportModel = new ReportModel();
		reportModel.setDBConn();

		// test
		assertNotNull("Assert connection is not null", reportModel.dbconnect);

		// teardown
		reportModel = null;
	}

	@Test
	public void testGetSchoolListWhenSchoolListIsEmpty() throws SQLException {
		// setup
		String QueryString = "SELECT id,name,district,county,schoolType from School where county=\'test\'order by name";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);

			// test
			assertFalse("no rows should be returned", rs.next());

		
		} catch (Exception e) {
			fail("exception");
		} finally {
			// teardown
			rs.close();
		}
	}

	@Test
	public void testGetSchoolListWhenTableDoesNotExist() throws SQLException {

		String QueryString = "SELECT id,name,district,county,schoolType from School1 order by name";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);
			
			
		} catch (Exception e) {
			assertNull("no rows should be returned", rs);
		} finally {
			
		}
	}

	@Test
	public void testGetSchoolListWhenValid() throws SQLException {

		String QueryString = "SELECT id,name,district,county,schoolType from School order by name";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);

			assertNotNull("rows fetched", rs.next());
			
		} catch (Exception e) {
			fail("no rows fetched");
		} finally {
			// teardown
			rs.close();
		}
	}

	@Test
	public void testGetSchoolListWhenValidCloseResultSet() {

		String QueryString = "SELECT id,name,district,county,schoolType from School order by name";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);

			while (rs.next()) {
			}
			assertTrue("rows fetched", rs.isClosed());
		} catch (Exception e) {
			fail("no rows fetched");
		}
	}

	@Test
	public void testClearExistingDataWhenTableDoesNotExist() {
		String QueryString = "truncate table CS442.dbo.School1";
		boolean output = false;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			output = statement.execute(QueryString);
		} catch (SQLException e) {
			assertFalse("sql exception thrown for invalid table", output);
		} catch (Exception e) {
			fail("some other error");
		}
	}

	@Test
	public void testClearExistingDataWhenValid() throws SQLException {
		populateDB();
		String QueryString = "truncate table CS442.dbo.School";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			statement.execute(QueryString);
			
			rs = statement.executeQuery("SELECT count(id) from School");

			rs.next();
			assertEquals(0, rs.getInt(1));
			
		} catch (Exception e) {
			fail("some other error");
		} finally {
			// teardown
			rs.close();
		}
	}

	// to be run after testClearExistingDataWhenValid
	@Test
	public void testClearExistingDataWhenTableIsEmpty() {
		try {
			clearDatabase();
		} catch (Exception e1) {
			fail("exception");
		}
		String QueryString = "truncate table CS442.dbo.School";
		boolean output=true;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			output = statement.execute(QueryString);
			assertFalse("table is truncated", output);
		} catch (Exception e) {
			fail("some other error");
		}
	}

	// to be run after testClearExistingDataWhenTableIsEmpty
	@SuppressWarnings("null")
	@Test
	public void testGetNewDataWhenTheUpdateStrategyReturnsNull() {
		// setup
		// simulating events where IllinoisUpdateStrategy returns null for
		// getData
		Iterator<School> temp = null;
		try {
			temp.next();
		} catch (NullPointerException e) {
			// test
			assertNull("The school iterator is null", temp);
		} catch (Exception e) {
			fail("some other exception");
		}
	}

	@Test
	public void testGetNewDataWhenTheIteratorIsEmpty() {
		// setup
		// simulating events where IllinoisUpdateStrategy returns an empty
		// Iterator for getData
		IllinoisUpdateStrategy st = new IllinoisUpdateStrategy();
		try {
			Iterator<School> temp = st.getData();
			while (temp.hasNext()) {
				temp.next(); // by running through the elements we simulate an
								// empty iterator
			}
			assertFalse("The school iterator is empty", temp.hasNext());
		} catch (Exception e) {
			fail("some other exception");
		}
	}

	@Test
	public void testGetNewDataWhenSaveToDBDoesNotWork() {
		// setup
		IllinoisUpdateStrategy st = new IllinoisUpdateStrategy();
		try {
			Iterator<School> temp = st.getData();
			while (temp.hasNext()) {
				temp.next();
				// simulate this by not calling save function for the school
			}
		} catch (IOException e) {
			fail("exception occured");
		}

		// test
		String QueryString = "SELECT count(id) from School";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);

			rs.next();
			assertEquals(0, rs.getInt(1));
		} catch (Exception e) {
			fail("exception occured");
		}
	}

	@Test
	public void testGetNewDataWhenValid() {
		try {
			clearDatabase();
		} catch (Exception e1) {
			fail("exception");
		}
		IllinoisUpdateStrategy st = new IllinoisUpdateStrategy();
		try {
			Iterator<School> temp = st.getData();
			while (temp.hasNext()) {
				School S = temp.next();
				try {
					S.save(DatabaseConnection.getConnection());
				} catch (Exception e) {

					fail("exception occured");
				}
			}
		} catch (IOException e) {
			fail("exception occured");
		}

		// test
		String QueryString = "SELECT count(id) from School";
		ResultSet rs = null;
		try {
			Statement statement = DatabaseConnection.getConnection()
					.createStatement();
			rs = statement.executeQuery(QueryString);
			rs.next();
			boolean output = (rs.getInt(1) > 0);

			assertTrue("rows inserted in database", output);
		} catch (Exception e) {
			fail("exception occured");
		}
	}
	
	

}
