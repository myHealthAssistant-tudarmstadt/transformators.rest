package de.tud.darmstadt.dvs.myhealthhub.transformators.rest;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyWebServer {

	private final static String webAppDir = "src/main/webapp/";
//	private static final String LOG_PATH = null;

	public static void main(String[] args) throws Exception {

		JettyWebServer jettyWebServer = new JettyWebServer();
		jettyWebServer.startServer();

	}

	public void startServer() throws Exception {

		Server server = new Server(8080);
		server.setThreadPool(createThreadPool());
		server.addConnector(createConnector());
		server.setHandler(createHandlers());
		server.setStopAtShutdown(true);
		server.start();
		server.join();
	}

	private Handler createHandlers() {

		WebAppContext webAppContext = new WebAppContext();
		webAppContext.setContextPath("/");
		webAppContext.setDescriptor(webAppDir + "WEB-INF/web.xml");
		webAppContext.setResourceBase(webAppDir);
		webAppContext.setParentLoaderPriority(true);
		
		HandlerList contexts = new HandlerList();
		List<Handler> handlers = new ArrayList<Handler>();

		handlers.add(webAppContext);
		contexts.setHandlers(handlers.toArray(new Handler[0]));
		/*RequestLogHandler log = new RequestLogHandler();
        log.setRequestLog(createRequestLog());*/

        HandlerCollection result = new HandlerCollection();
        result.setHandlers(new Handler[] {contexts});

        return result;
	}

	/*private RequestLog createRequestLog() {
		NCSARequestLog log = new NCSARequestLog();

        File _logPath = new File(LOG_PATH);
        logPath.getParentFile().mkdirs();

        log.setFilename(logPath.getPath());
        log.setRetainDays(90);
        log.setExtended(false);
        log.setAppend(true);
        log.setLogTimeZone("GMT");
        log.setLogLatency(true);
        return log;
	}*/

	private Connector createConnector() {
		SelectChannelConnector connector = new SelectChannelConnector();
		return connector;
	}

	private ThreadPool createThreadPool() {
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMinThreads(10);
		threadPool.setMaxThreads(100);
		return threadPool;
	}

}
