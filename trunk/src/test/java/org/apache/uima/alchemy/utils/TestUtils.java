package org.apache.uima.alchemy.utils;

import java.io.IOException;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.ProcessTrace;
import org.apache.uima.util.ProcessTraceEvent;
import org.apache.uima.util.XMLInputSource;
import org.junit.Ignore;

@Ignore
public class TestUtils {

  public static AnalysisEngine getAE(String filePath) throws IOException, InvalidXMLException,
          ResourceInitializationException {
    AnalysisEngine ae = null;
    // get Resource Specifier from XML file
    XMLInputSource in = new XMLInputSource(filePath);
    ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

    // create AE here
    ae = UIMAFramework.produceAnalysisEngine(specifier);

    return ae;
  }

  public static void executeAE(AnalysisEngine ae, String docText)
          throws AnalysisEngineProcessException, ResourceInitializationException {
    // create a JCas, given an Analysis Engine (ae)
    JCas jcas = ae.newJCas();

    // analyze a document
    jcas.setDocumentText(docText);
    ProcessTrace pt = ae.process(jcas);

    // analyze results
    for (Object eventObject : pt.getEvents()) {
      ProcessTraceEvent e = (ProcessTraceEvent) eventObject;
      if (e != null && e.getResultMessage() != null && e.getResultMessage().contains("error")) {
        throw new AnalysisEngineProcessException();
      }
    }
  }

}
