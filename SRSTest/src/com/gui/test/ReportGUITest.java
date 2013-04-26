package com.gui.test;
//application classes
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.gui.ReportGUI;
import com.business.ReportModel;
import com.controller.*;

//testing classes
import static org.junit.Assert.*;
import org.junit.*;



/**
* @author Ivan De Los Santos
* 
* The testing here will be structural testing with Modified Condition/Decision Coverage (MC/DC)
* Every branch will be tested.  Only conditions which effect the expression independently will also be tested.
* 
*
*/
public class ReportGUITest {
	ReportGUI theView;
	ReportModel theModel;
	SchoolController theController;
	
	public ReportGUITest(){
		theView = new ReportGUI();
		theModel= new ReportModel();
	}
	
	//NOTES ON TESTING
	/*
		1) There is no way to call initComponents() without going through the School Controller constructor with the current code
		
	*/
	
	
	///////Testing initComponents()//////////////
	@Test
	//tests initComponents, where the report list and school name list sizes are greater than zero.
	public void testInitComponentsListsGreaterThanZero() throws Exception{
		//test case of reportParametersList.size() = 1+, along with schoolNameValuesList.size()= 1+
		theController= new SchoolController(theView,theModel); //the standard case
	}
	
	@Test
	//tests initComponents, covering the case when the report list's size is zero
	public void testInitSchoolListIsZero() throws Exception{
		//When school list is size 0, the test should pass.  It is an acceptable value when the table is not populated.
		 theModel.clearExistingData(); //satisfies size of 0
		 theController= new SchoolController(theView,theModel);
		 
		 theModel.getNewData();  //reset back to normal for other tests
		 theController= new SchoolController(theView,theModel);
	}
	
	
	//Other GUI elements here.
		
	
	@After
	public void resetAfterEveryTest(){
		theView.dispose();  //gui resources returned to OS
		theView = new ReportGUI();
	}
	
}

