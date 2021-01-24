package TheSwordswoman.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import com.megacrit.cardcrawl.vfx.stance.WrathParticleEffect;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class FreeflutterStance extends AbstractStance {

    public static final String STANCE_ID = BlazMod.makeID("Freeflutter");

    public FreeflutterStance() {
        this.ID = STANCE_ID;// 21
        this.name = "Freeflutter";
        this.updateDescription();// 23
        this.img = TextureLoader.getTexture("blazmodResources/images/ui/Freeflutter.png");
    }// 24

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {// 46
            this.particleTimer -= Gdx.graphics.getDeltaTime();// 48
            if (this.particleTimer < 0.0F) {// 49
                this.particleTimer = 0.05F;// 50
                AbstractDungeon.effectsQueue.add(new WrathParticleEffect());// 51
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();// 56
        if (this.particleTimer2 < 0.0F) {// 57
            this.particleTimer2 = MathUtils.random(0.3F, 0.4F);// 58
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Wrath"));// 59
        }

    }// 61

    @Override
    public void updateDescription() {
        this.description = "You are in Freeflutter Stance.";
    }
}
