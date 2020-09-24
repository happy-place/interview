package com.bigdata.designpattern.builder.terrain;

public interface TerrainBuilder {

    TerrainBuilder buildWall(int x, int y, int w, int h);

    TerrainBuilder buildFort(int x, int y, int w, int h);

    TerrainBuilder buildMine(int x, int y, int w, int h);

    Terrain build();
}
