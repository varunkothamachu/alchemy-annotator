package org.apache.uima.alchemy.utils;

import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.digester.domain.Results;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;

public class Alchemy2TypeSystemMapper {

	public static void mapEntities(Results results, JCas aJCas) {
		
		for (Entity entity : results.getEntities().getEntities()) {
			System.out.println("en:"+entity.getText()+"+"+entity.getType()+"+"+entity.getCount()+"+"+entity.getRelevance());
			try {
				//FIXME create type system classes first!
				Object fsObject = ("org.apache.uima.alchemy.ts.entity."+entity.getType()).getClass();
				FeatureStructure fs = (FeatureStructure) fsObject;
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "count"), entity.getCount()); //count
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "text"), entity.getText()); //text
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "relevance"), entity.getRelevance()); //relevance
				//TODO handle disambiguation
				aJCas.addFsToIndexes(fs);
			} catch (CASRuntimeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CASException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
