package dev.genesis.android.test

import com.android.build.api.dsl.AaptOptions
import com.android.build.api.dsl.AdbOptions
import com.android.build.api.dsl.AndroidSourceSet
import com.android.build.api.dsl.ApkSigningConfig
import com.android.build.api.dsl.BuildFeatures
import com.android.build.api.dsl.CompileOptions
import com.android.build.api.dsl.CompileSdkSpec
import com.android.build.api.dsl.ComposeOptions
import com.android.build.api.dsl.DataBinding
import com.android.build.api.dsl.ExternalNativeBuild
import com.android.build.api.dsl.JacocoOptions
import com.android.build.api.dsl.LibraryAndroidResources
import com.android.build.api.dsl.LibraryBuildFeatures
import com.android.build.api.dsl.LibraryBuildType
import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.dsl.LibraryExtension
import com.android.build.api.dsl.LibraryInstallation
import com.android.build.api.dsl.LibraryProductFlavor
import com.android.build.api.dsl.LibraryPublishing
import com.android.build.api.dsl.Lint
import com.android.build.api.dsl.LintOptions
import com.android.build.api.dsl.Packaging
import com.android.build.api.dsl.Prefab
import com.android.build.api.dsl.PrivacySandbox
import com.android.build.api.dsl.SdkComponents
import com.android.build.api.dsl.Splits
import com.android.build.api.dsl.TestCoverage
import com.android.build.api.dsl.TestFixtures
import com.android.build.api.dsl.TestOptions
import com.android.build.api.dsl.ViewBinding
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectContainer
import java.io.File

class FakeLibraryExtension(
    override val aidlPackagedList: MutableCollection<String>?,
    override val androidResources: LibraryAndroidResources,
    override val aaptOptions: AaptOptions,
    override val adbOptions: AdbOptions,
    override val compileOptions: CompileOptions,
    override val dataBinding: DataBinding,
    override val viewBinding: ViewBinding,
    override val jacoco: JacocoOptions,
    override val testCoverage: TestCoverage,
    override val lint: Lint,
    override val lintOptions: LintOptions,
    override val packagingOptions: Packaging,
    override val packaging: Packaging,
    override val signingConfigs: NamedDomainObjectContainer<out ApkSigningConfig>,
    override val externalNativeBuild: ExternalNativeBuild,
    override val testOptions: TestOptions,
    override val splits: Splits,
    override val composeOptions: ComposeOptions,
    override val sourceSets: NamedDomainObjectContainer<out AndroidSourceSet>,
    override val flavorDimensions: MutableList<String>,
    override var resourcePrefix: String?,
    override var ndkVersion: String,
    override var ndkPath: String?,
    override var buildToolsVersion: String,
    override val sdkComponents: SdkComponents,
    override var compileSdk: Int?,
    override var compileSdkExtension: Int?,
    override var compileSdkPreview: String?,
    override var compileSdkMinor: Int?,
    override var namespace: String?,
    override val experimentalProperties: MutableMap<String, Any>,
    override val buildTypes: NamedDomainObjectContainer<out LibraryBuildType>,
    override val installation: LibraryInstallation,
    override val productFlavors: NamedDomainObjectContainer<out LibraryProductFlavor>,
    override val defaultConfig: LibraryDefaultConfig,
    override val prefab: NamedDomainObjectContainer<Prefab>,
    override val publishing: LibraryPublishing,
    override val privacySandbox: PrivacySandbox,
    override var testBuildType: String,
    override var testNamespace: String?,
    override val testFixtures: TestFixtures
) : LibraryExtension {
    private val features = object : BuildFeatures {
        override var compose: Boolean = false
    }

    override val buildFeatures: BuildFeatures
        get() = features

    @Deprecated("Replaced by ", replaceWith = ReplaceWith("androidResources"))
    override fun aaptOptions(action: AaptOptions.() -> Unit) {
        TODO(buildString {
        append(/* str = */ "Not yet implemented")
    })
    }

    override fun adbOptions(action: AdbOptions.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun compileOptions(action: CompileOptions.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun dataBinding(action: DataBinding.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun viewBinding(action: ViewBinding.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun jacoco(action: JacocoOptions.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun testCoverage(action: TestCoverage.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun lint(action: Lint.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun lintOptions(action: LintOptions.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun packagingOptions(action: Packaging.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun packaging(action: Packaging.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun signingConfigs(action: NamedDomainObjectContainer<out ApkSigningConfig>.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun externalNativeBuild(action: ExternalNativeBuild.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun testOptions(action: TestOptions.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun splits(action: Splits.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun composeOptions(action: ComposeOptions.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun sourceSets(action: NamedDomainObjectContainer<out AndroidSourceSet>.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun buildToolsVersion(buildToolsVersion: String) {
        TODO("Not yet implemented")
    }

    override fun useLibrary(name: String) {
        TODO("Not yet implemented")
    }

    override fun useLibrary(name: String, required: Boolean) {
        TODO("Not yet implemented")
    }

    override fun compileSdk(action: CompileSdkSpec.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun compileSdkAddon(vendor: String, name: String, version: Int) {
        TODO("Not yet implemented")
    }

    override fun compileSdkVersion(apiLevel: Int) {
        TODO("Not yet implemented")
    }

    override fun compileSdkVersion(version: String) {
        TODO("Not yet implemented")
    }

    override fun getDefaultProguardFile(name: String): File {
        TODO("Not yet implemented")
    }

    override fun androidResources(action: LibraryAndroidResources.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun buildFeatures(action: LibraryBuildFeatures.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun buildTypes(action: NamedDomainObjectContainer<LibraryBuildType>.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun NamedDomainObjectContainer<LibraryBuildType>.debug(
        action: LibraryBuildType.() -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun NamedDomainObjectContainer<LibraryBuildType>.release(
        action: LibraryBuildType.() -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun installation(action: LibraryInstallation.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun productFlavors(action: NamedDomainObjectContainer<LibraryProductFlavor>.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun defaultConfig(action: LibraryDefaultConfig.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun publishing(action: LibraryPublishing.() -> Unit) {
        TODO("Not yet implemented")
    }

    override fun privacySandbox(action: PrivacySandbox.() -> Unit) {
        TODO("Not yet implemented")
    }

    /**
     * Applies [action] to this fake extension's internal [BuildFeatures] instance.
     *
     * @param action Action to execute with the internal [BuildFeatures].

     */
    override fun buildFeatures(action: Action<BuildFeatures>) {
        action.execute(features)
    }

    override fun testFixtures(action: TestFixtures.() -> Unit) {
        TODO("Not yet implemented")
    }
}