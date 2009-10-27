package org.apache.uima.alchemy.annotator;

import java.io.IOException;

import org.apache.uima.UIMAFramework;
import org.apache.uima.analysis_engine.AnalysisEngine;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceSpecifier;
import org.apache.uima.util.InvalidXMLException;
import org.apache.uima.util.ProcessTrace;
import org.apache.uima.util.ProcessTraceEvent;
import org.apache.uima.util.XMLInputSource;
import org.junit.Test;



public class AlchemyTextEntityExtractionAnnotatorTest {
	
	private AnalysisEngine getAE(String filePath) {
		AnalysisEngine ae = null;
		try {
			//get Resource Specifier from XML file
			XMLInputSource in = new XMLInputSource(filePath);
			ResourceSpecifier specifier = UIMAFramework.getXMLParser().parseResourceSpecifier(in);

			//create AE here
			ae =   UIMAFramework.produceAnalysisEngine(specifier);
			
		} catch (InvalidXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ResourceInitializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ae;
	}
	
	private void executeAE(AnalysisEngine ae, String docText) throws AnalysisEngineProcessException{
		try {
			//create a JCas, given an Analysis Engine (ae)
			JCas jcas = ae.newJCas();
			  
			 //analyze a document
			jcas.setDocumentText(docText);
			ProcessTrace pt = ae.process(jcas);
			
			//analyze results
			for (Object eventObject :  pt.getEvents()) {
				try {
					ProcessTraceEvent e = (ProcessTraceEvent) eventObject;
					System.err.println(e.getComponentName()+" ("+e.getType()+") - "+e.getDescription()+" ("+e.getDuration()+") : "+e.getResultMessage());
					if (e!=null && e.getResultMessage()!=null && e.getResultMessage().contains("error")) {
						throw new AnalysisEngineProcessException();
					}
				}
				catch (ClassCastException cce) {
					cce.printStackTrace();
				}
			}
			FSIterator<Annotation> it = jcas.getAnnotationIndex().iterator();
		}
		catch (Exception e) {
			
		}
	}
	
	@Test
	public void testAnnotator() {
		String doc = "Ecology of Infectious Diseases (EID) SF, NIH Award EID Grants See Press Release 08-160 for announcement of recent awards" +
				"CONTACTS Name Email Phone Room N. Thomas Hobbs nhobbs@nsf.gov (703) 292-8610 Donald  Rice drice@nsf.gov (703) 292-7708 " +
				"Deborah Winslow dwinslow@nsf.gov (703) 292-7315 PROGRAM GUIDELINES Solicitation 08-601 DUE DATES Full Proposal Deadline Date : December 10, 2008" +
				" Second Wednesday in December, Annually Thereafter SYNOPSIS The Ecology of Infectious Diseases program solicitation supports the development of" +
				" predictive models and the discovery of principles governing the transmission dynamics of infectious disease agents. To that end, research proposals" +
				" should focus on understanding the ecological and socio-ecological determinants of transmission by vectors or abiotic agents, the population dynamics " +
				"of reservoir species, the transmission to humans or other hosts, or the cultural, social, behavioral, and economic dimensions of disease communication." +
				"Research may be on zoonotic, vector-borne or enteric diseases of either terrestrial, freshwater, or marine systems and organisms, including diseases of" +
				"non-human animals and plants, at any scale from specific pathogens to inclusive environmental systems. Proposals for research on disease systems of " +
				"public health concern to developing countries are strongly encouraged. Investigators are encouraged to include links to the public health research community," +
				" including for example, participation of epidemiologists, physicians, veterinarians, medical social scientists, medical entomologists, virologists, or " +
				"parasitologists.RELATED URLS NIH Awards A Special Report: Ecology of Infectious Diseases THIS PROGRAM IS PART OF Additional Funding Opportunities for the " +
				"DEB Community Emerging Frontiers";
		String xmlPath = "src/main/resources/TextEntityExtractionAEDescriptor.xml";
		try {
			this.executeAE(this.getAE(xmlPath), doc);
		} catch (AnalysisEngineProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
