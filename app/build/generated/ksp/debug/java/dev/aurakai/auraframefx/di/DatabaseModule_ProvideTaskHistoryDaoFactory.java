package dev.aurakai.auraframefx.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.data.room.AppDatabase;
import dev.aurakai.auraframefx.data.room.TaskHistoryDao;
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
public final class DatabaseModule_ProvideTaskHistoryDaoFactory implements Factory<TaskHistoryDao> {
  private final Provider<AppDatabase> databaseProvider;

  private DatabaseModule_ProvideTaskHistoryDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public TaskHistoryDao get() {
    return provideTaskHistoryDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideTaskHistoryDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideTaskHistoryDaoFactory(databaseProvider);
  }

  public static TaskHistoryDao provideTaskHistoryDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideTaskHistoryDao(database));
  }
}
