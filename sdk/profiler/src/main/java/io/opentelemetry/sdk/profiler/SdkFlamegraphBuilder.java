/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.profiler;

import io.opentelemetry.api.profiler.Flamegraph;
import io.opentelemetry.api.profiler.FlamegraphBuilder;

public class SdkFlamegraphBuilder implements FlamegraphBuilder {
  //  private String description;

  @Override
  public FlamegraphBuilder setDescription(String description) {
    //    this.description = description;
    return this;
  }

  @Override
  public Flamegraph build() {
    return null;
  }
}
