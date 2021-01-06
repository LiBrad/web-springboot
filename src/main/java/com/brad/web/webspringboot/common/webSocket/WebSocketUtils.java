package com.brad.web.webspringboot.common.webSocket;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class WebSocketUtils {

    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();
    // 单用户推送
    public static void sendMessage(Session session, String message) {
        if (session == null) { return; }
        // 异步推送
        RemoteEndpoint.Async async = session.getAsyncRemote();
        if (async == null) { return; }
        async.sendText(message);
    }
    // 全用户推送
    public static void sendMessageAll(String message) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session, message));
    }
}
