package com.bigdata.designpattern.builder.terrain;

public class ComplexTerrainBuilder implements TerrainBuilder{

    Terrain terrain = new Terrain();

    @Override
    public TerrainBuilder buildWall(int x, int y, int w, int h) {
        terrain.wall = new Wall(x,y,w,h);
        return this;
    }

    @Override
    public TerrainBuilder buildFort(int x, int y, int w, int h) {
        terrain.fort = new Fort(x,y,w,h);
        return this;
    }

    @Override
    public TerrainBuilder buildMine(int x, int y, int w, int h) {
        terrain.mine = new Mine(x,y,w,h);
        return this;
    }

    @Override
    public Terrain build() {
        return terrain;
    }
}
