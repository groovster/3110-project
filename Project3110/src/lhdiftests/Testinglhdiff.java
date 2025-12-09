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
@Test
public void testWhitespaceOnlyModification() {
    String[] left = {
        "int x = 10;",
        "return x;"
    };

    String[] right = {
        "int  x  =  10;", // more spaces
        "return x;"       // identical
    };

    LHDiffResult result = LHDiff.compute(left, right);

    assertTrue(result.isModifiedLine(1));
    assertEquals(2, result.getMapping(2));
}
@Test
public void testCaseInsensitiveModification() {
    String[] left = {
        "Print(\"Hello\");"
    };

    String[] right = {
        "print(\"Hello\");"  // only case changed
    };

    LHDiffResult result = LHDiff.compute(left, right);

    assertTrue(result.isModifiedLine(1));
    assertEquals(1, result.getMapping(1));
}
@Test
public void testComplexReordering() {
    String[] left = {
        "A",
        "B",
        "C",
        "D",
        "E"
    };

    String[] right = {
        "C",
        "A",
        "E",
        "B",
        "D"
    };

    LHDiffResult result = LHDiff.compute(left, right);

    assertEquals(2, result.getMapping(1)); // A → 2
    assertEquals(4, result.getMapping(2)); // B → 4
    assertEquals(1, result.getMapping(3)); // C → 1
    assertEquals(5, result.getMapping(4)); // D → 5
    assertEquals(3, result.getMapping(5)); // E → 3
}
@Test
public void testAmbiguousSimilarityMatching() {
    String[] left = {
        "value = 100;",
        "value = 200;"
    };

    String[] right = {
        "value = 101;",  // closer to old line 1
        "value = 201;"   // closer to old line 2
    };

    LHDiffResult result = LHDiff.compute(left, right);

    assertEquals(1, result.getMapping(1)); 
    assertEquals(2, result.getMapping(2));

    assertTrue(result.isModifiedLine(1));
    assertTrue(result.isModifiedLine(2));
}
@Test
public void testLargeMixedEdits() {
    String[] left = {
        "A","B","C","D","E","F"
    };

    String[] right = {
        "X1","X2",   // inserted
        "C",         // moved from 3 → 3
        "A",         // moved 1 → 4
        "E",         // moved 5 → 5
        "Y1","Y2"    // inserted 
    };

    LHDiffResult result = LHDiff.compute(left, right);

    assertTrue(result.isAddedLine(1));
    assertTrue(result.isAddedLine(2));

    assertEquals(3, result.getMapping(3)); // C
    assertEquals(4, result.getMapping(1)); // A
    assertEquals(5, result.getMapping(5)); // E

    assertTrue(result.isDeletedLine(2)); // B
    assertTrue(result.isDeletedLine(4)); // D
    assertTrue(result.isDeletedLine(6)); // F

    assertTrue(result.isAddedLine(6)); // Y1
    assertTrue(result.isAddedLine(7)); // Y2
}


@Test
public void testRepeatedBlocks() {
    String[] left = {
        "A","B","A","B","A","B"
    };

    String[] right = {
        "A","B","A","B","A","B"  // identical but ambiguous repeated segments
    };

    LHDiffResult result = LHDiff.compute(left, right);

    for (int i = 1; i <= 6; i++) {
        assertEquals(i, result.getMapping(i));}
    }

@Test
public void testInsertBetweenRepeatedBlocks() {
    String[] left = {"A","B","A","B"};
    String[] right = {"A","B","X","A","B"};

    LHDiffResult result = LHDiff.compute(left, right);

    assertEquals(1, result.getMapping(1));
    assertEquals(2, result.getMapping(2));
    assertTrue(result.isAddedLine(3));
    assertEquals(4, result.getMapping(3));
    assertEquals(5, result.getMapping(4));
}
@Test
public void testSmallWidespreadModifications() {
    String[] left = {
        "A1","A2","A3","A4","A5"
    };
    String[] right = {
        "A1!","A2!","A3!","A4!","A5!"
    };

    LHDiffResult result = LHDiff.compute(left, right);

    for (int i = 1; i <= 5; i++) {
        assertEquals(i, result.getMapping(i));
        assertTrue(result.isModifiedLine(i));
    }
}
@Test
public void testWorstCasePattern() {
    String[] left = { "A","B","A","B","A","B" };
    String[] right = { "B","A","B","A","B","A" };

    LHDiffResult result = LHDiff.compute(left, right);

    assertEquals(2, result.getMapping(1));
    assertEquals(1, result.getMapping(2));
}
@Test
public void testSingleLineFiles() {
    String[] left = {"single line"};
    String[] right = {"single line"};
    
    LHDiffResult result = LHDiff.compute(left, right);
    
    assertEquals(1, result.getMapping(1));
    assertFalse(result.isModifiedLine(1));
    assertFalse(result.isDeletedLine(1));
    assertFalse(result.isAddedLine(1));
}

@Test
public void testOneLineLeftMultipleRight() {
    String[] left = {"start"};
    String[] right = {"start", "middle", "end"};
    
    LHDiffResult result = LHDiff.compute(left, right);
    
    assertEquals(1, result.getMapping(1));
    assertTrue(result.isAddedLine(2));
    assertTrue(result.isAddedLine(3));
}

@Test
public void testMultipleLeftOneRight() {
    String[] left = {"first", "second", "third"};
    String[] right = {"second"};
    
    LHDiffResult result = LHDiff.compute(left, right);
    
    assertTrue(result.isDeletedLine(1));
    assertEquals(1, result.getMapping(2));
    assertTrue(result.isDeletedLine(3));
}

@Test
public void testAllLinesChanged() {
    String[] left = {"a", "b", "c"};
    String[] right = {"x", "y", "z"};
    
    LHDiffResult result = LHDiff.compute(left, right);
    
    // All lines should be marked as modified or deleted/added
    assertNotNull(result);
    // Algorithm might try to match similar lines
}

@Test
public void testExtraSpacesAndTabs() {
    String[] left = {"int x=5;"};
    String[] right = {"int   x   =   5   ;"};  // Extra spaces
    
    LHDiffResult result = LHDiff.compute(left, right);
    
    assertEquals(1, result.getMapping(1));
    assertTrue(result.isModifiedLine(1));
}

@Test
public void testContextSensitivity() {
    // Test if algorithm uses context properly
    String[] left = {
        "// Method A",
        "void methodA() {",
        "    int x = 1;",
        "    int y = 2;",
        "    int z = x + y;",
        "}",
        "",
        "// Method B",
        "void methodB() {",
        "    int a = 10;",
        "    int b = 20;",
        "    int c = a + b;",  // Same calculation as in methodA
        "}"
    };
    
    String[] right = {
        "// Method A",
        "void methodA() {",
        "    int x = 100;",  // Changed value
        "    int y = 200;",  // Changed value
        "    int z = x + y;",
        "}",
        "",
        "// Method B",
        "void methodB() {",
        "    int a = 10;",
        "    int b = 20;",
        "    int c = a + b;",  // Same as before
        "}"
    };
    
    LHDiffResult result = LHDiff.compute(left, right);
    
    // Line 3 (x=1) should map to line 3 (x=100) in methodA, NOT to line 10 (a=10)
    assertEquals(3, result.getMapping(3));
    assertEquals(10, result.getMapping(10));
}


}