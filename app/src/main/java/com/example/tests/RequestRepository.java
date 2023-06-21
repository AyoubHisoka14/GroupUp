package com.example.tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class RequestRepository {
    private static RequestRepository instance; // Static instance variable
    private List<Request> requestByUser;
    static Integer counter=0;



    public static RequestRepository getInstance() {
        if (instance == null) {
            instance = new RequestRepository();
        }
        return instance;
    }

    public void save(Request request) {

        if (requestByUser == null) {
            requestByUser = new ArrayList<>();
        }
        requestByUser.add(request);
    }



    public List<Request> getAllRequests() {
        return requestByUser;
    }

    public Request getById(Integer id)
    {
        for(Request request : requestByUser)
        {
            if(request.id.equals(id))
            {
                return request;
            }
        }
        return null;
    }

    public Integer getId()
    {
        counter++;
        return counter;
    }


}
