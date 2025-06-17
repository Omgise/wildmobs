package com.wildmobsmod.entity.ai;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIBabyPanic extends EntityAIBase {

    private EntityAgeable creature;
    private double speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public EntityAIBabyPanic(EntityAgeable creature, double speed) {
        this.creature = creature;
        this.speed = speed;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (this.creature.getGrowingAge() >= 0 || (this.creature.getAITarget() == null && !this.creature.isBurning()))
            return false;
        Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.creature, 5, 4);
        if (vec3 == null) return false;
        this.randPosX = vec3.xCoord;
        this.randPosY = vec3.yCoord;
        this.randPosZ = vec3.zCoord;
        return true;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        return !this.creature.getNavigator()
            .noPath();
    }
}
