package com.controller.test;

import static org.junit.Assert.assertNotNull;
import com.business.ReportModel;
import com.controller.SchoolController;
import com.gui.ReportGUI;
import org.junit.Test;


public class SchoolControllerTest {

	private ReportModel theModel=new ReportModel();
	private ReportGUI theView=new ReportGUI();
	
	
	@Test
	public void testSchoolController() {
		SchoolController testSchool=new SchoolController(theView, theModel);
		
		//Testing if the constructor properly intializes the object and not returns null
		assertNotNull("Should have returned an object", testSchool);
		
	}

}
