package com.duzieblo.ewdserver.server;

import com.duzieblo.ewdserver.ewd.impl.EwdHandlerImpl;
import java.io.IOException;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.server.PropertyHandlerMapping;
import org.apache.xmlrpc.server.XmlRpcServer;
import org.apache.xmlrpc.webserver.WebServer;

/**
 *
 * @author Damian Uziębło
 */
public class EwdServer {

    private static final int SERVER_PORT = 4143;
    private static final String SERVER_NAMESPACE = "ewd";
    
    WebServer webserver;
    XmlRpcServer xmlRpcServer;
    PropertyHandlerMapping propertyMappingHandler;
    int port;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            EwdServer ewdServer = new EwdServer(SERVER_PORT);
            ewdServer.start();
            print("XML RPC server running!"); //TODO use LOGGER!
        } catch (XmlRpcException e) {
            print("Error while initialize server! Details: " + e.getMessage()); //TODO use LOGGER!
        } catch (IOException e) {
            print("Error while starting server! Details: " + e.getMessage());   //TODO use LOGGER!
        }
    }
    
    public EwdServer(int port) throws XmlRpcException {
        this.port = port;
        initialize();
    }

    private void initialize() throws XmlRpcException {
        this.webserver = new WebServer(port);
        this.xmlRpcServer = webserver.getXmlRpcServer();
        
        propertyMappingHandler = new PropertyHandlerMapping();
        addProperty(SERVER_NAMESPACE, EwdHandlerImpl.class);
        xmlRpcServer.setHandlerMapping(propertyMappingHandler);
    }
    
    public void start() throws XmlRpcException, IOException {
        if (webserver == null) {
            throw new XmlRpcException("Server is not starting!");
        }
        webserver.start();
    }
    
    private void addProperty(String name, Class implClass) throws XmlRpcException {
        propertyMappingHandler.addHandler(name, implClass);
    }
    
    public static void print(String content) {
        System.out.println(content); 
    }
}
  
