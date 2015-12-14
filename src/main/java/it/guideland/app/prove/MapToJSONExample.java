package it.guideland.app.prove;
 
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;


public class MapToJSONExample {
    private static final String jsonFilePath = "C:\\Users\\nikos7\\Desktop\\filesForExamples\\mapExample.json";
    public static void main(String[] args) {
 
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapObject = new HashMap<String, Object>();
 
/*        mapObject.put("domain", "JavaCodeGeeks.com");
        mapObject.put("interest", "Java");
        mapObject.put("Members", 400);*/
        Map<String, String> languagesMap = new HashMap<>();
        languagesMap.put("Italiano", "madrelingua");
        languagesMap.put("Inglese", "ottimo");
        languagesMap.put("Spagnolo", "base");
/*        List<Object> myList = new ArrayList<Object>();
        myList.add("Jonh");
        myList.add("Jack");
        myList.add("James");*/
        mapObject.put("languages", languagesMap);
        
            try {
				objectMapper.writeValue(System.out, mapObject);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 

    }

}

