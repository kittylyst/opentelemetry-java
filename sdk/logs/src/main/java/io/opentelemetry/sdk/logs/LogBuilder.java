/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.sdk.logs;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.sdk.logs.data.Body;
import io.opentelemetry.sdk.logs.data.Severity;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Used to construct and emit logs from a {@link LogEmitter}.
 *
 * <p>Obtain a {@link LogBuilder} via {@link LogEmitter#logBuilder()}, add properties using the
 * setters, and emit the log to downstream {@link LogProcessor}(s) by calling {@link #emit()}.
 */
public interface LogBuilder {

  /** Set the epoch timestamp using the timestamp and unit. */
  LogBuilder setEpoch(long timestamp, TimeUnit unit);

  /** Set the epoch timestamp using the instant. */
  LogBuilder setEpoch(Instant instant);

  /** Set the trace id. */
  LogBuilder setTraceId(String traceId);

  /** Set the span id. */
  LogBuilder setSpanId(String spanId);

  /** Set the flags. */
  LogBuilder setFlags(int flags);

  /** Set the severity. */
  LogBuilder setSeverity(Severity severity);

  /** Set the severity text. */
  LogBuilder setSeverityText(String severityText);

  /** Set the name. */
  LogBuilder setName(String name);

  /** Set the body. */
  LogBuilder setBody(Body body);

  /** Set the body string. */
  LogBuilder setBody(String body);

  /** Set the attributes. */
  LogBuilder setAttributes(Attributes attributes);

  /** Emit the log to downstream {@link LogProcessor}(s). */
  void emit();
}
