package org.example;

import org.glassfish.tyrus.server.Server;

public class Main {

    public static void main(String[] args) {
        Server server = new Server("localhost", 8080, "/test-task-realix", null, RandomNumberEndpoint.class);
        try {
            server.start();
            System.out.println("Press any key to stop the server...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            server.stop();
        }
    }
}
