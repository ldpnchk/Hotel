package ua.edu.util;

import java.util.ResourceBundle;

public class ResourceManager {
	
    private static ResourceManager instance;

    private ResourceBundle resourceBundle;
    private static final String RESOURCE_NAME = "config";

    private ResourceManager() {
    	resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME);
    }

    public static ResourceManager getInstance() {
    	if (instance == null) {
            synchronized (ResourceManager.class) {
                if (instance == null) {
                    instance = new ResourceManager();
                }
            }
        }
        return instance;
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

}
