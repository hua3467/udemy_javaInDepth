package com.thrillio;

import com.thrillio.constants.KidFriendlyStatus;
import com.thrillio.constants.UserType;
import com.thrillio.controllers.BookmarkController;
import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;
import com.thrillio.partner.Shareable;

import java.sql.SQLOutput;

public class View {

    public static void browse(User user, Bookmark[][] bookmarks){

        System.out.println("\n" + user.getEmail() + " is browsing items ...");

        int bookmarkCount = 0;

        for ( Bookmark[] bookmarkList : bookmarks ){
            for( Bookmark bookmark : bookmarkList ){

                // Bookmarking
                if(bookmarkCount < DataStore.USER_BOOKMARK_LIMIT){

                    boolean isBookmarked = getBookmarkDecision(bookmark);
                    if(isBookmarked){
                        bookmarkCount++;

                        BookmarkController.getInstance().saveUserBookmark(user, bookmark);

                        System.out.println("New Item Bookmarked - - " + bookmark);

                    }
                }

                if(user.getUserType().equals(UserType.EDITOR)
                        || user.getUserType().equals(UserType.CHIEF_EDITOR)){

                    // mark as kid-friendly
                    if(bookmark.isKidFriendlyEligible()
                            && bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)){

                        String kidFriendlyStatus = getKidFriendlyStatusDeccision(bookmark);

                        if(!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)){
                            BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
//                            bookmark.setKidFriendlyStatus(kidFriendlyStatus);
//                            System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", " + bookmark);
                        }

                    }

                    // Sharing!!
                    if(bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED)
                            && bookmark instanceof Shareable){
                        boolean isShared = getShareDecision();
                        if(isShared){
                            BookmarkController.getInstance().share(user, bookmark);
                        }
                    }
                }
            }
        }

    }

    private static String getKidFriendlyStatusDeccision(Bookmark bookmark) {
        return Math.random() < 0.4 ? KidFriendlyStatus.APPROVED
                : (Math.random() >= 0.4 && Math.random() < 0.8) ? KidFriendlyStatus.REJECTED
                : KidFriendlyStatus.UNKNOWN;
    }

    private static boolean getBookmarkDecision(Bookmark bookmark) {
        return Math.random() < 0.5;
    }

    // TODO: Below methods simulate user input. After IO, we take input via console.
    private static boolean getShareDecision(){
        return Math.random() < 0.5;
    }

//    public static void bookmark(User user, Bookmark[][] bookmarks){
//        System.out.println("\n" + user.getEmail() + " is bookmarking");
//        for(int i = 0; i < DataStore.USER_BOOKMARK_LIMIT; i++ ){
//
//            int typeOffset =(int) (Math.random() * DataStore.BOOKMARK_TYPES_COUNT);
//            int bookmarkOffset = (int)(Math.random() * DataStore.BOOKMARK_COUNT_PER_TYPE);
//
//            Bookmark bookmark = bookmarks[typeOffset][bookmarkOffset];
//
//            BookmarkController.getInstance().saveUserBookmark(user, bookmark);
//
//            System.out.println(bookmark);
//        }
//
//    }
}
