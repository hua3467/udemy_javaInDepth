package com.thrillio;


import com.thrillio.entities.Bookmark;
import com.thrillio.entities.User;
import com.thrillio.mamagers.BookmarkManager;
import com.thrillio.mamagers.UserManager;

public class Launch {

    private static User[] users;
    private static Bookmark[][] bookmarks;

    private static void loadData(){
        System.out.println("1. Loading data ...");
        DataStore.loadData();

        users = UserManager.getInstance().getUsers();
        bookmarks = BookmarkManager.getInstance().getBookmarks();

//        System.out.println("Print data ...");
//        printUserData();
//        printBookmarkData();

    }

    private static void printUserData(){
        for( User user : users){
            System.out.println(user);
        }
    }

    private static void printBookmarkData(){
        for( Bookmark[] bookmarklist : bookmarks){
            for( Bookmark bookmark : bookmarklist){
                System.out.println(bookmark);
            }
        }
    }

    private static void start(){
//        System.out.println("2. Bookmarking ...");
        for(User user : users){
            View.browse(user, bookmarks);
        }
    }

    public static void main(String[] args){
        loadData();
        start();
    }

}
