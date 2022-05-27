package org.billing.api.app.cucumber.step;

import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;

public class DataType {

  @DataTableType(replaceWithEmptyString = "[blank]")
  public String stringType(String cell) {
    return cell;
  }

  @ParameterType("uuid")
  public String color(String cell) {
    return cell;
  }
}
