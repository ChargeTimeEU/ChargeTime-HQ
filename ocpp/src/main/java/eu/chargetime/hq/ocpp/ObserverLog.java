package eu.chargetime.hq.ocpp;
/*
    ChargeTime.eu - ChargeTime HQ
    
    MIT License

    Copyright (C) 2016 Thomas Volden <tv@chargetime.eu>

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */

import eu.chargetime.ocpp.model.Confirmation;
import eu.chargetime.ocpp.model.Request;
import eu.chargetime.ocpp.model.SessionInformation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ObserverLog implements ILogService {

    private final static String FORMAT = "%1$c %s %s";
    private List<ILogSubscriber> subscribers;

    public ObserverLog() {
        subscribers = new ArrayList<>();
    }

    public void subscribe(ILogSubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    public void unsubscribe(ILogService subscriber) {
        this.subscribers.remove(subscriber);
    }

    public void broadcast(String update) {
        String news = String.format(FORMAT, new Date(System.currentTimeMillis()), "TRACE", update);
        for (ILogSubscriber subscriber: subscribers) {
            subscriber.notify(news);
        }
    }

    private String reflectOn(Object obj) {
        if (obj == null)
            return "NULL";

        Class<?> aClass = obj.getClass();

        StringBuilder values = new StringBuilder();
        for (Method method: aClass.getMethods()) {

            if (method.getParameterCount() > 0)
                continue;

            try {
                if (method.getName().startsWith("get")) {
                    values.append(String.format(", %s = %s", method.getName().substring(3), method.invoke(obj)));
                }
                else if (method.getName().startsWith("is")) {
                    values.append(String.format(", %s = %s", method.getName(), method.invoke(obj)));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (values.length() >= 0)
            return String.format("%s (%s)", aClass.getName(), values.toString().substring(2));
        return aClass.getName();
    }

    @Override
    public void logRequest(UUID uuid, Request request) {
        broadcast(String.format("Request on %s: %s", uuid, reflectOn(request)));
    }

    @Override
    public void logConfirmation(UUID uuid, Confirmation confirmation) {
        broadcast(String.format("Confirmation on %s: %s", uuid, reflectOn(confirmation)));
    }

    @Override
    public void logNewSession(UUID uuid, SessionInformation sessionInformation) {
        broadcast(String.format("new Session on %s: %s", uuid, reflectOn(sessionInformation)));
    }

    @Override
    public void logLostSession(UUID uuid) {
        broadcast(String.format("lost Session %s", uuid));
    }
}
