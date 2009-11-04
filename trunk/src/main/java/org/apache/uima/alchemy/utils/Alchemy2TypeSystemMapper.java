package org.apache.uima.alchemy.utils;

import org.apache.uima.alchemy.digester.domain.AnnotatedResults;
import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.utils.exception.MappingException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;

public class Alchemy2TypeSystemMapper {

	public static void mapEntities(EntitiesResults results, JCas aJCas) throws MappingException{
		
		for (Entity entity : results.getEntities().getEntities()) {
			try {
				//use reflection to instantiate classes of the proper type in the type system
				Object fsObject = Class.forName("org.apache.uima.alchemy.ts.entity."+entity.getType()).getConstructors()[1].newInstance(aJCas);
				FeatureStructure fs = (FeatureStructure) fsObject;
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "count"), entity.getCount()); //count
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "text"), entity.getText()); //text
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "relevance"), entity.getRelevance()); //relevance
				//TODO handle disambiguation
				aJCas.addFsToIndexes(fs);
			} catch (Exception e) {
				throw new MappingException(e);
			}
		}
		
	}

	public static void mapAnnotatedEntities(AnnotatedResults results, JCas aJCas) {
		// TODO Auto-generated method stub
		
	}

}