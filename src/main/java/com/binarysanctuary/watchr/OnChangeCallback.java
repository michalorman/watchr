package com.binarysanctuary.watchr;

import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.util.List;

/**
 * Callback invoked whenever change in certain directory is noticed.
 */
public interface OnChangeCallback {

    void onChange(Path dir, List<WatchEvent<?>> events);

}
