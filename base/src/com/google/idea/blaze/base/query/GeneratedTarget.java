/*
 * Copyright 2019 The Bazel Authors. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.idea.blaze.base.query;

import com.google.idea.blaze.base.model.primitives.Label;
import java.util.Objects;
import javax.annotation.Nullable;

/** A data class containing information about a blaze target generated by a macro. */
public final class GeneratedTarget {
  public final String ruleType;
  public final Label label;
  public final MacroData macro;

  GeneratedTarget(String ruleType, Label label, MacroData macro) {
    this.ruleType = ruleType;
    this.label = label;
    this.macro = macro;
  }

  @Override
  public String toString() {
    return String.format("%s:%s (%s)", label, macro.lineNumber, ruleType);
  }

  /** Data specifying a macro call in a BUILD file. */
  public static class MacroData {
    /** The 1-indexed line number of the macro within the BUILD file. */
    public final int lineNumber;
    /** The function name. */
    public final String macroFunction;
    /** The 'name' argument, if present. */
    @Nullable public final String name;

    public MacroData(int lineNumber, String macroFunction, @Nullable String name) {
      this.lineNumber = lineNumber;
      this.macroFunction = macroFunction;
      this.name = name;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof MacroData)) {
        return false;
      }
      MacroData other = (MacroData) o;
      return lineNumber == other.lineNumber
          && macroFunction.equals(other.macroFunction)
          && Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
      return Objects.hash(lineNumber, macroFunction, name);
    }
  }
}