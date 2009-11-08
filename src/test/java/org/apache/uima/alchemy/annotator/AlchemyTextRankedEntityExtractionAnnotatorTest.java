package org.apache.uima.alchemy.annotator;

import static org.junit.Assert.fail;

import org.apache.uima.alchemy.utils.TestUtils;
import org.junit.Test;

public class AlchemyTextRankedEntityExtractionAnnotatorTest {

  @Test
  public void testAnnotator() {
    String doc = "Eight US soldiers die in attacks in south Afghanistan, making October the deadliest month for the US in the war there";
    String doc2 = "The Frameworks run the components, and are available for both Java and C++. The Java Framework supports running both Java and non-Java components (using the C++ framework). The C++ framework, besides supporting annotators written in C/C++, also supports Perl, Python, and TCL annotators. The UIMA-AS Scaleout Framework is an add-on to the base Java framework, supporting a very flexible scaleout capability based on JMS (Java Messaging Services) and ActiveMQ."
            + "The frameworks support configuring and running pipelines of Annotator components. These components do the actual work of analyzing the unstructured information. Users can write their own annotators, or configure and use pre-existing annotators. Some annotators are available as part of this project; others are contained in various repositories on the internet."
            + "Additional infrastructure support components include a simple server that can receive REST requests and return annotation results, for use by other web services."
            + "		The sandbox is a place where new ideas are developed for potential incorporation into the project.";
    String xmlPath = "src/main/resources/TextRankedEntityExtractionAEDescriptor.xml";
    try {
      TestUtils.executeAE(TestUtils.getAE(xmlPath), doc);
      TestUtils.executeAE(TestUtils.getAE(xmlPath), doc2);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }
  }

}
