package com.mrivanplays.yalifslf4j.cfgprovider.jackson;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.util.ArrayList;
import java.util.List;

public class JacksonJsonObject implements YalifJsonObject {

  private ObjectNode parent;

  public JacksonJsonObject(ObjectNode parent) {
    this.parent = parent;
  }

  @Override
  public List<String> getStringJsonArrayAsList(String member) {
    ArrayNode array = (ArrayNode) parent.get(member);
    List<String> list = new ArrayList<>();
    for (JsonNode node : array) {
      list.add(node.asText());
    }
    return list;
  }

  @Override
  public String getString(String member) {
    JsonNode node = parent.get(member);
    return node == null ? null : node.asText();
  }
}
