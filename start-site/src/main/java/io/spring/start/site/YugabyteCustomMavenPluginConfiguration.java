/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.spring.start.site;

import io.spring.initializr.generator.buildsystem.Dependency;
import io.spring.initializr.generator.buildsystem.maven.MavenBuild;
import io.spring.initializr.generator.project.ProjectGenerationConfiguration;
import io.spring.initializr.generator.spring.build.BuildCustomizer;
import io.spring.initializr.generator.version.VersionReference;

import org.springframework.context.annotation.Bean;

@ProjectGenerationConfiguration
public class YugabyteCustomMavenPluginConfiguration {

	@Bean
	BuildCustomizer<MavenBuild> customPluginConfigurer() {
		return new BuildCustomizer<MavenBuild>() {
			@Override
			public void customize(MavenBuild mavenBuild) {
				mavenBuild.properties().property("hibernate.version", "5.4.2.Final");

				// If no YB option is selected add it automatically.
				if (!mavenBuild.dependencies().has("spring-data-yugabytedb")) {
					Dependency springDataYugabyteDependency = Dependency
							.withCoordinates("com.yugabyte", "spring-data-yugabytedb")
							.version(VersionReference.ofValue("2.1.10-yb-1")).build();
					mavenBuild.dependencies().add("spring-data-yugabytedb", springDataYugabyteDependency);
				}
			}
		};
	}

}
