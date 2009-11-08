

/* First created by JCasGen Wed Nov 04 23:56:01 CET 2009 */
package org.apache.uima.alchemy.ts.categorization;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP;
import org.apache.uima.jcas.cas.TOP_Type;


/** 
 * Updated by JCasGen Wed Nov 04 23:56:24 CET 2009
 * XML source: /Users/tommaso/Documents/workspaces/uima_ws/alchemy-annotator/src/main/resources/TextCategorizationAEDescriptor.xml
 * @generated */
public class Category extends TOP {
  /** @generated
   * @ordered 
   */
  public final static int typeIndexID = JCasRegistry.register(Category.class);
  /** @generated
   * @ordered 
   */
  public final static int type = typeIndexID;
  /** @generated  */
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected Category() {}
    
  /** Internal - constructor used by generator 
   * @generated */
  public Category(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public Category(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {}
     
 
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets 
   * @generated */
  public String getScore() {
    if (Category_Type.featOkTst && ((Category_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.categorization.Category");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Category_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated */
  public void setScore(String v) {
    if (Category_Type.featOkTst && ((Category_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.apache.uima.alchemy.ts.categorization.Category");
    jcasType.ll_cas.ll_setStringValue(addr, ((Category_Type)jcasType).casFeatCode_score, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated */
  public String getText() {
    if (Category_Type.featOkTst && ((Category_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.categorization.Category");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Category_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated */
  public void setText(String v) {
    if (Category_Type.featOkTst && ((Category_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "org.apache.uima.alchemy.ts.categorization.Category");
    jcasType.ll_cas.ll_setStringValue(addr, ((Category_Type)jcasType).casFeatCode_text, v);}    
  }

    