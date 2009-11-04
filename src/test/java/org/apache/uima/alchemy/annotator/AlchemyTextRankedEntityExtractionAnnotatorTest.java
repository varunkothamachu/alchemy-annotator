package org.apache.uima.alchemy.annotator;

import static org.junit.Assert.fail;

import org.apache.uima.alchemy.utils.TestUtils;
import org.junit.Test;



public class AlchemyTextRankedEntityExtractionAnnotatorTest {
	
	
	
	@Test
	public void testAnnotator() {
		String doc = "Eight US soldiers die in attacks in south Afghanistan, making October the deadliest month for the US in the war there";
		String xmlPath = "src/main/resources/TextRankedEntityExtractionAEDescriptor.xml";
		try {
			TestUtils.executeAE(TestUtils.getAE(xmlPath), doc);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
