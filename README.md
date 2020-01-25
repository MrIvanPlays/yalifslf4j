![license](https://img.shields.io/github/license/MrIvanPlays/yalifslf4j.svg?style=for-the-badge)
![issues](https://img.shields.io/github/issues/MrIvanPlays/yalifslf4j.svg?style=for-the-badge)
[![support](https://img.shields.io/discord/493674712334073878.svg?colorB=Blue&logo=discord&label=Support&style=for-the-badge)](https://mrivanplays.com/discord)
![version](https://img.shields.io/maven-metadata/v?color=blue&label=LATEST%20VERSION&metadataUrl=https%3A%2F%2Frepo.mrivanplays.com%2Frepository%2Fivan%2Fcom%2Fmrivanplays%2Fyalifslf4j-base%2Fmaven-metadata.xml&style=for-the-badge)
# yalifslf4j

Yet another logger implementation for slf4j

## Installation
Using maven:

```xml
<repositories>
    <repository>
        <id>ivan</id>
        <url>https://repo.mrivanplays.com/repository/ivan/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.mrivanplays</groupId>
        <artifactId>yalifslf4j-base</artifactId>
        <version>VERSION</version> <!-- Replace VERSION with latest version -->
        <scope>compile</scope>  
    </dependency>
    <!-- You should also add a config reader for yalifslf4j --> 
    <!-- Recommended one is jackson, but whatever you choose you might want to use it further in your application -->
    <!-- <dependency> -->
        <!-- <groupId>com.mrivanplays</groupId> -->
        <!-- <artifactId>yalifslf4j-configprovider-jackson</artifactId> -->
        <!-- <version>VERSION</version> If you're gonna install this dependency, replace VERSION with latest version -->
        <!-- <scope>compile</scope> -->
    <!-- </dependency> -->
</dependencies>
```

Using gradle:

```gradle
repositories {
    maven {
        url 'https://repo.mrivanplays.com/repository/ivan'
    }
}

dependencies {
    implementation group: 'com.mrivanplays', name: 'yalifslf4j-base', version: 'VERSION' // Replace VERSION with latest version
    // you should also add a config reader for yalifslf4j
    // recommended one is jackson, but whatever you choose you might want to use it further in your application 
    // implementation group: 'com.mrivanplays', name: 'yalifslf4j-configprovider-jackson', version: 'VERSION' // If you're gonna install this replace version with latest
}
```

## Configuration
Configuration example can be found [here](https://github.com/MrIvanPlays/api.mrivanplays.com/blob/master/src/main/resources/yalif.json)

Format placeholders: `%loggerName` - logger name ; `%level` - level name ; `%msg` - message ; `%threadName` - current thread name

Not required things in the configuration: 

`logFileDirectory` - log files won't be generated if this isn't present ; 
`timeDateFormat` - if you have a time date format into your logging format, then it won't be replaced if this isn't present ; 
`fileLogFormat` - if you want a custom logging format for your files, this is the variable to go with ;
`disabledLevels` - all logging levels are being allowed if this isn't present


## Usage

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyClass {
  private static Logger logger = LoggerFactory.getLogger("MyLogger");

  public static void main(String[] args) {
    logger.info("Hello, world!");
  }
}
```