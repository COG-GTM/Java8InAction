# Migration Notes: Java 9 → Java 11

## Java Version Change

- **From**: Java 9 (source/target 1.9)
- **To**: Java 11 (using `<release>11</release>`)
- The `<release>` flag replaces the legacy `<source>`/`<target>` configuration and provides stronger cross-compilation guarantees.

## Plugin Upgrades

| Plugin | Old Version | New Version | Notes |
|--------|-------------|-------------|-------|
| maven-compiler-plugin | 3.1 | 3.11.0 | Switched from source/target to release flag |
| maven-surefire-plugin | (none) | 3.2.5 | Added for test execution |
| maven-failsafe-plugin | (none) | 3.2.5 | Added for integration test support |
| maven-enforcer-plugin | (none) | 3.5.0 | Added to enforce Java 11+ at build time |
| maven-shade-plugin | (unchanged) | (unchanged) | Retained for JMH benchmark packaging |

## Dependency Upgrades

| Dependency | Old Version | New Version | Notes |
|------------|-------------|-------------|-------|
| JUnit | 4.11 | 4.13.2 | Latest JUnit 4.x; added `<scope>test</scope>` |
| JMH (jmh-core) | 1.17.4 | 1.37 | Java 11 compatible |
| JMH (jmh-generator-annprocess) | 1.17.4 | 1.37 | Java 11 compatible |

## Code Fixes Required

Minimal changes were needed:

- **Moved `ReadPositiveIntParam.java`** from `src/main/java` to `src/test/java` — this file contained JUnit `@Test` annotations but was incorrectly placed in the main source tree. With JUnit correctly scoped to `test`, it must reside under `src/test/java`.
- No Nashorn JavaScript engine references found
- No internal `sun.*` or `com.sun.*` API usage
- No Java EE modules (removed in Java 11) were used
- Deprecation warning in `Util.java` (deprecated API usage) — informational only, no breaking change

## New Test Coverage

Added integration tests covering core chapters:

| Test File | Chapter | Coverage |
|-----------|---------|----------|
| `FilteringApplesTest.java` | Chapter 1 | Lambda expressions, method references, Predicate-based filtering |
| `StreamBasicsTest.java` | Chapter 4 | Stream creation, filtering, sorting, Java 7 vs Java 8 comparison |
| `StreamProcessingTest.java` | Chapter 5 | Trader/Transaction queries, reduce, distinct, anyMatch |
| `OptionalTest.java` | Chapter 10 | Optional creation, map, flatMap, orElse, filter, stream() |
| `CompletableFutureTest.java` | Chapter 11 | supplyAsync, thenApply, thenCombine, thenCompose, exceptionally |
| `DateTimeTest.java` | Chapter 12 | LocalDate, LocalTime, LocalDateTime, Duration, TemporalAdjusters, DateTimeFormatter |

## CI Workflow Added

- GitHub Actions workflow (`.github/workflows/ci.yml`)
- Triggers on push/PR to master/main branches
- Uses Temurin JDK 11 with Maven caching
- Runs `mvn -B clean compile` and `mvn -B test`
