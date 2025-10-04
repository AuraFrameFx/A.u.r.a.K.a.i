import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.TaskOutcome
import java.io.File
import kotlin.io.path.createTempDirectory
import kotlin.test.assertContains
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

open class GradleTestkitBase {
    /**
     * Create a temporary Gradle project directory and run the provided setup callback to populate it.
     *
     * The directory is marked for deletion on JVM exit and contains a minimal settings.gradle.kts
     * that sets the root project name to "testkit-root" for deterministic output.
     *
     * @param setup Lambda invoked with the temporary project root where callers should create project files. Defaults to a no-op.
     * @return The temporary project root directory.
     */
    protected fun withTempProject(setup: (root: File) -> Unit = {}): File {
        val dir = createTempDirectory("gradle-testkit-").toFile()
        dir.deleteOnExit()
        // Copy the repository's root build files into the temp dir for realistic execution.
        // Use minimal settings.gradle to make it a valid build.
        File(dir, "settings.gradle.kts").writeText(
            // Keep project name stable for deterministic output
            "rootProject.name = \"testkit-root\"\n"
        )
        // Write a lightweight build that applies the real root build.gradle.kts using 'apply from'
        // Instead of that (which can break with relative paths), we embed a trimmed test driver build that
        // includes the specific snippets to test: tasks and configuration affected by the diff.
        // This avoids referencing the whole repo and keeps tests hermetic.
        // The test-specific build script will be provided by callers via writeBuildFile().
        setup(dir)
        return dir
    }

    /**
     * Writes the provided Kotlin build script content to `build.gradle.kts` in the given project root.
     *
     * Overwrites any existing `build.gradle.kts` file in `root`.
     *
     * @param root Directory to place the build file (expected to be a temporary test project root).
     * @param content The build.gradle.kts source to write.
     */
    protected fun writeBuildFile(root: File, content: String) {
        File(root, "build.gradle.kts").writeText(content)
    }

    /**
     * Execute a Gradle build in the given project directory using Gradle TestKit and return the resulting BuildResult.
     *
     * @param root The project directory to run the build in.
     * @param args Gradle CLI arguments (tasks and flags) to pass to the runner.
     * @param expectSuccess When `true`, the build is expected to succeed; when `false`, the build is expected to fail.
     * @return The Gradle TestKit `BuildResult` produced by the run.
     *
     * Requires `gradle-test-kit` on the test classpath for plugin classpath resolution. */
    protected fun run(root: File, vararg args: String, expectSuccess: Boolean = true): BuildResult {
        val runner = GradleRunner.create()
            .withProjectDir(root)
            .withPluginClasspath() // requires gradle-test-kit on test classpath
            .withArguments(*args, "--stacktrace")
        return if (expectSuccess) runner.build() else runner.buildAndFail()
    }

    /**
     * Asserts that the specified task exists in the given BuildResult and completed successfully.
     *
     * The check requires the task to be present in the result and its outcome to be either
     * [TaskOutcome.SUCCESS] or [TaskOutcome.UP_TO_DATE]. Fails the test with a clear message
     * if the task is missing or did not succeed.
     *
     * @param result The Gradle BuildResult to inspect.
     * @param taskPath The fully qualified task path (e.g. ":module:taskName") to verify.
     */
    protected fun assertTaskSuccess(result: BuildResult, taskPath: String) {
        val t = result.task(taskPath)
        assertNotNull(t, "Expected task $taskPath to be present in the result.")
        assertTrue(
            t.outcome == TaskOutcome.SUCCESS || t.outcome == TaskOutcome.UP_TO_DATE,
            "Task $taskPath should succeed."
        )
    }

    /**
     * Verifies the build's console output contains each of the specified snippets.
     *
     * Each snippet is checked independently; the assertion fails if any snippet is not found.
     *
     * @param result The Gradle BuildResult whose console output will be searched.
     * @param snippets One or more string fragments that must be present in the output.
     */
    protected fun assertOutputContains(result: BuildResult, vararg snippets: String) {
        val out = result.output
        for (s in snippets) {
            assertContains(out, s, "Expected console output to contain: $s")
        }
    }
}