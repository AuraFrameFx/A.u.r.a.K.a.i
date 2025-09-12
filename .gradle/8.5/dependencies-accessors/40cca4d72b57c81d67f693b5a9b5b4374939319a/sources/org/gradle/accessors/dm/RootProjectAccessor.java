package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class RootProjectAccessor extends TypeSafeProjectDependencyFactory {


    @Inject
    public RootProjectAccessor(DefaultProjectDependencyFactory factory, ProjectFinder finder) {
        super(factory, finder);
    }

    /**
     * Creates a project dependency on the project at path ":"
     */
    public AOSPReGenesisProjectDependency getAOSPReGenesis() { return new AOSPReGenesisProjectDependency(getFactory(), create(":")); }

    /**
     * Creates a project dependency on the project at path ":app"
     */
    public AppProjectDependency getApp() { return new AppProjectDependency(getFactory(), create(":app")); }

    /**
     * Creates a project dependency on the project at path ":benchmark"
     */
    public BenchmarkProjectDependency getBenchmark() { return new BenchmarkProjectDependency(getFactory(), create(":benchmark")); }

    /**
     * Creates a project dependency on the project at path ":collab-canvas"
     */
    public CollabCanvasProjectDependency getCollabCanvas() { return new CollabCanvasProjectDependency(getFactory(), create(":collab-canvas")); }

    /**
     * Creates a project dependency on the project at path ":colorblendr"
     */
    public ColorblendrProjectDependency getColorblendr() { return new ColorblendrProjectDependency(getFactory(), create(":colorblendr")); }

    /**
     * Creates a project dependency on the project at path ":core-module"
     */
    public CoreModuleProjectDependency getCoreModule() { return new CoreModuleProjectDependency(getFactory(), create(":core-module")); }

    /**
     * Creates a project dependency on the project at path ":datavein-oracle-native"
     */
    public DataveinOracleNativeProjectDependency getDataveinOracleNative() { return new DataveinOracleNativeProjectDependency(getFactory(), create(":datavein-oracle-native")); }

    /**
     * Creates a project dependency on the project at path ":feature-module"
     */
    public FeatureModuleProjectDependency getFeatureModule() { return new FeatureModuleProjectDependency(getFactory(), create(":feature-module")); }

    /**
     * Creates a project dependency on the project at path ":module-a"
     */
    public ModuleAProjectDependency getModuleA() { return new ModuleAProjectDependency(getFactory(), create(":module-a")); }

    /**
     * Creates a project dependency on the project at path ":module-b"
     */
    public ModuleBProjectDependency getModuleB() { return new ModuleBProjectDependency(getFactory(), create(":module-b")); }

    /**
     * Creates a project dependency on the project at path ":module-c"
     */
    public ModuleCProjectDependency getModuleC() { return new ModuleCProjectDependency(getFactory(), create(":module-c")); }

    /**
     * Creates a project dependency on the project at path ":module-d"
     */
    public ModuleDProjectDependency getModuleD() { return new ModuleDProjectDependency(getFactory(), create(":module-d")); }

    /**
     * Creates a project dependency on the project at path ":module-e"
     */
    public ModuleEProjectDependency getModuleE() { return new ModuleEProjectDependency(getFactory(), create(":module-e")); }

    /**
     * Creates a project dependency on the project at path ":module-f"
     */
    public ModuleFProjectDependency getModuleF() { return new ModuleFProjectDependency(getFactory(), create(":module-f")); }

    /**
     * Creates a project dependency on the project at path ":oracle-drive-integration"
     */
    public OracleDriveIntegrationProjectDependency getOracleDriveIntegration() { return new OracleDriveIntegrationProjectDependency(getFactory(), create(":oracle-drive-integration")); }

    /**
     * Creates a project dependency on the project at path ":romtools"
     */
    public RomtoolsProjectDependency getRomtools() { return new RomtoolsProjectDependency(getFactory(), create(":romtools")); }

    /**
     * Creates a project dependency on the project at path ":sandbox-ui"
     */
    public SandboxUiProjectDependency getSandboxUi() { return new SandboxUiProjectDependency(getFactory(), create(":sandbox-ui")); }

    /**
     * Creates a project dependency on the project at path ":screenshot-tests"
     */
    public ScreenshotTestsProjectDependency getScreenshotTests() { return new ScreenshotTestsProjectDependency(getFactory(), create(":screenshot-tests")); }

    /**
     * Creates a project dependency on the project at path ":secure-comm"
     */
    public SecureCommProjectDependency getSecureComm() { return new SecureCommProjectDependency(getFactory(), create(":secure-comm")); }

}
