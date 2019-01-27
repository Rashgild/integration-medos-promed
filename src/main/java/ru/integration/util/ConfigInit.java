package ru.integration.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ResourceBundle;


@WebListener()
public class ConfigInit implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        Configure();
    }

    public void contextDestroyed(ServletContextEvent event) {}

    private static void Configure(){

        System.out.println("initialization config...");
        ResourceBundle resource = ResourceBundle.getBundle("config", new UTF8Control());
        GlobalVariables.rootPath = resource.getString("rootpath");
        GlobalVariables.dbhost = resource.getString("dbhost");
        GlobalVariables.dbdriver = resource.getString("dbdriver");
        GlobalVariables.dblogin = resource.getString("dblogin");
        GlobalVariables.dbpassword = resource.getString("dbpassword");
        GlobalVariables.sessionId = resource.getString("sessionId");
        GlobalVariables.endpoint =resource.getString("endpoint");
        GlobalVariables.lpu_id =resource.getString("lpu_id");
        GlobalVariables.login =resource.getString("login");
        GlobalVariables.password =resource.getString("password");

        System.out.println("rootPath="+ GlobalVariables.rootPath);
    }
}