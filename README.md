# Flux SDK [![Maven Central](https://img.shields.io/maven-central/v/io.finarkein.flux/flux-parent.svg?label=Maven%20Central)](https://search.maven.org/search?q=io.finarkein.flux)

Flux SDK for Java.

## Quickstart

Simply add the required dependency in your `pom.xml`.

```xml
<dependencies>
    <dependency>
        <groupId>io.finarkein.flux</groupId>
        <artifactId>flux</artifactId>
        <version>0.2.0</version>
    </dependency>    
    <dependency>
        <groupId>io.finarkein.flux.fiber</groupId>
        <artifactId>hiu-fiber</artifactId>
        <version>0.2.0</version>
    </dependency>
</dependencies>
```

### Install from Github Packages

1. Create a [personal access token](https://github.com/settings/tokens).
2. Configure `~/.m2/settings.xml` with your Github's personal access token:

    ```xml
    <servers>
        <server>
            <id>github</id>
            <username>username</username>
            <password>personal_access_token</password>
        </server>
    </servers>
    ```

3. Lastly, configure `<repositories/>` either in your **.m2/settings.xml** or in project **pom.xml**.

    ```xml
    <repositories>
        <repository>
            <id>github</id> <!-- ensure this matches your settings.xml / server ID -->
            <url>https://maven.pkg.github.com/finarkein/flux-sdk</url>
        </repository>
    </repositories>
    ```
