// Copyright 2000-2017 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.java.decompiler.main.collectors;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class VarNamesCollector {

  private static final Set<String> reserved = new HashSet<>(Arrays.asList("byte", "char", "double", "float", "int", "long", "short", "boolean", "class"));
  private final Set<String> usedNames = new HashSet<>();

  public VarNamesCollector() { }

  public VarNamesCollector(Collection<String> setNames) {
    usedNames.addAll(setNames);
  }

  public void addName(String value) {
    usedNames.add(value);
  }

  public boolean isNameAvailable(String name) {
    return !reserved.contains(name) && !usedNames.contains(name);
  }

  public String getFreeName(int index) {
    return getFreeName("var" + index);
  }

  public String getFreeName(String proposition) {
    String original = proposition;
    int index = 1;
    while (reserved.contains(proposition) || usedNames.contains(proposition)) {
//      proposition += "x";
      proposition = original + index;
      index++;
    }
    usedNames.add(proposition);
    return proposition;
  }
}
