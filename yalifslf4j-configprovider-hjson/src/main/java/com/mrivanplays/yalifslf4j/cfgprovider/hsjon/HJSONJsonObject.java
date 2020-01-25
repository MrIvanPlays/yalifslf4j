package com.mrivanplays.yalifslf4j.cfgprovider.hsjon;

import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.util.ArrayList;
import java.util.List;
import org.hjson.JsonArray;
import org.hjson.JsonObject;
import org.hjson.JsonValue;

public class HJSONJsonObject implements YalifJsonObject {

  private JsonObject parent;

  public HJSONJsonObject(JsonObject parent) {
    this.parent = parent;
  }

  @Override
  public List<String> getStringJsonArrayAsList(String member) {
    List<String> list = new ArrayList<>();
    JsonArray array = parent.get(member).asArray();
    for (JsonValue value : array) {
      list.add(value.asString());
    }
    return list;
  }

  @Override
  public String getString(String member) {
    return parent.getString(member, null);
  }
}
