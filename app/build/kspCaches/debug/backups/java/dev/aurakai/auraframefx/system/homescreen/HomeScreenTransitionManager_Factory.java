package dev.aurakai.auraframefx.system.homescreen;

import com.highcapable.yukihookapi.hook.xposed.prefs.data.YukiHookModulePrefs;
import com.highcapable.yukihookapi.hook.xposed.service.YukiHookServiceManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import dev.aurakai.auraframefx.system.common.ImageResourceManager;
import dev.aurakai.auraframefx.system.overlay.ShapeManager;
import dev.aurakai.auraframefx.system.overlay.SystemOverlayManager;
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
public final class HomeScreenTransitionManager_Factory implements Factory<HomeScreenTransitionManager> {
  private final Provider<SystemOverlayManager> overlayManagerProvider;

  private final Provider<ShapeManager> shapeManagerProvider;

  private final Provider<ImageResourceManager> imageManagerProvider;

  private final Provider<YukiHookModulePrefs> prefsProvider;

  private final Provider<YukiHookServiceManager> overlayServiceProvider;

  private HomeScreenTransitionManager_Factory(Provider<SystemOverlayManager> overlayManagerProvider,
      Provider<ShapeManager> shapeManagerProvider,
      Provider<ImageResourceManager> imageManagerProvider,
      Provider<YukiHookModulePrefs> prefsProvider,
      Provider<YukiHookServiceManager> overlayServiceProvider) {
    this.overlayManagerProvider = overlayManagerProvider;
    this.shapeManagerProvider = shapeManagerProvider;
    this.imageManagerProvider = imageManagerProvider;
    this.prefsProvider = prefsProvider;
    this.overlayServiceProvider = overlayServiceProvider;
  }

  @Override
  public HomeScreenTransitionManager get() {
    return newInstance(overlayManagerProvider.get(), shapeManagerProvider.get(), imageManagerProvider.get(), prefsProvider.get(), overlayServiceProvider.get());
  }

  public static HomeScreenTransitionManager_Factory create(
      Provider<SystemOverlayManager> overlayManagerProvider,
      Provider<ShapeManager> shapeManagerProvider,
      Provider<ImageResourceManager> imageManagerProvider,
      Provider<YukiHookModulePrefs> prefsProvider,
      Provider<YukiHookServiceManager> overlayServiceProvider) {
    return new HomeScreenTransitionManager_Factory(overlayManagerProvider, shapeManagerProvider, imageManagerProvider, prefsProvider, overlayServiceProvider);
  }

  public static HomeScreenTransitionManager newInstance(SystemOverlayManager overlayManager,
      ShapeManager shapeManager, ImageResourceManager imageManager, YukiHookModulePrefs prefs,
      YukiHookServiceManager overlayService) {
    return new HomeScreenTransitionManager(overlayManager, shapeManager, imageManager, prefs, overlayService);
  }
}
