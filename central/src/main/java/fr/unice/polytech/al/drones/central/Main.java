package fr.unice.polytech.al.drones.central;

import fr.unice.polytech.al.drones.central.business.CentralModel;
import fr.unice.polytech.al.drones.central.config.AddressesHolder;
import fr.unice.polytech.al.drones.central.config.PortReacher;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.URL;

/**
 * Created by sebastien on 09/11/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {

		HandlerList handlers = new HandlerList();
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");


		// Server static content
		ResourceHandler resource_handler = new ResourceHandler();
		resource_handler.setDirectoriesListed(true);
		resource_handler.setWelcomeFiles(new String[]{"index.html"});

		String  baseStr  = "/webapp";  //... contains: helloWorld.html, login.html, etc. and folder: other/xxx.html
		URL baseUrl  = Main.class.getResource(baseStr);
		String  basePath = baseUrl.toExternalForm();

		resource_handler.setResourceBase(basePath);

		handlers.addHandler(resource_handler);
		handlers.addHandler(context);

        int port = PortReacher.getPort();

        Server jettyServer = new Server(port);
        jettyServer.setHandler(handlers);
        //jettyServer.setHandler(context);

        ServletHolder servlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        servlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.

        servlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                ShippingServiceImpl.class.getCanonicalName()
                + "," + PackageServiceImpl.class.getCanonicalName());

        // Load addresses
        CentralModel cm = new CentralModel(AddressesHolder.loadAddresses());

        System.out.println("// ------- Server starting on port " + port);
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}
