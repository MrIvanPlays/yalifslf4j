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
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.event.Level;

public class YalifLogFormatBase {

  String format;
  ZoneId timeTimeZone;
  String dateTimeFormat;
  String loggerName;

  public YalifLogFormatBase(String format, ZoneId timeTimeZone) {
    this.format = format;
    this.timeTimeZone = timeTimeZone;
    Pattern pattern = Pattern.compile("\\{.*?\\}");
    Matcher matcher = pattern.matcher(format);
    if (matcher.find()) {
      this.dateTimeFormat = matcher.group().subSequence(1, matcher.group().length() - 1).toString();
    }
  }

  public String getFormattedMessage(Level level, String message) {
    String replacements =
        format
            .replace("%level", level.name())
            .replace("%msg", message)
            .replace("%n", "")
            .replace("%threadName", Thread.currentThread().getName());
    if (dateTimeFormat != null) {
      replacements =
          replacements.replace(
              "{" + dateTimeFormat + "}",
              LocalDateTime.now(timeTimeZone).format(DateTimeFormatter.ofPattern(dateTimeFormat)));
    }
    if (loggerName != null) {
      replacements = replacements.replace("%loggerName", loggerName);
    }
    return replacements;
  }

  public String getFormattedMessage(Level level, String message, Throwable error) {
    String currentFormat =
        format
                .replace("%level", level.name())
                .replace("%msg", message)
                .replace("%threadName", Thread.currentThread().getName())
                .replace("%n", "")
            + "\n"
            + exception(error);
    if (dateTimeFormat != null) {
      currentFormat =
          currentFormat.replace(
              "\\{" + dateTimeFormat + "\\}",
              LocalDateTime.now(timeTimeZone).format(DateTimeFormatter.ofPattern(dateTimeFormat)));
    }
    if (loggerName != null) {
      currentFormat = currentFormat.replace("%loggerName", loggerName);
    }
    return currentFormat;
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
