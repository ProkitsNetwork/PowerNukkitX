package cn.nukkit.inventory;

import cn.nukkit.Player;
import cn.nukkit.block.BlockAnvil;
import cn.nukkit.block.BlockID;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemID;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.network.protocol.types.itemstack.ContainerSlotType;
import com.google.common.collect.BiMap;
import com.google.gson.annotations.Since;
import io.netty.util.internal.StringUtil;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author MagicDroidX (Nukkit Project)
 */
public class AnvilInventory extends ContainerInventory implements CraftTypeInventory {
    public static final int INPUT = 0;
    public static final int MATERIAL = 1;
    public static final int OUTPUT = 2;

    public AnvilInventory(BlockAnvil anvil) {
        super(anvil, InventoryType.ANVIL, 3);
    } //2 INPUT, 1 OUTPUT

    @Override
    public BiMap<Integer, Integer> networkSlotMap() {
        BiMap<Integer, Integer> map = super.networkSlotMap();
        map.put(0, 1);//INPUT
        map.put(1, 2);//MATERIAL
        map.put(2, 3);//OUTPUT
        return map;
    }

    @Override
    public Map<Integer, ContainerSlotType> slotTypeMap() {
        Map<Integer, ContainerSlotType> map = super.slotTypeMap();
        map.put(0, ContainerSlotType.ANVIL_INPUT);
        map.put(1, ContainerSlotType.ANVIL_MATERIAL);
        map.put(2, ContainerSlotType.ANVIL_RESULT);
        return map;
    }

    @Override
    public void onClose(Player who) {
        super.onClose(who);

        Item[] drops = new Item[]{getInputSlot(), getMaterialSlot()};
        drops = who.getInventory().addItem(drops);
        for (Item drop : drops) {
            if (!who.dropItem(drop)) {
                this.getHolder().getLevel().dropItem(this.getHolder().getVector3().add(0.5, 0.5, 0.5), drop);
            }
        }

        clear(INPUT);
        clear(MATERIAL);
    }

    public Item getInputSlot() {
        return this.getItem(INPUT);
    }

    public Item getMaterialSlot() {
        return this.getItem(MATERIAL);
    }

    public Item getOutputSlot() {
        return this.getItem(OUTPUT);
    }

    public boolean setInputSlot(Item item, boolean send) {
        return setItem(INPUT, item, send);
    }

    public boolean setInputSlot(Item item) {
        return setInputSlot(item, true);
    }

    public boolean setMaterialSlot(Item item, boolean send) {
        return setItem(MATERIAL, item, send);
    }

    public boolean setOutputSlot(Item item) {
        return setItem(OUTPUT, item, true);
    }

    private boolean setOutputSlot(Item item, boolean send) {
        return setItem(OUTPUT, item, send);
    }

    @Override
    public void sendContents(Player... players) {
        for (int slot = 0; slot < getSize(); slot++) {
            sendSlot(slot, players);
        }
    }
}
