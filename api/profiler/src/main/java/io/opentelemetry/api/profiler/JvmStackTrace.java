package io.opentelemetry.api.profiler;

import java.util.Collections;
import java.util.List;

public final class JvmStackTrace {
  private final String threadName;
  private final String threadState;
  private final List<JvmStackFrame> frames;

  public JvmStackTrace(String threadName, String threadState, List<JvmStackFrame> frames) {
    this.threadName = threadName;
    this.threadState = threadState;
    this.frames = Collections.unmodifiableList(frames);
  }

  public static final class JvmStackFrame {
    private final String desc;
    private final int line;
    private final int bytecodeIndex;

    public JvmStackFrame(String desc, int line, int bytecodeIndex) {
      this.desc = desc;
      this.line = line;
      this.bytecodeIndex = bytecodeIndex;
    }
  }

}
