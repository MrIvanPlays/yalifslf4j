package com.mrivanplays.yalifslf4j.config.reader;

import java.io.IOException;
import java.io.Reader;

public interface YalifConfigReader {

  YalifJsonObject read(Reader reader) throws IOException;

  @Override
  String toString();
}
