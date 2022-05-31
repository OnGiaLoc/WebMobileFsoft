//package com.mycompany.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//@Configuration
//public class MvcConfig implements WebMvcConfigurer {
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        exposeDirectory("./product-photo", registry);
//    }
//
//    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
//        Path productUploadDir = Paths.get(dirName);
//        String productUploadPath = productUploadDir.toFile().getAbsolutePath();
//
//        registry.addResourceHandler("./product-photo" + dirName + "/**").addResourceLocations("file:/"+ productuploadPath + "/");
//    }
//}
//
//
