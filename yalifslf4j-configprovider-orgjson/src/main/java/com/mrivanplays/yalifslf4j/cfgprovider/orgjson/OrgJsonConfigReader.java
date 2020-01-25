package com.mrivanplays.yalifslf4j.cfgprovider.orgjson;

import com.mrivanplays.yalifslf4j.config.reader.YalifConfigReader;
import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.io.Reader;
import org.json.JSONObject;
import org.json.JSONTokener;

public class OrgJsonConfigReader implements YalifConfigReader {

  @Override
  public YalifJsonObject read(Reader reader) {
    JSONObject object = new JSONObject(new JSONTokener(reader));
    return new OrgJsonJsonObject(object);
  }

  @Override
  public String toString() {
    return "com.mrivanplays OrgJsonConfigReader";
  }
}
