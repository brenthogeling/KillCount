package com.gmail.brentplaysmc.KillCount.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MinecraftVerifier {

    public static boolean checkAccount(String name) throws IOException {
        URL url = new URL("https://minecraft.net/haspaid.jsp?user=" + name);
        URLConnection connection = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        return Boolean.valueOf(reader.readLine());
    }
}
