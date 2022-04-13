package com.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    //Bu class'in amacı configuration.properties dosyasındaki verileri okumaktır.
    //create Properties instance
   private static Properties properties;
   //FileInputStream kullanarak bir dosya açıyorua
    //Properties'i bu dosyaya ekliyoruz
    //Daha sonra properties dosyasını okuyacağız

    //başlatmak için static blok oluşturduk

   static {
       String path = "configuration.properties";
       try {
           FileInputStream file = new FileInputStream(path);
           properties = new Properties();
           properties.load(file);
       } catch (Exception e) {
           e.printStackTrace();

       }
   }
   //dosyayı okumak için static bir method oluşturacağız
    //bu method kullanıı anahtar kelimeyi girdiğinde (key) value return eder.
    public static String getProperty(String key){
       return properties.getProperty(key);
    }
}
