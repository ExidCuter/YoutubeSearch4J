# YoutubeSearch4J

A basic Youtube search library that doesn't require a google api key to work.

The library uses [DuckDuckGo Video Search](https://duckduckgo.com/), so the accuracy is slightly worse than when using the Youtube API.
## Installation

Step 1. Add the JitPack repository to your build file.

Add it in your root build.gradle at the end of repositories:
```
repositories {
    ...
    maven { url 'https://jitpack.io' }
}
```

Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.ExidCuter:YoutubeSearch4J:master-SNAPSHOT'
}
```

## Usage

It's quite simple just call the `search` function:

```java
List<Video> videos = YoutubeSearch.search("query");
```
