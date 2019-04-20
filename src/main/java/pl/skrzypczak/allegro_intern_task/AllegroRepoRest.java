package pl.skrzypczak.allegro_intern_task;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

            String jsonName = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("name").toString();
            String jsonNameSubstring = jsonName.substring(1,jsonName.length()-1);
            Number jsonId = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("id").getAsNumber();


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("\"yyyy-MM-dd'T'hh:mm:ss'Z'\"");
            Date jsonUpdatedAt = simpleDateFormat.parse(String.valueOf(jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("updated_at")));

            AllegroRepoObject instanceOfAnObject = new AllegroRepoObject(jsonId, jsonNameSubstring, jsonUpdatedAt);
            AllegroRepos.add(instanceOfAnObject);

          }
        }
      }
    } catch (
            IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
    return AllegroRepos;
  }

  @GetMapping("/AllegroRepos/lastlyUpdated")
  public List grabLastlyUpdatedAllegroRepo() {

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

          for (int i = 0; i < 1; i++) {

            String jsonName = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("name").toString();
            String jsonNameSubstring = jsonName.substring(1,jsonName.length()-1);
            Number jsonId = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("id").getAsNumber();


            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("\"yyyy-MM-dd'T'hh:mm:ss'Z'\"");
            Date jsonUpdatedAt = simpleDateFormat.parse(String.valueOf(jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("updated_at")));

            AllegroRepoObject instanceOfAnObject = new AllegroRepoObject(jsonId, jsonNameSubstring, jsonUpdatedAt);
            AllegroRepos.add(instanceOfAnObject);

          }
        }
      }
    } catch (
            IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
    return AllegroRepos;
  }

}
