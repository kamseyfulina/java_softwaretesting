package ru.stqa.pft.rest;

import org.apache.http.client.fluent.Executor;

public class BaseHelper {
    public static Executor getExecutor() {
        return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", "");
    }
}
