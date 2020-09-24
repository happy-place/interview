package com.bigdata.game.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("tank/config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if(props==null){
            return null;
        }
        return props.get(key);
    }

    public static Integer getInt(String key){
        if(props==null){
            return null;
        }
        return Integer.parseInt((String)props.get(key));
    }

    public static String getString(String key){
        if(props==null){
            return null;
        }
        return (String)props.get(key);
    }

}

