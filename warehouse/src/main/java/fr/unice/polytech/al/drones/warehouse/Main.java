package fr.unice.polytech.al.drones.warehouse;

import fr.unice.polytech.al.drones.warehouse.config.AddressHolder;
import fr.unice.polytech.al.drones.warehouse.config.PortReacher;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

/**
 * Created by sebastien on 10/11/15.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        int port = PortReacher.getPort();

        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);

        ServletHolder servlet = context.addServlet(
                org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        servlet.setInitOrder(0);

        // Tells the Jersey Servlet which REST service/class to load.

        servlet.setInitParameter(
                "jersey.config.server.provider.classnames",
                DroneServiceImpl.class.getCanonicalName()
                        +","+BoxServiceImpl.class.getCanonicalName()
                        +","+TourServiceImpl.class.getCanonicalName()
        );

        // Load addresses
        AddressHolder.loadAddress();
        System.out.println("// ------- Server starting on port "+port);

        System.out.println("\n\n\t=====================================================\n\t\t\t\tWAREHOUSE\n" +
                "\t=====================================================");


        try
        {
            jettyServer.start();


            jettyServer.join();
        }
        finally
        {
            jettyServer.destroy();
        }
    }
}

//centralAddress.properties