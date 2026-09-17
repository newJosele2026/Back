package com.josele.crud;

import java.io.File;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

/**
 * Arranca la aplicacion clasica de Spring Framework (sin Spring Boot)
 * usando Tomcat embebido y el web.xml de la app.
 */
public class Main {

	private static final int PORT = 8080;

	public static void main(String[] args) throws Exception {
		Tomcat tomcat = new Tomcat();
		tomcat.setPort(PORT);
		tomcat.setBaseDir("target/tomcat");

		// Carga src/main/webapp/WEB-INF/web.xml (DispatcherServlet clasico)
		String webappDir = new File("src/main/webapp").getAbsolutePath();
		Context context = tomcat.addWebapp("/", webappDir);

		tomcat.getConnector();
		tomcat.start();
		System.out.println("CRUD clasico disponible en http://localhost:" + PORT + "/api/usuarios");
		tomcat.getServer().await();
	}

}