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
	
	//Temp Result
	//LHDiffResult result= new LHDiffResult();
	result.addMapping(1,1);
	result.addMapping(2,2);
	
	//Expected mapping
	//assertEquals(3, result.getMapping (3));
	assertEquals(1,result.getMapping(1));
	assertEquals(2,result.getMapping(2));
	
	//new line added that should not map to any new line
	assertTrue(result.isAddedLine(3));
	
}
	
	
//@Test
//public void testSingleLineAdditionAtBeginning() 
	
	
//@Test
//public void testSingleLineAdditionInMiddle()
	
	
//@Test
//public void testSingleLineDeletionFromEnd()
	
	
//@Test
//public void testSingleLineDeletionFromBeginning()
	
	
//@Test
//public void testSingleLineDeletionFromMiddle()
	
	
//@Test
//public void testSingleLineModification()
	
	
//@Test
//public void testMultipleAdditions()
	
	
//@Test
//public void testMultipleDeletions()
	
	
}