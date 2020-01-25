package com.mrivanplays.yalifslf4j.config.reader;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class YalifConfigReaderProvider {

  private static YalifConfigReader PROVIDER;

  private static List<YalifConfigReader> findServices() {
    ServiceLoader<YalifConfigReader> serviceLoader = ServiceLoader.load(YalifConfigReader.class);
    List<YalifConfigReader> providerList = new ArrayList<>();
    for (YalifConfigReader provider : serviceLoader) {
      providerList.add(provider);
    }
    return providerList;
  }

  private static void bind() {
    List<YalifConfigReader> services = findServices();
    if (services.isEmpty()) {
      throw new IllegalArgumentException("Cannot find services for config reader provider!");
    }
    YalifConfigReader firstProvider = services.get(0);
    if (services.size() != 1) {
      System.out.println("Found more than 1 yalifslf4j config reader providers!");
      for (YalifConfigReader reader : services) {
        System.out.println("Provider: [" + reader + "]");
      }
      System.out.println("The first one will be used, which is: " + firstProvider);
    }
    YalifConfigReaderProvider.PROVIDER = firstProvider;
  }

  public static YalifConfigReader getInstance() {
    if (PROVIDER == null) {
      bind();
    }
    return PROVIDER;
  }
}
