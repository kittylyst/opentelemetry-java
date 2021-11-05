/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.api.profiler;

import javax.annotation.concurrent.ThreadSafe;

/**
 * Provides instruments used to produce execution profile instruments.
 *
 * <p>Instruments are obtained through builders provided by this interface. Each builder has a
 * default "type" associated with recordings that may be changed.
 */
@ThreadSafe
public interface ExecutionProfile {

  FlamegraphBuilder flamegraphBuilder(String name);
}
