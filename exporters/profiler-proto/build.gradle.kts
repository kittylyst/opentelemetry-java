plugins {
  id("otel.protobuf-conventions")
  id("otel.publish-conventions")

  id("otel.animalsniffer-conventions")
}

description = "OpenTelemetry - Profiler Exporter Proto (Experimental Use Only)"
otelJava.moduleName.set("io.opentelemetry.exporter.profiler.proto")

dependencies {
  api("com.google.protobuf:protobuf-java")

  compileOnly("io.grpc:grpc-api")
  compileOnly("io.grpc:grpc-protobuf")
  compileOnly("io.grpc:grpc-stub")
}
