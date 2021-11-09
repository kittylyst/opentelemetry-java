/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.profiler;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.profiler.BoundFlamegraph;
import io.opentelemetry.api.profiler.Flamegraph;
import io.opentelemetry.api.profiler.JvmStackTrace;
import io.opentelemetry.context.Context;

public class SdkFlamegraph implements Flamegraph {
  @Override
  public void record(JvmStackTrace sample) {}

  @Override
  public void record(JvmStackTrace sample, Attributes attributes) {}

  @Override
  public void record(JvmStackTrace sample, Attributes attributes, Context context) {}

  @Override
  public BoundFlamegraph bind(Attributes attributes) {
    return null;
  }
}
