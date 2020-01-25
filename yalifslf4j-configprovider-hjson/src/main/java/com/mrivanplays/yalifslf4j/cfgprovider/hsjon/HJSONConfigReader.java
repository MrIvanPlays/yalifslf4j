package com.mrivanplays.yalifslf4j.cfgprovider.hsjon;

import com.mrivanplays.yalifslf4j.config.reader.YalifConfigReader;
import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.io.IOException;
import java.io.Reader;
import org.hjson.JsonValue;

public class HJSONConfigReader implements YalifConfigReader {

  @Override
  public YalifJsonObject read(Reader reader) throws IOException {
    return new HJSONJsonObject(JsonValue.readJSON(reader).asObject());
  }

  @Override
  public String toString() {
    return "com.mrivanplays HJSONConfigReader";
  }
}
