[![](https://jitpack.io/v/PowerNukkitX/PowerNukkitX.svg)](https://jitpack.io/#PowerNukkitX/PowerNukkitX)![Coverage](.github/badges/jacoco.svg)
### Version 2.0.0 of PowerNukkitX, work in progress...

### Start PowerNukkitX:
#### Windows
`click start.bat`
#### Linux
```shell
chmod +x start.sh
./start.sh
```

### Build Guide:
1. gradle/tasks/alpha build/build - Build all products, including source jar,jar,shaded jar,javadoc
2. gradle/tasks/alpha build/buildSkipChores - Build partial products, including jar and shaded jar, skip test
3. gradle/tasks/alpha build/buildFast - Only build `jar`, skip test
4. gradle/tasks/alpha build/clean - clean all build products


### Concise Guide to start PNX from sources for Newbies
```shell
#make sure you have a proper JDK 17 or above environment 
java -version

# preview changes
./gradlew buildFast

# copy dependencies .....
./gradlew buildSkipChores

cd build

# window users see start.bat
# for linux or macOS users
bash start.sh
```
### Code coverage too low? 
Feel free to submit more tests for PNX!
