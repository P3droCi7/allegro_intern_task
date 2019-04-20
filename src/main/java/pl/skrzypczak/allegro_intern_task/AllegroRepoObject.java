package pl.skrzypczak.allegro_intern_task;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

import java.time.LocalDateTime;

public class AllegroRepoObject {
  private String id;
  private String name;
  private String date;

  public AllegroRepoObject(String id, String name, String date) {
    this.id = id;
    this.name = name;
    this.date = date;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public String toString() {
    return "AllegroRepoObject{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", date=" + date +
            '}';
  }
}
