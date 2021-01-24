package TheSwordswoman.stances;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.stance.CalmParticleEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import TheSwordswoman.BlazMod;
import TheSwordswoman.util.TextureLoader;

public class DawnflyStance extends AbstractStance {

    public static final String STANCE_ID = BlazMod.makeID("Dawnfly");

    public DawnflyStance() {
        this.ID = STANCE_ID;// 21
        this.name = "Dawnfly";
        this.updateDescription();// 23
        this.img = TextureLoader.getTexture("blazmodResources/images/ui/Dawnfly.png");
    }// 24

    public void updateAnimation() {
        if (!Settings.DISABLE_EFFECTS) {// 33
            this.particleTimer -= Gdx.graphics.getDeltaTime();// 35
            if (this.particleTimer < 0.0F) {// 36
                this.particleTimer = 0.04F;// 37
                AbstractDungeon.effectsQueue.add(new CalmParticleEffect());// 38
            }
        }

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();// 43
        if (this.particleTimer2 < 0.0F) {// 44
            this.particleTimer2 = MathUtils.random(0.45F, 0.55F);// 45
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect("Calm"));// 46
        }

    }// 48

    @Override
    public void updateDescription() {
        this.description = "You are in Dawnfly Stance.";
    }
}
