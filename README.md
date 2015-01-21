# Watchr

*Watchr* is high level abstraction over JDK 7's
 [WatchService](http://docs.oracle.com/javase/7/docs/api/java/nio/file/WatchService.html). It
 registers `WatchService` in separate thread and watch for changes in
 all given directories, its subdirectories and each new directory
 created within watched directories. Change notifications are
 asynchronously handled via `OnChangeCallback`.

## Usage

Simplest usage of *Watchr*:

```java
Watchr.watch(new OnChangeCallback() {
    @Override
    public void onChange(Path dir, List<WatchEvent<?>> events) {
        // handle change event...
    }
}, "/dir1", "/dir2");
```

The `watch()` method takes callback that will be invoked whenever
change is noticed, and directories to watch.

`watch()` method returns `WatchrThread` which can be used to request
for interruption and join:

```java
WatchrThread thread = Watchr.watch(...)
//...
thread.interrupt();
thread.join();
```

Note that `interrupt()` won't immediately interrupt the `WatchrThread`
so you should join the thread and wait until it ends.

# License

The MIT License (MIT)

Copyright (c) 2015 Michał Orman

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
