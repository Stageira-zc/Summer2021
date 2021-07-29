package com.aicity.smartparkingapplication.TileComponent;

import com.mapbox.mapboxsdk.geometry.LatLng;

import java.util.Locale;

import static com.aicity.smartparkingapplication.TileComponent.TileUtil.getAvailableTileX;
import static com.aicity.smartparkingapplication.TileComponent.TileUtil.convert;
import static com.aicity.smartparkingapplication.TileComponent.TileUtil.getTileCode;


class PixelBoundingBox {
    public Point northEast;
    public Point northWest;
    public Point southEast;
    public Point southWest;
}

class TileBoundingBox {
    public LatLng northEast;
    public LatLng northWest;
    public LatLng southEast;
    public LatLng southWest;

    public TileBoundingBox() {

    }

    public TileBoundingBox(LatLng northEast,
                           LatLng northWest,
                           LatLng southEast,
                           LatLng southWest) {
        this.northEast = northEast;
        this.northWest = northWest;
        this.southEast = southEast;
        this.southWest = southWest;
    }
}

public class MercatorProjector {
    static final long tileSize = 256;
    static final double PI = 3.1415926;
    static final double initialResolution = 2 * PI * 6378137 / tileSize;
    static double originShift = 2 * PI * 6378137 / 2.0;


    public static Point coordinateToMeters(LatLng coordinate) {
        double x = coordinate.getLongitude() * originShift / 180.0;
        double y = Math.log(Math.tan((90 + coordinate.getLatitude()) * PI / 360.0)) /(PI / 180.0);
        y = y * originShift / 180.0;
        return new Point(x, y);
    }

    public static LatLng metersToCoordinate(Point meters) {
        double longitude = (meters.x /originShift) * 180.0;
        double latitude = (meters.y /originShift) * 180;
        latitude = 180 / PI * (2 * Math.atan(Math.exp(latitude * PI / 180)) - PI /2.0);
        LatLng coordinate = new LatLng(latitude, longitude);
        return coordinate;
    }

    public static Point metersToPixels(Point meters, long zoom) {
        double resolution = resolutionInZoom(zoom);
        double x = (meters.x + originShift) / resolution;
        double y = (meters.y + originShift) / resolution;
        return new Point(x, y);
    }

    public static Point pixelToMeters(Point pixel, long zoom) {
        double resolution = resolutionInZoom(zoom);
        double x = pixel.x * resolution - originShift;
        double y = pixel.y * resolution - originShift;
        return new Point(x, y);
    }

    public static Point pixelToTileXY(Point pixel) {
        long x = (int) Math.ceil(pixel.x / tileSize) - 1;
        long y = (int) Math.ceil(pixel.y / tileSize) - 1;
        return new Point(x, y);
    }

    public static double resolutionInZoom(long zoom) {
        return (2 * PI * 6378137) / (tileSize * Math.pow(2, zoom));
    }

    public static PixelBoundingBox getPixelBoundingBox(long x, long y) {
        PixelBoundingBox box = new PixelBoundingBox();
        box.northWest = new Point(x * tileSize, y * tileSize);
        box.northEast = new Point((x + 1) * tileSize, y * tileSize);
        box.southWest = new Point(x * tileSize, (y + 1) * tileSize);
        box.southEast = new Point((x + 1) * tileSize, (y + 1)* tileSize);
        return box;
    }

    public static TileBoundingBox getTileBoundingBox(long zoom, PixelBoundingBox mercator) {
       TileBoundingBox box = new TileBoundingBox();
        box.northWest = metersToCoordinate(pixelToMeters(mercator.northWest, zoom));
        box.northEast = metersToCoordinate(pixelToMeters(mercator.northEast, zoom));
        box.southWest = metersToCoordinate(pixelToMeters(mercator.southWest, zoom));
        box.southEast = metersToCoordinate(pixelToMeters(mercator.southEast, zoom));
        return box;
    }

    public static TileBoundingBox getTileBoundingBox(long zoom, long x, long y) {
        PixelBoundingBox pixelBox = getPixelBoundingBox(x, y);
        return getTileBoundingBox(zoom, pixelBox);
    }

    public static Point getTileXY(long zoom, LatLng coordinate) {
        Point meters = coordinateToMeters(coordinate);
        Point pixel = metersToPixels(meters, zoom);
        Point tileXY = pixelToTileXY(pixel);
        tileXY.y = convert((long)tileXY.y, zoom);
        return tileXY;
    }

    public static TileRegion getTileRegion(long zoom, LatLng coordinate) {
        Point tileXY = getTileXY(zoom, coordinate);
        return getTileRegion(zoom, (long)tileXY.x, (long)tileXY.y);
    }

    public static TileRegion getTileRegion(long zoom, long x, long y) {
        x = getAvailableTileX(zoom, x);
        long yGoogle = y;
        long yTMS = convert(yGoogle, zoom);
        TileBoundingBox box = getTileBoundingBox(zoom, x, yTMS);
        String tileCode = getTileCode(zoom, x, yGoogle);
        return new TileRegion(tileSize, zoom, x, yGoogle, tileCode, box);
    }
}
