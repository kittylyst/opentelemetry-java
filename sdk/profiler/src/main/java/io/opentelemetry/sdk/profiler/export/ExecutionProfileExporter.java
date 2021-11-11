/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.profiler.export;

import io.opentelemetry.api.profiler.ExecutionProfile;
import io.opentelemetry.sdk.common.CompletableResultCode;
import java.util.Collection;

/**
 * {@code MetricExporter} is the interface that all "push based" metric libraries should use to
 * export metrics to the OpenTelemetry exporters.
 *
 * <p>All OpenTelemetry exporters should allow access to a {@code MetricExporter} instance.
 */
public interface ExecutionProfileExporter {

  /**
   * Exports the collection of given profile data. Note that export operations can be performed
   * simultaneously depending on the type of metric reader being used.
   *
   * @param metrics the collection of {@link ExecutionProfile} to be exported.
   * @return the result of the export, which is often an asynchronous operation.
   */
  CompletableResultCode export(Collection<ExecutionProfile> metrics);

  /**
   * Exports the collection of profile data that have not yet been exported. Note that flush
   * operations can be performed simultaneously depending on the type of metric reader being used.
   *
   * @return the result of the flush, which is often an asynchronous operation.
   */
  CompletableResultCode flush();

  /**
   * Called when the associated IntervalMetricReader is shutdown.
   *
   * @return a {@link CompletableResultCode} which is completed when shutdown completes.
   */
  CompletableResultCode shutdown();
}
