/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.exporter.otlp.profiler;

import com.google.perftools.profiles.ProfileProto;
import io.opentelemetry.api.profiler.ExecutionProfile;
import java.util.Collection;
import java.util.Collections;

public final class ExecutionProfileAdapter {
  private ExecutionProfileAdapter() {}

  public static Iterable<ProfileProto.Profile> toProtoResourceMetrics(
      Collection<ExecutionProfile> metrics) {
    return Collections.emptyList();
  }
}
