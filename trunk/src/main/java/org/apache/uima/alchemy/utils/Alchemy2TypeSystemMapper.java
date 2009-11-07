package org.apache.uima.alchemy.utils;

import org.apache.uima.alchemy.digester.domain.AnnotatedResults;
import org.apache.uima.alchemy.digester.domain.CategorizationResults;
import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.ts.categorization.Category;
import org.apache.uima.alchemy.utils.exception.MappingException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;

public class Alchemy2TypeSystemMapper {

	public static void mapRankedEntities(EntitiesResults results, JCas aJCas) throws MappingException{
		
		for (Entity entity : results.getEntities().getEntities()) {
			try {
				//use reflection to instantiate classes of the proper type in the type system
				Object fsObject = Class.forName("org.apache.uima.alchemy.ts.entity."+entity.getType()).getConstructors()[1].newInstance(aJCas);
				FeatureStructure fs = (FeatureStructure) fsObject;
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "count"), entity.getCount()); //count
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "text"), entity.getText()); //text
				fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "relevance"), entity.getRelevance()); //relevance
				if (entity.getDisambiguated()!=null) {
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "disambiguation"), entity.getDisambiguated().getName()); //disambiguation name
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "dbpedia"), entity.getDisambiguated().getDbpedia()); //dbpedia
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "website"), entity.getDisambiguated().getWebsite()); //website
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "subType"), entity.getDisambiguated().getSubType()); //subtype
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "geo"), entity.getDisambiguated().getGeo()); //geo
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "opencyc"), entity.getDisambiguated().getOpencyc()); //opencyc
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "yago"), entity.getDisambiguated().getYago()); //yago
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "umbel"), entity.getDisambiguated().getUmbel()); //umbel
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "freebase"), entity.getDisambiguated().getFreebase()); //freebase
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "ciaFactbook"), entity.getDisambiguated().getCiaFactbook()); //ciaFactbook
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "census"), entity.getDisambiguated().getCensus()); //census
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "geonames"), entity.getDisambiguated().getGeonames()); //geonames
					fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "musicBrainz"), entity.getDisambiguated().getMusicBrainz()); //musicBrainz
				}
				if (entity.getQuotations()!=null && entity.getQuotations().getQuotations()!=null && entity.getQuotations().getQuotations().size()>0) {
					StringArray quotationsFeatureStructure = new StringArray(aJCas, entity.getQuotations().getQuotations().size());
					int i=0;
					for (String quotation : entity.getQuotations().getQuotations()) {
						quotationsFeatureStructure.set(i, quotation);
						i++;
					}
					fs.setFeatureValue(aJCas.getRequiredFeature(fs.getType(), "quotations"), quotationsFeatureStructure);
				}
				aJCas.addFsToIndexes(fs);
			} catch (Exception e) {
				throw new MappingException(e);
			}
		}
		
	}

	public static void mapAnnotatedEntities(AnnotatedResults results, JCas aJCas) {
		// TODO Auto-generated method stub
		
	}
	
	public static void mapCategorizationEntity(CategorizationResults results, JCas aJCas) throws MappingException {
		try {
			FeatureStructure fs = new Category(aJCas);
			fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "score"), results.getScore());
			fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "text"), results.getCategory());
			aJCas.addFsToIndexes(fs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MappingException(e);
		} 
	}

}
