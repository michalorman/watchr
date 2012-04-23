# Watchr

*Watchr* is high level abstraction over JDK 7's
 [WatchService](http://docs.oracle.com/javase/7/docs/api/java/nio/file/WatchService.html). It
 registers `WatchService` in separate thread and watch for changes in
 all given directories, its subdirectories and each new directory
 created within watched directories. Change notifications are
 asynchronously handled via `OnChangeCallback`.

## Usage

Simplest usage of *Watchr*:

    Watchr.watch(new OnChangeCallback() {
        @Override
        public void onChange(Path dir, List<WatchEvent<?>> events) {
            // handle change event...
        }
    }, "/dir1", "/dir2");

The `watch()` method takes callback that will be invoked whenever
change is noticed, and directories to watch.

`watch()` method returns `WatchrThread` which can be used to request
for interruption and join:

    WatchrThread thread = Watchr.watch(...)
    ...
    thread.interrupt();
    thread.join();

Note that `interrupt()` won't immediately interrupt the `WatchrThread`
so you should join the thread and wait until it ends.
