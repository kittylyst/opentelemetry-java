/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.api.profiler.internal;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.profiler.BoundFlamegraph;
import io.opentelemetry.api.profiler.ExecutionProfile;
import io.opentelemetry.api.profiler.Flamegraph;
import io.opentelemetry.api.profiler.FlamegraphBuilder;
import io.opentelemetry.api.profiler.JvmStackTrace;
import io.opentelemetry.context.Context;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NoopExecutionProfile implements ExecutionProfile {
  private static final NoopExecutionProfile INSTANCE = new NoopExecutionProfile();

  private NoopExecutionProfile() {}

  public static ExecutionProfile getInstance() {
    return INSTANCE;
  }

  @Override
  public FlamegraphBuilder flamegraphBuilder(String name) {
    return new NoopFlamegraphBuilder();
  }

  private static class NoopFlamegraphBuilder implements FlamegraphBuilder {

    @Override
    public FlamegraphBuilder setDescription(String description) {
      return this;
    }

    @Override
    public Flamegraph build() {
      return new NoopFlamegraph();
    }

    private static class NoopFlamegraph implements Flamegraph {
      @Override
      public void record(JvmStackTrace sample) {}

      @Override
      public void record(JvmStackTrace sample, Attributes attributes) {}

      @Override
      public void record(JvmStackTrace sample, Attributes attributes, Context context) {}

      @Override
      public BoundFlamegraph bind(Attributes attributes) {
        return new NoopBoundFlamegraph();
      }

      private static class NoopBoundFlamegraph implements BoundFlamegraph {
        @Override
        public void record(JvmStackTrace sample) {}

        @Override
        public void record(JvmStackTrace sample, Context context) {}
      }
    }
  }
}
