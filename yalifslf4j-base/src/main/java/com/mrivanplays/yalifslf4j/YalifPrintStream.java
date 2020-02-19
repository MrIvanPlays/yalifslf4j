package com.mrivanplays.yalifslf4j;

import com.mrivanplays.yalifslf4j.config.YalifLogFormat;
import com.mrivanplays.yalifslf4j.config.YalifLogFormatBase;
import java.io.OutputStream;
import java.io.PrintStream;
import org.slf4j.event.Level;

public class YalifPrintStream extends PrintStream {

  private YalifLogFormatBase logFormat;
  private Level level;

  public YalifPrintStream(OutputStream out, YalifLogFormatBase logFormat, Level level) {
    super(out);
    this.logFormat = logFormat;
    this.level = level;
  }

  @Override
  public void print(String s) {
    if (s.contains("formatted")) {
      super.print(s.replace("formatted", ""));
      return;
    }
    String formatted = logFormat.getFormattedMessage(level, s);
    YalifSlf4jLogFile.add(formatted);
    super.print(formatted);
  }

  @Override
  public void print(Object obj) {
    print(String.valueOf(obj));
  }

  public void println(String s, YalifLogFormat loggerLogFormat, YalifLogFormat fileLogFormat) {
    String formatted = loggerLogFormat.getFormattedMessage(level, s);
    YalifSlf4jLogFile.add(fileLogFormat.getFormattedMessage(level, s));
    String characterizedString = formatted + "formatted";
    super.println(characterizedString);
  }

  @Override
  public void print(char[] s) {
    print(String.valueOf(s));
  }
}
