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

import com.mrivanplays.yalifslf4j.utils.YalifUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class YalifSlf4jLogFile {

  private static File logFile;
  private static List<String> toLogFile;

  public static void init(File fileLoggingDirectory) {
    toLogFile = new ArrayList<>();
    logFile = new File(fileLoggingDirectory, "latest.log");
    if (logFile.exists()) {
      YalifUtils.zip(logFile, fileLoggingDirectory);
      logFile.delete();
    }
    try {
      logFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void add(String format) {
    if (toLogFile != null) {
      toLogFile.add(format);
      try (Writer writer = new FileWriter(logFile)) {
        for (String log : toLogFile) {
          writer.write(log);
          writer.append('\n');
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
