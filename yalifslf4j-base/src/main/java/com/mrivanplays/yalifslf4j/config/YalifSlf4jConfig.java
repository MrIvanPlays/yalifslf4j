/*
    Copyright (c) 2019 Ivan Pekov
    Copyright (c) 2019 Contributors

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
*/
package com.mrivanplays.yalifslf4j.config;

import com.mrivanplays.yalifslf4j.config.reader.YalifConfigReader;
import com.mrivanplays.yalifslf4j.config.reader.YalifConfigReaderProvider;
import com.mrivanplays.yalifslf4j.config.reader.YalifJsonObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.EnumSet;
import java.util.List;
import org.slf4j.event.Level;

public class YalifSlf4jConfig {

  private String loggingFormat;
  private String fileLoggingDirectory;
  private String timeTimeZone;
  private String fileLogFormat;
  private EnumSet<Level> disabledLevels;

  public YalifSlf4jConfig(InputStream in) {
    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
      YalifConfigReader configReader = YalifConfigReaderProvider.getInstance();
      YalifJsonObject object = configReader.read(reader);
      loggingFormat = object.getString("loggingFormat");
      fileLoggingDirectory = object.getString("logFileDirectory");
      timeTimeZone = object.getString("timeTimeZone");
      fileLogFormat = object.getString("fileLogFormat");
      List<String> disabledLevelsArray = object.getStringJsonArrayAsList("disabledLevels");
      if (disabledLevelsArray != null) {
        disabledLevels = EnumSet.noneOf(Level.class);
        for (String level : disabledLevelsArray) {
          try {
            Level slf4jLevel = Level.valueOf(level.toUpperCase());
            disabledLevels.add(slf4jLevel);
          } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid level '" + level.toUpperCase() + "'");
          }
        }
      }
    } catch (IOException ignored) {
    }
  }

  public String getLoggingFormat() {
    return loggingFormat;
  }

  public String getFileLoggingDirectory() {
    return fileLoggingDirectory;
  }

  public String getTimeTimeZone() {
    return timeTimeZone;
  }

  public EnumSet<Level> getDisabledLevels() {
    return disabledLevels;
  }

  public String getFileLogFormat() {
    return fileLogFormat;
  }
}
