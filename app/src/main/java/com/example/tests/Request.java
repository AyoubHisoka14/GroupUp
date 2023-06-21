package com.example.tests;

public class Request {

    User user;
    Integer id;
    String course;
    String modul;
    String professor;
    String block;

    Request(Integer eId, User eUser, String eCourse, String eModul, String eProfessor, String eBlock)
    {
        id=eId;
        user=eUser;
        course=eCourse;
        modul=eModul;
        professor=eProfessor;
        block=eBlock;
    }
}
