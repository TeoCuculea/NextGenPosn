package com.posn.nextgenpos.service.publish;

import com.posn.nextgenpos.service.PositionEvent;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@Singleton
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class RealTimeEventBroker {

    private ConcurrentLinkedQueue<BrowserWindow> browsers = new ConcurrentLinkedQueue<>();
    private ConcurrentHashMap<String, String> toValidate = new ConcurrentHashMap<>();

    public void onBrowserRequest(@Observes BrowserWindow browserWindow) {
        System.err.println("RealTimeEventBroker#onBrowserRequest");
        browsers.add(browserWindow);
    }

    public void onNewEvent(@Observes PositionEvent message) {
        //info
        System.err.println("RealTimeEventBroker#onNewEvent...msg=" + message);

        if (message.getMsg().startsWith("RequestValidate")) {
            toValidate.put(message.getId(), message.getMsg());
        } else if (message.getMsg().startsWith("Done")){
            toValidate.remove(message.getId());
        }
        updateBrowsers();

    }

    private void updateBrowsers() {
        System.err.println("RealTimeEventBroker#updateBrowsers...msg");
        for (BrowserWindow browserWindow : browsers) {
            try {
                String rez = "";
                rez = toValidate.values().stream().collect(Collectors.joining("<BR>"));
                browserWindow.sendAndCommit(rez);
            } finally {
                browsers.remove(browserWindow);
            }
        }
    }

}
