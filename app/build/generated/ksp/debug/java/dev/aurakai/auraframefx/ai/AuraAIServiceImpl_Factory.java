package dev.aurakai.auraframefx.ai;

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
import dev.aurakai.auraframefx.data.network.CloudStatusMonitor;
import dev.aurakai.auraframefx.network.AuraApiService;
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
public final class AuraAIServiceImpl_Factory implements Factory<AuraAIServiceImpl> {
  private final Provider<TaskScheduler> taskSchedulerProvider;

  private final Provider<TaskExecutionManager> taskExecutionManagerProvider;

  private final Provider<MemoryManager> memoryManagerProvider;

  private final Provider<ErrorHandler> errorHandlerProvider;

  private final Provider<ContextManager> contextManagerProvider;

  private final Provider<CloudStatusMonitor> cloudStatusMonitorProvider;

  private final Provider<AuraApiService> apiServiceProvider;

  private AuraAIServiceImpl_Factory(Provider<TaskScheduler> taskSchedulerProvider,
      Provider<TaskExecutionManager> taskExecutionManagerProvider,
      Provider<MemoryManager> memoryManagerProvider, Provider<ErrorHandler> errorHandlerProvider,
      Provider<ContextManager> contextManagerProvider,
      Provider<CloudStatusMonitor> cloudStatusMonitorProvider,
      Provider<AuraApiService> apiServiceProvider) {
    this.taskSchedulerProvider = taskSchedulerProvider;
    this.taskExecutionManagerProvider = taskExecutionManagerProvider;
    this.memoryManagerProvider = memoryManagerProvider;
    this.errorHandlerProvider = errorHandlerProvider;
    this.contextManagerProvider = contextManagerProvider;
    this.cloudStatusMonitorProvider = cloudStatusMonitorProvider;
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public AuraAIServiceImpl get() {
    return newInstance(taskSchedulerProvider.get(), taskExecutionManagerProvider.get(), memoryManagerProvider.get(), errorHandlerProvider.get(), contextManagerProvider.get(), cloudStatusMonitorProvider.get(), apiServiceProvider.get());
  }

  public static AuraAIServiceImpl_Factory create(Provider<TaskScheduler> taskSchedulerProvider,
      Provider<TaskExecutionManager> taskExecutionManagerProvider,
      Provider<MemoryManager> memoryManagerProvider, Provider<ErrorHandler> errorHandlerProvider,
      Provider<ContextManager> contextManagerProvider,
      Provider<CloudStatusMonitor> cloudStatusMonitorProvider,
      Provider<AuraApiService> apiServiceProvider) {
    return new AuraAIServiceImpl_Factory(taskSchedulerProvider, taskExecutionManagerProvider, memoryManagerProvider, errorHandlerProvider, contextManagerProvider, cloudStatusMonitorProvider, apiServiceProvider);
  }

  public static AuraAIServiceImpl newInstance(TaskScheduler taskScheduler,
      TaskExecutionManager taskExecutionManager, MemoryManager memoryManager,
      ErrorHandler errorHandler, ContextManager contextManager,
      CloudStatusMonitor cloudStatusMonitor, AuraApiService apiService) {
    return new AuraAIServiceImpl(taskScheduler, taskExecutionManager, memoryManager, errorHandler, contextManager, cloudStatusMonitor, apiService);
  }
}
