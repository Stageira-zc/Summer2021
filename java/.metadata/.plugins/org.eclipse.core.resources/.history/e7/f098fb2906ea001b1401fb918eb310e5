package com.aicity.smartparkingapplication.TileComponent;

class TileData {
    long pixelSize;
    long zoom;
    long x;
    long y;
    public TileData(long size, long zoom, long x, long y) {
        this.pixelSize = size;
        this.zoom = zoom;
        this.x = x;
        this.y = y;
    }

    public boolean isEqual(TileData tileData) {
        return ((this.pixelSize == tileData.pixelSize) &&
                (this.zoom == tileData.zoom) &&
                (this.x == tileData.x) &&
                (this.y == tileData.y));
    }
}

public class TileRegion {
    TileData tileData;
    TileBoundingBox boundingBox;
    public String tileCode;

    public TileRegion() {
        this(0, 0, 0, 0, "", new TileBoundingBox());
    }

    public TileRegion(long size, long zoom, long x, long y, String tileCode, TileBoundingBox boundingBox) {
        this.tileData = new TileData(size, zoom, x, y);
        this.boundingBox = boundingBox;
        this.tileCode = tileCode;
    }

    public TileRegion(TileData tileData, TileBoundingBox boundingBox, String tileCode) {
        this.tileData = tileData;
        this.boundingBox = boundingBox;
        this.tileCode = tileCode;
    }

    public boolean isEqual(TileRegion tileRegion) {
        return tileData.isEqual(tileRegion.tileData);
    }
}
