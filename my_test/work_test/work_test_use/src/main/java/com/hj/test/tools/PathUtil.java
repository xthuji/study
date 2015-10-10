package com.hj.test.tools;

import com.hj.test.usetest.ReadFile;

public class PathUtil {

    

    public static String getRealPath(String path) {
        String basePath = new ReadFile().getClass().getClassLoader()
                .getResource("").getPath();
        String pathString = basePath + path;
        
//      String pathString = null;
//      try {
//          pathString = new ReadFile().getClass().getClassLoader().getResource(path).toURI().getPath();
//      } catch (URISyntaxException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//      }
        return pathString;
    }
    
}
