# Launcher Profile Dumper

The LPD (Launcher Profile Dumper) is a util for minecraft which dumps every account which is listed in the launcher profile json of minecraft. This util can be used to log into one of the accounts that are listed.

## Installation

Download the LPD.jar and add it as a library to your project.

Alternatively: download the ReadAccountFiles.java and create the package "me.zyfer1337" and put the java file into the created package.

## Usage

Now I will provide a small example of how you can use the LPD API.

It is also important that you remove the final modifier of the variable "session" and make it public.

```java
public void logInLauncherAccount (final int account) {
   final String path = System.getenv("APPDATA") + "\.minecraft\launcher_profiles.json";
   Minecraft.getMinecraft().session = new Session(ReadAccountFiles.getReadAccountFiles().getOneInfo("displayName", account, path),
                                      ReadAccountsFiles.getReadAccountFiles().getOneInfo("uuid", account, path).replace("-", ""),
                                      ReadAccountFiles.getReadAccountFiles().getOneInfo("accessToken", account, path), "mojang");
} 
```

## License
MIT License

Copyright (c) 2019 zyfer1337

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
