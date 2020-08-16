package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("8e9429aca4f0a707445da44d38847a25f34b2c7a ");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("kamseyfulina", "java_softwaretesting")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
      };
    }
  
}
