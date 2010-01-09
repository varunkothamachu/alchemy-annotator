

/* First created by JCasGen Sat Jan 09 18:51:20 CET 2010 */
package org.apache.uima.alchemy.ts.entity;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Jan 09 18:51:22 CET 2010
 * XML source: /Users/tommaso/Documents/workspaces/uima_ws/alchemy-annotator/src/main/resources/TextAnnotatedEntityExtractionAEDescriptor.xml
 * @generated */
public class AlchemyAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(AlchemyAnnotation.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected AlchemyAnnotation() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public AlchemyAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public AlchemyAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public AlchemyAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: alchemyType

  /** getter for alchemyType - gets 
   * @generated */
  public String getAlchemyType() {
    if (AlchemyAnnotation_Type.featOkTst && ((AlchemyAnnotation_Type)jcasType).casFeat_alchemyType == null)
      jcasType.jcas.throwFeatMissing("alchemyType", "org.apache.uima.alchemy.ts.entity.AlchemyAnnotation");
    return jcasType.ll_cas.ll_getStringValue(addr, ((AlchemyAnnotation_Type)jcasType).casFeatCode_alchemyType);}
    
  /** setter for alchemyType - sets  
   * @generated */
  public void setAlchemyType(String v) {
    if (AlchemyAnnotation_Type.featOkTst && ((AlchemyAnnotation_Type)jcasType).casFeat_alchemyType == null)
      jcasType.jcas.throwFeatMissing("alchemyType", "org.apache.uima.alchemy.ts.entity.AlchemyAnnotation");
    jcasType.ll_cas.ll_setStringValue(addr, ((AlchemyAnnotation_Type)jcasType).casFeatCode_alchemyType, v);}    
  }

    