package com.pandong.common.units;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
public class FileUtils {

  public static boolean checkDirectory(String path, boolean isCreate) {
    return checkDirectory(Paths.get(path), isCreate);
  }

  public static boolean checkDirectory(Path path, boolean isCreate) {
    if (Files.exists(path)) {
      return true;
    }
    if (isCreate) {
      Path newPath = null;
      try {
        newPath = Files.createDirectories(path);
      } catch (IOException e) {
        log.error(e.getMessage(), e);
        return false;
      }
      if (Files.exists(newPath)) {
        return true;
      }
    }
    return false;
  }
}
