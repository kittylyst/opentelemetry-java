/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.profiler;

import io.opentelemetry.api.profiler.ExecutionProfile;
import io.opentelemetry.api.profiler.FlamegraphBuilder;

public class SdkExecutionProfile implements ExecutionProfile {

  @Override
  public FlamegraphBuilder flamegraphBuilder(String name) {
    return new SdkFlamegraphBuilder();
  }
}
