/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.profiler;

import io.opentelemetry.api.profiler.BoundFlamegraph;
import io.opentelemetry.api.profiler.JvmStackTrace;
import io.opentelemetry.context.Context;

public class SdkBoundFlamegraph implements BoundFlamegraph {
  @Override
  public void record(JvmStackTrace sample) {}

  @Override
  public void record(JvmStackTrace sample, Context context) {}
}
