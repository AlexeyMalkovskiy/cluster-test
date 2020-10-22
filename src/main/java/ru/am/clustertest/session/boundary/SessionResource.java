package ru.am.clustertest.session.boundary;

import java.net.InetAddress;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import ru.am.clustertest.session.entity.SessionData;

@Path("session")
@Produces(MediaType.APPLICATION_JSON)
public class SessionResource {
    
    @Inject
    SessionData data;
    
    @Inject
    @ConfigProperty(name = "server.name")
    String serverName;
    
    @Inject
    @ConfigProperty(name = "app.title")
    String title;
    
    @Path("")
    @GET
    public SessionData getSessionData(@Context HttpServletRequest req) throws Exception {
        String currentIp = InetAddress.getLocalHost().getHostAddress();
        data.setCurrServerName(serverName);
        data.setCurrentIp(currentIp);
        data.setSessionId(req.getSession().getId());
        data.setTitle(title);
        return data.unwrap();
    }
    
    @Path("")
    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateValue(String value) {
        data.setData(value);
        return Response.ok().build();
    }
    
    @Path("")
    @DELETE
    public Response invalidateSession(@Context HttpServletRequest req) {
        req.getSession().invalidate();
        return Response.ok().build();
    }
}
