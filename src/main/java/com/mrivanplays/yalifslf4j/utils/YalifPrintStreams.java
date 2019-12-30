package com.mrivanplays.yalifslf4j.utils;

import com.mrivanplays.yalifslf4j.YalifPrintStream;
import com.mrivanplays.yalifslf4j.config.YalifLogFormatBase;
import java.io.PrintStream;
import org.slf4j.event.Level;

public class YalifPrintStreams {

  public static YalifPrintStream INFO;
  public static YalifPrintStream ERROR;
  public static YalifPrintStream TRACE;
  public static YalifPrintStream WARN;
  public static YalifPrintStream DEBUG;
  public static PrintStream DEFAULT_OUT;

  public static void initialize(YalifLogFormatBase logFormat) {
    DEFAULT_OUT = System.out;
    INFO = new YalifPrintStream(DEFAULT_OUT, logFormat, Level.INFO);
    ERROR = new YalifPrintStream(DEFAULT_OUT, logFormat, Level.ERROR);
    TRACE = new YalifPrintStream(DEFAULT_OUT, logFormat, Level.TRACE);
    WARN = new YalifPrintStream(DEFAULT_OUT, logFormat, Level.WARN);
    DEBUG = new YalifPrintStream(DEFAULT_OUT, logFormat, Level.DEBUG);
    System.setOut(INFO);
    System.setErr(ERROR);
  }

  public static YalifPrintStream getPrintStream(Level level) {
    switch (level) {
      case ERROR:
        return ERROR;
      case TRACE:
        return TRACE;
      case WARN:
        return WARN;
      case DEBUG:
        return DEBUG;
      default:
        return INFO;
    }
  }
}
