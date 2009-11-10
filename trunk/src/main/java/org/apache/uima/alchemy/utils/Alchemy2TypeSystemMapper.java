package org.apache.uima.alchemy.utils;

import org.apache.uima.alchemy.digester.domain.AnnotatedResults;
import org.apache.uima.alchemy.digester.domain.CategorizationResults;
import org.apache.uima.alchemy.digester.domain.EntitiesResults;
import org.apache.uima.alchemy.digester.domain.Entity;
import org.apache.uima.alchemy.ts.categorization.Category;
import org.apache.uima.alchemy.utils.exception.MappingException;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.Type;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;

public class Alchemy2TypeSystemMapper {

  public static void mapRankedEntities(EntitiesResults results, JCas aJCas) throws MappingException {

    for (Entity entity : results.getEntities().getEntities()) {
      try {
        // use reflection to instantiate classes of the proper type in the type system
        Object fsObject;
        try {
          fsObject = Class.forName("org.apache.uima.alchemy.ts.entity." + entity.getType())
                  .getConstructors()[1].newInstance(aJCas);
        } catch (Exception e) {
          fsObject = Class.forName("org.apache.uima.alchemy.ts.entity." + entity.getType())
                  .getConstructors()[0].newInstance(aJCas);
        }
        FeatureStructure fs = (FeatureStructure) fsObject;

        Type type = fs.getType();

        fs.setFeatureValueFromString(type.getFeatureByBaseName("count"), entity.getCount()); // count
        fs.setFeatureValueFromString(type.getFeatureByBaseName("text"), entity.getText()); // text
        fs.setFeatureValueFromString(type.getFeatureByBaseName("relevance"), entity.getRelevance()); // relevance
        if (entity.getDisambiguated() != null) {
          fs.setFeatureValueFromString(type.getFeatureByBaseName("disambiguation"), entity
                  .getDisambiguated().getName()); // disambiguation name
          fs.setFeatureValueFromString(type.getFeatureByBaseName("dbpedia"), entity
                  .getDisambiguated().getDbpedia()); // dbpedia
          fs.setFeatureValueFromString(type.getFeatureByBaseName("website"), entity
                  .getDisambiguated().getWebsite()); // website
          fs.setFeatureValueFromString(type.getFeatureByBaseName("subType"), entity
                  .getDisambiguated().getSubType()); // subtype
          fs.setFeatureValueFromString(type.getFeatureByBaseName("geo"), entity.getDisambiguated()
                  .getGeo()); // geo
          fs.setFeatureValueFromString(type.getFeatureByBaseName("opencyc"), entity
                  .getDisambiguated().getOpencyc()); // opencyc
          fs.setFeatureValueFromString(type.getFeatureByBaseName("yago"), entity.getDisambiguated()
                  .getYago()); // yago
          fs.setFeatureValueFromString(type.getFeatureByBaseName("umbel"), entity
                  .getDisambiguated().getUmbel()); // umbel
          fs.setFeatureValueFromString(type.getFeatureByBaseName("freebase"), entity
                  .getDisambiguated().getFreebase()); // freebase
          fs.setFeatureValueFromString(type.getFeatureByBaseName("ciaFactbook"), entity
                  .getDisambiguated().getCiaFactbook()); // ciaFactbook
          fs.setFeatureValueFromString(type.getFeatureByBaseName("census"), entity
                  .getDisambiguated().getCensus()); // census
          fs.setFeatureValueFromString(type.getFeatureByBaseName("geonames"), entity
                  .getDisambiguated().getGeonames()); // geonames
          fs.setFeatureValueFromString(type.getFeatureByBaseName("musicBrainz"), entity
                  .getDisambiguated().getMusicBrainz()); // musicBrainz
        }
        if (entity.getQuotations() != null && entity.getQuotations().getQuotations() != null
                && entity.getQuotations().getQuotations().size() > 0) {
          StringArray quotationsFeatureStructure = new StringArray(aJCas, entity.getQuotations()
                  .getQuotations().size());
          int i = 0;
          for (String quotation : entity.getQuotations().getQuotations()) {
            quotationsFeatureStructure.set(i, quotation);
            i++;
          }
          fs.setFeatureValue(type.getFeatureByBaseName("quotatiotans"), quotationsFeatureStructure);
        }
        aJCas.addFsToIndexes(fs);
      } catch (Exception e) {
        System.err.println("error: " + entity.getType());
        throw new MappingException(e);
      }
    }

  }

  public static void mapAnnotatedEntities(AnnotatedResults results, JCas aJCas) {
    // TODO Auto-generated method stub

  }

  public static void mapCategorizationEntity(CategorizationResults results, JCas aJCas)
          throws MappingException {
    try {
      FeatureStructure fs = new Category(aJCas);
      fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "score"), results
              .getScore());
      fs.setFeatureValueFromString(aJCas.getRequiredFeature(fs.getType(), "text"), results
              .getCategory());
      aJCas.addFsToIndexes(fs);
    } catch (Exception e) {
      e.printStackTrace();
      throw new MappingException(e);
    }
  }

}
