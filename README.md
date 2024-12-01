## osys-cli

A simple command-line interface (CLI) tool for retrieving general information about the operating system, 
memory, networking, and users. It is built with Java 17, the Micronaut Framework, and Picocli, and 
compiled into a native executable file using GraalVM-CE.


- SDKMAN https://sdkman.io/
- Micronaut Launch https://micronaut.io/launch
- Picocli Documentation https://picocli.info/quick-guide.html
- GraalVM native-image documentation https://www.graalvm.org/22.0/reference-manual/native-image/
- GraalVM native-image build-configuration https://www.graalvm.org/latest/reference-manual/native-image/overview/BuildConfiguration/
---

## Install

```
sdk list java

JDK : sdk install java [--version]

GraalVM CE : sdk install java [--version]

```

## Build & Run the JAR file

```
./gradlew clean build

./gradlew --no-daemon assemble

java -jar build/libs/osys-cli-*-all.jar -h
Usage: osys-cli [-hvV] [COMMAND]
...
-h, --help      Show this help message and exit.
-v, --verbose   ...
-V, --version   Print version information and exit.
Commands:
info  Get operating system information.
```

```
java -jar build/libs/osys-cli-*-all.jar info -mem

Total Swap Space Size         : 8.0 GB
Committed Virtual Memory Size : 5.1 GB
Total Memory Size             : 7.6 GB
Total Free Swap Space Size    : 7.6 GB
Cpu Load                      : 0.0
Used Memory Size              : 7.1 GB
Used Swap Space Size          : 426.5 MB
Free Memory Size              : 481.8 MB
```

## Compile the native binary file

```
$ sdk use java 17.0.9-graalce 

Using java version 17.0.9-graalce in this shell.

$ native-image -jar build/libs/osys-cli-*-all.jar

========================================================================================================================
GraalVM Native Image: Generating 'osys-cli' (executable)...
========================================================================================================================

$ ./osys-cli info -mem

Total Swap Space Size         : 8.0 GB
Committed Virtual Memory Size : 5.1 GB
Total Memory Size             : 7.6 GB
Total Free Swap Space Size    : 7.0 GB
Cpu Load                      : 0.0
Used Memory Size              : 5.1 GB
Used Swap Space Size          : 1.0 GB
Free Memory Size              : 2.5 GB

```

