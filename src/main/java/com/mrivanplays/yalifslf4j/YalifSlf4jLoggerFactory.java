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
package com.mrivanplays.yalifslf4j;

import com.mrivanplays.yalifslf4j.config.YalifLogFormat;
import com.mrivanplays.yalifslf4j.config.YalifLogFormatBase;
import com.mrivanplays.yalifslf4j.config.YalifSlf4jConfig;
import com.mrivanplays.yalifslf4j.utils.YalifPrintStreams;
import java.io.File;
import java.io.InputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.EnumSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.event.Level;

public class YalifSlf4jLoggerFactory implements ILoggerFactory {

  private Map<String, Logger> loggerMap;
  private YalifLogFormatBase logFormat;
  private YalifLogFormatBase fileLogFormat;
  private EnumSet<Level> disabledLevels;

  public YalifSlf4jLoggerFactory() {
    loggerMap = new ConcurrentHashMap<>();
    InputStream configStream =
        AccessController.doPrivileged(
            (PrivilegedAction<InputStream>)
                () -> {
                  ClassLoader threadCL = Thread.currentThread().getContextClassLoader();
                  if (threadCL != null) {
                    return threadCL.getResourceAsStream("yalif.json");
                  } else {
                    return ClassLoader.getSystemResourceAsStream("yalif.json");
                  }
                });
    if (configStream == null) {
      throw new IllegalArgumentException("Config file cannot be found inside jar");
    }
    YalifSlf4jConfig config = new YalifSlf4jConfig(configStream);
    if (config.getFileLoggingDirectory() != null) {
      File fileLoggingDirectory = new File(config.getFileLoggingDirectory());
      fileLoggingDirectory.mkdirs();
      if (!fileLoggingDirectory.isDirectory()) {
        throw new IllegalArgumentException(
            String.format("%s is not a directory", config.getFileLoggingDirectory()));
      }
      YalifSlf4jLogFile.init(fileLoggingDirectory);
    }
    if (config.getLoggingFormat() == null) {
      throw new IllegalArgumentException("Logging format cannot be null");
    }
    logFormat = new YalifLogFormatBase(config.getLoggingFormat(), config.getTimeDateFormat());
    fileLogFormat = new YalifLogFormatBase(config.getFileLogFormat(), config.getTimeDateFormat());
    YalifPrintStreams.initialize(logFormat);
    disabledLevels = config.getDisabledLevels();
  }

  public YalifSlf4jLoggerFactory(YalifLogFormatBase logFormat) { // testing purposes
    YalifPrintStreams.initialize(logFormat);
    loggerMap = new ConcurrentHashMap<>();
    this.fileLogFormat = logFormat;
    this.logFormat = logFormat;
    this.disabledLevels = null;
  }

  @Override
  public Logger getLogger(String s) {
    Logger logger = loggerMap.get(s);
    if (logger != null) {
      return logger;
    } else {
      Logger newInstance =
          new YalifSlf4jLogger(
              s,
              new YalifLogFormat(logFormat, s),
              new YalifLogFormat(fileLogFormat, s),
              disabledLevels);
      Logger oldInstance = loggerMap.putIfAbsent(s, newInstance);
      return oldInstance == null ? newInstance : oldInstance;
    }
  }
}
