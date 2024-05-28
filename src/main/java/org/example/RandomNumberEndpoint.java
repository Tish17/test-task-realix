package org.example;

import org.glassfish.tyrus.core.TyrusSession;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/random-number", encoders = MessageEncoder.class)
public class RandomNumberEndpoint {

    private final static Set<String> IPS = new HashSet<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        String ip = ((TyrusSession) session).getRemoteAddr();
        if (IPS.contains(ip)) {
            session.getBasicRemote().sendText("IP is already connected");
            session.close();
        }
        System.out.println("Connection opened: " + session.getId());
        session.getBasicRemote().sendText("Welcome to the WebSocket server!");
        IPS.add(ip);
    }

    @OnMessage
    public void onMessage(Session session, String message) throws IOException, EncodeException {
        System.out.println("Received message: " + message);
        session.getBasicRemote().sendObject(new Message());
    }

    @OnClose
    public void onClose(Session session) {
        IPS.remove(((TyrusSession) session).getRemoteAddr());
        System.out.println("Connection closed: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.printf("Error with session = %s and message = %s%n", session.getId(), throwable.getMessage());
    }
}
