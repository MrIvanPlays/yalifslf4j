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
import java.util.List;
import org.slf4j.event.Level;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

public class YalifSlf4jLogger extends MarkerIgnoringBase {

  private List<Level> disabledLevels;
  private YalifLogFormat logFormatFormatter;

  public YalifSlf4jLogger(YalifLogFormat format, List<Level> disabledLevels) {
    logFormatFormatter = format;
    this.disabledLevels = disabledLevels;
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
      System.out.println(format);
      YalifSlf4jLogFile.add(format);
    } else {
      String format = logFormatFormatter.getFormattedMessage(level, formattedMesage);
      System.out.println(format);
      YalifSlf4jLogFile.add(format);
    }
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
}
