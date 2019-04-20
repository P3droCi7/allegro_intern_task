package pl.skrzypczak.allegro_intern_task;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class AllegroRepoRest {

  @GetMapping("/AllegroRepos/listAll")
  public List grabAllAllegroGitInfoSorted() {

    HttpClient client = new HttpClient();
    HttpMethod method = new GetMethod("https://api.github.com/orgs/allegro/repos?type=all&sort=updated&per_page=100");
    List AllegroRepos = new ArrayList();

    try {
      client.executeMethod(method);

      if (method.getStatusCode() == HttpStatus.SC_OK) {
        InputStream is = method.getResponseBodyAsStream();

        if (is != null) {
          Writer writer = new StringWriter();
          char[] buffer = new char[1024];
          try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int length;
            while ((length = reader.read(buffer)) != -1) {
              writer.write(buffer, 0, length);
            }
          } finally {
            is.close();
          }
          String jsonString = writer.toString();

          JsonParser parser = new JsonParser();

          JsonElement jsonTree = parser.parse(jsonString);

          for (int i = 0; i < jsonTree.getAsJsonArray().size(); i++) {


            String f1 = String.valueOf((JsonPrimitive) jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("name"));
            String f2 = String.valueOf(jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("id"));
            String f3 = String.valueOf((JsonPrimitive) jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("updated_at"));

            AllegroRepoObject instanceOfAnObject = new AllegroRepoObject(f2, f1, f3);
            AllegroRepos.add(instanceOfAnObject);

          }
        }
      }
    } catch (
            IOException e) {
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
    return AllegroRepos;
  }

  @GetMapping("/AllegroRepos/lastlyUpdated")
  public AllegroRepoObject grabLastlyUpdatedAllegroRepo() {

    HttpClient client = new HttpClient();
    HttpMethod method = new GetMethod("https://api.github.com/orgs/allegro/repos?type=all&sort=updated&per_page=100");
    AllegroRepoObject allegroRepoObject = null;

    try {
      client.executeMethod(method);

      if (method.getStatusCode() == HttpStatus.SC_OK) {
        InputStream is = method.getResponseBodyAsStream();

        if (is != null) {
          Writer writer = new StringWriter();
          char[] buffer = new char[1024];
          try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int length;
            while ((length = reader.read(buffer)) != -1) {
              writer.write(buffer, 0, length);
            }
          } finally {
            is.close();
          }
          String jsonString = writer.toString();

          JsonParser parser = new JsonParser();

          JsonElement jsonTree = parser.parse(jsonString);


          String f1 = String.valueOf((JsonPrimitive) jsonTree.getAsJsonArray().get(0).getAsJsonObject().get("name"));
          String f2 = String.valueOf((JsonPrimitive) jsonTree.getAsJsonArray().get(0).getAsJsonObject().get("id"));
          String f3 = String.valueOf((JsonPrimitive) jsonTree.getAsJsonArray().get(0).getAsJsonObject().get("updated_at"));

          allegroRepoObject = new AllegroRepoObject(f2, f1, f3);

        }
      }
    } catch (
            IOException e) {
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
    return allegroRepoObject;
  }

}
