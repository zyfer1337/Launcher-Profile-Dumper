package me.zyfer1337;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author zyfer1337
 * @apiNote This util is used to get information out of the launcher profile.
 * Created on 06/25/2019
 */
public class ReadAccountFiles {

    /**
     * Introducing an instance for the util to reduce the CPU and memory usage
     */
    private final static ReadAccountFiles READ_ACCOUNT_FILES = new ReadAccountFiles();

    /**
     * Introducing a method which returns one info out of the dumped launcher profile of a specified account
     *
     * @param info            The information you want to get out of the launcher profile
     * @param numberOfAccount The account number (e.g. first account) you want the
     *                        information from
     * @param path            The path to the launcher profile
     */
    public String getOneInfo(final String info, final int numberOfAccount, final String path) {
        final String dump = readAuthenticationDatabase(path);
        final String[] getCount = dump.split("/");

        if (numberOfAccount > Integer.decode(getCount[0]))
            return "ERROR: Given number was too high";

        if (numberOfAccount <= 0)
            return "ERROR: Given number was too low";

        if (!(info.equalsIgnoreCase("displayName") || info.equalsIgnoreCase("accessToken")
                || info.equalsIgnoreCase("userid") || info.equalsIgnoreCase("uuid")
                || info.equalsIgnoreCase("username"))) {
            return "ERROR: Failed to get info";
        }

        final String[] getINFO = dump.split("\n");
        String line = "";
        for (int i = (numberOfAccount - 1) * 5 + 1; i <= numberOfAccount * 5; i++) {
            if (i <= numberOfAccount * 5) {
                if (getINFO[i].contains(info)) {
                    String[] getOneINFO = getINFO[i].split(":");
                    line = getOneINFO[1];
                }
            }
        }
        return line;
    }

    /**
     * Introducing a method which gets every information of one account in the dumped launcher profile
     *
     * @param numberOfAccount The account number (e.g. first account is equal to 1) you want the
     *                        information from
     * @param path            The path to the launcher profile
     */
    public String getAllInfo(final int numberOfAccount, final String path) {
        final String dump = readAuthenticationDatabase(path);
        final String[] getCount = dump.split("/");

        if (numberOfAccount > Integer.decode(getCount[0]))
            return "ERROR: Given number was too high";

        if (numberOfAccount <= 0)
            return "ERROR: Given number was too low";

        final String[] getINFO = dump.split("\n");
        String line = "";
        for (int i = (numberOfAccount - 1) * 5 + 1; i <= numberOfAccount * 5; i++) {
            if (i <= numberOfAccount * 5)
                line += getINFO[i] + "\n";
        }
        return line;
    }

    /**
     * Introducing a method which returns the sum of every account in the dumped launcher profile
     *
     * @param path The path to the launcher profile
     */
    public int getSumOfAccounts(final String path) {
        final String dump = readAuthenticationDatabase(path);
        final String[] getCount = dump.split("/");

        return Integer.decode(getCount[0]);
    }

    /**
     * Introducing a method which reads the authentication database of the dumped launcher profile
     *
     * @param path The path to the launcher profile
     */
    private String readAuthenticationDatabase(final String path) {
        String output = "";
        int count = 0;
        try {
            final BufferedReader br = new BufferedReader(new FileReader(new File(path)));

            String line;
            boolean read;
            while ((line = br.readLine()) != null) {
                read = line.contains("displayName") || line.contains("accessToken") || line.contains("userid") || line.contains("uuid") || line.contains("username");
                if (read) {
                    count++;
                    output += line + "\n";
                }
            }
            count = count / 5;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count + "/\n" + replace(output);
    }

    /**
     * Introducing a method which replaces all the useless information out of a string in the dumped launcher profile
     *
     * @param string The string you want to replace
     */
    private String replace(final String string) {
        return string.replace("      ", "").replace(" ", "").replace("\"", "").replace(",", "");
    }

    /**
     * Introducing the getter for the instance of the util
     */
    public static ReadAccountFiles getReadAccountFiles() {
        return READ_ACCOUNT_FILES;
    }
}
