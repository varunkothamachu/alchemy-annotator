
/* First created by JCasGen Sun Nov 01 10:29:05 CET 2009 */
package org.apache.uima.alchemy.ts.entity;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.cas.TOP_Type;

/** 
 * Updated by JCasGen Sun Nov 01 10:33:38 CET 2009
 * @generated */
public class NaturalDisaster_Type extends TOP_Type {
  /** @generated */
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (NaturalDisaster_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = NaturalDisaster_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new NaturalDisaster(addr, NaturalDisaster_Type.this);
  			   NaturalDisaster_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new NaturalDisaster(addr, NaturalDisaster_Type.this);
  	  }
    };
  /** @generated */
  public final static int typeIndexID = NaturalDisaster.typeIndexID;
  /** @generated 
     @modifiable */
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("org.apache.uima.alchemy.ts.entity.NaturalDisaster");
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.entity.NaturalDisaster");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.entity.NaturalDisaster");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  
 
  /** @generated */
  final Feature casFeat_relevance;
  /** @generated */
  final int     casFeatCode_relevance;
  /** @generated */ 
  public String getRelevance(int addr) {
        if (featOkTst && casFeat_relevance == null)
      jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.entity.NaturalDisaster");
    return ll_cas.ll_getStringValue(addr, casFeatCode_relevance);
  }
  /** @generated */    
  public void setRelevance(int addr, String v) {
        if (featOkTst && casFeat_relevance == null)
      jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.entity.NaturalDisaster");
    ll_cas.ll_setStringValue(addr, casFeatCode_relevance, v);}
    
  
 
  /** @generated */
  final Feature casFeat_count;
  /** @generated */
  final int     casFeatCode_count;
  /** @generated */ 
  public String getCount(int addr) {
        if (featOkTst && casFeat_count == null)
      jcas.throwFeatMissing("count", "org.apache.uima.alchemy.ts.entity.NaturalDisaster");
    return ll_cas.ll_getStringValue(addr, casFeatCode_count);
  }
  /** @generated */    
  public void setCount(int addr, String v) {
        if (featOkTst && casFeat_count == null)
      jcas.throwFeatMissing("count", "org.apache.uima.alchemy.ts.entity.NaturalDisaster");
    ll_cas.ll_setStringValue(addr, casFeatCode_count, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @generated */
  public NaturalDisaster_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

 
    casFeat_relevance = jcas.getRequiredFeatureDE(casType, "relevance", "uima.cas.String", featOkTst);
    casFeatCode_relevance  = (null == casFeat_relevance) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_relevance).getCode();

 
    casFeat_count = jcas.getRequiredFeatureDE(casType, "count", "uima.cas.String", featOkTst);
    casFeatCode_count  = (null == casFeat_count) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_count).getCode();

  }
}



    