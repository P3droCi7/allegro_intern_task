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

@RestController
public class AllegroRepoRest {

  @GetMapping("/AllegroRepos/listAll")
  public void grabAllegroGitInfo(){

    HttpClient client = new HttpClient();
    HttpMethod method = new GetMethod("https://api.github.com/orgs/allegro/repos?type=all&sort=updated&per_page=100");

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


            JsonElement f1 = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("name");
            JsonElement f2 = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("id");
            JsonElement f3 = jsonTree.getAsJsonArray().get(i).getAsJsonObject().get("updated_at");

            System.out.println(i);
            System.out.println("name: " + f1);
            System.out.println("id " + f2);
            System.out.println("updated at " + f3 + "\n");
          }
        }
      }
    } catch (
            IOException e) {
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
  }

}
