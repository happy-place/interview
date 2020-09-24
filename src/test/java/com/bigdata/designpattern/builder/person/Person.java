package com.bigdata.designpattern.builder.person;

public class Person {

    int id;
    String name;
    int age;
    double weight;
    int score;
    Location loc;

    public Person() {
    }

    public static class PersonBuilder {
        Person person = new Person();

        public PersonBuilder basicInfo(int id,String name,int age){
            person.id = id;
            person.name = name;
            person.age = age;
            return this;
        }

        public PersonBuilder weight(double weight){
            person.weight = weight;
            return this;
        }

        public PersonBuilder score(int score){
            person.score = score;
            return this;
        }

        public PersonBuilder loc(String street,String roomNo){
            person.loc = new Location(street,roomNo);
            return this;
        }

        public Person build(){
            return person;
        }
    }

}



