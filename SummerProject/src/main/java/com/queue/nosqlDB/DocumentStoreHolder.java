package com.queue.nosqlDB;

import net.ravendb.client.documents.DocumentStore;
import net.ravendb.client.documents.IDocumentStore;
import net.ravendb.client.documents.session.IDocumentSession;

public class DocumentStoreHolder {

    private static IDocumentStore store;

    static {
        store = new DocumentStore("http://localhost:8000", "requests");
        store.initialize();
    }

    public static IDocumentStore getStore() {
        return store;
    }


    public static IDocumentSession getSession() {
        return store.openSession();
    }
}
