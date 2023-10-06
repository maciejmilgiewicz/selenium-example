package com.macmil.utilities.file;

import com.macmil.cucumber.cardetails.CsvCarDetails;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * General file manipulation test utilities
 */
public class FileUtilities {

    /**
     * Lists files inside of given directory within resources/files/
     * @param directoryName the directory to search in, e.g. input or output directory
     * @return a collection of java.io.File objects found in the directory
     */
    public static Collection<File> listFilesInDir(String directoryName) {
        File file = new File(Objects.requireNonNull(FileUtilities.class.getResource(String.format("/files/%s", directoryName))).getFile());
        return FileUtils.listFiles(file, TrueFileFilter.INSTANCE, null);
    }

    /**
     * Reads file into String
     * @param file file to read from
     * @return String with file contents
     * @throws IOException exception
     */
    public static String readFile(File file) throws IOException {
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }

    /**
     * Reads file in CSV format with comma separated columns labelled as MAKE_MODEL, VARIANT_REG and YEAR_BODY
     * @param file file to read from
     * @return List of CsvCarDetails objects containing info from the file
     * @throws IOException exception
     */
    public static List<CsvCarDetails> readCarDetailsFromFile(File file) throws IOException {
        Path path = Paths.get(file.getPath());
        List<CsvCarDetails> details;
        try (Reader reader = Files.newBufferedReader(path)) {
            details  = new CsvToBeanBuilder<CsvCarDetails>(reader)
                    .withType(CsvCarDetails.class)
                    .build()
                    .parse();
        }
        return details;
    }
}
