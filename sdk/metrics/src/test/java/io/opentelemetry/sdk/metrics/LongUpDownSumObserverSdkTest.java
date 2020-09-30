/*
 * Copyright 2020, OpenTelemetry Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.opentelemetry.sdk.metrics;

import static io.opentelemetry.common.AttributesKeys.stringKey;
import static org.assertj.core.api.Assertions.assertThat;

import io.opentelemetry.common.Attributes;
import io.opentelemetry.common.Labels;
import io.opentelemetry.sdk.common.InstrumentationLibraryInfo;
import io.opentelemetry.sdk.internal.TestClock;
import io.opentelemetry.sdk.metrics.data.MetricData;
import io.opentelemetry.sdk.metrics.data.MetricData.LongPoint;
import io.opentelemetry.sdk.resources.Resource;
import java.util.Collections;
import org.junit.jupiter.api.Test;

/** Unit tests for {@link LongUpDownSumObserverSdk}. */
class LongUpDownSumObserverSdkTest {
  private static final long SECOND_NANOS = 1_000_000_000;
  private static final Resource RESOURCE =
      Resource.create(Attributes.of(stringKey("resource_key"), "resource_value"));
  private static final InstrumentationLibraryInfo INSTRUMENTATION_LIBRARY_INFO =
      InstrumentationLibraryInfo.create(
          "io.opentelemetry.sdk.metrics.LongUpDownSumObserverSdkTest", null);
  private final TestClock testClock = TestClock.create();
  private final MeterProviderSharedState meterProviderSharedState =
      MeterProviderSharedState.create(testClock, RESOURCE);
  private final MeterSdk testSdk =
      new MeterSdk(meterProviderSharedState, INSTRUMENTATION_LIBRARY_INFO, new ViewRegistry());

  @Test
  void collectMetrics_NoCallback() {
    LongUpDownSumObserverSdk longUpDownSumObserver =
        testSdk
            .longUpDownSumObserverBuilder("testObserver")
            .setDescription("My own LongUpDownSumObserver")
            .setUnit("ms")
            .build();
    assertThat(longUpDownSumObserver.collectAll()).isEmpty();
  }

  @Test
  void collectMetrics_NoRecords() {
    LongUpDownSumObserverSdk longUpDownSumObserver =
        testSdk
            .longUpDownSumObserverBuilder("testObserver")
            .setDescription("My own LongUpDownSumObserver")
            .setUnit("ms")
            .build();
    longUpDownSumObserver.setCallback(
        result -> {
          // Do nothing.
        });
    assertThat(longUpDownSumObserver.collectAll())
        .containsExactly(
            MetricData.create(
                RESOURCE,
                INSTRUMENTATION_LIBRARY_INFO,
                "testObserver",
                "My own LongUpDownSumObserver",
                "ms",
                MetricData.Type.NON_MONOTONIC_LONG,
                Collections.emptyList()));
  }

  @Test
  void collectMetrics_WithOneRecord() {
    LongUpDownSumObserverSdk longUpDownSumObserver =
        testSdk.longUpDownSumObserverBuilder("testObserver").build();
    longUpDownSumObserver.setCallback(result -> result.observe(12, Labels.of("k", "v")));
    testClock.advanceNanos(SECOND_NANOS);
    assertThat(longUpDownSumObserver.collectAll())
        .containsExactly(
            MetricData.create(
                RESOURCE,
                INSTRUMENTATION_LIBRARY_INFO,
                "testObserver",
                "",
                "1",
                MetricData.Type.NON_MONOTONIC_LONG,
                Collections.singletonList(
                    LongPoint.create(
                        testClock.now() - SECOND_NANOS,
                        testClock.now(),
                        Labels.of("k", "v"),
                        12))));
    testClock.advanceNanos(SECOND_NANOS);
    assertThat(longUpDownSumObserver.collectAll())
        .containsExactly(
            MetricData.create(
                RESOURCE,
                INSTRUMENTATION_LIBRARY_INFO,
                "testObserver",
                "",
                "1",
                MetricData.Type.NON_MONOTONIC_LONG,
                Collections.singletonList(
                    LongPoint.create(
                        testClock.now() - 2 * SECOND_NANOS,
                        testClock.now(),
                        Labels.of("k", "v"),
                        12))));
  }
}
