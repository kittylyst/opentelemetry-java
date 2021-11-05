/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.api.profiler;

import io.opentelemetry.context.Context;
import javax.annotation.concurrent.ThreadSafe;

/** A flamegraph instrument that records execution samples with pre-associated attributes. */
@ThreadSafe
public interface BoundFlamegraph {
  /**
   * Records a sample with a pre-bound set of attributes.
   *
   * <p>Note: This may use {@code Context.current()} to pull the context associated with this
   * measurement.
   *
   * @param sample The sample.
   */
  void record(JvmStackTrace sample);

  /**
   * Records a sample with a pre-bound set of attributes.
   *
   * @param sample The sample.
   * @param context The explicit context to associate with this measurement.
   */
  void record(JvmStackTrace sample, Context context);
}
