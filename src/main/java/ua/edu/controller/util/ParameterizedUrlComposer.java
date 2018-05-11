package ua.edu.controller.util;

import java.util.Map;

public class ParameterizedUrlComposer {
	
	private static volatile ParameterizedUrlComposer instance;
	
    private ParameterizedUrlComposer() {
    	
    }
    
    public String composeUrl(String url, Map<String, String> parameters){
    	StringBuilder result = new StringBuilder().append(url);
    	result.append("?");
    	for (String parameterName : parameters.keySet()){
    		result.append(parameterName)
    				.append("=")
    				.append(parameters.get(parameterName))
    				.append("&");
    	}
    	result.deleteCharAt(result.length() - 1);
    	return result.toString();
    }

    public static ParameterizedUrlComposer getInstance() {
    	if (instance == null) {
            synchronized (ParameterizedUrlComposer.class) {
                if (instance == null) {
                    instance = new ParameterizedUrlComposer();
                }
            }
        }
        return instance;
    }

}
