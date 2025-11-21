package lhdiftests;

import static org.junit.Assert.*;


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


	
//@Test
//public void testEmptyFiles()
	
	
//@Test
//public void testSingleLineAdditionAtEnd()
	
	
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