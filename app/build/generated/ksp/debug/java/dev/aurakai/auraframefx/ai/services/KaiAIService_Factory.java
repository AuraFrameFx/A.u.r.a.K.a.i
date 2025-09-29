package dev.aurakai.auraframefx.ai.services;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.ai.context.ContextManager;
import dev.aurakai.auraframefx.ai.error.ErrorHandler;
import dev.aurakai.auraframefx.ai.memory.MemoryManager;
import dev.aurakai.auraframefx.ai.task.TaskScheduler;
import dev.aurakai.auraframefx.ai.task.execution.TaskExecutionManager;
import dev.aurakai.auraframefx.data.logging.AuraFxLogger;
import dev.aurakai.auraframefx.data.network.CloudStatusMonitor;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class KaiAIService_Factory implements Factory<KaiAIService> {
  private final Provider<TaskScheduler> taskSchedulerProvider;

  private final Provider<TaskExecutionManager> taskExecutionManagerProvider;

  private final Provider<MemoryManager> memoryManagerProvider;

  private final Provider<ErrorHandler> errorHandlerProvider;

  private final Provider<ContextManager> contextManagerProvider;

  private final Provider<Context> applicationContextProvider;

  private final Provider<CloudStatusMonitor> cloudStatusMonitorProvider;

  private final Provider<AuraFxLogger> auraFxLoggerProvider;

  private KaiAIService_Factory(Provider<TaskScheduler> taskSchedulerProvider,
      Provider<TaskExecutionManager> taskExecutionManagerProvider,
      Provider<MemoryManager> memoryManagerProvider, Provider<ErrorHandler> errorHandlerProvider,
      Provider<ContextManager> contextManagerProvider, Provider<Context> applicationContextProvider,
      Provider<CloudStatusMonitor> cloudStatusMonitorProvider,
      Provider<AuraFxLogger> auraFxLoggerProvider) {
    this.taskSchedulerProvider = taskSchedulerProvider;
    this.taskExecutionManagerProvider = taskExecutionManagerProvider;
    this.memoryManagerProvider = memoryManagerProvider;
    this.errorHandlerProvider = errorHandlerProvider;
    this.contextManagerProvider = contextManagerProvider;
    this.applicationContextProvider = applicationContextProvider;
    this.cloudStatusMonitorProvider = cloudStatusMonitorProvider;
    this.auraFxLoggerProvider = auraFxLoggerProvider;
  }

  @Override
  public KaiAIService get() {
    return newInstance(taskSchedulerProvider.get(), taskExecutionManagerProvider.get(), memoryManagerProvider.get(), errorHandlerProvider.get(), contextManagerProvider.get(), applicationContextProvider.get(), cloudStatusMonitorProvider.get(), auraFxLoggerProvider.get());
  }

  public static KaiAIService_Factory create(Provider<TaskScheduler> taskSchedulerProvider,
      Provider<TaskExecutionManager> taskExecutionManagerProvider,
      Provider<MemoryManager> memoryManagerProvider, Provider<ErrorHandler> errorHandlerProvider,
      Provider<ContextManager> contextManagerProvider, Provider<Context> applicationContextProvider,
      Provider<CloudStatusMonitor> cloudStatusMonitorProvider,
      Provider<AuraFxLogger> auraFxLoggerProvider) {
    return new KaiAIService_Factory(taskSchedulerProvider, taskExecutionManagerProvider, memoryManagerProvider, errorHandlerProvider, contextManagerProvider, applicationContextProvider, cloudStatusMonitorProvider, auraFxLoggerProvider);
  }

  public static KaiAIService newInstance(TaskScheduler taskScheduler,
      TaskExecutionManager taskExecutionManager, MemoryManager memoryManager,
      ErrorHandler errorHandler, ContextManager contextManager, Context applicationContext,
      CloudStatusMonitor cloudStatusMonitor, AuraFxLogger auraFxLogger) {
    return new KaiAIService(taskScheduler, taskExecutionManager, memoryManager, errorHandler, contextManager, applicationContext, cloudStatusMonitor, auraFxLogger);
  }
}
