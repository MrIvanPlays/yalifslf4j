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
import com.mrivanplays.yalifslf4j.utils.YalifPrintStreams;
import java.util.EnumSet;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.slf4j.event.Level;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

// we're ignoring markers cuz they're stupid
public class YalifSlf4jLogger implements Logger {

  private EnumSet<Level> disabledLevels;
  private YalifLogFormat logFormatFormatter;
  private YalifLogFormat fileLogFormat;
  private String name;

  public YalifSlf4jLogger(
      String name,
      YalifLogFormat format,
      YalifLogFormat fileLogFormat,
      EnumSet<Level> disabledLevels) {
    logFormatFormatter = format;
    this.fileLogFormat = fileLogFormat;
    this.disabledLevels = disabledLevels;
    this.name = name;
  }

  private boolean isDisabled(Level level) {
    return disabledLevels != null && disabledLevels.contains(level);
  }

  private void log(Level level, String message, Throwable exception, Object... params) {
    if (isDisabled(level)) {
      return;
    }
    String formattedMesage = message;
    if (params != null) {
      if (!message.isEmpty()) {
        FormattingTuple tp = MessageFormatter.arrayFormat(message, params);
        formattedMesage = tp.getMessage();
      }
    }
    if (exception != null) {
      String format = logFormatFormatter.getFormattedMessage(level, formattedMesage, exception);
      YalifPrintStreams.ERROR.println(format);
      YalifSlf4jLogFile.add(fileLogFormat.getFormattedMessage(level, formattedMesage, exception));
    } else {
      YalifPrintStreams.getPrintStream(level)
          .println(formattedMesage, logFormatFormatter, fileLogFormat);
    }
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean isTraceEnabled() {
    return !isDisabled(Level.TRACE);
  }

  @Override
  public void trace(String s) {
    log(Level.TRACE, s, null);
  }

  @Override
  public void trace(String s, Object o) {
    log(Level.TRACE, s, null, o);
  }

  @Override
  public void trace(String s, Object o, Object o1) {
    log(Level.TRACE, s, null, o, o1);
  }

  @Override
  public void trace(String s, Object... objects) {
    log(Level.TRACE, s, null, objects);
  }

  @Override
  public void trace(String s, Throwable throwable) {
    log(Level.TRACE, s, throwable);
  }

  @Override
  public boolean isTraceEnabled(Marker marker) {
    return isTraceEnabled();
  }

  @Override
  public void trace(Marker marker, String msg) {
    trace(msg);
  }

  @Override
  public void trace(Marker marker, String format, Object arg) {
    trace(format, arg);
  }

  @Override
  public void trace(Marker marker, String format, Object arg1, Object arg2) {
    trace(format, arg1, arg2);
  }

  @Override
  public void trace(Marker marker, String format, Object... argArray) {
    trace(format, argArray);
  }

  @Override
  public void trace(Marker marker, String msg, Throwable t) {
    trace(msg, t);
  }

  @Override
  public boolean isDebugEnabled() {
    return !isDisabled(Level.DEBUG);
  }

  @Override
  public void debug(String s) {
    log(Level.DEBUG, s, null);
  }

  @Override
  public void debug(String s, Object o) {
    log(Level.DEBUG, s, null, o);
  }

  @Override
  public void debug(String s, Object o, Object o1) {
    log(Level.DEBUG, s, null, o, o1);
  }

  @Override
  public void debug(String s, Object... objects) {
    log(Level.DEBUG, s, null, objects);
  }

  @Override
  public void debug(String s, Throwable throwable) {
    log(Level.DEBUG, s, throwable);
  }

  @Override
  public boolean isDebugEnabled(Marker marker) {
    return isDebugEnabled();
  }

  @Override
  public void debug(Marker marker, String msg) {
    debug(msg);
  }

  @Override
  public void debug(Marker marker, String format, Object arg) {
    debug(format, arg);
  }

  @Override
  public void debug(Marker marker, String format, Object arg1, Object arg2) {
    debug(format, arg1, arg2);
  }

  @Override
  public void debug(Marker marker, String format, Object... arguments) {
    debug(format, arguments);
  }

  @Override
  public void debug(Marker marker, String msg, Throwable t) {
    debug(msg, t);
  }

  @Override
  public boolean isInfoEnabled() {
    return !isDisabled(Level.INFO);
  }

  @Override
  public void info(String s) {
    log(Level.INFO, s, null);
  }

  @Override
  public void info(String s, Object o) {
    log(Level.INFO, s, null, o);
  }

  @Override
  public void info(String s, Object o, Object o1) {
    log(Level.INFO, s, null, o, o1);
  }

  @Override
  public void info(String s, Object... objects) {
    log(Level.INFO, s, null, objects);
  }

  @Override
  public void info(String s, Throwable throwable) {
    log(Level.INFO, s, throwable);
  }

  @Override
  public boolean isInfoEnabled(Marker marker) {
    return isInfoEnabled();
  }

  @Override
  public void info(Marker marker, String msg) {
    info(msg);
  }

  @Override
  public void info(Marker marker, String format, Object arg) {
    info(format, arg);
  }

  @Override
  public void info(Marker marker, String format, Object arg1, Object arg2) {
    info(format, arg1, arg2);
  }

  @Override
  public void info(Marker marker, String format, Object... arguments) {
    info(format, arguments);
  }

  @Override
  public void info(Marker marker, String msg, Throwable t) {
    info(msg, t);
  }

  @Override
  public boolean isWarnEnabled() {
    return !isDisabled(Level.WARN);
  }

  @Override
  public void warn(String s) {
    log(Level.WARN, s, null);
  }

  @Override
  public void warn(String s, Object o) {
    log(Level.WARN, s, null, o);
  }

  @Override
  public void warn(String s, Object... objects) {
    log(Level.WARN, s, null, objects);
  }

  @Override
  public void warn(String s, Object o, Object o1) {
    log(Level.WARN, s, null, o, o1);
  }

  @Override
  public void warn(String s, Throwable throwable) {
    log(Level.WARN, s, throwable);
  }

  @Override
  public boolean isWarnEnabled(Marker marker) {
    return isWarnEnabled();
  }

  @Override
  public void warn(Marker marker, String msg) {
    warn(msg);
  }

  @Override
  public void warn(Marker marker, String format, Object arg) {
    warn(format, arg);
  }

  @Override
  public void warn(Marker marker, String format, Object arg1, Object arg2) {
    warn(format, arg1, arg2);
  }

  @Override
  public void warn(Marker marker, String format, Object... arguments) {
    warn(format, arguments);
  }

  @Override
  public void warn(Marker marker, String msg, Throwable t) {
    warn(msg, t);
  }

  @Override
  public boolean isErrorEnabled() {
    return !isDisabled(Level.ERROR);
  }

  @Override
  public void error(String s) {
    log(Level.ERROR, s, null);
  }

  @Override
  public void error(String s, Object o) {
    log(Level.ERROR, s, null, o);
  }

  @Override
  public void error(String s, Object o, Object o1) {
    log(Level.ERROR, s, null, o, o1);
  }

  @Override
  public void error(String s, Object... objects) {
    log(Level.ERROR, s, null, objects);
  }

  @Override
  public void error(String s, Throwable throwable) {
    log(Level.ERROR, s, throwable);
  }

  @Override
  public boolean isErrorEnabled(Marker marker) {
    return isErrorEnabled();
  }

  @Override
  public void error(Marker marker, String msg) {
    error(msg);
  }

  @Override
  public void error(Marker marker, String format, Object arg) {
    error(format, arg);
  }

  @Override
  public void error(Marker marker, String format, Object arg1, Object arg2) {
    error(format, arg1, arg2);
  }

  @Override
  public void error(Marker marker, String format, Object... arguments) {
    error(format, arguments);
  }

  @Override
  public void error(Marker marker, String msg, Throwable t) {
    error(msg, t);
  }
}
