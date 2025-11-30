package lhdiftests;

import static org.junit.Assert.*;

import javax.xml.transform.Result;

import org.junit.Test;

public class Testinglhdiff {


@Test
public void testIdenticalFiles() {
String[]left= {"A","B","C"};
String[]right= {"A","B","C"};
	
	//TODO:replace with the call of actual LHDIFF
//LHDiffResult result = LHDiff.compute(left, right);
	
//Expected Mapping
assertEquals(1, result.getMapping(1));
assertEquals(2, result.getMapping (2));
assertEquals(3, result.getMapping (3));
//waiting for the algorithim to be ready
}
	
@Test
public void testEmptyFiles() {
	String[] left= {};
	String[] right= {};
	
	
	//TODO:replace with the call of actual LHDIFF
	//LHDiffResult result = LHDiff.compute(left, right);
		
	//Expected no Mapping, both files are empty
	assertTrue(result.isempty());
}

//Test what happens when a single line is added at the END of the new file.
@Test
public void testSingleLineAdditionAtEnd() {
	String[]left= {
			"A",
			"B"
	};
	String[] right = {
			"A",
            "B",
            "C" //New line added at the end
};
	
	// TODO: replace with the call of actual LHDIFF when ready
    // LHDiffResult result = LHDiff.compute(left, right);

	
	//Expected mapping
	//assertEquals(3, result.getMapping (3));
	assertEquals(1,result.getMapping(1));
	assertEquals(2,result.getMapping(2));
	
	//new line added that should not map to any new line
	assertTrue(result.isAddedLine(3));
	
}
	
	
@Test
public void testSingleLineAdditionAtBeginning() {
	
		String[]left= {
				"A",
				"B"
		};
		String[] right = {
				"Z",
				"A",
	            "B",
	            
	};
		
		// TODO: replace with the call of actual LHDIFF when ready
	    // LHDiffResult result = LHDiff.compute(left, right);
		
		
		//Expected mapping
		assertEquals(2, result.getMapping (1));
		
		assertEquals(3,result.getMapping(2));
		
		//new line added that should not map to any new line
		assertTrue(result.isAddedLine(1));
		
	}
	
	
	

	
@Test
public void testSingleLineAdditionInMiddle() {

String[]left= {
		"A",
		"B"
};
String[] right = {
		
		"A",
		"Z",
        "B",
        
};

// TODO: replace with the call of actual LHDIFF when ready
// LHDiffResult result = LHDiff.compute(left, right);


//Expected mapping
assertEquals(1, result.getMapping (1));

assertEquals(3,result.getMapping(2));

//new line added that should not map to any new line
assertTrue(result.isAddedLine(2));

}


	//====Single line Deletion=======//
	
@Test
public void testSingleLineDeletionFromEnd() {
	
String[]left= {
		"A",
		"B",
		"C",
};
String[] right = {
		
		"A",
        "B",
        
};

// TODO: replace with the call of actual LHDIFF when ready
// LHDiffResult result = LHDiff.compute(left, right);


//Expected mapping
assertEquals(1, result.getMapping (1));

assertEquals(2,result.getMapping(2));

//Deleted line should not map to any new line
assertTrue(result.isDeletedLine(3));


// Expected mapping:
// Old 1 → New 1
// Old 2 → New 2
// Old 3 → deleted

}



@Test
public void testSingleLineDeletionFromBeginning() {
	String[]left= {
			"A",
			"B",
			"C",
	};
	String[] right = {
			
			
	        "B",
	        "C",
	};

	// TODO: replace with the call of actual LHDIFF when ready
	// LHDiffResult result = LHDiff.compute(left, right);


	//Expected mapping
	//Deleted line should not map to any new line
		assertTrue(result.isDeletedLine(1));
	assertEquals(1, result.getMapping (2));
    assertEquals(2,result.getMapping(3));

	

}
	
	
@Test
public void testSingleLineDeletionFromMiddle() {
	String[]left= {
			"A",
			"B",
			"C",
	};
	String[] right = {
			"A",
	        "C",
	};

	// TODO: replace with the call of actual LHDIFF when ready
	// LHDiffResult result = LHDiff.compute(left, right);


	//Expected mapping
	//Deleted line should not map to any new line
		assertTrue(result.isDeletedLine(2));
	assertEquals(1, result.getMapping (1));
    assertEquals(2,result.getMapping(3));

	
	
	
	
	
}
	
	
@Test
public void testSingleLineModification() {
	String[]left= {
			"A",
			"B",
			"C",
	};
	String[] right = {
			"A",
			"BQ",
			"C",
	};

	// TODO: replace with the call of actual LHDIFF when ready
	// LHDiffResult result = LHDiff.compute(left, right);


	//Expected mapping
	//Deleted line should not map to any new line

	assertEquals(1, result.getMapping (1));
    assertEquals(2,result.getMapping(2));
    assertEquals(3,result.getMapping(3));
	
	
}
	
	
	
	
	
	
	
@Test
public void testMultipleAdditions() {
	String[]left= {
			"A",
			"B",
			"C",
	};
	String[] right = {
			"A",
			"B",
			"C",
			"F",
			"H",
			"G",
			
	};

	// TODO: replace with the call of actual LHDIFF when ready
	// LHDiffResult result = LHDiff.compute(left, right);


	//Expected mapping


	assertEquals(1, result.getMapping (1));
    assertEquals(2,result.getMapping(2));
    assertEquals(3,result.getMapping(3));
	assertTrue(result.isAddedLine(4));
	assertTrue(result.isAddedLine(5));
	assertTrue(result.isAddedLine(6));
}
	
	
@Test
public void testMultipleDeletions() {
	

    String[] left = {
        "A",
        "B",
        "C",
        "D"
    };

    String[] right = {
        "A",
        "D"
    };
	

    // TODO: replace with the actual LHDIFF call when ready
    // LHDiffResult result = LHDiff.compute(left, right);
	
	
    assertEquals(1, result.getMapping(1));  // A stays at 1
    assertTrue(result.isDeletedLine(2));    // B deleted
    assertTrue(result.isDeletedLine(3));    // C deleted
    assertEquals(2, result.getMapping(4));  // D moves up to new line 2

	
}
	
	
}
