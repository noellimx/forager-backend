package com.noellimx.main.test.utils;

import java.util.Random;

public class SerialGenerator {


  private Random ran = new Random();


  public Integer next() {
    return ran.nextInt() & Integer.MAX_VALUE;
  }

}
