package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.apache.http.client.fluent.Request.Post;
import static org.testng.Assert.*;

public class RestTests extends TestBase {

    @BeforeMethod
    public void isNeedToRunTest() throws IOException {
        //Создание issue и проставление статуса (3-Closed,1-In Progress)
        Issue newIssue = new Issue()
                .withSubject("Test issue")
                .withDescription("New test issue");
        int issueId = IssueHelper.createIssue(newIssue);
        IssueHelper.updateStateIssueById(issueId,3);
        skipIfNotFixed(issueId);
    }

    @Test
    public void testCreateIssue() throws IOException {
        Set<Issue> oldIssues = IssueHelper.getIssues();
        Issue newIssue = new Issue()
                .withSubject("Test issue")
                .withDescription("New test issue");
        int issueId = IssueHelper.createIssue(newIssue);
        Set<Issue> newIssues = IssueHelper.getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }


}
