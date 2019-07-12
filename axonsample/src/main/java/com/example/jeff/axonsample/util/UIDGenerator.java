package com.example.jeff.axonsample.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UIDGenerator {

  private SnowFlake flake = new SnowFlake();

  public long getId() {
    flake.setMachineId(new Random().nextInt(10));
    return flake.nextId();
  }
}
