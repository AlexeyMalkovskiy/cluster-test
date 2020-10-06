package ru.am.clustertest.buildinfo.boundary;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Manifest;

import org.eclipse.microprofile.config.spi.ConfigSource;

public class ManifestConfigSource implements ConfigSource {

    Map<String, String> manifestEntries = new HashMap<>();

    public ManifestConfigSource() {
        var path = this.getClass().getResource("ManifestConfigSource.class").toString();
        int idx = path.indexOf("WEB-INF");
        String manifestPath = path.substring(0, idx) + "META-INF/MANIFEST.MF";
        try (InputStream manifestIn = new URL(manifestPath).openStream()) {
            Manifest manifest = new Manifest(manifestIn);
            manifest.getMainAttributes().entrySet().forEach(e -> 
                manifestEntries.put(e.getKey().toString(), e.getValue().toString()));
        } catch (IOException e) {
            // ignore
        }
        setDefaultValues();
    }

	private void setDefaultValues() {
		String build = manifestEntries.get("build-number");
        if (build == null || build.isBlank()) {
        	manifestEntries.remove("build-number");
        }
        String branch = manifestEntries.get("branch");
        if (branch == null || branch.isBlank()) {
        	manifestEntries.put("branch", "unknown");
        }
	}

    @Override
    public Map<String, String> getProperties() {
        return manifestEntries;
    }

    @Override
    public String getValue(String propertyName) {
        return manifestEntries.get(propertyName);
    }

    @Override
    public String getName() {
        return "MANIFEST.MF properties";
    }

    @Override
    public int getOrdinal() {
        return 110;
    }
}
