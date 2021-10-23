package org.billing.accountingpoints.utils;

import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

public class FileContentUtls {
  private FileContentUtls() {}

  public static String contentAsString(String resource) {
    try {
      return IOUtils.toString(new ClassPathResource(resource).getInputStream(), StandardCharsets.UTF_8);
    } catch (Exception exception) {
      System.out.println(exception.getMessage());
      return "";
    }
  }
}
