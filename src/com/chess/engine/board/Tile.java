/**
 * Code written by Software YT channel and Alex Prout
 */
package com.chess.engine.board;
import com.chess.engine.pieces.Piece;
import java.util.HashMap;
import java.util.Map;
import com.google.common.collect.ImmutableMap;

public abstract class Tile {

    protected final int tileCoordinate;

    private static final Map<Integer, EmptyTile> EMPTY_TILES_CACHE = createAllPossibleEmptyTiles();
    private static Map<Integer, EmptyTile> createAllPossibleEmptyTiles(){
        final Map<Integer, EmptyTile> emptyTileMap = new HashMap<>();
        for (int i = 0; i < BoardUtils.NUM_TILES;i++){
            emptyTileMap.put(i, new EmptyTile(i));
        }
        return ImmutableMap.copyOf(emptyTileMap); //download the Guava Library
    }
    //when creating a tile, it is either an occupiedTile or an Empty tile: only use this method to create a tile
    public static Tile createTile(final int tileCoordinate, final Piece piece){
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES_CACHE.get(tileCoordinate);
    }
    private Tile(final int tileCoordinate){
        this.tileCoordinate = tileCoordinate;
    }
    public abstract boolean isOccupied();
    public abstract Piece getPiece();
    public static final class EmptyTile extends Tile{

        private EmptyTile(final int coordinate){
            super(coordinate);
        }

        @Override
        public boolean isOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }


    }
    public static final class OccupiedTile extends Tile{
        private final Piece pieceOnTile;
        private OccupiedTile(int tileCoordinate, Piece pieceOnTile){
            super(tileCoordinate);
            this.pieceOnTile = pieceOnTile;
        }
        @Override
        public boolean isOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return this.pieceOnTile;
        }
    }
}
