package com.thrillio.entities;

import com.thrillio.mamagers.BookmarkManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class WebLinkTest {

    @Test
    public void isKidFriendlyEligible() {

        // Test 1: porn in url  -- false
        WebLink webLink = BookmarkManager.getInstance().createWebLink(2000,
                "Taming Tiger, Part 2",
                "http://www.javaworld.com/article/2072759/core-java/taming-porn--part-2.html",
                "http://www.javaworld.com");

        boolean isKidFriendlyEligible =  webLink.isKidFriendlyEligible();

        assertFalse("For porn in url = isKidFriendlyEligible() must return false", isKidFriendlyEligible);


        // Test 2: porn in title -- false
        webLink = BookmarkManager.getInstance().createWebLink(2000,
                "Taming porn, Part 2",
                "http://www.javaworld.com/article/2072759/taming-c--part-2.html",
                "http://www.javaworld.com");

        isKidFriendlyEligible =  webLink.isKidFriendlyEligible();

        assertFalse("For porn in title = isKidFriendlyEligible() must return false", isKidFriendlyEligible);


        // TEst 3: adult in host -- false
        webLink = BookmarkManager.getInstance().createWebLink(2000,
                "Taming, Part 2",
                "http://www.javaworld.com/article/2072759/taming-c--part-2.html",
                "http://www.adult.com");

        isKidFriendlyEligible =  webLink.isKidFriendlyEligible();

        assertFalse("For adult in host = isKidFriendlyEligible() must return false", isKidFriendlyEligible);


        // Test 4: adult in url, but not in host part -- true
        webLink = BookmarkManager.getInstance().createWebLink(2000,
                "Taming, Part 2",
                "http://www.javaworld.com/article/2072759/adult-c--part-2.html",
                "http://www.javaworld.com");

        isKidFriendlyEligible =  webLink.isKidFriendlyEligible();

        assertTrue("For adult in url only = isKidFriendlyEligible() must return true", isKidFriendlyEligible);


        // Test 5: adult in title only -- true
        webLink = BookmarkManager.getInstance().createWebLink(2000,
                "Taming adult, Part 2",
                "http://www.javaworld.com/article/2072759/taming-c--part-2.html",
                "http://www.javaworld.com");

        isKidFriendlyEligible =  webLink.isKidFriendlyEligible();

        assertTrue("For adult in title only = isKidFriendlyEligible() must return true", isKidFriendlyEligible);
    }
}