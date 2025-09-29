package dev.aurakai.auraframefx.utils;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class ApiErrorHandler_Factory implements Factory<ApiErrorHandler> {
  private final Provider<Context> contextProvider;

  private ApiErrorHandler_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ApiErrorHandler get() {
    return newInstance(contextProvider.get());
  }

  public static ApiErrorHandler_Factory create(Provider<Context> contextProvider) {
    return new ApiErrorHandler_Factory(contextProvider);
  }

  public static ApiErrorHandler newInstance(Context context) {
    return new ApiErrorHandler(context);
  }
}
