package com.mrivanplays.yalifslf4j.config.reader;

import java.util.List;

public interface YalifJsonObject {

  List<String> getStringJsonArrayAsList(String member);

  String getString(String member);
}
