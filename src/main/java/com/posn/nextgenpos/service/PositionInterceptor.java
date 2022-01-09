package com.posn.nextgenpos.service;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
public class PositionInterceptor {
    @Inject
    Event<PositionEvent> message;

    @AroundInvoke
    public Object notifyBrowser(InvocationContext ctx) {
        System.out.println("PositionInterceptor Start");
        String methName = ctx.getMethod().getName();
        int ok=-1;
        try {
            ok = (int) ctx.proceed();
        } catch (Exception e) {
            return null;
        }
        if (ok!=-1) {
            
            String msgEvt;
            PositionEvent e = null;
            Object[] parameters = ctx.getParameters();
            if (methName.equals("createUser")) {
                String id = String.valueOf(ok);
                String name = parameters[0].toString();
                System.out.println("PositionInterceptor #ID =" + id + " Name=" + name);
                //HTML link: <a href="url">link text</a>
                String url = "http://localhost:8080/POS/validare?id=" + id;
                String htmlLink = "<a href=\"" + url + "\"target=\"bbb\">" + name + "</a>";

                msgEvt = "New User:" + htmlLink;
                e = new PositionEvent(id, msgEvt);

            } else if(methName.equals("validateAccount")) {//methName.equals("validate")
                String id = String.valueOf(ok);
                msgEvt = "Done:" + id;
                e = new PositionEvent(id, msgEvt);
            }

            message.fire(e);
        }
        return ok;
    }
}
