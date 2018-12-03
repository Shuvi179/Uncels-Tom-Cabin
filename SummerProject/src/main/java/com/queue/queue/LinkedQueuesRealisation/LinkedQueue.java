package com.queue.queue.LinkedQueuesRealisation;

import com.queue.constants.PriorityType;
import com.queue.queue.Request;

import java.util.Timer;
import java.util.TimerTask;

public class LinkedQueue {
    private LinkedQueueNode headOfList;
    private static int size = 0;

    public Request getRequest(){
        size--;
        Request requestToReturn = headOfList.getRequest();
        if(headOfList.getNodeNext() == null){
            headOfList = null;
        }
        else {
            headOfList = headOfList.getNodeNext();
        }
        return requestToReturn;
    }

    public boolean isEmpty(){
        return headOfList == null;
    }

    public void setRequest(Request request){
        size++;
        LinkedQueueNode nodeToSet = new LinkedQueueNode();
        nodeToSet.setRequest(request);
        if(headOfList==null){
            headOfList = nodeToSet;
        }
        else {
            if(CompareRequest(request, headOfList.getRequest())){
                nodeToSet.setNodeNext(headOfList);
                headOfList.setNodeLast(nodeToSet);
                headOfList = nodeToSet;
            }
            else {
                LinkedQueueNode nodeToSearchInQueue = headOfList;

                while(nodeToSearchInQueue.getNodeNext() != null && !CompareRequest(request, nodeToSearchInQueue.getRequest())){
                    nodeToSearchInQueue = nodeToSearchInQueue.getNodeNext();
                }

                if(!CompareRequest(request, nodeToSearchInQueue.getRequest())){
                    nodeToSearchInQueue.setNodeNext(nodeToSet);
                    nodeToSet.setNodeLast(nodeToSearchInQueue);
                }
                else {
                    nodeToSet.setNodeLast(nodeToSearchInQueue.getNodeLast());
                    nodeToSearchInQueue.getNodeLast().setNodeNext(nodeToSet);
                    nodeToSearchInQueue.setNodeLast(nodeToSet);
                    nodeToSet.setNodeNext(nodeToSearchInQueue);
                }
            }

        }
    }


    public boolean deleteFromQueue(Request request){
        LinkedQueueNode node = headOfList;
        if(node != null) {

            while((node.getRequest().getPriority() != request.getPriority() && !node.getRequest().getCreationTime().equals(request.getCreationTime())) && node.getNodeNext()!=null ){
                node = node.getNodeNext();
            }

            if(node.getRequest().getPriority() == request.getPriority() && node.getRequest().getCreationTime().equals(request.getCreationTime())){
                if(node.getNodeNext()!=null && node.getNodeLast()!=null) {
                    node.getNodeLast().setNodeNext(node.getNodeNext());
                    node.getNodeNext().setNodeLast(node.getNodeLast());
                }
                else if (node.getNodeNext()==null && node.getNodeLast()!=null){
                    node.getNodeLast().setNodeNext(null);
                }
                else if(node.getNodeNext()!=null && node.getNodeLast()==null){
                    headOfList = node.getNodeNext();
                    headOfList.setNodeLast(null);
                }
                else {
                    headOfList = null;
                }
                return true;
            }

        }
        return false;
    }

    public int getSize(){
        return size;
    }

    private boolean CompareRequest(Request req1 , Request req2){
        if(req1.getPriority()>req2.getPriority()){
            return true;
        }
        else if(req1.getPriority() < req2.getPriority()) {
            return false;
        }
        else {
            if(req1.getCreationTime().compareTo(req2.getCreationTime()) > 0){
                return false;
            }
            else if(req1.getCreationTime().compareTo(req2.getCreationTime()) < 0){
                return true;
            }
        }
        return false;
    }
}
