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
LHDiffResult result = LHDiff.compute(left, right);
	
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
	LHDiffResult result = LHDiff.compute(left, right);
		
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
    LHDiffResult result = LHDiff.compute(left, right);

	
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
	     LHDiffResult result = LHDiff.compute(left, right);
		
		
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
 LHDiffResult result = LHDiff.compute(left, right);


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
LHDiffResult result = LHDiff.compute(left, right);


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
	LHDiffResult result = LHDiff.compute(left, right);


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
	 LHDiffResult result = LHDiff.compute(left, right);


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
	LHDiffResult result = LHDiff.compute(left, right);


	//Expected mapping
	//Deleted line should not map to any new line

	assertEquals(1, result.getMapping (1));
    assertEquals(2,result.getMapping(2));
    assertEquals(3,result.getMapping(3));
	
	
}
	

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
	 LHDiffResult result = LHDiff.compute(left, right);


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
     LHDiffResult result = LHDiff.compute(left, right);
	
	
    assertEquals(1, result.getMapping(1));  // A stays at 1
    assertTrue(result.isDeletedLine(2));    // B deleted
    assertTrue(result.isDeletedLine(3));    // C deleted
    assertEquals(2, result.getMapping(4));  // D moves up to new line 2

	
}
	


//Test Line reorder
@Test
public void testLineReordering() {
    String[] left = {
        "A",
        "B",
        "C"
    };

    String[] right = {
        "C",
        "A",
        "B"
    };

    // TODO: enable when LHDIFF is ready
     LHDiffResult result = LHDiff.compute(left, right);

    assertEquals(2, result.getMapping(1));
     assertEquals(3, result.getMapping(2));
     assertEquals(1, result.getMapping(3));
}


//Test Line Splitting
@Test 
public void testLineSplitting() {
	String[]left= {
			"return x + y+z;"
	};
	
	String[]right= {
			"return x +",
			"      y+",
			"     z;"
	};
	// TODO: enable when LHDIFF is ready
     LHDiffResult result = LHDiff.compute(left, right);
	  
	int[] mapping = result.getSplitMapping(1); // example API
    assertArrayEquals(new int[]{1, 2, 3}, mapping);

}

@Test
public void testLineMerge() {
    String[] left = {
        "x =",
        "y +",
        "z;"
    };

    String[] right = {
        "x = y + z;"
    };

    // TODO: enable when LHDIFF is ready
     LHDiffResult result = LHDiff.compute(left, right);

    // assertEquals(1, result.getMapping(1));
    // assertEquals(1, result.getMapping(2));
    // assertEquals(1, result.getMapping(3));
}

@Test
public void testMultipleModifications() {
    String[] left = {
        "int a = 1;",
        "int b = 2;",
        "return a + b;"
    };

    String[] right = {
        "int a = 10;",
        "int b = 20;",
        "return a + b + 5;"
    };

  LHDiffResult result = LHDiff.compute(left, right);

    // TODO: enable when ready
    assertEquals(1, result.getMapping(1));
    assertEquals(2, result.getMapping(2));
    assertEquals(3, result.getMapping(3));

    assertTrue(result.isModifiedLine(1));
    assertTrue(result.isModifiedLine(2));
    assertTrue(result.isModifiedLine(3));
}

@Test
public void testMixedChanges() {
    String[] left = {
        "A",
        "B",
        "C",
        "D"
    };

    String[] right = {
        "A",      // unchanged
        "BQ",     // modified
        "E",      // new line added
        "D"       // moved from line 4 to line 4
    };


    //TODO: enable when ready
 LHDiffResult result = LHDiff.compute(left, right);

     assertEquals(1, result.getMapping(1));
     assertEquals(2, result.getMapping(2));
     assertTrue(result.isDeletedLine(3));
     assertEquals(4, result.getMapping(4));
     assertTrue(result.isAddedLine(3));
}
@Test
public void testRepeatedLines() {
	String[] left =
		{
				"A",
				"A",
				"A"
		};
	
	String[] right =
		{
				"A",
				"A",
				"A"
		};
	 // TODO: enable when ready
	LHDiffResult result = LHDiff.compute(left, right);

    assertEquals(1, result.getMapping(1));
     assertEquals(2, result.getMapping(2));
     assertEquals(3, result.getMapping(3));
	
		}
	
//Test Completely differenet files
@Test
public void testCompletelyDifferentFiles() {
    String[] left = {
        "AAA",
        "BBB",
        "CCC"
    };

    String[] right = {
        "XXX",
        "YYY",
        "ZZZ"
    };

    // None of the lines match → all should be deleted, new lines added
  LHDiffResult result = LHDiff.compute(left, right);

    // TODO: enable when ready
     assertTrue(result.isDeletedLine(1));
    assertTrue(result.isDeletedLine(2));
     assertTrue(result.isDeletedLine(3));

     assertTrue(result.isAddedLine(1));
    assertTrue(result.isAddedLine(2));
    assertTrue(result.isAddedLine(3));
}

@Test
public void testSimilarButNotIdenticalLines() {
    String[] left = {
        "int count = 10;",
        "int total = count + 5;"
    };

    String[] right = {
        "int count = 100;",      // similar but not identical → modified
        "int total = count + 5;" // identical
    };

    // TODO: enable when LHDIFF is ready
    LHDiffResult result = LHDiff.compute(left, right);

    // Expected:
    // Line 1 is modified
    // Line 2 maps correctly

    assertTrue(result.isModifiedLine(1));
     assertEquals(2, result.getMapping(2));
}
@Test
public void testLargeBlockInsertion() {
    String[] left = {
        "A",
        "B",
        "C",
        "D"
    };

    String[] right = {
        "A",
        "B",
        "X1",
        "X2",
        "X3",
        "C",
        "D"
    };

    // TODO: enable when LHDIFF is ready
     LHDiffResult result = LHDiff.compute(left, right);

    // Expected:
    // Old 1 → 1
    // Old 2 → 2
    // X1,X2,X3 are added
    // Old 3 (C) → 6
    // Old 4 (D) → 7

    assertEquals(1, result.getMapping(1));
    assertEquals(2, result.getMapping(2));
    assertTrue(result.isAddedLine(3));
    assertTrue(result.isAddedLine(4));
    assertTrue(result.isAddedLine(5));
    assertEquals(6, result.getMapping(3));
    assertEquals(7, result.getMapping(4));
}


}
