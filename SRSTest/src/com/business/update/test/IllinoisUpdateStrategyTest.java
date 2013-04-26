package com.business.update.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.bean.School;
import com.business.update.IllinoisUpdateStrategy;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

public class IllinoisUpdateStrategyTest {
	@Test
	public void testGetData() throws IOException {
		Iterator<School> iter = new IllinoisUpdateStrategy().getData();
		assertNotNull("Should have returned an iterator", iter);
		
		int count = 0;
		while (iter.hasNext() && count < 100000) {
			School s = iter.next();
			assertNotNull("Should never return null", s);
			count++;
		}
		assertTrue("Too few schools returned", count > 100);
		assertTrue("Too many schools returned", count < 100000);
	}
	
	@Test
	public void testIteratorRemove() throws IOException {
		Iterator<School> iter = new IllinoisUpdateStrategy().getData();
		assertNotNull("Should have returned an iterator", iter);
		
		try {
			iter.remove();
			fail("Should have thrown an exception");
		} catch (UnsupportedOperationException e) {
			// expected
		} catch (IllegalStateException e) {
			// also valid
		}
		
		assertTrue("Too few schools returned", iter.hasNext());
		iter.next();
		
		try {
			iter.remove();
			fail("Should have thrown an exception");
		} catch (UnsupportedOperationException e) {
			// expected
		} // IllegalStateException is no longer valid
	}
	
	// fails correctly
	@Test
	public void testIteratorTooManyNextCalls() throws IOException {
		Iterator<School> iter = new IllinoisUpdateStrategy().getData();
		assertNotNull("Should have returned an iterator", iter);
		
		while (iter.hasNext()) {
			iter.next();
		}
		
		try {
			iter.next();
			fail("Should have thrown an exception");
		} catch (NoSuchElementException e) {
			// expected
		}
	}
}
