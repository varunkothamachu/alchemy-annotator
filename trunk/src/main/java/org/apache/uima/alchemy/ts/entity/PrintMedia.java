

/* First created by JCasGen Sun Nov 01 10:30:15 CET 2009 */
package org.apache.uima.alchemy.ts.entity;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.TOP;


/** 
 * Updated by JCasGen Sun Nov 01 10:33:38 CET 2009
 * XML source: /Users/tommasoteofili/Documents/workspaces/default_workspace/alchemy-annotator/src/main/resources/TextEntityExtractionAEDescriptor.xml
 * @generated */
public class PrintMedia extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(PrintMedia.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected PrintMedia() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public PrintMedia(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public PrintMedia(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated */
  public String getText() {
    if (PrintMedia_Type.featOkTst && ((PrintMedia_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.entity.PrintMedia");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PrintMedia_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated */
  public void setText(String v) {
    if (PrintMedia_Type.featOkTst && ((PrintMedia_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.entity.PrintMedia");
    jcasType.ll_cas.ll_setStringValue(addr, ((PrintMedia_Type)jcasType).casFeatCode_text, v);}    
   
    
  //*--------------*
  //* Feature: count

  /** getter for count - gets 
   * @generated */
  public String getCount() {
    if (PrintMedia_Type.featOkTst && ((PrintMedia_Type)jcasType).casFeat_count == null)
      jcasType.jcas.throwFeatMissing("count", "org.apache.uima.alchemy.ts.entity.PrintMedia");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PrintMedia_Type)jcasType).casFeatCode_count);}
    
  /** setter for count - sets  
   * @generated */
  public void setCount(String v) {
    if (PrintMedia_Type.featOkTst && ((PrintMedia_Type)jcasType).casFeat_count == null)
      jcasType.jcas.throwFeatMissing("count", "org.apache.uima.alchemy.ts.entity.PrintMedia");
    jcasType.ll_cas.ll_setStringValue(addr, ((PrintMedia_Type)jcasType).casFeatCode_count, v);}    
   
    
  //*--------------*
  //* Feature: relevance

  /** getter for relevance - gets 
   * @generated */
  public String getRelevance() {
    if (PrintMedia_Type.featOkTst && ((PrintMedia_Type)jcasType).casFeat_relevance == null)
      jcasType.jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.entity.PrintMedia");
    return jcasType.ll_cas.ll_getStringValue(addr, ((PrintMedia_Type)jcasType).casFeatCode_relevance);}
    
  /** setter for relevance - sets  
   * @generated */
  public void setRelevance(String v) {
    if (PrintMedia_Type.featOkTst && ((PrintMedia_Type)jcasType).casFeat_relevance == null)
      jcasType.jcas.throwFeatMissing("relevance", "org.apache.uima.alchemy.ts.entity.PrintMedia");
    jcasType.ll_cas.ll_setStringValue(addr, ((PrintMedia_Type)jcasType).casFeatCode_relevance, v);}    
  }

    