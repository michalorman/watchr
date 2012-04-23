package com.binarysanctuary.watchr;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * Watch for changes in specified set of directories. It is a high level abstraction
 * over JDK 7 {@link java.nio.file.WatchService}.
 * <p/>
 * The <tt>Watchr</tt> setups thread that is responsible for watching for changes in
 * specified directories and invokes callback whenever change is noticed. <tt>Watchr</tt>
 * watches for CREATE, MODIFY or DELETE events in all provided directories and subdirectories.
 * Each directory created inside watched directory is also registered for watch.
 */
public class Watchr {
    public static WatchrThread watch(OnChangeCallback callback, String... dirs) throws IOException {
        return watch(callback, Paths.get(dirs[0], Arrays.copyOfRange(dirs, 1, dirs.length)));
    }

    public static WatchrThread watch(OnChangeCallback callback, Path... dirs) throws IOException {
        WatchrThread thread = new WatchrThread(callback, dirs);
        thread.start();
        return thread;
    }
}
