/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.api.profiler;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.context.Context;
import javax.annotation.concurrent.ThreadSafe;

/** A flamegraph instrument that records execution samples. */
@ThreadSafe
public interface Flamegraph {
  /**
   * Records a sample.
   *
   * <p>Note: This may use {@code Context.current()} to pull the context associated with this
   * measurement.
   *
   * @param sample The sample.
   */
  void record(JvmStackTrace sample);

  /**
   * Records a sample.
   *
   * <p>Note: This may use {@code Context.current()} to pull the context associated with this
   * measurement.
   *
   * @param sample The sample.
   * @param attributes A set of attributes to associate with the count.
   */
  void record(JvmStackTrace sample, Attributes attributes);

  /**
   * Records a sample.
   *
   * @param sample The sample.
   * @param attributes A set of attributes to associate with the count.
   * @param context The explicit context to associate with this measurement.
   */
  void record(JvmStackTrace sample, Attributes attributes, Context context);

  /**
   * Construct a bound version of this flamegraph where all recorded values use the given
   * attributes.
   */
  BoundFlamegraph bind(Attributes attributes);
}
