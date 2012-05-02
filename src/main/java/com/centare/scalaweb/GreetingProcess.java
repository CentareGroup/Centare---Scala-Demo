package com.centare.scalaweb;

import com.example.scalawebapp.util.ScalaHelp;
public class GreetingProcess {
  
  public String buildGreeting(String message) {
    return ScalaHelp.capitalizeAll(message);
  }

}
