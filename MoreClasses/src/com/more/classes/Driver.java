package com.more.classes;

public class Driver
{
    public static void main(String[] args)
    {
        Subject subject1 = new Subject("TPB10AB", "Technical Programming 1");
//        Subject subject2 = new Subject("TPB10AB", "Technical Programming 1");
//
//        if (subject1.equals(subject2))
//        {
//            System.out.println("They are the same!");
//        }
//
//        System.out.println(subject1);

        Student student1 = new Student("Chuck Norris", 1232435325);

        ExamPaper paper = new ExamPaper(student1, subject1, 100);

        //System.out.println(paper);

        System.out.println("Subject code: " + paper.getSubject().getSubjectCode());
        System.out.println("Student number: " + paper.getStudent().getStudentNumber());
    }
}
