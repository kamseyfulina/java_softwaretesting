package ru.stqa.pft.rest;

import org.testng.SkipException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static ru.stqa.pft.rest.IssueHelper.getStateIssueById;

public class TestBase {

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String issueState = getStateIssueById(issueId);
        if(issueState.equalsIgnoreCase("Closed")){
            return false;
        }
        else return true;
    }
}
