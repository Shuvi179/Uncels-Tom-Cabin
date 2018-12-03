package com.queue.nosqlDB.dao;

import com.queue.nosqlDB.DocumentStoreHolder;
import com.queue.nosqlDB.entity.RequestEntity;
import com.queue.queue.Request;
import net.ravendb.client.documents.session.IDocumentSession;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.queue.constants.StatusID.RECEIVED;


@Repository
public class RequestDAO implements IRequestDAO{

    private IDocumentSession session;

    @Override
    public Request saveToDatabase(Request request){
        session = DocumentStoreHolder.getSession();
        request.setCreationTime(getCurrentDate());
        RequestEntity entity = new RequestEntity(request);
        entity.setStatusId(RECEIVED);
        entity.setCreationTime(getCurrentDate());

        session.store(entity, entity.getGuid());
        session.saveChanges();
        session.close();
        return entity;
    }

    @Override
    public List<RequestEntity> getAllWithTheSameStatus(byte statusId){
        session = DocumentStoreHolder.getSession();
        List<RequestEntity> list = session.advanced()
                .documentQuery(RequestEntity.class)
                .whereEquals("statusId", statusId).toList();
        session.close();
        return list;
    }

    @Override
    public RequestEntity getEntityByGuid(String guid){
        session = DocumentStoreHolder.getSession();
        RequestEntity entity = session.load(RequestEntity.class, guid);
        session.close();
        return entity;
    }

    @Override
    public void updateStatus(String guid, byte statusId){
        session = DocumentStoreHolder.getSession();
        session.advanced().patch(guid, "statusId", statusId);
        session.saveChanges();
        session.close();
    }

    @Override
    public void updateCompletionTime(String guid){
        session = DocumentStoreHolder.getSession();
        session.advanced().patch(guid, "completionTime", getCurrentDate());
        session.saveChanges();
        session.close();
    }

    @Override
    public void delete(String guid){
        session = DocumentStoreHolder.getSession();
        RequestEntity request = getEntityByGuid(guid);
        session.delete(request);
        session.saveChanges();
        session.close();
    }

    @Override
    public boolean isExists(String guid) {
        session = DocumentStoreHolder.getSession();
        RequestEntity request = getEntityByGuid(guid);
        return request != null;
    }

    private String getCurrentDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
