# eryk-android-common

## Description
This my own library with common android stuff that I often use in  new projects.

# Initialization and requirements
- Androis Studio min version is 3.1
- ktlint
```bash
curl -sSLO https://github.com/shyiko/ktlint/releases/download/0.15.0/ktlint &&
chmod a+x ktlint
sudo mv ktlint /usr/local/bin/
ktlint --apply-to-idea --android
``` 
- fabric
```groovy
buildscript {
    repositories {
        mavenCentral()
        maven { url 'https://maven.fabric.io/public' }
    }
    dependencies {
        classpath 'io.fabric.tools:gradle:1.25.4'
    }
}
```
- Follow DI pattern (injector)

## Demo debug

```bash
./gradlew assembleDebug diawiPublishDebug
```

## Building for Play Store

```bash
./gradlew assembleRelease diawiPublishRelease
```
### Przydatne skrypty
```bash
find . -mindepth 1 -name '.kt' -printf '%h %f\n' | sort -t ' ' -k 2,2 | uniq -f 1 --all-repeated=separate | tr ' ' '/'
```
```bash
git log --all --full-history -- */xxxx.java
```
```bash
./gradlew app:dependencies | grep -i -B 10 animated-vector-drawable
```