package com.mrivanplays.yalifslf4j.cfgprovider.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mrivanplays.yalifslf4j.config.reader.YalifConfigReader;
import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.io.IOException;
import java.io.Reader;

public class JacksonConfigReader implements YalifConfigReader {

  @Override
  public YalifJsonObject read(Reader reader) throws IOException {
    return new JacksonJsonObject((ObjectNode) new ObjectMapper().readTree(reader));
  }

  @Override
  public String toString() {
    return "com.mrivanplays JacksonConfigReader";
  }
}
