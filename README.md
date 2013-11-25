selenium4j-maven-plugin
=======================

Maven plugin for invoking the selenium4j tool during the code generation phase

Source files are read from src/main/selenium and java files are generated in target/test-src.

Note that the target/test-src must be added to the maven-build-helper-plugin for classes to be compiled.


Below is an example of maven build configuration:

	<build>
		<plugins>
			<plugin>
				<groupId>se.askware.selenium</groupId>
				<artifactId>selenium4j-maven-plugin</artifactId>
				<executions>
					<execution>
						<goals>
							<goal>selenium4j</goal>
						</goals>
					</execution>
				</executions>
			</plugin>
			<plugin>
				<groupId>org.codehaus.mojo</groupId>
				<artifactId>build-helper-maven-plugin</artifactId>
				<executions>
					<execution>
						<id>add-test-source</id>
						<phase>generate-test-sources</phase>
						<goals>
							<goal>add-test-source</goal>
						</goals>
						<configuration>
							<sources>
								<source>
									${project.build.directory}/test-src
								</source>
							</sources>
						</configuration>
					</execution>
				</executions>
			</plugin>
		</plugins>
	</build>

	
You will also need a dependency to the selenium4j tool

	<dependencies>
		<dependency>
			<groupId>org.roussev</groupId>
			<artifactId>selenium4j</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	</dependencies>
