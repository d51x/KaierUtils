package ru.d51x.kaierutils.dialog;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class FileSelector {
    public static final String TAG = "FileSelector";

    private String fileMask;
    private String path;
    public FileSelector(String path, String fileMask) {
        this.fileMask = fileMask;
        this.path = path;
    }

    public List<File> getFiles(String path, String fileType) {
        File findDir = new File(path);
        FilenameFilter filter = (file, s) -> s.toLowerCase().endsWith("." + fileType);
        File[] list = findDir.listFiles(filter);
        return Arrays.asList(list);
    }
}
