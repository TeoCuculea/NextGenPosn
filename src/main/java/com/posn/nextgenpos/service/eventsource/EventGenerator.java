package com.posn.nextgenpos.service.eventsource;

import com.posn.nextgenpos.service.PositionEvent;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.inject.Inject;

@Startup
@Singleton
public class EventGenerator {

    @Inject
    Event<PositionEvent> message;

    @Schedule(minute="*",second="*/5",hour="*")
    public void sendTime(){

        message.fire(new PositionEvent("-1", "Update"));
        //System.out.println("."+date);
    }
}
