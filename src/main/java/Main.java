import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlet.*;
import util.DBHelper;

public class Main {
    public static void main(String[] args) throws Exception {
        DBHelper.getSessionFactory().openSession();
        startServer();
    }

    public static void startServer() throws Exception {
        MainServlet mainServlet = new MainServlet();
        CustomerServlet customerServlet = new CustomerServlet();
        DailyReportServlet dailyReportServlet = new DailyReportServlet();
        NewDayServlet newDayServlet = new NewDayServlet();
        ProducerServlet producerServlet = new ProducerServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(customerServlet), "/customer");
        context.addServlet(new ServletHolder(dailyReportServlet), "/report/*");
        context.addServlet(new ServletHolder(newDayServlet), "/newday");
        context.addServlet(new ServletHolder(producerServlet), "/producer");
        context.addServlet(new ServletHolder(mainServlet), "/");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        server.join();
    }
}