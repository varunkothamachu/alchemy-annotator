package org.apache.uima.alchemy.digester.domain;

import java.util.ArrayList;
import java.util.List;

public class Entities {

  private List<Entity> entities;

  public Entities() {
    this.entities = new ArrayList<Entity>();
  }

  public void setEntities(List<Entity> entities) {
    this.entities = entities;
  }

  public List<Entity> getEntities() {
    return entities;
  }

  public void addEntity(Entity entity) {
    this.entities.add(entity);
  }

}
