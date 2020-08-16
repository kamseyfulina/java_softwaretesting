package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.*;

public class IssueHelper extends BaseHelper {

  public static Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Get("https://bugify.stqa.ru/api/issues.json"))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }


  public static int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Post("https://bugify.stqa.ru/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  public static String getStateIssueById(Integer idIssue) throws IOException {
    String json = getExecutor().execute(Get("https://bugify.stqa.ru/api/issues" + idIssue.toString() + ".json")
            .bodyForm(new BasicNameValuePair("issue_id", idIssue.toString())))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
    return parsed.getAsJsonObject().get("state_name").getAsString();
  }

  public static void updateStateIssueById(Integer idIssue, Integer idState) throws IOException {
    String json = getExecutor().execute(Post("https://bugify.stqa.ru/api/issues/" + idIssue.toString() + ".json")
            .bodyForm(new BasicNameValuePair("state", idState.toString())))
            .returnContent().asString();
    JsonElement parsed = JsonParser.parseString(json);
  }
}
