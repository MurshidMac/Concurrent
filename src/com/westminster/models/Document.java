package com.westminster.models;

/**
 * ********************************************************************
 * File:	  Document.java	(Class)
 * Author:	  P. Howells
 * Contents:  6SENG002W CWK
 * This provides an "abstract" document object.
 * It includes the user id, the document's name & its length
 * in pages.
 * ***********************************************************************
 */

public class Document {

    private final String userID;
    private final String documentName;
    private final int numberOfPages;

    Document(String UID, String name, int length) {
        this.userID = UID;
        this.documentName = name;
        this.numberOfPages = length;
    }


    String getUserID() {
        return userID;
    }

    String getDocumentName() {
        return documentName;
    }

    int getNumberOfPages() {
        return numberOfPages;
    }

    public String toString() {
        return "Document[ " +
                "UserID: " + userID + ", " +
                "Name: " + documentName + ", " +
                "Pages: " + numberOfPages +
                "]";
    }

}
