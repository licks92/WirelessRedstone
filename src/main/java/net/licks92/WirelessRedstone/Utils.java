package net.licks92.WirelessRedstone;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String getBukkitVersion() {
        final String packageName = Bukkit.getServer().getClass().getPackage().getName();
        return packageName.substring(packageName.lastIndexOf('.') + 1);
    }

    public static boolean sameLocation(Location loc1, Location loc2){
        return loc1.getBlockX() == loc2.getBlockX() && loc1.getBlockY() == loc2.getBlockY()
                && loc1.getBlockZ() == loc2.getBlockZ() && loc1.getWorld() == loc2.getWorld();
    }

    public static boolean isCompatible(){
        try {
            String[] pieces = getBukkitVersion().substring(1).split("_");

            return Integer.parseInt(pieces[0]) >= 1 && Integer.parseInt(pieces[1]) >= 8;
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
    }

    /*
    * 1.8 - 1.10
    * Torch Facing
    * 0 - Air
    * 1 - East
    * 2 - West
    * 3 - South
    * 4 - North
    * */

    public static int torchFaceToInt(BlockFace face) {
        switch (face) {
            case NORTH:
                return 4;
            case EAST:
                return 1;
            case SOUTH:
                return 3;
            case WEST:
                return 2;
            default:
                return 0;
        }
    }

    public static BlockFace intToBlockFaceTorch(Integer dir) {
        switch (dir) {
            case 4:
                return BlockFace.NORTH;
            case 1:
                return BlockFace.EAST;
            case 3:
                return BlockFace.SOUTH;
            case 2:
                return BlockFace.WEST;
            default:
                return BlockFace.SELF;
        }
    }

    /*
    * 1.8 - 1.10
    * Wall Sign facing
    * 0 - North
    * 3 - South
    * 4 - West
    * 5 - East
    * */

    public static int wallSignFaceToInt(BlockFace face){
        switch (face) {
            case NORTH:
                return 0;
            case EAST:
                return 5;
            case SOUTH:
                return 3;
            case WEST:
                return 4;
            default:
                return 0;
        }
    }

    public static BlockFace intToBlockFaceWallSign(Integer dir) {
        switch (dir) {
            case 0:
                return BlockFace.NORTH;
            case 5:
                return BlockFace.EAST;
            case 3:
                return BlockFace.SOUTH;
            case 4:
                return BlockFace.WEST;
            default:
                return BlockFace.NORTH;
        }
    }

    /*
    * 1.8 - 1.10
    * Sign facing
    * 0 - South
    * 4 - West
    * 8 - North
    * 12 - East
    * */

    public static int signFaceToInt(BlockFace face){
        switch (face) {
            case NORTH:
                return 8;
            case EAST:
                return 12;
            case SOUTH:
                return 0;
            case WEST:
                return 4;
            default:
                return 0;
        }
    }

    public static BlockFace intToBlockFaceSign(Integer dir) {
        switch (dir) {
            case 8:
                return BlockFace.NORTH;
            case 12:
                return BlockFace.EAST;
            case 0:
                return BlockFace.SOUTH;
            case 4:
                return BlockFace.WEST;
            default:
                return BlockFace.NORTH;
        }
    }

    public static boolean isValidWallLocation(Block block) {
        org.bukkit.material.Sign sign = (org.bukkit.material.Sign) block
                .getState().getData();
        BlockFace face = sign.getAttachedFace();
        Block tempBlock = block.getRelative(face);

        return !(tempBlock.getType() == Material.AIR
                || tempBlock.getType() == Material.PISTON_BASE
                || tempBlock.getType() == Material.PISTON_EXTENSION
                || tempBlock.getType() == Material.PISTON_MOVING_PIECE
                || tempBlock.getType() == Material.PISTON_STICKY_BASE
                || tempBlock.getType() == Material.GLOWSTONE
                || tempBlock.getType() == Material.REDSTONE_LAMP_ON
                || tempBlock.getType() == Material.REDSTONE_LAMP_OFF
                || tempBlock.getType() == Material.LEAVES
                || tempBlock.getType() == Material.WOOD_STAIRS
                || tempBlock.getType() == Material.COBBLESTONE_STAIRS
                || tempBlock.getType() == Material.RED_SANDSTONE_STAIRS
                || tempBlock.getType() == Material.SANDSTONE_STAIRS
                || tempBlock.getType() == Material.FENCE
                || tempBlock.getType() == Material.ACACIA_FENCE
                || tempBlock.getType() == Material.DARK_OAK_FENCE
                || tempBlock.getType() == Material.JUNGLE_FENCE
                || tempBlock.getType() == Material.BIRCH_FENCE
                || tempBlock.getType() == Material.WOOD_DOOR
                || tempBlock.getType() == Material.WOODEN_DOOR
                || tempBlock.getType() == Material.IRON_DOOR_BLOCK
                || tempBlock.getType() == Material.IRON_DOOR
                || tempBlock.getType() == Material.GLASS
                || tempBlock.getType() == Material.THIN_GLASS
                || tempBlock.getType() == Material.STAINED_GLASS
                || tempBlock.getType() == Material.STAINED_GLASS_PANE
                || tempBlock.getType() == Material.COBBLE_WALL
                || tempBlock.getType() == Material.ICE
                || tempBlock.getType() == Material.WOOD_STEP
                || tempBlock.getType() == Material.STEP
                || tempBlock.getType() == Material.TNT
                || tempBlock.getType() == Material.SEA_LANTERN);
    }

    public static boolean isValidLocation(Block block) {
        if (block == null)
            return false;

        Block tempBlock = block.getRelative(BlockFace.DOWN);

        return !(tempBlock.getType() == Material.AIR
                || tempBlock.getType() == Material.PISTON_BASE
                || tempBlock.getType() == Material.PISTON_EXTENSION
                || tempBlock.getType() == Material.PISTON_MOVING_PIECE
                || tempBlock.getType() == Material.PISTON_STICKY_BASE
                || tempBlock.getType() == Material.GLOWSTONE
                || tempBlock.getType() == Material.REDSTONE_LAMP_ON
                || tempBlock.getType() == Material.REDSTONE_LAMP_OFF
                || tempBlock.getType() == Material.LEAVES
                || tempBlock.getType() == Material.TNT
                || tempBlock.getType() == Material.SEA_LANTERN);
    }

    public static void signWarning(Block block, int code) {
        Sign sign = (Sign) block.getState();
        switch (code) {
            case 1:
                sign.setLine(2, "Bad block");
                sign.setLine(3, "Behind sign");
                sign.update();
                break;

            default:
                break;
        }
    }

    public static List<BlockFace> getEveryBlockFace(boolean addUpAndDown){
        ArrayList<BlockFace> possibleBlockface = new ArrayList<BlockFace>();
        possibleBlockface.add(BlockFace.NORTH);
        possibleBlockface.add(BlockFace.EAST);
        possibleBlockface.add(BlockFace.SOUTH);
        possibleBlockface.add(BlockFace.WEST);

        if(addUpAndDown) {
            possibleBlockface.add(BlockFace.UP);
            possibleBlockface.add(BlockFace.DOWN);
        }

        return possibleBlockface;
    }

    public static void loadChunks() {
        if (ConfigManager.getConfig().getCancelChunkUnload()) { //TODO: Fix this
//            for (IWirelessPoint point : cache.getAllSigns()) {
//                Location location = point.getLocation();
//                if (location.getWorld() == null)
//                    continue; // world currently not loaded.
//
//                Chunk center = location.getBlock().getChunk();
//                World world = center.getWorld();
//                int range = WirelessRedstone.config.getChunkUnloadRange();
//                for (int dx = -(range); dx <= range; dx++) {
//                    for (int dz = -(range); dz <= range; dz++) {
//                        Chunk chunk = world.getChunkAt(center.getX() + dx,
//                                center.getZ() + dz);
//                        world.loadChunk(chunk);
//                    }
//                }
//            }
        }
    }
}
