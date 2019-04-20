package pl.skrzypczak.allegro_intern_task;

import java.util.Date;

public class AllegroRepoObject {
  private Number id;
  private String name;
  private Date updateDatetime;

  public AllegroRepoObject(Number id, String name, Date updateDatetime) {
    this.id = id;
    this.name = name;
    this.updateDatetime = updateDatetime;
  }

  public Number getId() {
    return id;
  }

  public void setId(Number id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getUpdateDatetime() {
    return updateDatetime;
  }

  public void setUpdateDatetime(Date updateDatetime) {
    this.updateDatetime = updateDatetime;
  }

  @Override
  public String toString() {
    return "AllegroRepoObject{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", updateDatetime=" + updateDatetime +
            '}';
  }
}
