package com.bigdata.designpattern.builder;

import com.bigdata.designpattern.builder.person.Person;
import com.bigdata.designpattern.builder.terrain.ComplexTerrainBuilder;
import com.bigdata.designpattern.builder.terrain.Terrain;
import org.junit.Test;

public class Main {

    @Test
    public void terrainBuild(){
        ComplexTerrainBuilder builder = new ComplexTerrainBuilder();
        Terrain terrain = builder.buildFort(10, 10, 10, 10)
                .buildMine(20, 3, 5, 1)
                .buildWall(200, 300, 40, 50)
                .build();
    }

    public void buildPerson(){
        Person.PersonBuilder builder = new Person.PersonBuilder();
        Person person = builder.basicInfo(10, "Tom", 23)
                .score(98)
                .weight(54)
                .loc("Wall Street", "27th")
                .build();
    }



}
