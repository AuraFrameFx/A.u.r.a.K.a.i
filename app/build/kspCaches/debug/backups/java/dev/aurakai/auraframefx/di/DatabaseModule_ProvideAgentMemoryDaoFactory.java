package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.data.room.AgentMemoryDao;
import dev.aurakai.auraframefx.data.room.AppDatabase;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class DatabaseModule_ProvideAgentMemoryDaoFactory implements Factory<AgentMemoryDao> {
  private final Provider<AppDatabase> databaseProvider;

  private DatabaseModule_ProvideAgentMemoryDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public AgentMemoryDao get() {
    return provideAgentMemoryDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideAgentMemoryDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideAgentMemoryDaoFactory(databaseProvider);
  }

  public static AgentMemoryDao provideAgentMemoryDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAgentMemoryDao(database));
  }
}
