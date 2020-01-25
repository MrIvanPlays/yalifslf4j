package com.mrivanplays.yalifslf4j.cfgprovider.orgjson;

import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class OrgJsonJsonObject implements YalifJsonObject {

  private JSONObject parent;

  public OrgJsonJsonObject(JSONObject parent) {
    this.parent = parent;
  }

  @Override
  public List<String> getStringJsonArrayAsList(String member) {
    List<String> list = new ArrayList<>();
    JSONArray array = parent.getJSONArray(member);
    for (int i = 0; i < array.length(); i++) {
      list.add(array.getString(i));
    }
    return list;
  }

  @Override
  public String getString(String member) {
    return parent.getString(member);
  }
}
