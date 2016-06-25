package com.example.madhurarora.cleartaxtask.Utils;

import com.example.madhurarora.cleartaxtask.Response.TwitterSearch.TweetStatus;

import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by madhur.arora on 25/06/16.
 */
public class TopThreeWords {

    private Map<String, Integer> countMap;

    public String topThreeWords(ArrayList<TweetStatus> status) {
        int SIZE = 3;
        countMap = new HashMap<>();

        for (TweetStatus tweetStatus : status) {
            String statusText = tweetStatus.getText();
            String user = tweetStatus.getUser().getName();
            if (checktags(statusText)) {
                String[] wordsFromSentence = getWordsFromSentence(statusText);
                for (String word : wordsFromSentence) {
                    if (countMap.containsKey(word)) {
                        countMap.put(word, countMap.get(word) + 1);
                    } else {
                        countMap.put(word, Integer.valueOf(1));
                    }
                }
            }
        }
        Map<String, Integer> sortedMap = new TreeMap<>(new ValueComparator());
        sortedMap.putAll(countMap);

        Set<String> strings = sortedMap.keySet();
        StringBuilder resultBuilder = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        int i = 0;
        while (iterator.hasNext() && i < SIZE) {
            String word = iterator.next();
            resultBuilder.append("\nWord: '" + WordUtils.capitalize(word) + "' , Count: " + countMap.get(word));
            i++;
        }
        //Toast.makeText(ApplicationClass.getAppContext(), "Top count words in search query " + resultBuilder.toString(), Toast.LENGTH_LONG).show();
        return resultBuilder.toString();
    }

    private String[] getWordsFromSentence(String s) {
        String[] words = s.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^\\w]", "").toLowerCase();
        }
        return words;
    }

    class ValueComparator implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            if (countMap.get(s1) >= countMap.get(s2)) {
                return -1;
            } else {
                return 1;
            }
        }
    }

    public static boolean checktags(String status) {
        CharSequence check = "cleartax";
        CharSequence hashTag = "#cleartax";
        CharSequence account = "@cleartax";

        return status.toLowerCase().contains(check) && !status.toLowerCase().contains(hashTag) && !status.toLowerCase().contains(account);
    }
}
