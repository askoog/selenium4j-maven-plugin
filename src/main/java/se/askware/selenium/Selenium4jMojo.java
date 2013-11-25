package se.askware.selenium;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;
import org.roussev.selenium4j.Transformer;

/**
 * Generates junit test classes from html files in src/main/selenium using the selenium4j tool
 * 
 */
@Mojo(name = "selenium4j", defaultPhase = LifecyclePhase.GENERATE_TEST_SOURCES)
public class Selenium4jMojo extends AbstractMojo {

	@Component
	private MavenProject project;

	public void execute() throws MojoExecutionException {
		File srcDir = new File(project.getBasedir(), "src/main/selenium");
		File outputDir = new File(project.getBasedir(), "target/test-src");
		getLog().info("Running selenium4j tool.");
		getLog().debug("Source files in " + srcDir.getAbsolutePath() + ". Output in " + outputDir.getAbsolutePath());
		try {
			String[] args = new String[] { "-htmlDir", srcDir.getAbsolutePath(), "-junitDir",
					outputDir.getAbsolutePath(), "-classNameSuffix", "Test" };
			new Transformer().execute(args);
		} catch (Exception e) {
			throw new MojoExecutionException("Failed to generate java sources from html", e);
		}
		getLog().info("Done generating test classes with Selenium4j");
	}
}
