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
package com.mrivanplays.yalifslf4j.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.zip.GZIPOutputStream;

public class YalifUtils {

  public static void zip(File file, File logDirectory) {
    String dateFormatted = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    OptionalInt logNumberOptional =
        Arrays.stream(logDirectory.listFiles((a, name) -> name.endsWith(".gz")))
            .mapToInt(
                zipInDir -> {
                  if (!zipInDir.getName().contains(dateFormatted)) {
                    return 0;
                  }
                  return Integer.parseInt(
                      zipInDir
                          .getName()
                          .replace(dateFormatted + "-", "")
                          .replace(".gz", "")
                          .replace(".log", ""));
                })
            .max();
    int logNumber = 1;
    if (logNumberOptional.isPresent()) {
      int logNumberData = logNumberOptional.getAsInt();
      if (logNumberData > 0) {
        logNumber = logNumberData + 1;
      }
    }
    File gzipFile = new File(logDirectory, dateFormatted + "-" + logNumber + ".log.gz");
    try {
      gzipFile.createNewFile();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    try (GZIPOutputStream out = new GZIPOutputStream(new FileOutputStream(gzipFile))) {
      try (FileInputStream in = new FileInputStream(file)) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) > 0) {
          out.write(buffer, 0, bytesRead);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
