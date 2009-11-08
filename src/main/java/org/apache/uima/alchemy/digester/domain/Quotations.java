package org.apache.uima.alchemy.digester.domain;

import java.util.ArrayList;
import java.util.List;

public class Quotations {

  private List<String> quotations = new ArrayList<String>();

  public void setQuotations(List<String> quotations) {
    this.quotations = quotations;
  }

  public List<String> getQuotations() {
    return quotations;
  }

  public void setQuotation(String quotation) {
    this.quotations.add(quotation);
  }

}
