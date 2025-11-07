# Processing 2 IntelliJ

Project template for developing Processing sketches in IntelliJ IDEA using Gradle.

## Description

This project provides a setup to develop Processing applications in IntelliJ IDEA instead of the Processing IDE. It uses Gradle for build management and includes example sketches.

## Features

- IntelliJ IDEA project structure
- Gradle build system
- Processing library integration
- Example sketches included

## Project Structure

```
processing2intellij/
├── app/
│   └── src/
│       └── main/
│           └── java/
│               └── processing/
│                   ├── App.java
│                   └── sketchs/
│                       ├── DrawSquare.java
│                       ├── HotPoint.java
│                       ├── RecordSound.java
│                       └── VinculoSentido.java
├── build.gradle
└── settings.gradle
```

## Setup

1. Clone this repository
2. Open in IntelliJ IDEA
3. Import Gradle project
4. Add Processing library to project dependencies

## Building

```bash
./gradlew build
```

## Running

Run sketches from IntelliJ IDEA or use Gradle:

```bash
./gradlew run
```

## Example Sketches

- **DrawSquare**: Basic drawing example
- **HotPoint**: Interactive point detection
- **RecordSound**: Audio recording functionality
- **VinculoSentido**: Interactive installation sketch

## Author

Fernando Ortega Gorrita (@fernandoog)

## License

See LICENSE file for details.
