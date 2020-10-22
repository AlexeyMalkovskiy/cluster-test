package ru.am.clustertest.session.entity;

import java.io.Serializable;
import java.net.InetAddress;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@SessionScoped
public class SessionData implements Serializable {
    private static final long serialVersionUID = -4344943071237394635L;
    
    private String appVersion;
    private String sessionId;
    private String initialServerName;
    private String initialIp;
    private String currServerName;
    private String currentIp;
    private String title;
    private String data;
    private String buildTime;
    
    @Inject
    public void init(@ConfigProperty(name = "server.name") String serverName,
                     @ConfigProperty(name = "build-number") String appVer,
                     @ConfigProperty(name = "build-time") String bldTime) throws Exception {
        this.initialServerName = serverName;
        this.initialIp = InetAddress.getLocalHost().getHostAddress();
        this.appVersion = appVer;
        this.buildTime = bldTime;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getInitialServerName() {
        return initialServerName;
    }

    public void setInitialServerName(String initialServerName) {
        this.initialServerName = initialServerName;
    }

    public String getInitialIp() {
        return initialIp;
    }

    public void setInitialIp(String initialIp) {
        this.initialIp = initialIp;
    }

    public String getCurrServerName() {
        return currServerName;
    }

    public void setCurrServerName(String currServerName) {
        this.currServerName = currServerName;
    }

    public String getCurrentIp() {
        return currentIp;
    }

    public void setCurrentIp(String currentIp) {
        this.currentIp = currentIp;
    }
    
    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(String buildTime) {
        this.buildTime = buildTime;
    }

    public SessionData unwrap() {
        SessionData copy = new SessionData();
        copy.setAppVersion(appVersion);
        copy.setSessionId(sessionId);
        copy.setInitialServerName(initialServerName);
        copy.setInitialIp(initialIp);
        copy.setCurrServerName(currServerName);
        copy.setCurrentIp(currentIp);
        copy.setTitle(title);
        copy.setData(data);
        copy.setBuildTime(buildTime);
        return copy;
    }
}
