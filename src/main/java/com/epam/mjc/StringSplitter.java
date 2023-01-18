package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> result = new ArrayList<>();

        for (String s : delimiters) {
            StringTokenizer st = new StringTokenizer(source, s);
            source = "";
            while (st.hasMoreTokens()) {
                source =source + st.nextToken() + " ";
            }
        }

        StringTokenizer st2 = new StringTokenizer(source, " ");
        while (st2.hasMoreTokens()) {
            result.add(st2.nextToken());
        }
        return result;
    }
}
