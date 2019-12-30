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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.event.Level;

public class YalifLogFormatBase {

  String format;
  String dateTimeFormat;
  String loggerName;

  public YalifLogFormatBase(String format, String dateTimeFormat) {
    this.format = format;
    this.dateTimeFormat = dateTimeFormat;
  }

  public String getFormattedMessage(Level level, String message) {
    String replacements =
        format.replace("%level", level.name()).replace("%msg", message).replace("%n", "");
    if (dateTimeFormat != null) {
      replacements =
          replacements.replace(
              dateTimeFormat,
              LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateTimeFormat)));
    }
    if (loggerName != null) {
      replacements = replacements.replace("%loggerName", loggerName);
    }
    return replacements;
  }

  public String getFormattedMessage(Level level, String message, Throwable error) {
    return format
            .replace("%level", level.name())
            .replace("%msg", message)
            .replace("%loggerName", loggerName)
            .replace("%n", "")
        + "\n"
        + exception(error);
  }

  private String exception(Throwable exception) {
    StringBuilder toWrite = new StringBuilder();
    toWrite.append(exception.toString()).append('\n').append(getStackTraceElement(exception));
    if (exception.getCause() != null) {
      toWrite
          .append("Caused by: ")
          .append(exception.getCause().toString())
          .append('\n')
          .append(getStackTraceElement(exception.getCause()));
    }
    return toWrite.toString();
  }

  private String getStackTraceElement(Throwable t) {
    StringBuilder toWrite = new StringBuilder();
    for (StackTraceElement element : t.getStackTrace()) {
      toWrite.append("     ").append("at").append(' ').append(element.toString()).append('\n');
    }
    return toWrite.toString();
  }
}
