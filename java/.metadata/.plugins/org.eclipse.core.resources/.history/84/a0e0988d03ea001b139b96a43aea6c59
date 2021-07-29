package com.aicity.smartparkingapplication.TileComponent;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

interface Tile {
    public TileRegion getTileRegion(long x, long y);
}

interface Intersect {
    public List<TileRegion> getIntersect(TileCollection input);
}

interface Minus {
    public List<TileRegion> getMinus(List<TileRegion> input);
}

public class TileCollection implements Tile, Intersect, Minus {
    public TileCollectionRange range;
    public List<TileRegion> tiles;
    public TileCollection(TileCollectionRange range, List<TileRegion> tiles) {
        this.range = range;
        this.tiles = tiles;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public boolean isContainsTile(String tileCode) {
        Stream<TileRegion> stream = tiles.stream();
        return stream.anyMatch(tile -> tile.tileCode.equals(tileCode));
    }
    public List<String> getTileCodes(){
        List<String> tileCodes = new ArrayList<>();
        for (TileRegion tileRegion : tiles){
            tileCodes.add(tileRegion.tileCode);
        }
        return tileCodes;
    }
    public TileCollectionChanges getTileCollectionChanges(TileCollection from){
        List<TileRegion> entered = null;
        List<TileRegion> exited = null;
        List<TileRegion> remained = null;

        if (from == null){
            entered = this.tiles;
        }else {
            List<TileRegion> intersectTiles = from.getIntersect(from);
            if (intersectTiles.size() == 0){
                entered = this.tiles;
                exited = from.tiles;
            }else {
                exited = from.getMinus(intersectTiles);
                entered = this.getMinus(intersectTiles);
                remained = intersectTiles;
            }
        }
        return new TileCollectionChanges(entered, exited, remained);
    }


    public boolean isEqual(TileCollection collection) {
        return range.isEqual(collection.range);
    }

    public Tile getTile() {
        return (x, y) -> {
            if (x < range.xStart || x > range.xStart + range.xRange ||
                    y < range.yStart || y > range.yStart + range.yRange) {
                return null;
            } else {
                long column = x - range.xStart;
                long row = y - range.yStart;
                return tiles.get((int)(column + row * range.xRange));
            }
        };
    }

    public Intersect getIntersect() {
        return (new Intersect() {
            @Override
            public List<TileRegion> getIntersect(TileCollection input) {
                Set<TileRegion> left = new HashSet<TileRegion>(tiles);
                left.retainAll(input.tiles);
                return new ArrayList<TileRegion>(left);
            }
        });
    }

    public Minus getMinus() {
        return (new Minus() {
            @Override
            public List<TileRegion> getMinus(List<TileRegion> input) {
                Set<TileRegion> left = new HashSet<TileRegion>(tiles);
                left.removeAll(input);
                return new ArrayList<TileRegion>(left);
            }
        });
    }

    @Override
    public TileRegion getTileRegion(long x, long y) {
        if (x < range.xStart || x > range.xStart + range.xRange ||
                y < range.yStart || y > range.yStart + range.yRange) {
            return null;
        } else {
            long column = x - range.xStart;
            long row = y - range.yStart;
            return tiles.get((int)(column + row * range.xRange));
        }
    }

    @Override
    public List<TileRegion> getIntersect(TileCollection input) {
        Set<TileRegion> left = new HashSet<TileRegion>(tiles);
        left.retainAll(input.tiles);
        return new ArrayList<TileRegion>(left);
    }

    @Override
    public List<TileRegion> getMinus(List<TileRegion> input) {
        Set<TileRegion> left = new HashSet<TileRegion>(tiles);
        left.removeAll(input);
        return new ArrayList<TileRegion>(left);
    }
}
