package mindustry.client.navigation;

import arc.math.*;
import arc.math.geom.*;
import mindustry.*;
import mindustry.gen.*;

import static mindustry.Vars.*;

public class PayloadDropoffWaypoint extends Waypoint implements Position {
    public final int tileX, tileY;
    private boolean done = false;

    public PayloadDropoffWaypoint(int tileX, int tileY) {
        this.tileX = tileX;
        this.tileY = tileY;
    }

    @Override
    public float getX() {
        return tileX * tilesize;
    }

    @Override
    public float getY() {
        return tileY * tilesize;
    }

    @Override
    boolean isDone() {
        return done;
    }

    @Override
    void run() {
        if (Vars.player.within(getX(), getY(), 8f)) {
            Call.requestDropPayload(Vars.player, tileX * tilesize, tileY * tilesize);
            done = true;
        } else {
            float direction = player.angleTo(this);
            float x = Mathf.cosDeg(direction) * 2f;
            float y = Mathf.sinDeg(direction) * 2f;
            x = Mathf.clamp(x / 10, -1f, 1f);
            y = Mathf.clamp(y / 10, -1f, 1f);
            control.input.updateMovementCustom(player.unit(), x, y, direction);
        }
    }

    @Override
    void draw() {}
}
