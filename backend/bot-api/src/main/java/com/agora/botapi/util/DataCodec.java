package com.agora.botapi.util;

import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataCodec {


    public static String encode(Map<String, String> data) {
        URIBuilder builder = new URIBuilder();
        for(String key : data.keySet()){
            builder.addParameter(key, data.get(key));
        }
        try {
            return builder.build().getQuery();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static Map<String, String> decode(String data) {
        String queryParam = data.split("\\?")[1];
        Pattern pat = Pattern.compile("([^&=]+)=([^&]*)");
        Matcher matcher = pat.matcher(queryParam);
        Map<String,String> map = new HashMap<>();
        while (matcher.find()) {
            map.put(matcher.group(1), matcher.group(2));
        }
        System.out.println(map);
        return map;
    }


}
